import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.io.*;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Prasenjit {
    public static void main(String[] args) {
        HttpServer hs = null;
        try {
            hs = HttpServer.create(new InetSocketAddress(3000),0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        hs.createContext("/test", new MyHandler());
        hs.createContext("/hello", new HelloWorld());
        hs.setExecutor(null); // creates a default executor
        hs.start();
    }
    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "Welcome h HowTo test page";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class HelloWorld implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "hello World";
            Headers h = t.getResponseHeaders();
            h.set("Content-Type","image/jpeg");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
