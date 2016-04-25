package managedBeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import managers.JPAUtil;
import managers.MessagingManager;
import model.Tim7Messagereceived;
import model.Tim7Messagesent;
import model.Tim7User;

@ManagedBean (name="massageManagedBean")
@SessionScoped
public class MassageManagedBean {
	
	private EntityManager em;
	private MessagingManager MM;
	
	Tim7Messagesent newSentM;	
	Tim7Messagereceived newReceM;	
	
	Tim7User sender;
	Tim7User reciver;
	
	@ManagedProperty(value="#{loggedUserManagedBean}")
	private LoggedUserManagedBean loggedUserManagedBean;
	
	String content;
	
	boolean ok;
	String feedback;
	
	public MassageManagedBean(){
		
		MM = new MessagingManager();
		em = JPAUtil.getEntityManager();
		content="";
		feedback="";		
	}
	
	@PostConstruct
	public void post() {
		sender = loggedUserManagedBean.getUser();
		newSentM = new Tim7Messagesent();
		newReceM = new Tim7Messagereceived();
		
	}
	
	public void createNewMassage(){		
		
		newSentM.setIduserreceiver(reciver.getIduser());		
		newSentM.setMessagesentcontent(content);		
		newSentM.setTim7User(loggedUserManagedBean.getUser());		
		
		newReceM.setIdusersender(loggedUserManagedBean.getUser().getIduser());
		newReceM.setMessagereceivedcontent(content);
		newReceM.setTim7User(reciver);
		
		ok = MM.createNewMassage(sender, reciver, newSentM, newReceM);
		if(ok){
			feedback="Poruka uspesno poslata";
		}else{
			feedback="Poruka NIJE poslata";
		}
	}
	
	public String loadReciver(Tim7User reciver){
		System.out.println("dobio sam nesto");
		this.reciver = reciver;
		return "/pages/newMessage?faces-redirect=true";
	}
	
	public Tim7Messagesent getNewSentM() {
		return newSentM;
	}

	public void setNewSentM(Tim7Messagesent newSentM) {
		this.newSentM = newSentM;
	}

	public Tim7Messagereceived getNewReceM() {
		return newReceM;
	}

	public void setNewReceM(Tim7Messagereceived newReceM) {
		this.newReceM = newReceM;
	}

	public Tim7User getReciver() {
		return reciver;
	}

	public void setReciver(Tim7User reciver) {
		this.reciver = reciver;
	}

	public Tim7User getSender() {
		return sender;
	}

	public void setSender(Tim7User sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LoggedUserManagedBean getLoggedUserManagedBean() {
		return loggedUserManagedBean;
	}

	public void setLoggedUserManagedBean(LoggedUserManagedBean loggedUserManagedBean) {
		this.loggedUserManagedBean = loggedUserManagedBean;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	
}
