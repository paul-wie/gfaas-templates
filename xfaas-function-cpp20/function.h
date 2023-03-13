#pragma once

#include "xfaas-cpp-httplib/httplib.h"

namespace xfaas{

    class Function{
    public:
        void call(const httplib::Request &, httplib::Response &res);
    };

}
