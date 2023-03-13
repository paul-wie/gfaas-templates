#include "xserver.h"

xfaas::XServer::XServer(int port) {
    this->port = port;
}

void xfaas::XServer::start(httplib::Server::Handler& handler) {
    httplib::Server svr;

    svr.Get("/", handler);

    svr.listen("0.0.0.0", this->port);
}

