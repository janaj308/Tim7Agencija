package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Tim7Messagereceived;
import model.Tim7Messagesent;
import model.Tim7User;

public class MessagingManager {
	
	public boolean createNewMassage(Tim7User sender, Tim7User reciver, Tim7Messagesent newSentM, Tim7Messagereceived newReceM){
		EntityManager em = JPAUtil.getEntityManager();
		try {
			
			em.getTransaction().begin();
			
			em.merge(sender);
			em.merge(reciver);
			em.persist(newSentM);
			em.persist(newReceM);
			
//			em.merge(newSentM);
//			em.merge(newReceM);
			
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;

		} finally {

			em.close();

		}
		
	}
	
	public List<Tim7Messagesent> getAllUserSentMessages(int idUser){
		try{
			EntityManager em = JPAUtil.getEntityManager();
			TypedQuery<Tim7Messagesent> tq = em.createQuery("select m from Tim7Messagesent m where m.tim7User.iduser =:iduser", Tim7Messagesent.class);			
			tq.setParameter("iduser", idUser);
			
			if(tq.getResultList().isEmpty()){
				
				return null;
				
			}else{
				
				List<Tim7Messagesent> sentMessages = new ArrayList<>();
				for (Tim7Messagesent m : tq.getResultList()) {

					sentMessages.add(m);

				}
				return sentMessages;
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("Nema poslatih poruka");
			return null;			
		}
	}
	
	public List<Tim7Messagereceived> getAllUserRecivedMessages(int idUser){
		try{
			EntityManager em = JPAUtil.getEntityManager();
			TypedQuery<Tim7Messagereceived> tq = em.createQuery("select m from Tim7Messagereceived m where m.tim7User.iduser =:iduser", Tim7Messagereceived.class);			
			tq.setParameter("iduser", idUser);
			
			if(tq.getResultList().isEmpty()){
				
				return null;
				
			}else{
				
				List<Tim7Messagereceived> sentMessages = new ArrayList<>();
				for (Tim7Messagereceived m : tq.getResultList()) {
	
					sentMessages.add(m);
	
				}
				return sentMessages;
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("Nema poslatih poruka");
			return null;			
		}
	}

}
