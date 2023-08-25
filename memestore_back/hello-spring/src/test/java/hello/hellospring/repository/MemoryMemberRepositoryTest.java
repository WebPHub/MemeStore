package hello.hellospring.repository;

import com.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){            //각 test 이후 clear
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("Lee");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
        //Assertions.assertEquals(member, null);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Lee1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Lee2");
        repository.save(member2);

        Member result = repository.findByName("Lee1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Lee1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Lee2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
