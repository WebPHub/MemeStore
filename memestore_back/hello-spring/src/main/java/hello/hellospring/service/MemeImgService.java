package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemeImg;
import hello.hellospring.repository.ImgRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemeImgService {

    private final ImgRepository imgRepository;

    @Autowired
    public MemeImgService(ImgRepository imgRepository) {
        this.imgRepository = imgRepository;
    }

    public Long join(MemeImg memeimg){
        imgRepository.save(memeimg);
        return memeimg.getId();
    }

    public List<MemeImg> findImgs(){
        return imgRepository.findAll();
    }

    public List<MemeImg> findMemberId(Long memberId){
        return imgRepository.findByMemberId(memberId);
    }

    public List<MemeImg> findTitle(String title) { return imgRepository.findByTitle(title); }
}
