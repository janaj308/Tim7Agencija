package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import model.Tim7Destination;
import model.Tim7Offer;

public class OfferManager {

	public List<Tim7Offer> getAllOffers() {
		
		try {
			
			TypedQuery<Tim7Offer> tq = JPAUtil.getEntityManager().createQuery("select o from Tim7Offer o", Tim7Offer.class);
			return tq.getResultList();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("FAIL");
			return new ArrayList<>();
			
		}
		
	}
	
	public Tim7Offer findOffer(int offerId) {
		
		return JPAUtil.getEntityManager().find(Tim7Offer.class, offerId);
		
	}
	
	public List<Tim7Destination> getAllDestinations(){
		try{
			TypedQuery<Tim7Destination> tq = JPAUtil.getEntityManager().createQuery("select d from Tim7Destination d", Tim7Destination.class);
			return tq.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("FAIL");
			return new ArrayList<>();
		}
	}
	
	public boolean postOffer(Tim7Offer of){
		try{
			
    		JPAUtil.getEntityManager().persist(of);
    		return true;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}

	}
}
