package managers;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Tim7User;

public class UserManager {
	
	public Integer saveNewUser(EntityManager em, Tim7User user){
		//testiramo da li je username validan, ako jeste idemo dalje ako ne vracamo vrednost -1 za koju ce biti ispisana poruka o gresci
		if(testUserNameValidity(em, user.getUsername())){
			return -1;
		}
		//testiramo da li je email validan, ako jeste idemo dalje ako ne vracamo vrednost -2 za koju ce biti ispisana poruka o gresci
		if(testEmailValidity(em, user.getUseremail())){
			return -2;
		}
		
		try{
			
			em.getTransaction().begin();
			// Kod svakog novog korisnika na pocetku je rateing 0
			user.setRating(0);				
			em.persist(user);			
			em.getTransaction().commit();
			return user.getIduser();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
	}
	
	
	// Svaki user name treba da bude unikatan
	public boolean testUserNameValidity(EntityManager em, String userName){
		try{
			
			TypedQuery<Tim7User> tq = em.createQuery("select u from Tim7User u where u.username =:username", Tim7User.class);			
			tq.setParameter("username", userName);
			
			if(tq.getResultList().isEmpty()){
				return false;
			}else{
				return true;
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("Ne postoji takav userName");
			return true;			
		}		
	}
	
	// Svaki Mail treba da bude unikatan
	public boolean testEmailValidity(EntityManager em, String eMail){
		try{
			
			TypedQuery<Tim7User> tq = em.createQuery("select u from Tim7User u where u.useremail =:useremail", Tim7User.class);
			tq.setParameter("useremail", eMail);
			
			if(tq.getResultList().isEmpty()){
				return false;
			}else{
				return true;
			}
			
		}catch(Exception e){			
			e.printStackTrace();
			System.out.println("Ne postoji takav mail");
			return true;			
		}		
	}
}
