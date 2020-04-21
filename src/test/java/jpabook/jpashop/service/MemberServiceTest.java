package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // default rollback

public class MemberServiceTest {


    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em ;

    //transaction 기본값이 rollback이므로 insert 문을 실행 하지 않음. rollback 하거나 flush 사용하면된다.
    @Test
    //@Rollback(false)
    public void 회원가입()throws  Exception{
        Member member = new Member();
        member.setName("kim");
    
        Long saveId = memberService.join(member);

        //실제 디비 저장 insert
        //em.flush();
        assertEquals(member,memberRepository.findOne(saveId));
    }


    @Test (expected = IllegalStateException.class)
    public void test유저_중복가입() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kalkin");

        Member member2 = new Member();
        member2.setName("kalkin");

        memberService.join(member1);
        // when
        memberService.join(member2);


     // then
        fail("예외가 발생해야 한다.");
     }



}