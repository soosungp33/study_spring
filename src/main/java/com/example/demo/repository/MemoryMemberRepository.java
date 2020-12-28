package com.example.demo.repository;

import com.example.demo.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    // 동시성 문제 고려 x
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        return Optional.ofNullable(store.get(id)); // Null이여도 사용 가능하게
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 파라미터로 넘어온 name과 member에 있는 name이 같은 것을 필터링해서 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
