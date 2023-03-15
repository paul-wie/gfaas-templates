
#include "function.h"

void xfaas::Function::call(const httplib::Request &req, httplib::Response &res) {
    //
    // Place your function code here
    //
    res.set_content("Hello from XFaaS C++ 20", "text/json");
}
