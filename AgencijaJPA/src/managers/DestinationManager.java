package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import model.Tim7Destination;

public class DestinationManager {

	public List<Tim7Destination> getPopularDestinations(){
		try {
			TypedQuery<Tim7Destination> tq=JPAUtil.getEntityManager().createQuery("select d from Tim7Destination where d.popularity>5",Tim7Destination.class);
			List<Tim7Destination> l=tq.getResultList();
			return tq.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Tim7Destination>();
		}
	}
	
	public void selectionSort(List<Tim7Destination> l){
		for (int i=0;i<l.size()-1;i++){
			int maxIndex=i;
			for(int j=i+1;j<l.size();j++){
				if(l.get(i).getPopularity()<l.get(j).getPopularity()){
					maxIndex=j;
				}
			}
			if (maxIndex!=i){
				Tim7Destination d=l.get(i);
				l.set(i, l.get(maxIndex));
				l.set(maxIndex,d );
			}
		}
	}
	
	
}
