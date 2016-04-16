package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TIM7_OFFER database table.
 * 
 */
@Entity
@Table(name="TIM7_OFFER")
@NamedQuery(name="Tim7Offer.findAll", query="SELECT t FROM Tim7Offer t")
public class Tim7Offer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idoffer;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date enddate;

	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	@ManyToOne
	@JoinColumn(name="IDDESTINATION")
	private Tim7Destination tim7Destination;
	
	private float price;

	@Temporal(TemporalType.DATE)
	private Date startdate;

	private String startingpoint;

	private int travelercurrentcount;

	private int travelernumber;

	public Tim7Offer() {
	}

	public int getIdoffer() {
		return this.idoffer;
	}

	public void setIdoffer(int idoffer) {
		this.idoffer = idoffer;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Tim7Destination getTim7Destination() {
		return tim7Destination;
	}

	public void setTim7Destination(Tim7Destination tim7Destination) {
		this.tim7Destination = tim7Destination;
	}

	public Tim7User getTim7User() {
		return tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getStartingpoint() {
		return this.startingpoint;
	}

	public void setStartingpoint(String startingpoint) {
		this.startingpoint = startingpoint;
	}

	public int getTravelercurrentcount() {
		return this.travelercurrentcount;
	}

	public void setTravelercurrentcount(int travelercurrentcount) {
		this.travelercurrentcount = travelercurrentcount;
	}

	public int getTravelernumber() {
		return this.travelernumber;
	}

	public void setTravelernumber(int travelernumber) {
		this.travelernumber = travelernumber;
	}

}