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

	private int iduser;

	private String notificationmessage;

	public Tim7Notification() {
	}

	public int getIdnotification() {
		return this.idnotification;
	}

	public void setIdnotification(int idnotification) {
		this.idnotification = idnotification;
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getNotificationmessage() {
		return this.notificationmessage;
	}

	public void setNotificationmessage(String notificationmessage) {
		this.notificationmessage = notificationmessage;
	}

}