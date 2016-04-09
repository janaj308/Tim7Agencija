package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Tim7Offer;

@ManagedBean
@SessionScoped
public class OffersManagedBean {

	private Tim7Offer chosenOffer;

	public String loadOffer() {
		
		//chosenOffer = new OfferManager().findOffer(chosenId);
		return "/pages/offerDetails";
		
	}

	public Tim7Offer getChosenOffer() {
		return chosenOffer;
	}

	public void setChosenOffer(Tim7Offer chosenOffer) {
		this.chosenOffer = chosenOffer;
	}
	
}
