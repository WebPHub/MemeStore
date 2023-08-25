package hello.hellospring.controller;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

public class ImgForm {

    private Long memberid;
    private MultipartFile img;
    private String title;

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
