package jdbc.pack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

public class LoginServlet extends HttpServlet{
 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");   
                
        String username=request.getParameter("username");  
        String password=request.getParameter("password");  
        String switchUser = request.getParameter("switch");
        
        Login loginObj = new Login();
        loginObj.username = username;
        loginObj.password = password;

        if(switchUser==null) {
        	
        	boolean login_status = loginObj.loginValidation();

	        if(login_status){
	
	        	request.getSession().setAttribute("currentuser", username);
	        	request.setAttribute("username", username);
	            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
	            rd.forward(request, response);       
	            
	        }
	
	        else{
	
	            request.setAttribute("errorMessage", "* invalid data entry");
	            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
	            rd.include(request, response);
	
	        }
	        
        }
        
        else {
        	
        	boolean login_status = loginObj.adminLoginValidation();
        	
        	if(login_status) {
        		
        		request.getSession().setAttribute("company", loginObj.company);
	        	request.setAttribute("logo", loginObj.base64Image);
	            RequestDispatcher rd = request.getRequestDispatcher("/admin_home.jsp");
	            rd.forward(request, response);    
        		
        	}
        	
        	else {
        		
        		request.setAttribute("errorMessage", "* invalid data entry");
	            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
	            rd.include(request, response);
        		
        	}
        	
        }
        
    }

}