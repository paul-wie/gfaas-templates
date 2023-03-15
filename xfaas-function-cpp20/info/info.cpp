#include "info.h"

void xfaas::Info::health(const httplib::Request &, httplib::Response &res) {
    res.set_content("Ok", "text/json");
}

void xfaas::Info::ready(const httplib::Request &, httplib::Response &res) {
    res.set_content("Ok", "text/json");
}

