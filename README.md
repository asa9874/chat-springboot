# SpringBoot 채팅 서버

> 이 프로젝트는 STOMP 프로토콜을 활용한 실시간 메시징 시스템을 구현한 예제입니다. 웹소켓(WebSocket)과 STOMP, 그리고 JWT 기반 인증을 사용하여 실시간으로 메시지를 송수신할 수 있습니다. 클라이언트는 React와 SockJS, STOMP.js를 사용하고, 서버는 Spring Boot로 구성되었습니다.

## 기술 스택

* **백엔드**: Spring Boot (WebSocket, STOMP, JWT 인증)
* **프론트엔드**: React, SockJS, STOMP.js
* **메시징 프로토콜**: STOMP, WebSocket
* **JWT 인증**: 클라이언트에서 JWT 토큰을 사용한 인증
* **실시간 통신**: STOMP 프로토콜을 통해 실시간 메시지 송수신

  
## 📂 프로젝트 구성
- **프론트엔드**: [React 채팅 웹](https://github.com/asa9874/chat-react)
- **백엔드**: SpringBoot + WebSocket

## 🌐 기능
- **웹소켓 서버**: 실시간 메시지 송수신을 위한 WebSocket 구현.
- **유저 연결 관리**: 유저가 접속하고 나갈 때 서버에서 유저 목록을 관리합니다.
- **메시지 전송**: 클라이언트로부터 받은 메시지를 실시간으로 다른 클라이언트에게 전달합니다.

# 🔍관련 작성한 블로깅 내용
- [React + SpringBoot + JWT + Stomp으로 WebSocket 채팅 구현하기](https://asa9874.tistory.com/697)
- [STOMP WebSocket 1:1 메시징 사용자별 구독관리](https://asa9874.tistory.com/699)
- [websocket 채팅방 이미지 업로드하기](https://asa9874.tistory.com/700)


## 🔧 설치 방법

### 1. 클론
```bash

git clone https://github.com/asa9874/chat-springboot.git
cd chat-springboot
```
