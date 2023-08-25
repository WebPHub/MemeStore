package hello.hellospring.controller;

import org.springframework.web.multipart.MultipartFile;

public class LikeForm {

    private Long id;
    private Long memberid;
    private Long imgid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getImgid() {
        return imgid;
    }

    public void setImgid(Long imgid) {
        this.imgid = imgid;
    }
}
