package managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import managers.OfferManager;
import model.Tim7Destination;
import model.Tim7Offer;

@ManagedBean
@RequestScoped
public class DataManagedBean {

	private List<Tim7Offer> allOffers = new OfferManager().getAllOffers();
	
	private List<Tim7Destination> allDestinations= new OfferManager().getAllDestinations();
	

	public List<Tim7Offer> getAllOffers() {
		return allOffers;
	}

	public void setAllOffers(List<Tim7Offer> allOffers) {
		this.allOffers = allOffers;
	}

	public List<Tim7Destination> getAllDestinations() {
		return allDestinations;
	}

	public void setAllDestinations(List<Tim7Destination> allDestinations) {
		this.allDestinations = allDestinations;
	}
	
	
}
