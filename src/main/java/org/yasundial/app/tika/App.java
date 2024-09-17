package org.yasundial.app.tika;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.StatusLine;

public class App {
    public static void main(String[] args) {
        App app = new App();
        String url = "https://www.yadiary.net/notfound";
        app.builtin(url);
        app.httpclient5(url);
        url = "https://www.yadiary.net/";
        app.builtin(url);
        app.httpclient5(url);
    }

    public App() {
    }

    public void parse(byte[] data) {
        try {
            InputStream input = new ByteArrayInputStream(data);

            HtmlParser parser = new HtmlParser();
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();
            ContentHandler handler = new DefaultHandler();

            System.out.println(metadata);
            parser.parse(input, handler, metadata, context);
            System.out.println(metadata);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void builtin(String urlText) {
        System.out.println("builtin: " + urlText);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlText))
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            byte[] bodyBytes = response.body().getBytes();
            System.out.println("builtin: bodyBytes.length=" + bodyBytes.length);
            parse(bodyBytes);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /*
      @see https://github.com/apache/httpcomponents-client/blob/5.3.x/httpclient5/src/test/java/org/apache/hc/client5/http/examples/ClientResponseProcessing.java
     */
    public void httpclient5(String urlText) {
        System.out.println("httpclient5: " + urlText);
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpget = new HttpGet(urlText);

            final Result result = httpclient.execute(httpget, response -> {
                    return new Result(response.getCode(), EntityUtils.toString(response.getEntity()));
                });
            byte[] bodyBytes = result.content.getBytes();
            System.out.println("httpclient5: bodyBytes.length=" + bodyBytes.length);
            parse(bodyBytes);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

     static class Result {

        final int status;
        final String content;

        Result(final int status, final String content) {
            this.status = status;
            this.content = content;
        }

    }
}
