package managedBeans;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import managers.ForumManager;
import model.Tim7Offer;
import model.Tim7Post;
import model.Tim7Thread;

@ManagedBean(name = "forumManagedBean")
@ApplicationScoped
public class ForumManagedBean {

	private Tim7Post post;
	@ManagedProperty(value = "#{loggedUserManagedBean}")
	LoggedUserManagedBean loggedUserManagedBean;
	private ForumManager fm;
	private String feedbackP;
	private List<Tim7Thread> threads;
	private List<Tim7Post> posts;
	private int threadId;
	
	public ForumManagedBean() {
		post= new Tim7Post();
		threads= new ForumManager().getAllThreads();
	}
	
	public void postOffer() {

		post.setTim7User(loggedUserManagedBean.getUser());
		boolean posted = fm.saveNewPost(post);

		if (posted) {
			feedbackP = "Post was successfully added";
			post = new Tim7Post();
		} else
			feedbackP = "There was an error while adding the post. Try again!";
	}
	
	public void loadPostsForThread(){
		posts= new ForumManager().getAllPostsForThread(threadId);
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

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
		
	

}
