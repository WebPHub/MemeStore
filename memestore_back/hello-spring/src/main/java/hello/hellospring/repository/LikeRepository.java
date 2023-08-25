package hello.hellospring.repository;

import hello.hellospring.domain.LikeMeme;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository {
    LikeMeme findByMemberImgId(Long memberid, Long imgId);
    LikeMeme save(LikeMeme like);
    int delete(LikeMeme like);
    List<LikeMeme> findByMember(Long memberid);
    List<LikeMeme> findByImage(Long imgId);
}