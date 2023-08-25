package hello.hellospring.controller;

import hello.hellospring.domain.LikeMeme;
import hello.hellospring.domain.MyMessage;
import hello.hellospring.service.LikeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {
    private final LikeService likeService;
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @ResponseBody
    @GetMapping("/like/user")
    public LikeMeme getUserLike(@RequestParam Long userId, @RequestParam Long imgId){
        return likeService.select(userId, imgId);
    }

    @GetMapping("/like")
    public int getLikeCnt(@RequestParam Long imgId){
        return likeService.getLikeCnt(imgId);
    }

    @ResponseBody
    @PostMapping("/like/up")
    public Object likeup(@RequestBody LikeForm form){
        try{
            LikeMeme like = new LikeMeme();
            like.setImgid(form.getImgid());
            like.setMemberid(form.getMemberid());
            if(likeService.join(like) != -1L)
                return like;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new MyMessage("좋아요중 에러발생. (중복되거나 없는 좋아요)");
    }

    @ResponseBody
    @PutMapping("/like/down")
    public Object likedown(@RequestBody LikeForm form){
        LikeMeme like = new LikeMeme();
        like.setImgid(form.getImgid());
        like.setMemberid(form.getMemberid());
        int cnt = likeService.delete(like);
        if(cnt > 0)   return like;
        return new MyMessage("좋아요 취소중 에러발생.");
    }
}
