package managedBeans;

import javax.faces.bean.ManagedBean;
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

@ManagedBean (name="UserRegistrationManagedBean")
@RequestScoped
public class UserRegistrationManagedBean {
	
	private Tim7User user;
	private UserManager UM;
	private EntityManager em;
	
	private String feedback;
	
	public UserRegistrationManagedBean() {
		
		user = new Tim7User();
		UM = new UserManager();
		em = JPAUtil.getEntityManager();
		
		feedback = "";
	}
	
	public String saveUser(){
		
		Integer userID = UM.saveNewUser(em, user);
		
		if(userID == null){
			feedback = "Error during registration";		
		}else if(userID == -1){
			feedback = "Username already exists: " + user.getUsername();
		}else if(userID == -2){
			feedback = "Email already exists: " + user.getUseremail();
		}else{
			feedback = "Successful registration!";
			return "/pages/UserLogin";
		}
		return "";
	}

	public Tim7User getUser() {
		return user;
	}

	public void setUser(Tim7User user) {
		this.user = user;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}	

}
