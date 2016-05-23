package managedBeans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import managers.ForumManager;
import model.Tim7Post;
import model.Tim7Thread;

@ManagedBean(name = "forumManagedBean")
@SessionScoped
public class ForumManagedBean {

	private Tim7Post post;
	@ManagedProperty(value = "#{loggedUserManagedBean}")
	LoggedUserManagedBean loggedUserManagedBean;
	private ForumManager fm;
	private String feedbackP;
	private List<Tim7Thread> threads;
	private List<Tim7Post> posts;
	private Tim7Thread selectedThread;
	private Tim7Thread newThread;
	
	public ForumManagedBean() {
		post= new Tim7Post();
		fm = new ForumManager();
		threads= fm.getAllThreads();
		newThread = new Tim7Thread();
	}
	
	public void addPost() {

		post.setTim7User(loggedUserManagedBean.getUser());
		post.setPosttime(new Date());
		post.setTim7Thread(selectedThread);
		
		boolean posted = fm.saveNewPost(post);

		if (posted) {
			feedbackP = "Post was successfully added";
			post = new Tim7Post();
			posts = new ForumManager().getAllPostsForThread(selectedThread.getIdthread());
		} else
			feedbackP = "There was an error while adding the post. Try again!";
	}
	
	public String loadPostsForThread(Tim7Thread thread){
		selectedThread = thread;
		posts = new ForumManager().getAllPostsForThread(selectedThread.getIdthread());
		
		return "/pages/posts?faces-redirect=true";
	}
	
	public void createThread() {
		
		newThread.setThreadcreatedtime(new Date());
		newThread.setTim7User(loggedUserManagedBean.getUser());
		
		fm.createThread(newThread);
		
		threads= fm.getAllThreads();
		
	}

	public List<Tim7Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Tim7Post> posts) {
		this.posts = posts;
	}

	public Tim7Post getPost() {
		return post;
	}

	public void setPost(Tim7Post post) {
		this.post = post;
	}

	public LoggedUserManagedBean getLoggedUserManagedBean() {
		return loggedUserManagedBean;
	}

	public void setLoggedUserManagedBean(LoggedUserManagedBean loggedUserManagedBean) {
		this.loggedUserManagedBean = loggedUserManagedBean;
	}

	public ForumManager getFm() {
		return fm;
	}

	public void setFm(ForumManager fm) {
		this.fm = fm;
	}

	public String getFeedbackP() {
		return feedbackP;
	}

	public void setFeedbackP(String feedbackP) {
		this.feedbackP = feedbackP;
	}

	public List<Tim7Thread> getThreads() {
		return threads;
	}

	public void setThreads(List<Tim7Thread> threads) {
		this.threads = threads;
	}

	public Tim7Thread getSelectedThread() {
		return selectedThread;
	}

	public void setSelectedThread(Tim7Thread selectedThread) {
		this.selectedThread = selectedThread;
	}

	public Tim7Thread getNewThread() {
		return newThread;
	}

	public void setNewThread(Tim7Thread newThread) {
		this.newThread = newThread;
	}
	
}
