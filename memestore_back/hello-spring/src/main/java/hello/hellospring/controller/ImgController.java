package hello.hellospring.controller;

import hello.hellospring.domain.MemeImg;
import hello.hellospring.domain.MyMessage;
import hello.hellospring.service.MemeImgService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class ImgController {
    private final MemeImgService memeimgService;
    public ImgController(MemeImgService memeimgService) {
        this.memeimgService = memeimgService;
    }

    @PostMapping("/memeimg/new")
    public Object create(ImgForm form){
        try{
            MemeImg memeimg = new MemeImg();
            memeimg.setMemberid(form.getMemberid());
            memeimg.setImg(form.getImg().getBytes());
            memeimg.setTitle(form.getTitle());
            if(memeimgService.join(memeimg) != -1)
                return memeimg;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new MyMessage("이미지 저장중 에러발생.");
    }

    @PostMapping("/memeimg/find/mine")
    public Object GetMyImg(ImgForm img){
        List<MemeImg> imgs = memeimgService.findMemberId(img.getMemberid());
        return imgs;
    }

    @PostMapping("/memeimg/find/title")
    public Object GetTitleImg(ImgForm img){
        List<MemeImg> imgs = memeimgService.findTitle(img.getTitle());
        return imgs;
    }

//    @PostMapping("/memeimg/find/recommend")
//    public Object RecommendImg(){
//        return List<MemeImg>;
//    }
}
