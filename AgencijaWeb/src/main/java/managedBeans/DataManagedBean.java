package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import managers.MessagingManager;
import managers.OfferManager;
import model.Tim7Destination;
import model.Tim7Messagereceived;
import model.Tim7Messagesent;
import model.Tim7Offer;

@ManagedBean
@RequestScoped
public class DataManagedBean {

	@ManagedProperty(value = "#{loggedUserManagedBean}")
	LoggedUserManagedBean loggedUserManagedBean;
	
	private List<Tim7Offer> allOffers;
	
	private List<Tim7Offer> recommendedOffers;
	
	private List<Tim7Destination> allDestinations;
	
	private List<Tim7Messagesent> allMessageSent;
	
	private List<Tim7Messagereceived> allMessageRecived;
	
	public void reloadDestinations() {
		
		allDestinations = new OfferManager().getAllDestinations();
		
	}
	
	public void reloadSentMessages(){
		allMessageSent = new MessagingManager().getAllUserSentMessages(loggedUserManagedBean.getUser().getIduser());
	}
	public void reloadRecivedMessages(){
		allMessageRecived = new MessagingManager().getAllUserRecivedMessages(loggedUserManagedBean.getUser().getIduser());
	}
	
	@PostConstruct
	public void post() {
		
		OfferManager om = new OfferManager();
		MessagingManager mm = new MessagingManager();
		
		allOffers = om.getAllOffers();		
		allDestinations = om.getAllDestinations();
		try{
			recommendedOffers = om.getOffersByAge(loggedUserManagedBean.getUser().getDateofbirth());
			allMessageSent = mm.getAllUserSentMessages(loggedUserManagedBean.getUser().getIduser());
			allMessageRecived = mm.getAllUserRecivedMessages(loggedUserManagedBean.getUser().getIduser());
		}catch(Exception e){}
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

	public List<Tim7Messagesent> getAllMessageSent() {
		return allMessageSent;
	}

	public void setAllMessageSent(List<Tim7Messagesent> allMessageSent) {
		this.allMessageSent = allMessageSent;
	}

	public List<Tim7Messagereceived> getAllMessageRecived() {
		return allMessageRecived;
	}

	public void setAllMessageRecived(List<Tim7Messagereceived> allMessageRecived) {
		this.allMessageRecived = allMessageRecived;
	}
	
	
}
