package hello.hellospring.repository;

import hello.hellospring.domain.LikeMeme;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class JpaLikeRepository implements LikeRepository{
    private final EntityManager em;

    public JpaLikeRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public LikeMeme save(LikeMeme like) {
        em.persist(like);
        return like;
    }

    @Override
    public LikeMeme findByMemberImgId(Long memberid, Long imgId) {
        List<LikeMeme> result = em.createQuery("select m from LikeMeme m " +
        "where m.memberid = :memberid and m.imgid = :imgid", LikeMeme.class)
                .setParameter("memberid", memberid)
                .setParameter("imgid", imgId)
                .getResultList();

        if(result.size() == 0) return new LikeMeme();
        return result.get(0);
    }

    @Override
    public int delete(LikeMeme like) {
        int deletedcnt = em.createQuery("delete from LikeMeme m where m.memberid = :memberid and m.imgid = :imgid")
                .setParameter("memberid", like.getMemberid())
                .setParameter("imgid", like.getImgid())
                .executeUpdate();
        return deletedcnt;
    }

    @Override
    public List<LikeMeme> findByMember(Long memberid) {
        List<LikeMeme> result = em.createQuery("select m from LikeMeme m where m.memberid = :memberid", LikeMeme.class)
                .setParameter("memberid", memberid)
                .getResultList();
        return result;
    }

    @Override
    public List<LikeMeme> findByImage(Long imgId) {
        List<LikeMeme> result = em.createQuery("select m from LikeMeme m where m.imgid = :imgid", LikeMeme.class)
                .setParameter("imgid", imgId)
                .getResultList();
        return result;
    }
}
