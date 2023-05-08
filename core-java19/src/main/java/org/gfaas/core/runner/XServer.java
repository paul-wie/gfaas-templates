package org.gfaas.core.runner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.gfaas.core.info.Health;
import org.gfaas.core.info.Ready;
import org.gfaas.core.model.XFunction;
import org.gfaas.core.model.XRequest;
import org.gfaas.core.model.XResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class XServer {

    public void startServer(XFunction xFunction) throws Exception{
        System.out.println("XFaaS Function is listening on port 8080");
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Health endpoints for OpenFaaS
        server.createContext("/_/health", new XHandler(new Health()));
        server.createContext("/_/ready", new XHandler(new Ready()));

        // Health endpoints for Nuclio
        server.createContext("/__internal/health", new XHandler(new Health()));

        server.createContext("/", new XHandler(xFunction));
        server.setExecutor(null); // creates a default executor
        server.start();

        HttpServer nuclioLive = HttpServer.create(new InetSocketAddress(8082), 0);
        nuclioLive.createContext("/live", new XHandler(new Health()));
        nuclioLive.setExecutor(null); // creates a default executor
        nuclioLive.start();
    }

    private class XHandler implements HttpHandler{

        XFunction xFunction;

        public XHandler(XFunction xFunction){
            this.xFunction = xFunction;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {

             /*
                Transform HttpExchange to XRequest
             */

            InputStream is = exchange.getRequestBody();
            var xRequest = new XRequest();
            xRequest.body = is.readAllBytes();
            xRequest.requestMethod = exchange.getRequestMethod();

            // Headers
            exchange.getRequestHeaders().forEach((k, v) -> xRequest.headers.put(k, v.get(0)));

            // Parameters

            try{
                var queryParameters = exchange.getRequestURI().getQuery();
                if(queryParameters != null){
                    Arrays.stream(queryParameters.split("&")).forEach(parameter -> {
                        var keyValue = parameter.split("=");
                        xRequest.queryParameters.put(keyValue[0], keyValue[1]);
                    });
                }
            }catch (Exception e){
                // Ignore query parameters on error
            }

            /*
                Call the function
             */
            XResponse xResponse = null;
            try {
                xResponse = xFunction.call(xRequest);
            } catch (Exception e) {
                // Internal Exception, should not occur
                xResponse.setBody(e.toString());
                xResponse.setStatusCode(500);
            }

            /*
                Transform XResponse to HttpExchange
             */


            // Response Heasers
            xResponse.getHeaders().forEach((k,v) -> exchange.getResponseHeaders().set(k, v));

            // Send Body
            var responseBody = xResponse.getBody().getBytes();
            exchange.sendResponseHeaders(xResponse.getStatusCode(), responseBody.length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseBody);
            os.close();
        }
    }
}
