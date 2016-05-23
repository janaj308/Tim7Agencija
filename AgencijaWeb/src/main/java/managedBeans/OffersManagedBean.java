package managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import managers.OfferManager;
import model.Tim7Destination;
import model.Tim7Offer;

@ManagedBean
@SessionScoped
public class OffersManagedBean {

	private Tim7Offer offer;
	private Tim7Destination dest;
	private OfferManager OM;
	private String feedbackO;
	private String feedbackD;
	@ManagedProperty(value = "#{loggedUserManagedBean}")
	private LoggedUserManagedBean loggedUserManagedBean;
	@ManagedProperty(value="#{offerDetailsManagedBean}")
	private OfferDetailsManagedBean offerDetailsManagedBean;
	
	private float priceLow;
	private float priceHigh;
	private float maxPrice;
	private String destinationname;
	private String startingPoint;
	private List<Tim7Offer> searchResults;
	private boolean searched;
	
	public OffersManagedBean() {
		
		offer = new Tim7Offer();
		OM = new OfferManager();
		feedbackO = "";
		feedbackD = "";
		destinationname="";
		startingPoint="";
		priceHigh=OM.getMaxPrice();
		maxPrice = priceHigh;
		dest= new Tim7Destination();
		searchResults = OM.getAllActiveOffers();
	}

	public String postOffer() {
		
		offer.setTim7User(loggedUserManagedBean.getUser());
		boolean posted = OM.postOffer(offer);
		
		if (posted) {
			feedbackO = "Offer was successfully posted";
			Tim7Offer temp = offer;
			offer = new Tim7Offer();
			
			priceHigh=OM.getMaxPrice();
			maxPrice = priceHigh;
			dest= new Tim7Destination();
			searchResults = OM.getAllActiveOffers();
			
			return offerDetailsManagedBean.loadOffer(temp);
			
		} else {
			feedbackO="There was an error while posting the offer. Try again!";
			return "";
		}
	}
	
	public void addDestination(){
		
		boolean added= OM.addNewDestination(dest);
		
		dest = new Tim7Destination();
		
		if (added){
			feedbackD = "Destination is added";
		}
		else
			feedbackD ="Destination is not added. Try again!";
		
	}

	public void searchOffers(){
		
		if (destinationname.equals("")){
			destinationname=null;
		}
		if (startingPoint.equals("")){
			startingPoint=null;
		}
		searchResults = OM.searchOff(destinationname, startingPoint, priceLow, priceHigh);
		searched = true;	
	}
	
	public void resetFilter() {
		
		destinationname = "";
		startingPoint = "";
		priceLow = 0.0f;
		priceHigh = maxPrice;
		searchResults = OM.getAllActiveOffers();
		
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

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
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

	public List<Tim7Offer> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<Tim7Offer> searchResults) {
		this.searchResults = searchResults;
	}

	public String getFeedbackO() {
		return feedbackO;
	}

	public void setFeedbackO(String feedbackO) {
		this.feedbackO = feedbackO;
	}

	public String getFeedbackD() {
		return feedbackD;
	}

	public void setFeedbackD(String feedbackD) {
		this.feedbackD = feedbackD;
	}

	public Tim7Offer getOffer() {
		return offer;
	}

	public void setOffer(Tim7Offer offer) {
		this.offer = offer;
	}

	public Tim7Destination getDest() {
		return dest;
	}

	public void setDest(Tim7Destination dest) {
		this.dest = dest;
	}

	public LoggedUserManagedBean getLoggedUserManagedBean() {
		return loggedUserManagedBean;
	}

	public void setLoggedUserManagedBean(LoggedUserManagedBean loggedUserManagedBean) {
		this.loggedUserManagedBean = loggedUserManagedBean;
	}

	public OfferDetailsManagedBean getOfferDetailsManagedBean() {
		return offerDetailsManagedBean;
	}

	public void setOfferDetailsManagedBean(OfferDetailsManagedBean offerDetailsManagedBean) {
		this.offerDetailsManagedBean = offerDetailsManagedBean;
	}

	public boolean isSearched() {
		return searched;
	}

	public void setSearched(boolean searched) {
		this.searched = searched;
	}
	
}
