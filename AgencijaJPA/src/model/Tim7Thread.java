package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	@Temporal(TemporalType.TIMESTAMP)
	private Date threadcreatedtime;

	private String threadname;

	public Tim7Thread() {
	}

	public int getIdthread() {
		return this.idthread;
	}

	public void setIdthread(int idthread) {
		this.idthread = idthread;
	}

	public Tim7User getTim7User() {
		return tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

	public Date getThreadcreatedtime() {
		return threadcreatedtime;
	}

	public void setThreadcreatedtime(Date threadcreatedtime) {
		this.threadcreatedtime = threadcreatedtime;
	}

	public String getThreadname() {
		return this.threadname;
	}

	public void setThreadname(String threadname) {
		this.threadname = threadname;
	}

}