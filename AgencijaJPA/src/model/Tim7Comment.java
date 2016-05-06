package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIM7_COMMENT database table.
 * 
 */
@Entity
@Table(name="TIM7_COMMENT")
@NamedQuery(name="Tim7Comment.findAll", query="SELECT t FROM Tim7Comment t")
public class Tim7Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcomment;

	private String commentcontent;

	@ManyToOne
	@JoinColumn(name="IDUSERTO")
	private Tim7User userto;
	
	@ManyToOne
	@JoinColumn(name="IDUSERBY")
	private Tim7User userby;

	//bi-directional many-to-one association to Tim7Offer
	@ManyToOne
	@JoinColumn(name="IDOFFER")
	private Tim7Offer tim7Offer;

	public Tim7Comment() {
	}

	public int getIdcomment() {
		return this.idcomment;
	}

	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
	}

	public String getCommentcontent() {
		return this.commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	public Tim7User getUserto() {
		return userto;
	}

	public void setUserto(Tim7User userto) {
		this.userto = userto;
	}

	public Tim7User getUserby() {
		return userby;
	}

	public void setUserby(Tim7User userby) {
		this.userby = userby;
	}

	public Tim7Offer getTim7Offer() {
		return this.tim7Offer;
	}

	public void setTim7Offer(Tim7Offer tim7Offer) {
		this.tim7Offer = tim7Offer;
	}

}