package managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import managers.OfferManager;
import model.Tim7Destination;
import model.Tim7Offer;
import model.Tim7User;

@ManagedBean
@RequestScoped
public class DataManagedBean {

	@ManagedProperty(value = "#{loggedUserManagedBean}")
	LoggedUserManagedBean loggedUserManagedBean;
	
	private List<Tim7Offer> allOffers = new OfferManager().getAllOffers();
	
	private List<Tim7Offer> recommendedOffers= new OfferManager().getOffersByAge(loggedUserManagedBean.getUser().getDateofbirth());
	
	private List<Tim7Destination> allDestinations= new OfferManager().getAllDestinations();
	
	public void reloadDestinations() {
		
		allDestinations = new OfferManager().getAllDestinations();
		
	}
	

	
	public LoggedUserManagedBean getLoggedUserManagedBean() {
		return loggedUserManagedBean;
	}



	public void setLoggedUserManagedBean(LoggedUserManagedBean loggedUserManagedBean) {
		this.loggedUserManagedBean = loggedUserManagedBean;
	}



	public List<Tim7Offer> getRecommendedOffers() {
		return recommendedOffers;
	}



	public void setRecommendedOffers(List<Tim7Offer> recommendedOffers) {
		this.recommendedOffers = recommendedOffers;
	}



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
