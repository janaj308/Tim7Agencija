package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Tim7Post;
import model.Tim7Thread;

public class ForumManager {

	public boolean saveNewPost(Tim7Post post) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(post);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public List<Tim7Thread> getAllThreads() {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			TypedQuery<Tim7Thread> tq = em.createQuery("select t from Tim7Thread t order by t.threadname", Tim7Thread.class);
			return tq.getResultList();

		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();

		} finally {

			em.close();

		}
	}

	public List<Tim7Post> getAllPostsForThread(int threadId) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			TypedQuery<Tim7Post> tq = em.createQuery("select p from Tim7Post p where p.tim7Thread.idthread = :id",
					Tim7Post.class);
			tq.setParameter("id", threadId);
			return tq.getResultList();
		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();

		} finally {

			em.close();

		}
	}
	
	public void createThread(Tim7Thread thread) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			
			em.getTransaction().begin();
			em.persist(thread);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			em.getTransaction().rollback();
			
		} finally {
			
			em.close();
			
		}
		
	}

}
