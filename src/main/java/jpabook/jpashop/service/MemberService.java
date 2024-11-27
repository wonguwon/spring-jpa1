package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //데이터의 변경이 없는 읽기 전용 메서드에 사용, 영속성 컨텍스트를 플러시 하지 않으므로 약간의 성능 향상(읽기 전용에는 다 적용)
public class MemberService {


    // final 키워드를 추가하면 컴파일 시점에 memberRepository를 설정하지 않는 오류를 체크할 수 있다.
    private final MemberRepository memberRepository;

    //생성자가 하나면 @Autowired를 생략할 수 있다.
    //@Autowired
    //public MemberService(MemberRepository memberRepository) {
    //    this.memberRepository = memberRepository;
    //}

    /**
     * 회원가입
     */
    @Transactional // 일기전용해제
    public Long join(Member member) {

        validateDuplicateMember(member); //중복회원검증

        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다."); //올바른 메소드가 아닐 때
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(Long id) {
        return memberRepository.findOne(id);
    }

    public void update(Long id, String name) {
        memberRepository
                .findOne(id)
                .setName(name);
    }
}
