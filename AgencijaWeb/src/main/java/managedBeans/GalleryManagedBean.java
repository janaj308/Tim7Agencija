package managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mysql.fabric.xmlrpc.base.Array;

import model.Tim7Destination;
import model.Tim7Photo;

@ManagedBean
@SessionScoped
public class GalleryManagedBean {
	private Tim7Destination destin;
	private List<Tim7Photo> photo=new ArrayList<Tim7Photo>();
	
	
}
