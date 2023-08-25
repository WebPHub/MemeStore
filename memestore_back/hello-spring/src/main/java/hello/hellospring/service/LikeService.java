package hello.hellospring.service;

import hello.hellospring.domain.LikeMeme;
import hello.hellospring.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public LikeMeme select(Long memberId, Long imgId){
        return likeRepository.findByMemberImgId(memberId, imgId);
    }

    public int getLikeCnt(Long imgId){
        return likeRepository.findByImage(imgId).size();
    }

    public Long join(LikeMeme like){
        Long validateLike = validateDuplicateLike(like);
        if(validateLike != null){
            likeRepository.save(like);
            return like.getId();
        }
        return -1L;
    }

    public int delete(LikeMeme like){
        int cnt = likeRepository.delete(like);
        return cnt;
    }

    private Long validateDuplicateLike(LikeMeme like) {
        List<LikeMeme> alreadyliked = likeRepository.findByMember(like.getMemberid());
        Long target = like.getImgid();
        for (LikeMeme liked : alreadyliked) {
            if (liked.getImgid().equals(target)) {
                return null;
            }
        }
        return 1L;
    }
}