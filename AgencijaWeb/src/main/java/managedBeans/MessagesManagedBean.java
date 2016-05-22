package managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import managers.MessagingManager;
import model.Tim7Messagereceived;
import model.Tim7Messagesent;
import model.Tim7User;

@ManagedBean
@SessionScoped
public class MessagesManagedBean {
	
	private MessagingManager MM;
	
	private Tim7Messagesent newSentM;	
	private Tim7Messagereceived newReceM;
	
	private List<Tim7Messagereceived> receivedMessages;
	private List<Tim7Messagesent> sentMessages;
	private List<Tim7User> contacts;
	private Tim7User selectedContact;
	private List<Message> allMessages;
	
	@ManagedProperty(value="#{loggedUserManagedBean}")
	private LoggedUserManagedBean loggedUserManagedBean;
	
	@ManagedProperty(value="#{userProfileManagedBean}")
	private UserProfileManagedBean userProfileManagedBean;
	
	private String content;
	
	boolean ok;
	String feedback;
	
	public MessagesManagedBean(){
		
		MM = new MessagingManager();
		content="";
		feedback="";
	}
	
	@PostConstruct
	public void post() {
		
		newSentM = new Tim7Messagesent();
		newReceM = new Tim7Messagereceived();
		contacts = MM.getAllContacts(loggedUserManagedBean.getUser().getIduser());
		allMessages = new ArrayList<>();
		
	}
	
	public void createNewMessage(Tim7User receiver){		
		
		Date currentDateTime = new Date();
		
		newSentM.setUserSender(loggedUserManagedBean.getUser());
		newSentM.setUserReceiver(receiver);
		newSentM.setMessagesentcontent(content);
		newSentM.setMessagesenttime(currentDateTime);
		
		newReceM.setUserReceiver(receiver);
		newReceM.setUserSender(loggedUserManagedBean.getUser());
		newReceM.setMessagereceivedcontent(content);
		newReceM.setMessagereceivedtime(currentDateTime);
		
		ok = MM.createNewMessage(newSentM, newReceM);
		if(ok){
			feedback="Poruka uspesno poslata";			
		}else{
			feedback="Poruka NIJE poslata";
		}
		
		allMessages = new ArrayList<>();
		loadConversation(null);
		
	}
	
	public void loadConversation(SelectEvent event) {
		
		receivedMessages = MM.getAllUserReceivedMessages(loggedUserManagedBean.getUser().getIduser(), selectedContact.getIduser());
		sentMessages = MM.getAllUserSentMessages(loggedUserManagedBean.getUser().getIduser(), selectedContact.getIduser());
		
		for (Tim7Messagereceived mr : receivedMessages) {
			
			Message m = new Message(mr.getMessagereceivedcontent(), mr.getMessagereceivedtime(), true);
			allMessages.add(m);
			
		}
		
		for (Tim7Messagesent ms : sentMessages) {
			
			Message m = new Message(ms.getMessagesentcontent(), ms.getMessagesenttime(), false);
			allMessages.add(m);
			
		}
		
		if (receivedMessages.size() > 0 && sentMessages.size() > 0) {
			allMessages = mergeSort(allMessages);
			MM.checkAsSeen(receivedMessages);
		}
		
	}
	
	private List<Message> mergeSort(List<Message> whole) {
		
	    List<Message> left = new ArrayList<Message>();
	    List<Message> right = new ArrayList<Message>();
	    int center;
	 
	    if (whole.size() == 1) {    
	        return whole;
	    } else {
	    	
	        center = whole.size()/2;
	        
	        for (int i = 0; i < center; i++) {
	                left.add(whole.get(i));
	        }
	 
	        for (int i = center; i < whole.size(); i++) {
	                right.add(whole.get(i));
	        }
	 
	        left  = mergeSort(left);
	        right = mergeSort(right);
	 
	        merge(left, right, whole);
	        
	    }
	    return whole;
	    
	}
	
	private void merge(List<Message> left, List<Message> right, List<Message> whole) {
		
	    int leftIndex = 0;
	    int rightIndex = 0;
	    int wholeIndex = 0;
	 
	    while (leftIndex < left.size() && rightIndex < right.size()) {
	        if ((left.get(leftIndex).compareTo(right.get(rightIndex))) < 0) {
	            whole.set(wholeIndex, left.get(leftIndex));
	            leftIndex++;
	        } else {
	            whole.set(wholeIndex, right.get(rightIndex));
	            rightIndex++;
	        }
	        wholeIndex++;
	    }
	 
	    List<Message> rest;
	    int restIndex;
	    if (leftIndex >= left.size()) {
	    	
	        rest = right;
	        restIndex = rightIndex;
	        
	    } else {
	    	
	        rest = left;
	        restIndex = leftIndex;
	        
	    }
	 
	    for (int i = restIndex; i < rest.size(); i++) {
	        whole.set(wholeIndex, rest.get(i));
	        wholeIndex++;
	    }
	    
	}
	
	public class Message {
		
		String content;
		Date dateTime;
		boolean received;
		
		public Message(String content, Date dateTime, boolean received) {
			
			this.content = content;
			this.dateTime = dateTime;
			this.received = received;
			
		}

		public int compareTo(Message message) {
			
			if (dateTime.before(message.dateTime)) {
				return -1;
			}
			else {
				return 1;
			}
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getDateTime() {
			return dateTime;
		}

		public void setDateTime(Date dateTime) {
			this.dateTime = dateTime;
		}

		public boolean isReceived() {
			return received;
		}

		public void setReceived(boolean received) {
			this.received = received;
		}
		
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

	public UserProfileManagedBean getUserProfileManagedBean() {
		return userProfileManagedBean;
	}

	public void setUserProfileManagedBean(UserProfileManagedBean userProfileManagedBean) {
		this.userProfileManagedBean = userProfileManagedBean;
	}

	public List<Tim7Messagereceived> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<Tim7Messagereceived> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public List<Tim7Messagesent> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Tim7Messagesent> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<Tim7User> getContacts() {
		return contacts;
	}

	public void setContacts(List<Tim7User> contacts) {
		this.contacts = contacts;
	}

	public Tim7User getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(Tim7User selectedContact) {
		this.selectedContact = selectedContact;
	}

	public List<Message> getAllMessages() {
		return allMessages;
	}

	public void setAllMessages(List<Message> allMessages) {
		this.allMessages = allMessages;
	}
	
}
