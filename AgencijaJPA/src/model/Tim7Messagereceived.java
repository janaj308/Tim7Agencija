package model;

import java.io.Serializable;
import javax.persistence.*;


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

	private int idusersender;

	private String messagereceivedcontent;

	//bi-directional many-to-one association to Tim7User
	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	public Tim7Messagereceived() {
	}

	public int getIdmessagereceived() {
		return this.idmessagereceived;
	}

	public void setIdmessagereceived(int idmessagereceived) {
		this.idmessagereceived = idmessagereceived;
	}

	public int getIdusersender() {
		return this.idusersender;
	}

	public void setIdusersender(int idusersender) {
		this.idusersender = idusersender;
	}

	public String getMessagereceivedcontent() {
		return this.messagereceivedcontent;
	}

	public void setMessagereceivedcontent(String messagereceivedcontent) {
		this.messagereceivedcontent = messagereceivedcontent;
	}

	public Tim7User getTim7User() {
		return this.tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

}