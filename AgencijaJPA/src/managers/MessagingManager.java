package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Tim7Messagereceived;
import model.Tim7Messagesent;
import model.Tim7User;

public class MessagingManager {
	
	public boolean createNewMessage(Tim7Messagesent newSentM, Tim7Messagereceived newReceM){
		EntityManager em = JPAUtil.getEntityManager();
		try {
			
			em.getTransaction().begin();
			
			em.persist(newSentM);
			em.persist(newReceM);
			
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;

		} finally {

			em.close();

		}
		
	}
	
	public List<Tim7Messagesent> getAllUserSentMessages(int idUser, int receiver){
		try{
			EntityManager em = JPAUtil.getEntityManager();
			TypedQuery<Tim7Messagesent> tq = em.createQuery("select m from Tim7Messagesent m where m.userSender.iduser =:s and m.userReceiver.iduser = :r order by m.messagesenttime", Tim7Messagesent.class);			
			tq.setParameter("s", idUser);
			tq.setParameter("r", receiver);
			
			return tq.getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("Nema poslatih poruka");
			return new ArrayList<>();			
		}
	}
	
	public List<Tim7Messagereceived> getAllUserReceivedMessages(int idUser, int sender){
		try{
			EntityManager em = JPAUtil.getEntityManager();
			TypedQuery<Tim7Messagereceived> tq = em.createQuery("select m from Tim7Messagereceived m where m.userReceiver.iduser =:r and m.userSender.iduser = :s order by m.messagereceivedtime", Tim7Messagereceived.class);			
			tq.setParameter("r", idUser);
			tq.setParameter("s", sender);
			
			return tq.getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("Nema poslatih poruka");
			return new ArrayList<>();			
		}
	}
	
	public List<Tim7Messagereceived> getNewReceivedMessages(Tim7User user) {
		
		try {
			
			if (user != null) {
			
				TypedQuery<Tim7Messagereceived> tq = JPAUtil.getEntityManager().createQuery("select m from Tim7Messagereceived m where m.userReceiver.iduser = :id and m.messageseen = :s", Tim7Messagereceived.class);
				tq.setParameter("id", user.getIduser());
				tq.setParameter("s", (byte) 0);
				
				return tq.getResultList();
			
			} else {
				
				return new ArrayList<>();
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ArrayList<>();
			
		}
		
	}

	public List<Tim7User> getAllContacts(int iduser) {
		
		try {
			
			TypedQuery<Tim7User> tq = JPAUtil.getEntityManager().createQuery("select distinct u from Tim7User u, Tim7Messagesent ms where "
					+ "ms.userSender.iduser = u.iduser and ms.userReceiver.iduser = :r "
					+ "or ms.userReceiver.iduser = u.iduser and ms.userSender.iduser = :s "
					+ "order by ms.messagesenttime", Tim7User.class);
			tq.setParameter("r", iduser);
			tq.setParameter("s", iduser);
			
			return tq.getResultList();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ArrayList<>();
			
		}
		
	}
	
	public void checkAsSeen(List<Tim7Messagereceived> messages) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			
			em.getTransaction().begin();
			
			for (Tim7Messagereceived m : messages) {
				
				if (m.getMessageseen() == 0) {
					m.setMessageseen((byte) 1);
					em.merge(m);
				}
				
			}
			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			em.getTransaction().rollback();
			
		}
		
	}
	
}
