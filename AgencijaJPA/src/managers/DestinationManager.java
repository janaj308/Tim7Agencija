package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import model.Tim7Destination;
import model.Tim7Offer;

public class DestinationManager {

	public List<Tim7Destination> getPopularDestinations(){
		try {
			TypedQuery<Tim7Destination> dest=JPAUtil.getEntityManager().createQuery("select d from Tim7Destination d",Tim7Destination.class);
			TypedQuery<Tim7Offer> off=JPAUtil.getEntityManager().createQuery("select o from Tim7Offer o",Tim7Offer.class);
			List<Tim7Destination> listDest=dest.getResultList();
			List<Tim7Offer> listOffer=off.getResultList();
			List<Tim7Destination> result=new ArrayList<>();
			for (Tim7Destination d:listDest){
				int counter=0;
				for(Tim7Offer o:listOffer){
					if (o.getTim7Destination().getIddestination()==d.getIddestination()){
						counter++;
					}
				}
				if (counter>2){
					result.add(d);
				}
			}
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Tim7Destination>();
		}
	}
	
	public void selectionSort(List<Tim7Destination> l){
//		for (int i=0;i<l.size()-1;i++){
//			int maxIndex=i;
//			for(int j=i+1;j<l.size();j++){
//				if(l.get(i).getPopularity()<l.get(j).getPopularity()){
//					maxIndex=j;
//				}
//			}
//			if (maxIndex!=i){
//				Tim7Destination d=l.get(i);
//				l.set(i, l.get(maxIndex));
//				l.set(maxIndex,d );
//			}
//		}
	}
	
	public static void main(String args[]){
		DestinationManager dm=new DestinationManager();
		List<Tim7Destination> d=dm.getPopularDestinations();
		for (Tim7Destination d1:d){
			System.out.println(d1.getDestinationname());
		}
	}
	
	
}
