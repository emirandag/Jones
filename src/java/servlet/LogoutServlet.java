/**
 * 
 */
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.weld.context.http.Http;
import org.omg.CORBA.PUBLIC_MEMBER;

//private static final long serialVersionIUD = 1L;

/**
 * @author iaw26540084
 *
 */
@WebServlet("/LogoutServlet/*")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	public LogoutServlet() {
		super();

	}
	


	protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsp = "/Jones"; 
		request.getSession().invalidate(); 
		request.removeAttribute("clientSession"); 
		response.sendRedirect(jsp);
	}
	
	

	protected void doPost() {
	//HttpSession session = 

}
}




