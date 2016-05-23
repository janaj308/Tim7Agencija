package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import managers.OfferManager;
import model.Tim7Destination;
import model.Tim7Messagereceived;
import model.Tim7Messagesent;
import model.Tim7Offer;
import model.Tim7User;

@ManagedBean
@RequestScoped
public class DataManagedBean {

	@ManagedProperty(value = "#{loggedUserManagedBean}")
	LoggedUserManagedBean loggedUserManagedBean;
	private List<Tim7Offer> allActiveOffers;
	
	private List<Tim7Offer> recommendedOffers;
	
	private List<Tim7Destination> allDestinations;
	
	private List<Tim7Messagesent> allMessageSent;
	private List<Tim7User> allUsersForSentMessages;
	
	private List<Tim7Messagereceived> allMessageRecived;
	private List<Tim7User> allUsersForRecivedMessages;
	
	public void reloadDestinations() {
		
		allDestinations = new OfferManager().getAllDestinations();
		
	}
	
	@PostConstruct
	public void post() {
		
		OfferManager om = new OfferManager();		
		
		allActiveOffers = om.getAllActiveOffers();		
		allDestinations = om.getAllDestinations();
		try{
			recommendedOffers = om.getOffersByAge(loggedUserManagedBean.getUser().getDateofbirth(), loggedUserManagedBean.getUser().getIduser());
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

	public List<Tim7Offer> getAllActiveOffers() {
		return allActiveOffers;
	}

	public void setAllActiveOffers(List<Tim7Offer> allActiveOffers) {
		this.allActiveOffers = allActiveOffers;
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

	public List<Tim7User> getAllUsersForSentMessages() {
		return allUsersForSentMessages;
	}

	public void setAllUsersForSentMessages(List<Tim7User> allUsersForSentMessages) {
		this.allUsersForSentMessages = allUsersForSentMessages;
	}

	public List<Tim7User> getAllUsersForRecivedMessages() {
		return allUsersForRecivedMessages;
	}

	public void setAllUsersForRecivedMessages(List<Tim7User> allUsersForRecivedMessages) {
		this.allUsersForRecivedMessages = allUsersForRecivedMessages;
	}
	
}
