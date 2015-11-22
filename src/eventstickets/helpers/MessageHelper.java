package eventstickets.helpers;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessageHelper {
	public static void addMensage(String message, Severity type) {
		FacesContext.getCurrentInstance().addMessage(message, new FacesMessage(type, message, ""));
	}
}
