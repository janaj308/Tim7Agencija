package model;

import java.io.Serializable;
import javax.persistence.*;


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

	private int iduserreceiver;

	private String messagesentcontent;

	//bi-directional many-to-one association to Tim7User
	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	public Tim7Messagesent() {
	}

	public int getIdmessagesent() {
		return this.idmessagesent;
	}

	public void setIdmessagesent(int idmessagesent) {
		this.idmessagesent = idmessagesent;
	}

	public int getIduserreceiver() {
		return this.iduserreceiver;
	}

	public void setIduserreceiver(int iduserreceiver) {
		this.iduserreceiver = iduserreceiver;
	}

	public String getMessagesentcontent() {
		return this.messagesentcontent;
	}

	public void setMessagesentcontent(String messagesentcontent) {
		this.messagesentcontent = messagesentcontent;
	}

	public Tim7User getTim7User() {
		return this.tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

}