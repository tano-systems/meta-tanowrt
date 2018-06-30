# From meta-arago-distro layer

# sourceipk.bbclass enables the creation of an ipk file that contains the
# sources used during the build.  The sources contained in the ipk are the
# patched sources before configuration has been done.
#
# This class is used to provide an easy method to ship the corresponding
# sources for a package to end users so that they can install them on their
# host or target systems.
#
# This package uses the following variables to control its operations:
#   - CREATE_SRCIPK         = When set to 1 this variable indicates that 
#                             a source ipk should be generated for the package.
#   - SRCIPK_INSTALL_DIR    = This variable indicates the directory to install
#                             the sources into.
#   - SRCIPK_PACKAGE_ARCH   = This variable allows specific recipies to
#                             specify an architecture for the sourcetree
#                             package, defaults to the same as binary pkg
#   - SRCIPK_INC_EXTRAFILES = When set to 1 this variable indicates that
#                             the source ipk should contain extra files
#                             such as the README file and recipe.
#
# The default installation directory for the sources is:
#   /usr/src/${PN}-src
#
# By setting the SRCIPK_INSTALL_DIR this default can be changed to any
# location desired.  When combined with the opkg -o option this allows for the
# system building to specify a relative package install location to the
# install root given to opkg.  Each source ipk can have a different directory.
# 
# Creation of the source ipk can be controlled per package by setting
# CREATE_SRCIPK = "1" in the package recipe or by setting
# CREATE_SRCIPK_pn-<package name> = "1" in your local.conf
#
#TODO: 
# Need to figure out how to use opkg-build in this class.
# I tried adding it as a dependency for the do_create_srcipk
# task using:
#   do_create_srcipk[depends] += "opkg-utils-native:do_populate_sysroot"
# But then there is a circular dependency between sourcipk.bbclass and
# opkg-utils-native.  Until I can figure out how to resolve this
# circular dependency I am extracting the needed pieces from opkg-build
# into this class and building the source ipk myself.


# Default is to not create the source ipk
CREATE_SRCIPK ?= "0"

# Default installation prefix
SRCIPK_INSTALL_DIR ?= "/usr/src/${PN}-src"

# Specify the directory of the sources
SRCIPK_SRC_DIR ?= "${S}"

# Default PACKAGE_ARCH for sources is "all"
SRCIPK_PACKAGE_ARCH ?= "${PACKAGE_ARCH}"

# Default section matches the recipe section
SRCIPK_SECTION ?= "${SECTION}"

# Default SRCIPK_INCLUDE_EXTRAFILES is to include the extra files
SRCIPK_INCLUDE_EXTRAFILES ?= "1"

SRCIPK_PRESERVE_GIT ?= "false"

# Git commit message that explains the purpose of the custom git branch
SRCIPK_CUSTOM_GIT_MESSAGE ?= ""

# File that will store the same information as the commit message but is also
# used to add something to insure a commit can be committed.
SRCIPK_SDK_README ?= "TISDK-README"

# Name used when creating the custom branch
SRCIPK_CUSTOM_GIT_BRANCH ?= ""

# Create a shallow clone of the git repository to reduce the size of
# the sourceipk
SRCIPK_SHALLOW_CLONE ?= "false"

# By default limit the history to 1 commit since the user can always
# use git pull --unshallow to fetch the rest of history.  The depth
# level of 1 is set to keep from tracking through all merges and
# pulling excess history
SRCIPK_SHALLOW_DEPTH ?= "1"

# This function will return the fetch URL for a git repository passed as
# the first parameter.
get_remote() {
    git_repo="$1"

    if [ "$git_repo" == "" ]
    then
        echo "git_repo not passed to get_remote"
        exit 1
    fi

    cd $git_repo

    # Get the remote repository fetch URL
    remote=`git remote -v | grep "(fetch)" | cut -d ' ' -f 1   | cut -c 7- | tr -d ' '`

    # Since the echo'ed value of this statment is the returned value redirect
    # the output of this command to /dev/null
    cd - > /dev/null

    # echo back the remote repository URL as the output of this function
    echo $remote

    return 0
}

# Some git repositories are very large and we do not want to ship the
# full history.  Instead we want to limit history to reduce the size while
# still keeping the git repository in place.  The full history can be
# fetched using git pull --unshallow or just git pull
# NOTE: This function depends on a git version >= 1.7.10.  It will work
#       with older versions but the size will be larger because rather
#       than just a single branch the limited history will be a depth of
#       1 for all branches and tags.
limit_git_history() {

    # Temporary directory to make shallow clones in
    gitshallowclone="${WORKDIR}/temp-git-shallow-clone"

    # Change directory to the git repository to be safe
    cd $tmp_dir/${SRCIPK_INSTALL_DIR}

    # Create a temporary directory to hold the shallow git clone
    mkdir -p $gitshallowclone

    remote=`get_remote $PWD`

    # Clone a shallow version of the workdir repo so that the preserved git
    # points to the commit used in the build.
    git clone --depth ${SRCIPK_SHALLOW_DEPTH} --branch ${BRANCH} file://$PWD/.git $gitshallowclone

    # remove original kernel clone since we will replace it with the shallow
    # clone
    rm -rf $tmp_dir/${SRCIPK_INSTALL_DIR}/.git

    # replace the original kernel git data with the shallow clone git data
    mv $gitshallowclone/.git $tmp_dir/${SRCIPK_INSTALL_DIR}/
    rm -rf $gitshallowclone

    # Remove the local remote
    git remote rm origin

    # This is forceful, but unfortuantely, removing the origin remote does not
    # remove all origin refs, and that causes problems.
    rm -rf ./.git/refs/remotes/origin

    # Point remote back to upstream
    git remote add origin $remote

    cd -
}

adjust_git() {
    orig_dir="$PWD"

    cd $tmp_dir/${SRCIPK_INSTALL_DIR}

    if [ -d ".git" ]
    then
        if [ "${SRCIPK_PRESERVE_GIT}" = "true" ]
        then
            # If SRCIPK_SHALLOW_CLONE is true then make a shallow copy of the
            # git repository and then fix up the git URLs
            if [ "${SRCIPK_SHALLOW_CLONE}" == "true" ]
            then
                limit_git_history
            fi

            # Repackage the repository so its a proper clone of the original
            # (remote) git repository
            git repack -a -d

            if [ "${SRCIPK_CUSTOM_GIT_BRANCH}" != "" -a "${SRCIPK_CUSTOM_GIT_MESSAGE}" != "" ]
            then
                # Create local git config settings to create commit
                git config user.email "<>"
                git config user.name "Texas Instruments SDK Builder"

                # For recipes like the kernel which may end up running
                # sourceipk more than once.
                branch_exist=`git branch | grep ${SRCIPK_CUSTOM_GIT_BRANCH}` || echo ""
                if [ "$branch_exist" != "" -a -f ${SRCIPK_SDK_README} ]
                then
                    git add -A
                    git commit --amend
                else
                    echo -e ${SRCIPK_CUSTOM_GIT_MESSAGE} > ${SRCIPK_SDK_README}
                    git add -A
                    git commit -F ${SRCIPK_SDK_README}
                    git checkout -b "${SRCIPK_CUSTOM_GIT_BRANCH}"
                fi

                # Delete local git config settings previously set
                git config --unset user.email
                git config --unset user.name

            fi

            rm -f .git/objects/info/alternates

        else
            rm -rf .git
        fi

    fi

    cd $orig_dir
}

# Create a README file that describes the contents of the source ipk
sourceipk_create_readme() {
    readme="$1/README.${PN}-src"
    touch $readme
    echo 'This package contains the patched sources for ${PN} that' >> $readme
    echo 'were used to generate the ${PN} binary ipk package(s).' >> $readme
    echo 'This package does not build or generate the binaries' >> $readme
    echo 'directly.  To build the binaries you must either' >> $readme
    echo 'configure and build the sources yourself or use:' >> $readme
    echo '    bitbake ${PN}' >> $readme
    echo '' >> $readme
    echo 'NOTE: The patches applied to the sources can be found in' >> $readme
    echo "      the \"patches\" directory" >> $readme
}

SRCIPK_DEPLOYSRC_DIR = "${WORKDIR}/deploy-src"

SSTATETASKS += "do_create_srcipk"
do_create_srcipk[sstate-inputdirs] = "${SRCIPK_DEPLOYSRC_DIR}"
do_create_srcipk[sstate-outputdirs] = "${DEPLOY_DIR_IPK}"

python do_create_srcipk_setscene () {
    sstate_setscene(d)
}
addtask do_create_srcipk_setscene

do_create_srcipk[dirs] = "${SRCIPK_DEPLOYSRC_DIR}"
do_create_srcipk[cleandirs] = "${SRCIPK_DEPLOYSRC_DIR}"
do_create_srcipk[umask] = "022"

# Create the source ipk file.  The ipk is manually created here instead
# of using the normal ipk system because some recipes will over write
# the PACKAGES variable.  Thus if this class added a -src package
# to the list of packages to be created that package would be lost.
# See the linux kernel recipe for an example of this issue.
sourceipk_do_create_srcipk() {
    if [ ${CREATE_SRCIPK} != "0" ]
    then

        tmp_dir="${WORKDIR}/sourceipk-tmp"
        srcipk_dir="${WORKDIR}/sourceipk-data"
        mkdir -p $tmp_dir/CONTROL
        mkdir -p $srcipk_dir
        control_file="$tmp_dir/CONTROL/control"

        # Write the control file
        echo "Package: ${PN}-src" > $control_file
        echo "Version: ${PV}-${PR}" >> $control_file
        echo "Description: Patched sources for ${PN}" >> $control_file
        echo "Section: ${SRCIPK_SECTION}" >> $control_file
        echo "Priority: Optional" >> $control_file
        echo "Maintainer: ${MAINTAINER}" >> $control_file
        echo "License: ${LICENSE}" >> $control_file
        echo "Architecture: ${SRCIPK_PACKAGE_ARCH}" >> $control_file
        srcuri="${SRC_URI}"
        if [ "$srcuri" = "" ]
        then
            srcuri="OpenEmbedded"
        fi
        echo "Source: $srcuri" >> $control_file
        #Write the control tarball
        tar -C $tmp_dir/CONTROL --owner=0 --group=0 -czf $srcipk_dir/control.tar.gz .

        # Get rid of temporary control file
        rm -rf $tmp_dir/CONTROL

        # Copy sources for packaging
        mkdir -p $tmp_dir/${SRCIPK_INSTALL_DIR}
        if [ -e ${SRCIPK_SRC_DIR} ]; then
            if [ "${SRCIPK_SRC_DIR}" = "${WORKDIR}" ]; then
                excludes='--exclude ./temp --exclude ./sourceipk-tmp --exclude ./sourceipk-data'
            fi
            tar -C ${SRCIPK_SRC_DIR} -cO $excludes . | tar -C $tmp_dir/${SRCIPK_INSTALL_DIR} -xpf -
        fi

        # Fix up patches/ directory to contain actual patches instead of symlinks
        if [ -e $tmp_dir/${SRCIPK_INSTALL_DIR}/patches ]
        then
            mv $tmp_dir/${SRCIPK_INSTALL_DIR}/patches $tmp_dir/${SRCIPK_INSTALL_DIR}/patches-links
            cp -rL $tmp_dir/${SRCIPK_INSTALL_DIR}/patches-links $tmp_dir/${SRCIPK_INSTALL_DIR}/patches
            rm -rf $tmp_dir/${SRCIPK_INSTALL_DIR}/patches-links
        fi

        if [ ${SRCIPK_INCLUDE_EXTRAFILES} != "0" ]
        then
            sourceipk_create_readme $tmp_dir/${SRCIPK_INSTALL_DIR}/
            cp ${FILE} $tmp_dir/${SRCIPK_INSTALL_DIR}/
        fi

        # Adjust the git repository if there is one.  Do this adjustment
        # here so we don't have to modify the original sources.
        adjust_git

        #Write the data tarball
        tar -C $tmp_dir --owner=0 --group=0 -czf $srcipk_dir/data.tar.gz .

        # Create the debian-binary file
        echo "2.0" > $srcipk_dir/debian-binary

        #Write the ipk file
        mkdir -p ${SRCIPK_DEPLOYSRC_DIR}/${SRCIPK_PACKAGE_ARCH}
        pkg_file=${SRCIPK_DEPLOYSRC_DIR}/${SRCIPK_PACKAGE_ARCH}/${PN}-src_${PV}-${PR}_${SRCIPK_PACKAGE_ARCH}.ipk
        rm -f $pkg_file
        ( cd $srcipk_dir && ar -crf $pkg_file ./debian-binary ./control.tar.gz ./data.tar.gz )

        # Remove the temporary directory
        rm -rf $tmp_dir
    fi
}

EXPORT_FUNCTIONS do_create_srcipk

do_create_srcipk[deptask] = "do_patch"

addtask create_srcipk after do_patch before do_configure

python () {
    if d.getVar('do_compileconfigs', False):
        deps = d.getVarFlag('do_compileconfigs', 'deps') or []
        deps.append('do_create_srcipk')
        d.setVarFlag('do_compileconfigs', 'deps', deps)
}

#Add source packages to list of packages OE knows about
PACKAGES_DYNAMIC += "${PN}-src"
