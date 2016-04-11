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
	
	private float priceLow;
	private float priceHigh;
	private String destinationname;
	private String startingPoint;
	private List<Tim7Offer> list;
	
	public OffersManagedBean() {
		
		offer = new Tim7Offer();
		OM = new OfferManager();
		feedback = "";
		destinationname=null;
		startingPoint=null;
		priceHigh=OM.getMaxPrice();
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

	public String searchOffers(){
		
		if (destinationname.equals("")){
			destinationname=null;
		}
		if (startingPoint.equals("")){
			startingPoint=null;
		}
		list = OM.searchOff(destinationname, startingPoint, priceLow, priceHigh);
		System.out.println(list.isEmpty());
		return "/pages/offerFiltered";		
	}
	

	public float getPriceLow() {
		return priceLow;
	}

	public void setPriceLow(float priceLow) {
		this.priceLow = priceLow;
	}

	public float getPriceHigh() {
		return priceHigh;
	}

	public void setPriceHigh(float priceHigh) {
		this.priceHigh = priceHigh;
	}

	public String getDestinationname() {
		return destinationname;
	}

	public void setDestinationname(String destinationname) {
		this.destinationname = destinationname;
	}

	public String getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}

	public List<Tim7Offer> getList() {
		return list;
	}

	public void setList(List<Tim7Offer> list) {
		this.list = list;
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
