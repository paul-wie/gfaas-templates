package xfaas.config.openfaas;

import java.io.*;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import xfaas.config.XRequest;
import xfaas.config.XResponse;
import xfaas.function.Function;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;


public class OpenFaasEntrypoint {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8082), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            InputStream is = t.getRequestBody();

            var xRequest = new XRequest();
            xRequest.body = is.readAllBytes();
            xRequest.headers = new HashMap<>();
            t.getRequestHeaders().forEach((k, v) -> {
                xRequest.headers.put(k, v.get(0));
            });


            XResponse xResponse = null;
            try {
                xResponse = new Function().call(xRequest);
            } catch (Exception e) {
                // Internal Exception, should not occur
                xResponse.setBody(e.toString());
                xResponse.setStatusCode(500);
            }


            var responseBody = xResponse.getBody();

            xResponse.getHeaders().forEach((k,v) -> {
                t.getResponseHeaders().set(k, v);
            });

            t.sendResponseHeaders(xResponse.getStatusCode(), responseBody.length());

            OutputStream os = t.getResponseBody();
            os.write(responseBody.getBytes());
            os.close();
        }
    }
}

//    public static void main(String[] args) throws Exception {
//        Vertx vertx = Vertx.vertx();
//        Integer httpPort = Integer.parseInt(Optional.ofNullable(System.getenv("PORT")).orElse("8082"));
//        HttpServer server = vertx.createHttpServer();
//        Router router = Router.router(vertx);
//
//        if (Boolean.parseBoolean(Optional.ofNullable(System.getenv("FRONTAPP")).orElse("false"))) {
//            // serve static assets, see /resources/webroot directory
//            router.route("/*").handler(StaticHandler.create());
//        } else {
//            io.vertx.core.Handler<RoutingContext> handler = new xfaas.config.openfaas.Handler();
//            router.route().handler(handler);
//        }
//
//        server.requestHandler(router::accept).listen(httpPort, result -> {
//            if(result.succeeded()) {
//                System.out.println("Listening on port " + httpPort);
//            } else {
//                System.out.println("Unable to start server: " + result.cause().getMessage());
//            }
//        });
//    }
