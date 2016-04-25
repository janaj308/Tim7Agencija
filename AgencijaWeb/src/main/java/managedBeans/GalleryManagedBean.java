package managedBeans;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.mysql.fabric.xmlrpc.base.Array;

import managers.PhotoManager;
import model.Tim7Destination;
import model.Tim7Photo;

@ManagedBean
@SessionScoped
public class GalleryManagedBean {
	private Tim7Destination destin;
	private List<Tim7Photo> photos=new ArrayList<Tim7Photo>();
	private List<BufferedImage> slike=new ArrayList<>(); 
	private PhotoManager pm;
	
	@PostConstruct
	public void init(){
		destin=new Tim7Destination();
		pm=new PhotoManager();
		photos=pm.getAllPhotos();
		try {
			createArrayImage();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createArrayImage() throws FileNotFoundException, IOException{
		for (Tim7Photo p:photos){
			BufferedImage img=ImageIO.read(new ByteArrayInputStream(p.getPhotoinfo()));
			slike.add(img);
		}
	}
	
	public Tim7Destination getDestin() {
		return destin;
	}

	public void setDestin(Tim7Destination destin) {
		this.destin = destin;
	}

	public List<Tim7Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Tim7Photo> photos) {
		this.photos = photos;
	}
	
	
}
