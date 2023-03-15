#include <iostream>
#include "xfaas-cpp-httplib/httplib.h"
#include "function/function.h"
#include "server/xserver.h"

int main() {
    std::cout << "XFaaS Function is listening on port 8080\n" << std::endl;
    auto server = xfaas::XServer(8080);

    httplib::Server::Handler handler = [](const httplib::Request &req, httplib::Response &res) {
        xfaas::Function f;
        f.call(req, res);
    };

    server.start(handler);
    return 0;
}

