#
# TanoWrt Git revision
#
# Copyright (c) 2018-2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
# gitrev_run and mark_recipe_dependencies taken from
# gitver.bbclass meta-openembedded/meta-oe
# (git://git.openembedded.org/meta-openembedded)
#

def gitrev_run(cmd, path):
    (output, error) = bb.process.run(cmd, cwd=path)
    return output.rstrip()

def get_git_revision(p):
    import subprocess

    try:
        return subprocess.Popen("git rev-parse HEAD 2>/dev/null ", cwd=p, shell=True, stdout=subprocess.PIPE, universal_newlines=True).communicate()[0].rstrip()
    except OSError:
        return None

def get_git_branch(p):
    import subprocess

    try:
        rev = gitrev_run("git rev-parse HEAD", p)
        ref = gitrev_run("git name-rev --name-only %s" % rev, p)
        return ref
    except OSError:
        return None

def mark_recipe_dependencies(path, d):
    from bb.parse import mark_dependency

    gitdir = os.path.join(path, ".git")

    # Force the recipe to be reparsed so the version gets bumped
    # if the active branch is switched, or if the branch changes.
    mark_dependency(d, os.path.join(gitdir, "HEAD"))

    # Force a reparse if anything in the index changes.
    mark_dependency(d, os.path.join(gitdir, "index"))

    try:
        ref = gitrev_run("git symbolic-ref -q HEAD", gitdir)
    except bb.process.CmdError:
        pass
    else:
        if ref:
            mark_dependency(d, os.path.join(gitdir, ref))

    # Catch new tags.
    tagdir = os.path.join(gitdir, "refs", "tags")
    if os.path.exists(tagdir):
        mark_dependency(d, tagdir)


TANOWRT_SCM_BRANCH = ""
TANOWRT_SCM_REVISION = ""
TANOWRT_SCM_REVISION_SHORT = ""

python () {
    tano_base = d.getVar('TANOWRT_BASE')
    repo_base = os.path.normpath(os.path.join(tano_base, ".."))
    mark_recipe_dependencies(repo_base, d)
    rev = get_git_revision(repo_base)
    d.setVar('TANOWRT_SCM_REVISION', rev)
    d.setVar('TANOWRT_SCM_REVISION_SHORT', rev[:12])
    d.setVar('TANOWRT_SCM_BRANCH', get_git_branch(repo_base))
}
