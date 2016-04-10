package managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Tim7Destination;

@FacesConverter("destinationConverter")
public class DestinationConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		
		DataManagedBean data = context.getApplication().evaluateExpressionGet(context, "#{dataManagedBean}", DataManagedBean.class);
		
		for (Tim7Destination d : data.getAllDestinations()) {
			if (String.valueOf(d.getIddestination()).equals(id)) {
				return d;
			}
		}
		
		throw new ConverterException(new FacesMessage(String.format("Cannot convert " + id + " to 'Tim7Destination'")));
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object destination) {
		
		if (destination == null) {
			return null;
		}
		
		Tim7Destination d = (Tim7Destination) destination;
		return String.valueOf(d.getIddestination());
		
	}

}

