cmake_minimum_required(VERSION 3.17)
project(xfaas_function_cpp20)

set(CMAKE_CXX_STANDARD 20)


add_executable(
        main.cpp
        function.cpp function.h xfaas-cpp-httplib/httplib.h xserver.h xserver.cpp)

include_directories(xfaas_function_cpp20 xfaas-cpp-httplib)