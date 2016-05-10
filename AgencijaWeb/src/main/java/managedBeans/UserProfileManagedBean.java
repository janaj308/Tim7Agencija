package managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import managers.DestinationManager;
import managers.UserManager;
import model.Tim7Comment;
import model.Tim7Destination;
import model.Tim7Offer;
import model.Tim7User;

@ManagedBean (name="userProfileManagedBean")
@SessionScoped
public class UserProfileManagedBean {

	private Tim7User user;
	@ManagedProperty(value = "#{loggedUserManagedBean}")
	private LoggedUserManagedBean loggedUserManagedBean;
	private List<Tim7Comment> comments;
	private List<Tim7Offer> finishedOffers;
	private List<Tim7Destination> visitedDestinations;
	
public String loadProfile(Tim7User user) {
		
		this.user = user;
		UserManager um = new UserManager();
		DestinationManager dm= new DestinationManager();
		
		if (loggedUserManagedBean.getUser().getIduser() == this.user.getIduser()) {
			
			finishedOffers = um.getFinishedAcceptedOffers(this.user);
			visitedDestinations= dm.getVisitedDestinations(this.user);
		}
		else {
			
			finishedOffers = new ArrayList<>();
			visitedDestinations= new ArrayList<>();
		}
		comments = um.getCommentsForUser(user.getIduser());
		
		return "/pages/userProfile?faces-redirect=true";
		
	}
	
	public List<Tim7Destination> getVisitedDestinations() {
	return visitedDestinations;
}

public void setVisitedDestinations(List<Tim7Destination> visitedDestinations) {
	this.visitedDestinations = visitedDestinations;
}

	public List<Tim7Comment> getComments() {
		return comments;
	}

	public void setComments(List<Tim7Comment> comments) {
		this.comments = comments;
	}

	public List<Tim7Offer> getFinishedOffers() {
		return finishedOffers;
	}

	public void setFinishedOffers(List<Tim7Offer> finishedOffers) {
		this.finishedOffers = finishedOffers;
	}

	public Tim7User getUser() {
		return user;
	}

	public void setUser(Tim7User user) {
		this.user = user;
	}

	public LoggedUserManagedBean getLoggedUserManagedBean() {
		return loggedUserManagedBean;
	}

	public void setLoggedUserManagedBean(LoggedUserManagedBean loggedUserManagedBean) {
		this.loggedUserManagedBean = loggedUserManagedBean;
	}
	
}
