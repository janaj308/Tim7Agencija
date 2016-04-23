package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import managers.JPAUtil;
import managers.UserManager;
import model.Tim7User;

//Crated by 
//************************
//	Istvan Pete
//************************
//Email
//************************
//	perox49@gmail.com
//************************
//Date
//************************
//	4.10.2016
//************************

@ManagedBean (name="UserLogInManagedBean")
@RequestScoped
public class UserLogInManagedBean {

	private Tim7User user;
	private UserManager UM;
	private EntityManager em;
	
	private String userName;
	private String password;
	
	private String feedback;
	
	@ManagedProperty(value="#{loggedUserManagedBean}")
	private LoggedUserManagedBean loggedUserManagedBean;
	@ManagedProperty(value="#{offerDetailsManagedBean}")
	private OfferDetailsManagedBean offerDetailsManagedBean;
	
	public UserLogInManagedBean() {
		
		UM = new UserManager();
		em = JPAUtil.getEntityManager();
		
		feedback = "";
	}
	
	public String logInUser(){
		user = UM.logInUser(em, userName, password);
		if(user == null){
			feedback = "Incorrect username / password";
			return "";
		}else{
			feedback = "Logged in: "+ user.getUserfirstname();
			loggedUserManagedBean.setUser(user);
			
			return loggedUserManagedBean.getFromPage() + "?faces-redirect=true";
		}
	}
	
	public String logOutUser() {
		
		loggedUserManagedBean.setUser(null);
		return "/pages/index?faces-redirect=true";
		
	}
	
	public String loadLogin() {
		
		return "/pages/UserLogin?faces-redirect=true";
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
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

	public OfferDetailsManagedBean getOfferDetailsManagedBean() {
		return offerDetailsManagedBean;
	}

	public void setOfferDetailsManagedBean(OfferDetailsManagedBean offerDetailsManagedBean) {
		this.offerDetailsManagedBean = offerDetailsManagedBean;
	}
	
}
