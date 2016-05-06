package managedBeans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import managers.OfferManager;
import managers.UserManager;

@ManagedBean
@RequestScoped
public class RatingManagedBean {
	
	@ManagedProperty(value = "#{offerDetailsManagedBean}")
	private OfferDetailsManagedBean offerDetailsManagedBean;
	@ManagedProperty(value = "#{loggedUserManagedBean}")
	private LoggedUserManagedBean loggedUserManagedBean;
	private int rating;
	private String comm;
	
	public RatingManagedBean() {
		
		rating = 1;
		
	}
	
	public void rate(){
		
		UserManager um= new UserManager();
		um.setRating(offerDetailsManagedBean.getOffer(), offerDetailsManagedBean.getOffer().getTim7User(), rating, comm, loggedUserManagedBean.getUser());
	}
	
	public boolean canRate() {
		
		if (offerDetailsManagedBean.getOffer().getEnddate().after(new Date())) {
			
			return false;
			
		}
		
		OfferManager of = new OfferManager();
		return of.canBeRated(offerDetailsManagedBean.getOffer(), loggedUserManagedBean.getUser());
		
	}
	
	public OfferDetailsManagedBean getOfferDetailsManagedBean() {
		return offerDetailsManagedBean;
	}

	public void setOfferDetailsManagedBean(OfferDetailsManagedBean offerDetailsManagedBean) {
		this.offerDetailsManagedBean = offerDetailsManagedBean;
	}

	public LoggedUserManagedBean getLoggedUserManagedBean() {
		return loggedUserManagedBean;
	}

	public void setLoggedUserManagedBean(LoggedUserManagedBean loggedUserManagedBean) {
		this.loggedUserManagedBean = loggedUserManagedBean;
	}

	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	
}
