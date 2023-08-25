package hello.hellospring.repository;

import hello.hellospring.domain.MemeImg;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaImgRepository implements ImgRepository{
    private final EntityManager em;

    public JpaImgRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public MemeImg save(MemeImg memeimg) {
        em.persist(memeimg);
        return memeimg;
    }

    @Override
    public List<MemeImg> findByMemberId(Long memberid) {
        List<MemeImg> result = em.createQuery("select m from MemeImg m where m.memberid = :memberid", MemeImg.class)
                .setParameter("memberid", memberid)
                .getResultList();
        return result;
    }

    @Override
    public List<MemeImg> findByTitle(String title) {
        List<MemeImg> result = em.createQuery("select m from MemeImg m where m.title like '%" + title + "%'", MemeImg.class)
                .getResultList();
        return result;
    }

    @Override
    public List<MemeImg> findAll() {
        return em.createQuery("select m from MemeImg m", MemeImg.class)
                .getResultList();
    }
}
