package br.com.esig.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static String getParam(String param, FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get(param);
	}
	
	public static void enviarMensagem(String mensagemResumida, String mensagemInteira, FacesMessage.Severity severidade, FacesContext fc) {
		FacesMessage fm = new FacesMessage(severidade, mensagemResumida, mensagemInteira);
		fc.addMessage("messageOutput", fm);
	}
}
