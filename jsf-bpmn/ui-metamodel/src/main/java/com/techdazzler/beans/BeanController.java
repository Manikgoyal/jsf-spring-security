package com.techdazzler.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

@ManagedBean(name="beanController")
@ViewScoped
public class BeanController implements Serializable,PhaseListener {

	private static final long serialVersionUID = 3973801993975443027L;

	private String username;
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public String authenticateUser() throws ServletException,IOException{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest)context.getRequest()).getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward((ServletRequest)context.getRequest(), (ServletResponse)context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
		return null;
	}
	@Override
	public void afterPhase(PhaseEvent arg0) {
		
		
	}
	@Override
	public void beforePhase(PhaseEvent arg0) {
		System.out.println("Hi Manik ");
		Exception e =(Exception)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);
		if (e instanceof BadCredentialsException) {
        	System.out.println("kidaa fer");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
            		WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Username or Password is not valid.","Username or Password is not valid."));
        }
		
	}
	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.RENDER_RESPONSE;
	}
	
	
	
	
	
}