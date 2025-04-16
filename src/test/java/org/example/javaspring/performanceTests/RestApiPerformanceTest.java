package org.example.javaspring.performanceTests;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import java.io.IOException;


public class RestApiPerformanceTest {

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8080/";
        int totalRequests = 10000;
        int successCount = 0;
        long totalTime = 0;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            for (int i = 0; i < totalRequests; i++) {
                long startTime = System.nanoTime();

                HttpGet request = new HttpGet(url);
                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    long duration = System.nanoTime() - startTime;
                    totalTime += duration;

                    int status = response.getCode();
                    if (status >= 200 && status < 300) {
                        successCount++;
                    } else {
                        System.out.println("Failed with status: " + status);
                    }
                } catch (IOException e) {
                    System.out.println("Request failed: " + e.getMessage());
                }
            }
        }

        double averageTimeMs = totalTime / 1_000_000.0 / totalRequests;
        System.out.println("Total Requests: " + totalRequests);
        System.out.println("Successful: " + successCount);
        System.out.println("Failed: " + (totalRequests - successCount));
        System.out.println("Average Response Time: " + averageTimeMs + " ms");
    }

}
