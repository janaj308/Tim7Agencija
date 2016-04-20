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

<<<<<<< HEAD
=======
	private int idthread;

>>>>>>> branch 'master' of https://github.com/janaj308/Tim7Agencija
	@ManyToOne
	@JoinColumn(name="IDUSERBY")
	private Tim7User userby;
	
	@ManyToOne
	@JoinColumn(name="IDUSERTO")
	private Tim7User userto;

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

<<<<<<< HEAD
	public Tim7User getUserby() {
		return userby;
=======
	public int getIdthread() {
		return this.idthread;
>>>>>>> branch 'master' of https://github.com/janaj308/Tim7Agencija
	}

<<<<<<< HEAD
	public void setUserby(Tim7User userby) {
		this.userby = userby;
	}

	public Tim7User getUserto() {
		return userto;
	}

	public void setUserto(Tim7User userto) {
		this.userto = userto;
=======
	public void setIdthread(int idthread) {
		this.idthread = idthread;
>>>>>>> branch 'master' of https://github.com/janaj308/Tim7Agencija
	}

	public Tim7User getUserby() {
		return userby;
	}

	public void setUserby(Tim7User userby) {
		this.userby = userby;
	}

	public Tim7User getUserto() {
		return userto;
	}

	public void setUserto(Tim7User userto) {
		this.userto = userto;
	}



}