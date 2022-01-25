package jpql;

import javax.persistence.*;
import java.util.List;


public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            member.setType(MemberType.USER);
//            member.setTeam(team);
//
//            em.persist(member);
            Member member1 = new Member();
            member1.setUsername("관리자1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);


            em.flush();
            em.clear();

//            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//            MemberDTO  memberDTO = result.get(0);
//            System.out.println("memberDTO = "+memberDTO.getUsername());
//            System.out.println("memberDTO = "+memberDTO.getAge());
//
//            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            System.out.println("result.size = " + result.size());
//            for (Member member1 : resultList){
//                System.out.println("member1 = "+ member1);
//            }
//
//            String query = "select m from Member m inner join m.team t";
//            List<Member> resultList1 = em.createQuery(query, Member.class)
//                    .getResultList();

//            String query = "select m.username, 'HELLO', TRUE FROM Member m " +
//                    "where m.type = jpql.MemberType.USER";
//            List<Object[]> result = em.createQuery(query)
//                    .getResultList();
//            for (Object[] objects : result) {
//                System.out.println("objects = " + objects[0]);
//                System.out.println("objects = " + objects[1]);
//                System.out.println("objects = " + objects[2]);
//            }

//            String query =
//                    "select" +
//                            " case when m.age <= 10 then '학생요금'" +
//                            " when m.age >= 60 then '경로요금'" +
//                            " else '일반요금'" +
//                            " end" +
//                    " from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//
//            for(String s : result){
//                System.out.println("s = "+ s);
//            }

            String query =
                    "select function('group_concat', m.username) from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();

            for(String s : result){
                System.out.println("s = "+ s);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }

}