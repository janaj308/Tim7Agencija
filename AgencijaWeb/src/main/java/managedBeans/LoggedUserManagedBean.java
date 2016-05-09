package managedBeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Tim7User;

@ManagedBean (name="loggedUserManagedBean")
@ApplicationScoped
public class LoggedUserManagedBean {

	private Tim7User user;
	private Tim7User tempUser;
	private String fromPage;

	public Tim7User getUser() {
		return user;
	}

	public void setUser(Tim7User user) {
		this.user = user;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public Tim7User getTempUser() {
		return tempUser;
	}

	public void setTempUser(Tim7User tempUser) {
		this.tempUser = tempUser;
	}
	
}
