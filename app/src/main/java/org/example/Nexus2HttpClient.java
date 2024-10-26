package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Nexus2HttpClient {

    private final HttpRequest request;
    private final HttpClient client;

    public Nexus2HttpClient(String requestUri) {
        URI uri = URI.create(requestUri);
        this.request = HttpRequest.newBuilder(uri).build();
        this.client = HttpClient.newHttpClient();
    }

    public static String listFetch() throws IOException, InterruptedException {
        Nexus2HttpClient client = new Nexus2HttpClient(
                "http://localhost:8081/nexus/service/local/lucene/search?r=releases&g=org.gradle.sample");
        return client.get();
    }

    public static String pomFetch(String pomUri) throws IOException, InterruptedException {
        String baseURL = "http://localhost:8081/nexus/service/local/repositories/releases/content/";
        Nexus2HttpClient client = new Nexus2HttpClient(baseURL + pomUri);
        return client.get();
    }

    private String get() throws IOException, InterruptedException {
        // 同期API呼び出し
        HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        // System.out.println(response.body()); // 受信したJSON文字列を確認
        return response.body();

    }
}
