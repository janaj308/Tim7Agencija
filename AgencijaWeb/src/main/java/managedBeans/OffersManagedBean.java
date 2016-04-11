package managedBeans;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import managers.OfferManager;
import model.Tim7Offer;
import model.Tim7User;

@ManagedBean
@SessionScoped
public class OffersManagedBean {

	private Tim7Offer chosenOffer;
	private Tim7Offer offer;
	private OfferManager OM;
	private String feedback;
	@ManagedProperty(value = "#{UserLogInManagedBean}")
	UserLogInManagedBean loginManagedBean;
	private Tim7User loggedUser;
	private int priceLow=0;
	private int priceHigh=0;
	private String destinationname;
	private String startingPoint;
	private List<Tim7Offer> list;
	
	public OffersManagedBean() {
		
		offer = new Tim7Offer();
		OM = new OfferManager();
		feedback = "";
	
	}
	
	@PostConstruct
	private void init() {
		
		loggedUser = loginManagedBean.getUser();
		
	}

	public void postOffer() {
		
		offer.setTim7User(loggedUser);
		boolean posted = OM.postOffer(offer);
		
		if (posted)
			feedback = "Offer is posted.";
		else
			feedback="Offer is not posted. Try again!";
	}

	public void searchOffers(){
		
		list=OM.searchOff(destinationname,startingPoint,priceLow,priceHigh);
		
	}
	

	public String loadOffer() {

		return "/pages/offerDetails";

	}
	
	public String loadOfferBrowse() {
		
		return "/pages/viewOffers";
		
	}
	
	public String loadOfferPost() {
		
		return "/pages/postOffer";
		
	}

	public Tim7Offer getChosenOffer() {
		return chosenOffer;
	}

	public void setChosenOffer(Tim7Offer chosenOffer) {
		this.chosenOffer = chosenOffer;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Tim7Offer getOffer() {
		return offer;
	}

	public void setOffer(Tim7Offer offer) {
		this.offer = offer;
	}

	public UserLogInManagedBean getLoginManagedBean() {
		return loginManagedBean;
	}

	public void setLoginManagedBean(UserLogInManagedBean loginManagedBean) {
		this.loginManagedBean = loginManagedBean;
	}
	
}
