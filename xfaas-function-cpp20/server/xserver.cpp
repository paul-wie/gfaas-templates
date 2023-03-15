#include "xserver.h"
#include "../info/info.h"

xfaas::XServer::XServer(int port) {
    this->port = port;
}

void xfaas::XServer::start(httplib::Server::Handler& handler) {
    httplib::Server svr;

    svr.Get("/", handler);
    svr.Post("/", handler);
    // Required for OpenFaaS readiness and health checks
    svr.Get("/_/health", Info::health);
    svr.Get("/_/ready", Info::ready);

    svr.listen("0.0.0.0", this->port);
}

