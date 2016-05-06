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
	LoggedUserManagedBean loggedUserManagedBean;
	
	private float priceLow;
	private float priceHigh;
	private float maxPrice;
	private String destinationname;
	private String startingPoint;
	private List<Tim7Offer> list;
	
	public OffersManagedBean() {
		
		offer = new Tim7Offer();
		OM = new OfferManager();
		feedbackO = "";
		feedbackD = "";
		destinationname=null;
		startingPoint=null;
		priceHigh=OM.getMaxPrice();
		maxPrice = priceHigh;
		dest= new Tim7Destination();
	}

	public void postOffer() {
		
		offer.setTim7User(loggedUserManagedBean.getUser());
		boolean posted = OM.postOffer(offer);
		
		if (posted) {
			feedbackO = "Offer was successfully posted";
			offer = new Tim7Offer();
		} else
			feedbackO="There was an error while posting the offer. Try again!";
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

	public String searchOffers(){
		
		if (destinationname.equals("")){
			destinationname=null;
		}
		if (startingPoint.equals("")){
			startingPoint=null;
		}
		list = OM.searchOff(destinationname, startingPoint, priceLow, priceHigh);
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

	public List<Tim7Offer> getList() {
		return list;
	}

	public void setList(List<Tim7Offer> list) {
		this.list = list;
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
	
}
