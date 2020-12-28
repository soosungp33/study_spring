package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 메소드가 실행이 끝날 때마다 동작을 하는 콜백 메소드
    public void afterEach() {
        repository.clearStore();
    }

    @Test // save 테스트
    public void save() {
        Member member = new Member();
        member.setName("spring"); // member를 저장하고

        repository.save(member); // repository에 넣는다.

        Member result = repository.findByID(member.getId()).get(); // Optional에서 get으로 값을 꺼낸다.
        Assertions.assertThat(member).isEqualTo(result); // member와 result가 같은지 확인(Go에서 사용한거와 같음)
    }

    @Test // findByName 테스트
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test // findAll 테스트
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
