package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIM7_USER database table.
 * 
 */
@Entity
@Table(name="TIM7_USER")
@NamedQuery(name="Tim7User.findAll", query="SELECT t FROM Tim7User t")
public class Tim7User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iduser;

	private float rating;

	private String useremail;

	private String userfirstname;

	private String userlastname;

	private String username;

	private String userpassword;

	//bi-directional many-to-one association to Tim7Comment
	@OneToMany(mappedBy="tim7User")
	private List<Tim7Comment> tim7Comments;

	//bi-directional many-to-one association to Tim7Messagereceived
	@OneToMany(mappedBy="tim7User")
	private List<Tim7Messagereceived> tim7Messagereceiveds;

	//bi-directional many-to-one association to Tim7Messagesent
	@OneToMany(mappedBy="tim7User")
	private List<Tim7Messagesent> tim7Messagesents;

	//bi-directional many-to-one association to Tim7Offer
	@OneToMany(mappedBy="tim7User")
	private List<Tim7Offer> tim7Offers;

	//bi-directional many-to-one association to Tim7Photo
	@OneToMany(mappedBy="tim7User")
	private List<Tim7Photo> tim7Photos;

	//bi-directional many-to-one association to Tim7Thread
	@OneToMany(mappedBy="tim7User")
	private List<Tim7Thread> tim7Threads;

	//bi-directional many-to-one association to Tim7Traveleroffer
	@OneToMany(mappedBy="tim7User")
	private List<Tim7Traveleroffer> tim7Traveleroffers;

	public Tim7User() {
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getUseremail() {
		return this.useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserfirstname() {
		return this.userfirstname;
	}

	public void setUserfirstname(String userfirstname) {
		this.userfirstname = userfirstname;
	}

	public String getUserlastname() {
		return this.userlastname;
	}

	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return this.userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public List<Tim7Comment> getTim7Comments() {
		return this.tim7Comments;
	}

	public void setTim7Comments(List<Tim7Comment> tim7Comments) {
		this.tim7Comments = tim7Comments;
	}

	public Tim7Comment addTim7Comment(Tim7Comment tim7Comment) {
		getTim7Comments().add(tim7Comment);
		tim7Comment.setTim7User(this);

		return tim7Comment;
	}

	public Tim7Comment removeTim7Comment(Tim7Comment tim7Comment) {
		getTim7Comments().remove(tim7Comment);
		tim7Comment.setTim7User(null);

		return tim7Comment;
	}

	public List<Tim7Messagereceived> getTim7Messagereceiveds() {
		return this.tim7Messagereceiveds;
	}

	public void setTim7Messagereceiveds(List<Tim7Messagereceived> tim7Messagereceiveds) {
		this.tim7Messagereceiveds = tim7Messagereceiveds;
	}

	public Tim7Messagereceived addTim7Messagereceived(Tim7Messagereceived tim7Messagereceived) {
		getTim7Messagereceiveds().add(tim7Messagereceived);
		tim7Messagereceived.setTim7User(this);

		return tim7Messagereceived;
	}

	public Tim7Messagereceived removeTim7Messagereceived(Tim7Messagereceived tim7Messagereceived) {
		getTim7Messagereceiveds().remove(tim7Messagereceived);
		tim7Messagereceived.setTim7User(null);

		return tim7Messagereceived;
	}

	public List<Tim7Messagesent> getTim7Messagesents() {
		return this.tim7Messagesents;
	}

	public void setTim7Messagesents(List<Tim7Messagesent> tim7Messagesents) {
		this.tim7Messagesents = tim7Messagesents;
	}

	public Tim7Messagesent addTim7Messagesent(Tim7Messagesent tim7Messagesent) {
		getTim7Messagesents().add(tim7Messagesent);
		tim7Messagesent.setTim7User(this);

		return tim7Messagesent;
	}

	public Tim7Messagesent removeTim7Messagesent(Tim7Messagesent tim7Messagesent) {
		getTim7Messagesents().remove(tim7Messagesent);
		tim7Messagesent.setTim7User(null);

		return tim7Messagesent;
	}

	public List<Tim7Offer> getTim7Offers() {
		return this.tim7Offers;
	}

	public void setTim7Offers(List<Tim7Offer> tim7Offers) {
		this.tim7Offers = tim7Offers;
	}

	public Tim7Offer addTim7Offer(Tim7Offer tim7Offer) {
		getTim7Offers().add(tim7Offer);
		tim7Offer.setTim7User(this);

		return tim7Offer;
	}

	public Tim7Offer removeTim7Offer(Tim7Offer tim7Offer) {
		getTim7Offers().remove(tim7Offer);
		tim7Offer.setTim7User(null);

		return tim7Offer;
	}

	public List<Tim7Photo> getTim7Photos() {
		return this.tim7Photos;
	}

	public void setTim7Photos(List<Tim7Photo> tim7Photos) {
		this.tim7Photos = tim7Photos;
	}

	public Tim7Photo addTim7Photo(Tim7Photo tim7Photo) {
		getTim7Photos().add(tim7Photo);
		tim7Photo.setTim7User(this);

		return tim7Photo;
	}

	public Tim7Photo removeTim7Photo(Tim7Photo tim7Photo) {
		getTim7Photos().remove(tim7Photo);
		tim7Photo.setTim7User(null);

		return tim7Photo;
	}

	public List<Tim7Thread> getTim7Threads() {
		return this.tim7Threads;
	}

	public void setTim7Threads(List<Tim7Thread> tim7Threads) {
		this.tim7Threads = tim7Threads;
	}

	public Tim7Thread addTim7Thread(Tim7Thread tim7Thread) {
		getTim7Threads().add(tim7Thread);
		tim7Thread.setTim7User(this);

		return tim7Thread;
	}

	public Tim7Thread removeTim7Thread(Tim7Thread tim7Thread) {
		getTim7Threads().remove(tim7Thread);
		tim7Thread.setTim7User(null);

		return tim7Thread;
	}

	public List<Tim7Traveleroffer> getTim7Traveleroffers() {
		return this.tim7Traveleroffers;
	}

	public void setTim7Traveleroffers(List<Tim7Traveleroffer> tim7Traveleroffers) {
		this.tim7Traveleroffers = tim7Traveleroffers;
	}

	public Tim7Traveleroffer addTim7Traveleroffer(Tim7Traveleroffer tim7Traveleroffer) {
		getTim7Traveleroffers().add(tim7Traveleroffer);
		tim7Traveleroffer.setTim7User(this);

		return tim7Traveleroffer;
	}

	public Tim7Traveleroffer removeTim7Traveleroffer(Tim7Traveleroffer tim7Traveleroffer) {
		getTim7Traveleroffers().remove(tim7Traveleroffer);
		tim7Traveleroffer.setTim7User(null);

		return tim7Traveleroffer;
	}

}