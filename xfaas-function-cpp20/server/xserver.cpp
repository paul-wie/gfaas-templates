#include "xserver.h"
#include "../info/info.h"

xfaas::XServer::XServer(int port) {
    this->port = port;
}

void xfaas::XServer::start(httplib::Server::Handler& handler) {
    httplib::Server svr;

    svr.Get("/", handler);
    svr.Post("/", handler);
    // Health endpoints for OpenFaaS
    svr.Get("/_/health", Info::health);
    svr.Get("/_/ready", Info::ready);
    // Health endpoints for Nuclio
    svr.Get("/__internal/health", Info::health);

    svr.listen("0.0.0.0", this->port);
}

