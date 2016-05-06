package managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import managers.UserManager;
import model.Tim7Comment;
import model.Tim7User;

@ManagedBean
@SessionScoped
public class UserProfileManagedBean {

	private Tim7User user;
	@ManagedProperty(value = "#{loggedUserManagedBean}")
	LoggedUserManagedBean loggedUserManagedBean;
	private List<Tim7Comment> comments;
	
	public String loadProfile(Tim7User user) {
		
		this.user = user;
		comments = new UserManager().getCommentsForUser(user.getIduser());
		
		return "/pages/userProfile?faces-redirect=true";
		
	}
	
	public List<Tim7Comment> getComments() {
		return comments;
	}

	public void setComments(List<Tim7Comment> comments) {
		this.comments = comments;
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
