#include <iostream>
#include "xfaas-cpp-httplib/httplib.h"

int main() {
    httplib::Server::Handler handler = [](const httplib::Request &req, httplib::Response &res) {
        //
        // Place your function code here
        //
        res.set_content("Hello from XFaaS C++ 20", "text/json");
    };

    httplib::runFunction(handler);
    return 0;
}

