package eventstickets.helpers;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessageHelper {
	public static void addMensage(String mensagem, Severity tipo) {
		FacesContext.getCurrentInstance().addMessage(mensagem, new FacesMessage(tipo, mensagem, ""));
	}
}
