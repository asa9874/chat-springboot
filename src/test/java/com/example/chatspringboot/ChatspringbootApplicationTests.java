package com.example.chatspringboot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.domain.chatRoom.domain.ChatRoom;
import com.example.domain.chatRoom.repository.ChatRoomRepository;
import com.example.domain.member.domain.Member;
import com.example.domain.member.repository.MemberRepository;
import com.example.domain.message.domain.Message;
import com.example.domain.message.repository.MessageRepository;
//copy con local2.mv.db
@SpringBootTest
class ChatspringbootApplicationTests {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		List<Member> friends = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Member member = Member.builder()
					.name("홍길동" + i)
					.password(passwordEncoder.encode("qwer123"))
					.email("email" + i)
					.build();
			friends.add(member);
			memberRepository.save(member);
		}

		Member member = Member.builder()
				.name("주인공")
				.email("test")
				.password(passwordEncoder.encode("test"))
				.build();
		member.setFriends(friends);

		memberRepository.save(member);

		ChatRoom chatRoom = ChatRoom.builder()
				.roomName("테스트방")
				.roomDescription("테스트방 설명")
				.owner(member)
				.members(Set.of(friends.get(0), friends.get(1),member))
				.build();

		chatRoomRepository.save(chatRoom);

		for (int i = 0; i < 10; i++) {
			Message message = Message.builder()
					.chatRoom(chatRoom)
					.sender(member)
					.timestamp(LocalDateTime.now())
					.content("테스트 메시지 ㅋ" + i)
					.build();
			messageRepository.save(message);
		}

		for (int i = 0; i < 5; i++) {
			Message message = Message.builder()
					.chatRoom(chatRoom)
					.sender(friends.get(0))
					.timestamp(LocalDateTime.now())
					.content("친구1메시지임ㅋ" + i)
					.build();
			messageRepository.save(message);
		}

		for (int i = 0; i < 5; i++) {
			Message message = Message.builder()
					.chatRoom(chatRoom)
					.sender(friends.get(1))
					.content("친구2메시지임ㅠ" + i)
					.timestamp(LocalDateTime.now())
					.build();
			messageRepository.save(message);
		}

	}

}
