package managedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import managers.OfferManager;
import managers.PhotoManager;
import managers.UserManager;
import model.Tim7Destination;
import model.Tim7Offer;
import model.Tim7Photo;
import model.Tim7User;

@ManagedBean
@SessionScoped
public class PostPhotoManagedBean {
	private UploadedFile file;
	private Tim7Destination destin;
	private List<Tim7Destination> allDestinations;
	private Tim7Photo photo;
	private PhotoManager pm;
	private UserManager um;

	public PostPhotoManagedBean() {
		OfferManager om = new OfferManager();
		pm=new PhotoManager();
		um=new UserManager();
		file = null;
		allDestinations=om.getAllDestinations();
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			byte[] image=handleFileUpload(file);
			photo.setTim7Destination(destin);
			photo.setTim7User(um.getOneUser());
			photo.setPhotoinfo(image);
			
			pm.savePhoto(photo);
		} else {
			FacesMessage message = new FacesMessage("Not succesful, file does not exist.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public byte[] handleFileUpload(UploadedFile file) {
	      byte[] image=file.getContents();
	      return image;
	   }

	public Tim7Destination getDestin() {
		return destin;
	}

	public void setDestin(Tim7Destination destin) {
		this.destin = destin;
	}

	public List<Tim7Destination> getAllDestinations() {
		return allDestinations;
	}

	public void setAllDestinations(List<Tim7Destination> allDestinations) {
		this.allDestinations = allDestinations;
	}

	

}
