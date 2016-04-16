package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import managers.OfferManager;
import model.Tim7Offer;
import model.Tim7User;

@ManagedBean
@SessionScoped
public class OfferDetailsManagedBean {
	
	private Tim7Offer offer;
	@ManagedProperty(value = "#{loggedUserManagedBean}")
	LoggedUserManagedBean loggedUserManagedBean;
	private Tim7User loggedUser;
	private List<Tim7User> travelers;
	private boolean fromOffer;
	
	@PostConstruct
	private void init() {
		
		loggedUser = loggedUserManagedBean.getUser();
		
	}
	
	public String loadOffer(Tim7Offer offer) {
		
		this.offer = offer;
		travelers = new OfferManager().getTravelers(offer.getIdoffer());
		return "/pages/offerDetails?faces-redirect=true";
		
	}
	
	public void acceptOffer() {
		
		offer.setTravelercurrentcount(offer.getTravelercurrentcount() - 1);
		
		OfferManager om = new OfferManager();
		om.acceptOffer(loggedUser, offer);
		
		travelers.clear();
		travelers = om.getTravelers(offer.getIdoffer());
		
	}

	public Tim7Offer getOffer() {
		return offer;
	}

	public void setOffer(Tim7Offer offer) {
		this.offer = offer;
	}

	public LoggedUserManagedBean getLoggedUserManagedBean() {
		return loggedUserManagedBean;
	}

	public void setLoggedUserManagedBean(LoggedUserManagedBean loggedUserManagedBean) {
		this.loggedUserManagedBean = loggedUserManagedBean;
	}

	public Tim7User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(Tim7User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public List<Tim7User> getTravelers() {
		return travelers;
	}

	public void setTravelers(List<Tim7User> travelers) {
		this.travelers = travelers;
	}

	public boolean isFromOffer() {
		return fromOffer;
	}

	public void setFromOffer(boolean fromOffer) {
		this.fromOffer = fromOffer;
	}
	
}
