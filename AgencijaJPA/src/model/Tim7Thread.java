package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the TIM7_THREAD database table.
 * 
 */
@Entity
@Table(name="TIM7_THREAD")
@NamedQuery(name="Tim7Thread.findAll", query="SELECT t FROM Tim7Thread t")
public class Tim7Thread implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idthread;

	private Timestamp threadlastposted;

	private String threadname;

	private int threadnumofview;

	private Timestamp threadposted;

	//bi-directional many-to-one association to Tim7Comment
	@OneToMany(mappedBy="tim7Thread")
	private List<Tim7Comment> tim7Comments;

	//bi-directional many-to-one association to Tim7User
	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	public Tim7Thread() {
	}

	public int getIdthread() {
		return this.idthread;
	}

	public void setIdthread(int idthread) {
		this.idthread = idthread;
	}

	public Timestamp getThreadlastposted() {
		return this.threadlastposted;
	}

	public void setThreadlastposted(Timestamp threadlastposted) {
		this.threadlastposted = threadlastposted;
	}

	public String getThreadname() {
		return this.threadname;
	}

	public void setThreadname(String threadname) {
		this.threadname = threadname;
	}

	public int getThreadnumofview() {
		return this.threadnumofview;
	}

	public void setThreadnumofview(int threadnumofview) {
		this.threadnumofview = threadnumofview;
	}

	public Timestamp getThreadposted() {
		return this.threadposted;
	}

	public void setThreadposted(Timestamp threadposted) {
		this.threadposted = threadposted;
	}

	public List<Tim7Comment> getTim7Comments() {
		return this.tim7Comments;
	}

	public void setTim7Comments(List<Tim7Comment> tim7Comments) {
		this.tim7Comments = tim7Comments;
	}

	public Tim7Comment addTim7Comment(Tim7Comment tim7Comment) {
		getTim7Comments().add(tim7Comment);
		tim7Comment.setTim7Thread(this);

		return tim7Comment;
	}

	public Tim7Comment removeTim7Comment(Tim7Comment tim7Comment) {
		getTim7Comments().remove(tim7Comment);
		tim7Comment.setTim7Thread(null);

		return tim7Comment;
	}

	public Tim7User getTim7User() {
		return this.tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

}