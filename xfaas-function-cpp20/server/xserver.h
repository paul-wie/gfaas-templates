#include "httplib.h"


namespace xfaas{

    class XServer{
        int port;
    public:
        void start(httplib::Server::Handler& handler);
        explicit XServer(int port);
    };

}
