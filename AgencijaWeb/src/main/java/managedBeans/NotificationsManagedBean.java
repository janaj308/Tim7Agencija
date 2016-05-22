package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import managers.MessagingManager;
import managers.UserManager;
import model.Tim7Messagereceived;
import model.Tim7Notification;

@ManagedBean
@RequestScoped
public class NotificationsManagedBean {

	private List<Tim7Notification> newNotifications;
	private List<Tim7Notification> allNotifications;
	private List<Tim7Messagereceived> newMessages;
	@ManagedProperty(value="#{loggedUserManagedBean}")
	private LoggedUserManagedBean loggedUserManagedBean;
	
	@PostConstruct
	public void init() {
		
		UserManager um = new UserManager();
		newNotifications = um.getNewNotifications(loggedUserManagedBean.getUser());
		allNotifications = um.getAllNotifications(loggedUserManagedBean.getUser());
		newMessages = new MessagingManager().getNewReceivedMessages(loggedUserManagedBean.getUser());
		
	}
	
	public void checkAsSeen() {
		
		if (!newNotifications.isEmpty())
			new UserManager().checkAsSeen(newNotifications);
		
	}
	
	public boolean isSeen(Tim7Notification n) {
		
		return n.getSeen() == (short) 1;
		
	}

	public List<Tim7Notification> getNewNotifications() {
		return newNotifications;
	}

	public void setNewNotifications(List<Tim7Notification> newNotifications) {
		this.newNotifications = newNotifications;
	}

	public List<Tim7Notification> getAllNotifications() {
		return allNotifications;
	}

	public void setAllNotifications(List<Tim7Notification> allNotifications) {
		this.allNotifications = allNotifications;
	}

	public LoggedUserManagedBean getLoggedUserManagedBean() {
		return loggedUserManagedBean;
	}

	public void setLoggedUserManagedBean(LoggedUserManagedBean loggedUserManagedBean) {
		this.loggedUserManagedBean = loggedUserManagedBean;
	}

	public List<Tim7Messagereceived> getNewMessages() {
		return newMessages;
	}

	public void setNewMessages(List<Tim7Messagereceived> newMessages) {
		this.newMessages = newMessages;
	}
	
}
