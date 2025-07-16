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

public class peopleTest {
    // --- 테스트 설정값 ---
    // ✅ 1. 부하를 가할 URL (실행 중인 애플리케이션의 주소로 변경하세요)
    private static final String TARGET_URL = "http://localhost:8080/login";

    // ✅ 2. 동시 접속할 가상 사용자 수
    private static final int VIRTUAL_USERS = 10000;

    // ✅ 3. 각 사용자가 보낼 총 요청 수
    private static final int REQUESTS_PER_USER = 10;
    // --- 설정 끝 ---




    @Test
    @DisplayName("간단한 동시 접속 부하 테스트")
    void runSimpleLoadTest() throws InterruptedException {
        // HTTP 요청을 보낼 클라이언트 생성
        HttpClient client = HttpClient.newHttpClient();

        // 요청 객체 생성
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TARGET_URL))
                .GET()
                .build();

        // 성공/실패 카운트를 위한 변수 (여러 스레드에서 안전하게 사용하기 위해 AtomicInteger 사용)
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        // 가상 사용자 수만큼 스레드 풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(VIRTUAL_USERS);

        System.out.println("부하 테스트를 시작합니다...");
        long startTime = System.currentTimeMillis();

        // 모든 가상 사용자에 대해 요청 작업을 제출
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

        // 모든 작업이 끝날 때까지 대기
        executorService.shutdown();
        // 최대 1분까지 기다림
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
