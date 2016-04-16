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

	private String commentinfo;

	private int commentnegativerate;

	private int commentpositiverate;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcomment;

	//bi-directional many-to-one association to Tim7Thread
	@ManyToOne
	@JoinColumn(name="IDTHREAD", referencedColumnName="IDTHREAD")
	private Tim7Thread tim7Thread;

	//bi-directional many-to-one association to Tim7User
	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	public Tim7Comment() {
	}

	public String getCommentinfo() {
		return this.commentinfo;
	}

	public void setCommentinfo(String commentinfo) {
		this.commentinfo = commentinfo;
	}

	public int getCommentnegativerate() {
		return this.commentnegativerate;
	}

	public void setCommentnegativerate(int commentnegativerate) {
		this.commentnegativerate = commentnegativerate;
	}

	public int getCommentpositiverate() {
		return this.commentpositiverate;
	}

	public void setCommentpositiverate(int commentpositiverate) {
		this.commentpositiverate = commentpositiverate;
	}

	public int getIdcomment() {
		return this.idcomment;
	}

	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
	}

	public Tim7Thread getTim7Thread() {
		return this.tim7Thread;
	}

	public void setTim7Thread(Tim7Thread tim7Thread) {
		this.tim7Thread = tim7Thread;
	}

	public Tim7User getTim7User() {
		return this.tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

}