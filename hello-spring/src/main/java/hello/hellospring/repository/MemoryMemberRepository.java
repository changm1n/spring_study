package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // sequence가 키값 생성
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member); // id를 받아서 store에 저장
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환될 가능성이 있으면 optional로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        store.values().stream()
                .filter(member -> member.getName().equals(name)) // 같은 이름이 있는지 계속 돌려서 확인함
                .findAny(); // 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
