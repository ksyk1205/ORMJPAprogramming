package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf =Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        //JPA에서 모든 작업은 Transaction안에서 해야합니다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            List<Member> result = em.createQuery(
                    "select m from Member m where m.age > 18",
                    Member.class
            ).getResultList();

            tx.commit();
        } catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();

    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2"+(m1 instanceof Member));
        System.out.println("m1 == m2"+(m2 instanceof Member));
    }

    private static void printMember(Member member) {
        System.out.println("member = "+member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();;
        System.out.println("username = "+username);

        //Team team = member.getTeam();
        //System.out.println("team = "+ team.getName());
    }
}
