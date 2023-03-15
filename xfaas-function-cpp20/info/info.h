
#include "../xfaas-cpp-httplib/httplib.h"

namespace xfaas{
    class Info{
    public:
        static void health(const httplib::Request &, httplib::Response &res);
        static void ready(const httplib::Request &, httplib::Response &res);
    };
}
