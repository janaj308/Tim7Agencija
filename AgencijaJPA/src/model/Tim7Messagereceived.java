package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TIM7_MESSAGERECEIVED database table.
 * 
 */
@Entity
@Table(name="TIM7_MESSAGERECEIVED")
@NamedQuery(name="Tim7Messagereceived.findAll", query="SELECT t FROM Tim7Messagereceived t")
public class Tim7Messagereceived implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmessagereceived;

	@ManyToOne
	@JoinColumn(name="IDUSERRECEIVER")
	private Tim7User userReceiver;
	
	@ManyToOne
	@JoinColumn(name="IDUSERSENDER")
	private Tim7User userSender;

	private String messagereceivedcontent;

	@Temporal(TemporalType.TIMESTAMP)
	private Date messagereceivedtime;

	private byte messageseen;

	public Tim7Messagereceived() {
	}

	public int getIdmessagereceived() {
		return this.idmessagereceived;
	}

	public void setIdmessagereceived(int idmessagereceived) {
		this.idmessagereceived = idmessagereceived;
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

	public String getMessagereceivedcontent() {
		return this.messagereceivedcontent;
	}

	public void setMessagereceivedcontent(String messagereceivedcontent) {
		this.messagereceivedcontent = messagereceivedcontent;
	}

	public Date getMessagereceivedtime() {
		return this.messagereceivedtime;
	}

	public void setMessagereceivedtime(Date messagereceivedtime) {
		this.messagereceivedtime = messagereceivedtime;
	}

	public byte getMessageseen() {
		return this.messageseen;
	}

	public void setMessageseen(byte messageseen) {
		this.messageseen = messageseen;
	}

}