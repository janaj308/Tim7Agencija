package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIM7_NOTIFICATION database table.
 * 
 */
@Entity
@Table(name="TIM7_NOTIFICATION")
@NamedQuery(name="Tim7Notification.findAll", query="SELECT t FROM Tim7Notification t")
public class Tim7Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idnotification;

	@ManyToOne
	@JoinColumn(name="IDOFFER")
	private Tim7Offer tim7Offer;

	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	private String notificationmessage;

	private byte seen;

	public Tim7Notification() {
	}

	public int getIdnotification() {
		return this.idnotification;
	}

	public void setIdnotification(int idnotification) {
		this.idnotification = idnotification;
	}

	public Tim7Offer getTim7Offer() {
		return tim7Offer;
	}

	public void setTim7Offer(Tim7Offer tim7Offer) {
		this.tim7Offer = tim7Offer;
	}

	public Tim7User getTim7User() {
		return tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

	public String getNotificationmessage() {
		return this.notificationmessage;
	}

	public void setNotificationmessage(String notificationmessage) {
		this.notificationmessage = notificationmessage;
	}

	public byte getSeen() {
		return this.seen;
	}

	public void setSeen(byte seen) {
		this.seen = seen;
	}

}