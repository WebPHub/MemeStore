package hello.hellospring.controller;

import hello.hellospring.domain.MemeImg;
import hello.hellospring.domain.MyMessage;
import hello.hellospring.service.MemeImgService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ImgController {
    private final MemeImgService memeimgService;
    public ImgController(MemeImgService memeimgService) {
        this.memeimgService = memeimgService;
    }

    @ResponseBody
    @PostMapping("/memeimg/new")
    public Object create(@RequestBody ImgForm form){
        try{
            MemeImg memeimg = new MemeImg();
            memeimg.setMemberid(form.getMemberid());
            memeimg.setImg(form.getImg());
            memeimg.setTitle(form.getTitle());
            if(memeimgService.join(memeimg) != -1)
                return memeimg;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new MyMessage("이미지 저장중 에러발생.");
    }

    @ResponseBody
    @GetMapping("/memeimg/find/mine")
    public Object GetMyImg(@RequestParam Long memberId){
        List<MemeImg> imgs = memeimgService.findMemberId(memberId);
        return imgs;
    }

    @ResponseBody
    @GetMapping("/memeimg/find/title")
    public Object GetTitleImg(@RequestParam String title){
        List<MemeImg> imgs = memeimgService.findTitle(title);
        return imgs;
    }

    @ResponseBody
    @GetMapping("/memeimg/find")
    public Object GetAllImg(){
        return memeimgService.findImgs();
    }
}
