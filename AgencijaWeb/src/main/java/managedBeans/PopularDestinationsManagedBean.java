package managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import managers.DestinationManager;
import model.Tim7Destination;

@ManagedBean
@SessionScoped
public class PopularDestinationsManagedBean {
	
	List<Tim7Destination> list;
	
	public PopularDestinationsManagedBean() {
		DestinationManager d=new DestinationManager();
		list=d.getPopularDestinations();
	}

	public List<Tim7Destination> getList() {
		return list;
	}

	public void setList(List<Tim7Destination> list) {
		this.list = list;
	}
}
