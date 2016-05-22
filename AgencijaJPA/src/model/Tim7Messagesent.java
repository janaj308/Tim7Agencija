package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TIM7_MESSAGESENT database table.
 * 
 */
@Entity
@Table(name="TIM7_MESSAGESENT")
@NamedQuery(name="Tim7Messagesent.findAll", query="SELECT t FROM Tim7Messagesent t")
public class Tim7Messagesent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmessagesent;

	@ManyToOne
	@JoinColumn(name="IDUSERRECEIVER")
	private Tim7User userReceiver;
	
	@ManyToOne
	@JoinColumn(name="IDUSERSENDER")
	private Tim7User userSender;

	private String messagesentcontent;

	@Temporal(TemporalType.TIMESTAMP)
	private Date messagesenttime;

	public Tim7Messagesent() {
	}

	public int getIdmessagesent() {
		return this.idmessagesent;
	}

	public void setIdmessagesent(int idmessagesent) {
		this.idmessagesent = idmessagesent;
	}

	public Tim7User getUserReceiver() {
		return userReceiver;
	}

	public void setUserReceiver(Tim7User userReceiver) {
		this.userReceiver = userReceiver;
	}

	public Tim7User getUserSender() {
		return userSender;
	}

	public void setUserSender(Tim7User userSender) {
		this.userSender = userSender;
	}

	public String getMessagesentcontent() {
		return this.messagesentcontent;
	}

	public void setMessagesentcontent(String messagesentcontent) {
		this.messagesentcontent = messagesentcontent;
	}

	public Date getMessagesenttime() {
		return this.messagesenttime;
	}

	public void setMessagesenttime(Date messagesenttime) {
		this.messagesenttime = messagesenttime;
	}

}