package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIM7_DESTINATION database table.
 * 
 */
@Entity
@Table(name="TIM7_DESTINATION")
@NamedQuery(name="Tim7Destination.findAll", query="SELECT t FROM Tim7Destination t")
public class Tim7Destination implements Serializable {
	private static final long serialVersionUID = 1L;

	private String destinationname;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iddestination;

	public Tim7Destination() {
	}

	public String getDestinationname() {
		return this.destinationname;
	}

	public void setDestinationname(String destinationname) {
		this.destinationname = destinationname;
	}

	public int getIddestination() {
		return this.iddestination;
	}

	public void setIddestination(int iddestination) {
		this.iddestination = iddestination;
	}

}