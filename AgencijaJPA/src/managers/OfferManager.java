package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
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
			return new ArrayList<>();
			
		}
		
	}
	
	public List<Tim7Offer> getOfferForDestination(String name){
		try{
			TypedQuery<Tim7Offer> tq=JPAUtil.getEntityManager().createQuery("select * from Tim7Offer o join fetch o.tim7Destination d"+
			"d.destinationname=:destName",Tim7Offer.class);
			tq.setParameter("destName", name);
			return tq.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<Tim7Offer> getOfferForStartingPoint(String start){
		try{
			TypedQuery<Tim7Offer> tq=JPAUtil.getEntityManager().createQuery("select * from Tim7Offer o where o.startingpoint=:start",Tim7Offer.class);
			tq.setParameter("start", start);
			return tq.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<Tim7Offer> getOfferForPriceRange(float priceLow, float priceHigh){
		try{
			TypedQuery<Tim7Offer> tq=JPAUtil.getEntityManager().createQuery("select * from Tim7Offer o where o.price between :priceS and :priceF", Tim7Offer.class);
			tq.setParameter("priceS", priceLow);
			tq.setParameter("priceF", priceHigh);
			return tq.getResultList();
		}catch(Exception e){
			e.printStackTrace();
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
			return new ArrayList<>();
		}
	}
	
	public List<Tim7Offer> searchOff(String destName, String startPoint, int priceLow, int priceHigh){
		try{
			if (destName!=null && startPoint==null && priceHigh==0 && priceLow==0){
				return getOfferForDestination(destName);
			}
			if (destName==null && startPoint!=null && priceHigh==0 && priceLow==0){
				return getOfferForStartingPoint(startPoint);
			}
			if (destName==null && startPoint==null && priceHigh!=0 && priceHigh>priceLow){
				return getOfferForPriceRange(priceLow, priceHigh);
			}
			if (destName!=null && startPoint!=null && priceHigh==0 && priceLow==0){
				TypedQuery<Tim7Offer> tq=JPAUtil.getEntityManager().createQuery("select * from Tim7Offer o join fetch o.tim7Destination d"+
						"d.destinationname=:destName and o.startingpoint=:start",Tim7Offer.class);
						tq.setParameter("destName", destName);
						tq.setParameter("start", startPoint);
						return tq.getResultList();
			}
			if (destName!=null && startPoint==null && priceHigh!=0 && priceHigh>priceLow){
				TypedQuery<Tim7Offer> tq=JPAUtil.getEntityManager().createQuery("select * from Tim7Offer o join fetch o.tim7Destination d"+
						"d.destinationname=:destName and o.price between :priceL and :priceH",Tim7Offer.class);
						tq.setParameter("destName", destName);
						tq.setParameter("priceL", priceLow);
						tq.setParameter("priceH", priceHigh);
						return tq.getResultList();
			}
			if (destName==null && startPoint!=null && priceHigh!=0 && priceHigh>priceLow){
				TypedQuery<Tim7Offer> tq=JPAUtil.getEntityManager().createQuery("select * from Tim7Offer o"+
						"o.startingpoint=:start and o.price between :priceL and :priceH",Tim7Offer.class);
						tq.setParameter("start", startPoint);
						tq.setParameter("priceL", priceLow);
						tq.setParameter("priceH", priceHigh);
						return tq.getResultList();
			}
			if (destName!=null && startPoint!=null && priceHigh!=0 && priceHigh>priceLow){
				TypedQuery<Tim7Offer> tq=JPAUtil.getEntityManager().createQuery("select * from Tim7Offer o join fetch o.tim7Destination d"+
						"d.destinationname=:destName and o.startingpoint=:start and o.price between :priceL and :priceH",Tim7Offer.class);
						tq.setParameter("destName", destName);
						tq.setParameter("start", startPoint);
						tq.setParameter("priceL", priceLow);
						tq.setParameter("priceH", priceHigh);
						return tq.getResultList();
			}
			return new ArrayList<>();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public boolean postOffer(Tim7Offer of){
		try{
			EntityManager em = JPAUtil.getEntityManager();
    		em.getTransaction().begin();
    		em.persist(of);
    		em.getTransaction().commit();
    		return true;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}

	}
}
