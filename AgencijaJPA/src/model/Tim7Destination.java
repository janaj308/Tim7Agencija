package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIM7_DESTINATION database table.
 * 
 */
@Entity
@Table(name="TIM7_DESTINATION")
@NamedQuery(name="Tim7Destination.findAll", query="SELECT t FROM Tim7Destination t")
public class Tim7Destination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iddestination;

	private String destinationname;

	//bi-directional many-to-one association to Tim7Offer
	@OneToMany(mappedBy="tim7Destination")
	private List<Tim7Offer> tim7Offers;

	//bi-directional many-to-one association to Tim7Photo
	@OneToMany(mappedBy="tim7Destination")
	private List<Tim7Photo> tim7Photos;

	public Tim7Destination() {
	}

	public int getIddestination() {
		return this.iddestination;
	}

	public void setIddestination(int iddestination) {
		this.iddestination = iddestination;
	}

	public String getDestinationname() {
		return this.destinationname;
	}

	public void setDestinationname(String destinationname) {
		this.destinationname = destinationname;
	}

	public List<Tim7Offer> getTim7Offers() {
		return this.tim7Offers;
	}

	public void setTim7Offers(List<Tim7Offer> tim7Offers) {
		this.tim7Offers = tim7Offers;
	}

	public Tim7Offer addTim7Offer(Tim7Offer tim7Offer) {
		getTim7Offers().add(tim7Offer);
		tim7Offer.setTim7Destination(this);

		return tim7Offer;
	}

	public Tim7Offer removeTim7Offer(Tim7Offer tim7Offer) {
		getTim7Offers().remove(tim7Offer);
		tim7Offer.setTim7Destination(null);

		return tim7Offer;
	}

	public List<Tim7Photo> getTim7Photos() {
		return this.tim7Photos;
	}

	public void setTim7Photos(List<Tim7Photo> tim7Photos) {
		this.tim7Photos = tim7Photos;
	}

	public Tim7Photo addTim7Photo(Tim7Photo tim7Photo) {
		getTim7Photos().add(tim7Photo);
		tim7Photo.setTim7Destination(this);

		return tim7Photo;
	}

	public Tim7Photo removeTim7Photo(Tim7Photo tim7Photo) {
		getTim7Photos().remove(tim7Photo);
		tim7Photo.setTim7Destination(null);

		return tim7Photo;
	}

}