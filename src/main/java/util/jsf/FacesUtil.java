/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author deividnn
 */
public class FacesUtil {

    public static void mensagem(String texto) {
        FacesContext.getCurrentInstance().addMessage("",
                new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                texto,
                texto));
    }
    
     public static void mensagemAviso(String texto) {
        FacesContext.getCurrentInstance().addMessage("",
                new FacesMessage(
                FacesMessage.SEVERITY_WARN,
                texto,
                texto));
    }
     
      public static void mensagemErro(String texto) {
        FacesContext.getCurrentInstance().addMessage("",
                new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                texto,
                texto));
    }
}
