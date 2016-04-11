package managedBeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
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
@ApplicationScoped
public class UserLogInManagedBean {

	private Tim7User user;
	private UserManager UM;
	private EntityManager em;
	
	private String userName;
	private String password;
	
	private String feedback;
	
	public UserLogInManagedBean() {
		
		UM = new UserManager();
		em = JPAUtil.getEntityManager();
		
		feedback = "";
	}
	
	public String logInUser(){
		user = UM.logInUser(em, userName, password);
		if(user == null){
			feedback = "Pogreasan username / password";
			return "";
		}else{
			feedback = "Uspesno je ulogovan "+ user.getUserfirstname();
			return "index";
		}
	}
	
	public String loadLogin() {
		
		return "/pages/UserLogin";
		
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
	
}
