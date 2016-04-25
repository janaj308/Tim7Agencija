package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import managers.OfferManager;
import model.Tim7Destination;
import model.Tim7Offer;

@ManagedBean
@RequestScoped
public class DataManagedBean {

	@ManagedProperty(value = "#{loggedUserManagedBean}")
	LoggedUserManagedBean loggedUserManagedBean;
	
	private List<Tim7Offer> allOffers;
	
	private List<Tim7Offer> recommendedOffers;
	
	private List<Tim7Destination> allDestinations;
	
	public void reloadDestinations() {
		
		allDestinations = new OfferManager().getAllDestinations();
		
	}
	
	@PostConstruct
	public void post() {
		
		OfferManager om = new OfferManager();
		allOffers = om.getAllOffers();
		recommendedOffers = om.getOffersByAge(loggedUserManagedBean.getUser().getDateofbirth());
		allDestinations = om.getAllDestinations();
		
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
