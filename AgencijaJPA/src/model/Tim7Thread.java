package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


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

	private int iduser;

	private Timestamp threadlastposted;

	private String threadname;

	private int threadnumofview;

	private Timestamp threadposted;

	public Tim7Thread() {
	}

	public int getIdthread() {
		return this.idthread;
	}

	public void setIdthread(int idthread) {
		this.idthread = idthread;
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
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

}