package com.weavus.weavus_bankweb.transactionTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PeopleTest {
    private static final String TARGET_URL = "http://localhost:8080/login";

    private static final int VIRTUAL_USERS = 10000;

    private static final int REQUESTS_PER_USER = 10;

    @Test
    void runSimpleLoadTest() throws InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TARGET_URL))
                .GET()
                .build();

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        ExecutorService executorService = Executors.newFixedThreadPool(VIRTUAL_USERS);

        System.out.println("부하 테스트를 시작합니다...");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < VIRTUAL_USERS; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < REQUESTS_PER_USER; j++) {
                    try {
                        // HTTP 요청을 보내고 응답을 받음
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                        // 상태 코드가 200번대이면 성공으로 간주
                        if (response.statusCode() >= 200 && response.statusCode() < 300) {
                            successCount.incrementAndGet();
                        } else {
                            failureCount.incrementAndGet();
                        }
                    } catch (Exception e) {
                        failureCount.incrementAndGet();
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        int totalRequests = VIRTUAL_USERS * REQUESTS_PER_USER;

        // --- 결과 출력 ---
        System.out.println("\n--- 부하 테스트 결과 ---");
        System.out.println("총 테스트 시간: " + totalTime + " ms");
        System.out.println("총 요청 수: " + totalRequests);
        System.out.println("성공한 요청 수: " + successCount.get());
        System.out.println("실패한 요청 수: " + failureCount.get());
        if (totalTime > 0) {
            double rps = (double) totalRequests / (totalTime / 1000.0);
            System.out.printf("초당 처리량 (RPS): %.2f req/s\n", rps);
        }
        System.out.println("----------------------");
    }
}
