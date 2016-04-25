package managers;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Tim7Destination;
import model.Tim7Photo;
import model.Tim7User;

public class PhotoManager {

	
	public void savePhoto(Tim7Photo photo){
		try{
			EntityManager em=JPAUtil.getEntityManager();
			em.persist(photo);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public Tim7Destination getOneDestination(){
		try{
			EntityManager em=JPAUtil.getEntityManager();
			TypedQuery<Tim7Destination> tq=em.createQuery("select d from Tim7Destination d where d.iduser=1",Tim7Destination.class);
			return tq.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
