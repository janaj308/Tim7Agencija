package managedBeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Tim7User;

@ManagedBean
@ApplicationScoped
public class LoggedUserManagedBean {

	private Tim7User user;

	public Tim7User getUser() {
		return user;
	}

	public void setUser(Tim7User user) {
		this.user = user;
	}
	
}
