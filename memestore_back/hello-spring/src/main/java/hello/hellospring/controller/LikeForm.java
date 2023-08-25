package hello.hellospring.controller;

public class LikeForm {

    private Long memberid;
    private Long imgid;

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
