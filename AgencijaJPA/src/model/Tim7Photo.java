package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIM7_PHOTO database table.
 * 
 */
@Entity
@Table(name="TIM7_PHOTO")
@NamedQuery(name="Tim7Photo.findAll", query="SELECT t FROM Tim7Photo t")
public class Tim7Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idphoto;

	private byte[] photoinfo;

	private String photoname;

	//bi-directional many-to-one association to Tim7Destination
	@ManyToOne
	@JoinColumn(name="IDDESTINATION", referencedColumnName="IDDESTINATION")
	private Tim7Destination tim7Destination;

	//bi-directional many-to-one association to Tim7User
	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Tim7User tim7User;

	public Tim7Photo() {
	}

	public int getIdphoto() {
		return this.idphoto;
	}

	public void setIdphoto(int idphoto) {
		this.idphoto = idphoto;
	}

	public byte[] getPhotoinfo() {
		return this.photoinfo;
	}

	public void setPhotoinfo(byte[] photoinfo) {
		this.photoinfo = photoinfo;
	}

	public String getPhotoname() {
		return this.photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public Tim7Destination getTim7Destination() {
		return this.tim7Destination;
	}

	public void setTim7Destination(Tim7Destination tim7Destination) {
		this.tim7Destination = tim7Destination;
	}

	public Tim7User getTim7User() {
		return this.tim7User;
	}

	public void setTim7User(Tim7User tim7User) {
		this.tim7User = tim7User;
	}

}