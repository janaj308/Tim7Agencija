package managers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Tim7Destination;
import model.Tim7Photo;
import model.Tim7User;

public class PhotoManager {

	
	public void savePhoto(Tim7Photo photo){
		try{
			EntityManager em=JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(photo);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public List<Tim7Photo> getAllPhotos(){
		try {
			EntityManager em=JPAUtil.getEntityManager();
			TypedQuery<Tim7Photo> tq=em.createQuery("select p from Tim7Photo",Tim7Photo.class);
			return tq.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
