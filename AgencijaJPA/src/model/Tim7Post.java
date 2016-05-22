package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TIM7_POST database table.
 * 
 */
@Entity
@Table(name="TIM7_POST")
@NamedQuery(name="Tim7Post.findAll", query="SELECT t FROM Tim7Post t")
public class Tim7Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpost;

	@ManyToOne
	@JoinColumn(name="IDTHREAD")
	private Tim7Thread tim7Thread;

	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	private String postcontent;

	@Temporal(TemporalType.TIMESTAMP)
	private Date posttime;

	public Tim7Post() {
	}

	public int getIdpost() {
		return this.idpost;
	}

	public void setIdpost(int idpost) {
		this.idpost = idpost;
	}

	public Tim7Thread getTim7Thread() {
		return tim7Thread;
	}

	public void setTim7Thread(Tim7Thread tim7Thread) {
		this.tim7Thread = tim7Thread;
	}

	public Tim7User getTim7User() {
		return tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

	public String getPostcontent() {
		return this.postcontent;
	}

	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}

	public Date getPosttime() {
		return this.posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

}