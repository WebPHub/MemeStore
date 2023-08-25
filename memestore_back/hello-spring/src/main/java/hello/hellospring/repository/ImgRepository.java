package hello.hellospring.repository;

import hello.hellospring.domain.MemeImg;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImgRepository {
    MemeImg save(MemeImg memeimg);
    List<MemeImg> findByMemberId(Long memberid);
    List<MemeImg> findByTitle(String title);
    List<MemeImg> findAll();
}