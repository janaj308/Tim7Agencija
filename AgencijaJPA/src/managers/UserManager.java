package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Tim7Comment;
import model.Tim7User;

//Crated by 
//************************
//	Istvan Pete
//************************
//Email
//************************
//	perox49@gmail.com
//************************
//Date
//************************
//	4.10.2016
//************************

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
			
		}finally{
			em.close();
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
	
	// Vraca usera ako je validan userName i password
	public Tim7User logInUser(EntityManager em, String userName, String password){
		try{
			
			TypedQuery<Tim7User> tq = em.createQuery("select u from Tim7User u where u.username =:username and u.userpassword =:userpassword", Tim7User.class);			
			tq.setParameter("username", userName);
			tq.setParameter("userpassword", password);
			if(tq.getResultList().isEmpty()){
				return null;
			}else{				
				return tq.getSingleResult();				
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("Ne postoji takav userName");
			return null;			
		}finally{
			em.close();
		}
	}
	
	public void setRating(Tim7User user, int r, String comm, Tim7User userby){
		EntityManager em = JPAUtil.getEntityManager();
		try{
			if (user.getRating()==0.0f){
				user.setRating(r);
			}else{
				user.setRating((user.getRating()+r)/2.0f);
			}
			Tim7Comment c= new Tim7Comment();
			c.setCommentcontent(comm);
			c.setUserby(userby);
			c.setUserto(user);
			em.getTransaction().begin();
			em.merge(user);
			em.persist(c);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
    }
	
	public List<Tim7Comment> getCommentsForUser(int id){
		EntityManager em = JPAUtil.getEntityManager();
		try{
			TypedQuery<Tim7Comment> tq = em.createQuery("select c from Tim7Comment c where c.userto.iduser =:id", Tim7Comment.class);
			tq.setParameter("id", id);
			return tq.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
