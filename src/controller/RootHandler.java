package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RootHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        String resp = "<h1>Server Start Success </h1>";
        he.sendResponseHeaders(200,resp.length());
        OutputStream os = he.getResponseBody();
        os.write(resp.getBytes());
        os.close();
    }
}
