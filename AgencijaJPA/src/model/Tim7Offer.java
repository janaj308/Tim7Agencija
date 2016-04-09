package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	private float price;

	@Temporal(TemporalType.DATE)
	private Date startdate;

	private String startingpoint;

	private int travelercurrentcount;

	private int travelernumber;

	//bi-directional many-to-one association to Tim7Destination
	@ManyToOne
	@JoinColumn(name="IDDESTINATION")
	private Tim7Destination tim7Destination;

	//bi-directional many-to-one association to Tim7User
	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	//bi-directional many-to-one association to Tim7Traveleroffer
	@OneToMany(mappedBy="tim7Offer")
	private List<Tim7Traveleroffer> tim7Traveleroffers;

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

	public Tim7Destination getTim7Destination() {
		return this.tim7Destination;
	}

	public void setTim7Destination(Tim7Destination tim7Destination) {
		this.tim7Destination = tim7Destination;
	}

	public Tim7User getTim7User() {
		return this.tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

	public List<Tim7Traveleroffer> getTim7Traveleroffers() {
		return this.tim7Traveleroffers;
	}

	public void setTim7Traveleroffers(List<Tim7Traveleroffer> tim7Traveleroffers) {
		this.tim7Traveleroffers = tim7Traveleroffers;
	}

	public Tim7Traveleroffer addTim7Traveleroffer(Tim7Traveleroffer tim7Traveleroffer) {
		getTim7Traveleroffers().add(tim7Traveleroffer);
		tim7Traveleroffer.setTim7Offer(this);

		return tim7Traveleroffer;
	}

	public Tim7Traveleroffer removeTim7Traveleroffer(Tim7Traveleroffer tim7Traveleroffer) {
		getTim7Traveleroffers().remove(tim7Traveleroffer);
		tim7Traveleroffer.setTim7Offer(null);

		return tim7Traveleroffer;
	}

}