package managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import managers.OfferManager;
import model.Tim7Offer;

@ManagedBean
@SessionScoped
public class OffersManagedBean {

	private Tim7Offer chosenOffer;
	private Tim7Offer offer;
	private OfferManager OM;
	private String feedback;
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

	public void postOffer() {
		boolean posted = OM.postOffer(offer);

		if (posted)
			feedback = "Offer is posted.";
		else
			feedback = "Offer is not posted. Try again!";
	}

	public void searchOffers(){
		list=OM.searchOff(destinationname,startingPoint,priceLow,priceHigh);
	}
	

	public String loadOffer() {

		return "/pages/offerDetails";

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

}
