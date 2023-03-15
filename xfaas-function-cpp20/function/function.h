#pragma once

#include "httplib.h"

namespace xfaas{

    class Function{
    public:
        void call(const httplib::Request &, httplib::Response &res);
    };

}
