package managedBeans;

import java.util.List;

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
	private List<Tim7User> travelers;
	private boolean fromOffer;
	private boolean accepted;
	
	public String loadOffer(Tim7Offer offer) {
		
		this.offer = offer;
		travelers = new OfferManager().getTravelers(offer.getIdoffer());
		
		accepted = false;
		for (Tim7User u : travelers) {
			
			if (u.getIduser() == loggedUserManagedBean.getUser().getIduser()) {
				accepted = true;
				break;
			}
			
		}
		
		return "/pages/offerDetails?faces-redirect=true";
		
	}
	
	public void acceptOffer() {
		
		offer.setTravelercurrentcount(offer.getTravelercurrentcount() + 1);
		
		OfferManager om = new OfferManager();
		om.acceptOffer(loggedUserManagedBean.getUser(), offer);
		
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

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
}
