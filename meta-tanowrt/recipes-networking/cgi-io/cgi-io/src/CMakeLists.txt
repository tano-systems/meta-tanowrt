cmake_minimum_required(VERSION 2.6)

PROJECT(cgi-io C)

OPTION(ENABLE_UPLOAD_CHKSUM_OPTIONS "Enable uploaded file checksum calculation options" OFF)
OPTION(ENABLE_DIRECT_WRITE_MODE "Enable direct write mode (without temporary file)" OFF)
OPTION(ENABLE_APPEND_MODE "Enable append write mode (works only in combination with direct mode)" OFF)

INCLUDE(CheckFunctionExists)

FIND_PATH(ubus_include_dir libubus.h)
FIND_LIBRARY(ubox NAMES ubox)
FIND_LIBRARY(ubus NAMES ubus)
INCLUDE_DIRECTORIES(${ubus_include_dir})

ADD_DEFINITIONS(-Os -Wall -Werror -Wextra --std=gnu99 -g3)
ADD_DEFINITIONS(-Wno-unused-parameter -Wmissing-declarations)

IF(ENABLE_UPLOAD_CHKSUM_OPTIONS)
  ADD_DEFINITIONS(-DENABLE_UPLOAD_CHKSUM_OPTIONS)
ENDIF()

IF(ENABLE_DIRECT_WRITE_MODE)
  ADD_DEFINITIONS(-DENABLE_DIRECT_WRITE_MODE)
ENDIF()

IF(ENABLE_APPEND_MODE)
  ADD_DEFINITIONS(-DENABLE_APPEND_MODE)
ENDIF()

SET(CMAKE_SHARED_LIBRARY_LINK_C_FLAGS "")

IF(APPLE)
  INCLUDE_DIRECTORIES(/opt/local/include)
  LINK_DIRECTORIES(/opt/local/lib)
ENDIF()

ADD_EXECUTABLE(cgi-io main.c multipart_parser.c)
TARGET_LINK_LIBRARIES(cgi-io ${ubox} ${ubus})

INSTALL(TARGETS cgi-io RUNTIME DESTINATION sbin)
