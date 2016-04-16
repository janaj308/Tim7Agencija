package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIM7_TRAVELEROFFERS database table.
 * 
 */
@Entity
@Table(name="TIM7_TRAVELEROFFERS")
@NamedQuery(name="Tim7Traveleroffer.findAll", query="SELECT t FROM Tim7Traveleroffer t")
public class Tim7Traveleroffer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtraveleroffer;

	//bi-directional many-to-one association to Tim7Offer
	@ManyToOne
	@JoinColumn(name="IDOFFER", referencedColumnName="IDOFFER")
	private Tim7Offer tim7Offer;

	//bi-directional many-to-one association to Tim7User
	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	public Tim7Traveleroffer() {
	}

	public int getIdtraveleroffer() {
		return this.idtraveleroffer;
	}

	public void setIdtraveleroffer(int idtraveleroffer) {
		this.idtraveleroffer = idtraveleroffer;
	}

	public Tim7Offer getTim7Offer() {
		return this.tim7Offer;
	}

	public void setTim7Offer(Tim7Offer tim7Offer) {
		this.tim7Offer = tim7Offer;
	}

	public Tim7User getTim7User() {
		return this.tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

}