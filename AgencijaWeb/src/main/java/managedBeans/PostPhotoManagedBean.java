package managedBeans;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import managers.OfferManager;
import managers.PhotoManager;
import model.Tim7Destination;
import model.Tim7Photo;

@ManagedBean
@SessionScoped
public class PostPhotoManagedBean {
	private UploadedFile file;
	private Tim7Destination destin;
	private List<Tim7Destination> allDestinations;
	private List<Tim7Photo> photos;
	private Map<Integer, Tim7Photo> photoMap;
	private Tim7Photo photo;
	private PhotoManager pm;
	@ManagedProperty(value="#{loggedUserManagedBean}")
	private LoggedUserManagedBean loggedUserManagedBean;
	
	@PostConstruct
	public void init() {
		OfferManager om = new OfferManager();
		pm=new PhotoManager();
		photo=new Tim7Photo();
		file = null;
		allDestinations=om.getAllDestinations();
		//destinationPhotos = new ArrayList<>();
	}

	public void loadPhotos(Tim7Destination d) {
		
		//GET PHOTOS FOR DESTINATION
		//destinationPhotos = new ArrayList<>();
		photos = pm.getPhotosForDestination(d);
		
		photoMap = new HashMap<>();
        for (Tim7Photo p : photos) {
        	photoMap.put(p.getIdphoto(), p);
        }
		
	}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			byte[] image=handleFileUpload(file);
			photo.setTim7User(loggedUserManagedBean.getUser());
			photo.setPhotoinfo(image);
			
			pm.savePhoto(photo);
		} else {
			//as
		}
	}
	
	public byte[] handleFileUpload(UploadedFile file) {
		
      byte[] image=file.getContents();
      return image;
      
   }

    public StreamedContent getDynamicPhoto() {
        String imageId = FacesContext.getCurrentInstance()
                                     .getExternalContext()
                                     .getRequestParameterMap()
                                     .get("image_id");
        if (imageId == null) {
            return getStreamedImage(photos.get(0));
        }
        return getStreamedImage(photoMap.get(Integer.parseInt(imageId)));
    }
	
    private StreamedContent getStreamedImage(Tim7Photo photo) {
        StreamedContent streamedContent = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(photo.getPhotoinfo());
            streamedContent = new DefaultStreamedContent(inputStream, "image/png");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return streamedContent;
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

	public List<Tim7Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Tim7Photo> photos) {
		this.photos = photos;
	}

	public Tim7Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Tim7Photo photo) {
		this.photo = photo;
	}

	public LoggedUserManagedBean getLoggedUserManagedBean() {
		return loggedUserManagedBean;
	}

	public void setLoggedUserManagedBean(LoggedUserManagedBean loggedUserManagedBean) {
		this.loggedUserManagedBean = loggedUserManagedBean;
	}

}
