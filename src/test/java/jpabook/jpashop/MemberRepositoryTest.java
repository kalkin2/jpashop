package jpabook.jpashop;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void memberTest(){
        Member member = new Member();
        member.setName("kalkin");

        Long memberId = memberService.join(member);
        Member returnMember = memberService.findMember(memberId);

        Assertions.assertThat(returnMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(returnMember.getName()).isEqualTo(member.getName());


    }
}
