cmake_minimum_required(VERSION 3.17)
project(xfaas_function_cpp20)

set(CMAKE_CXX_STANDARD 20)

set(Sources main.cpp function/function.cpp server/xserver.cpp)
set(Headers function/function.h xfaas-cpp-httplib/httplib.h server/xserver.h info/info.h info/info.cpp)
add_executable(xfaas_function_cpp20 ${Sources} ${Headers})

include_directories(xfaas_function_cpp20 xfaas-cpp-httplib)