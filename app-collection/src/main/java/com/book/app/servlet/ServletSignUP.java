package com.book.app.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.book.app.business.AppServices;
import com.book.app.business.ImageService;

import entities.Image;
import entities.User;


@MultipartConfig
public class ServletSignUP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	/* referencia por inyección */
	@EJB
	private  AppServices service; 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
          
		
		String messageResponse="";
		String email= request.getParameter("email");  
		String name= request.getParameter("name");  
		   
		checkParameters(); 
		
		User user = new User();
		user.setEmail(email);
		user.setName(name); 
		
		try{
			
			service.signUpUser(user);
		
	    }catch(EJBException e){
	    	
	    	e.getCausedByException(); 
	    	if(e.getClass().isAssignableFrom(EntityExistsException.class)){
	    		//El usuario ya existe, responder adecuadamente 
	    		
	    	}else {
	    		//Erro, compruebe los datos del formulario o intentelo mas tarde
	    	}
	    	
	    	return; 
	    }
		
		
		   
		HttpHelper.saveSessionUser(request, user);
		response.sendRedirect("/ServletHome");
		
	}
	


	private void checkParameters() {
		// TODO Auto-generated method stub
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	

	
	
	private static byte[] inputStreamToByte(InputStream is){
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		try {
			while ((nRead = is.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}
			
			buffer.flush();
		} catch (IOException e) {
			
		}

		return buffer.toByteArray();
	}
	
	


}