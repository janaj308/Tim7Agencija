package managedBeans;

import javax.annotation.PostConstruct;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import managers.OfferManager;
import model.Tim7Destination;
import model.Tim7Offer;
import model.Tim7User;

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
	private Tim7User loggedUser;
	
	private float priceLow;
	private float priceHigh;
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
		dest= new Tim7Destination();
	}
	
	@PostConstruct
	private void init() {
		
		loggedUser = loggedUserManagedBean.getUser();
		
	}

	public void postOffer() {
		
		offer.setTim7User(loggedUser);
		boolean posted = OM.postOffer(offer);
		
		if (posted)
			feedbackO = "Offer is posted.";
		else
			feedbackO="Offer is not posted. Try again!";
	}
	
	public void addDestination(){
		
		boolean added= OM.addNewDestination(dest);
		
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

	public Tim7User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(Tim7User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
}
