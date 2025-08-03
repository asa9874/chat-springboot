package com.example.chatspringboot;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MockTest {
    @Test
    @DisplayName("MOCK 테스트")
    void testMapMock() {
        Map<String, String> mockMap = Mockito.mock(Map.class);
        // mock 객체에 대한 동작을 정의
        Mockito.when(mockMap.get("key")).thenReturn("value");

        // mock 객체의 동작을 확인
        System.out.println("동작 값을 확인 합니다 :: " + mockMap.get("key")); 

        // verify로 mock 객체의 메서드 호출 여부를 확인
        Mockito.verify(mockMap).get("key");
    }
}
