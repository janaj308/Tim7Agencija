package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import managers.JPAUtil;
import managers.UserManager;
import model.Tim7Comment;
import model.Tim7User;

@ManagedBean
@SessionScoped
public class UserProfileManagedBean {

	private Tim7User user;
	@ManagedProperty(value = "#{loggedUserManagedBean}")
	LoggedUserManagedBean loggedUserManagedBean;
	private int rating;
	private String comm;
	private List<Tim7Comment> comments;
	
	public UserProfileManagedBean(){
		EntityManager em = JPAUtil.getEntityManager();
		user= em.find(Tim7User.class, 1);
		rating=1;
		comments= new UserManager().getCommentsForUser(user.getIduser());
	}
	
	public void rate(){
		
		UserManager um= new UserManager();
		um.setRating(user, rating, comm, loggedUserManagedBean.getUser());
	}

	
	
	public List<Tim7Comment> getComments() {
		return comments;
	}



	public void setComments(List<Tim7Comment> comments) {
		this.comments = comments;
	}



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	public Tim7User getUser() {
		return user;
	}


	public void setUser(Tim7User user) {
		this.user = user;
	}




	public String getComm() {
		return comm;
	}



	public void setComm(String comm) {
		this.comm = comm;
	}

	public LoggedUserManagedBean getLoggedUserManagedBean() {
		return loggedUserManagedBean;
	}

	public void setLoggedUserManagedBean(LoggedUserManagedBean loggedUserManagedBean) {
		this.loggedUserManagedBean = loggedUserManagedBean;
	}


	
	
	
}
