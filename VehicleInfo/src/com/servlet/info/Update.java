package com.servlet.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.main.data.DBManager;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try

	       {   
		    HttpSession session=	request.getSession(false);
		   // session.setAttribute("userName",request.getRemoteUser());
	       
	        
	        if(session==null)
	        {
	        	PrintWriter out = response.getWriter();
	        	 out.print("Please login first");  
	             request.getRequestDispatcher("Login.html").include(request, response);  
	        }

	        else
	        {
	        
	        String licencse = (String)session.getAttribute("licencse");
	        String country = request.getParameter("country");
			String userPassword = request.getParameter("password");
			String userEmail = request.getParameter("email");
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			String day = request.getParameter("day");

        
	        try

	        {   
	    		ServletContext ctx = getServletContext();
	        	DBManager dbMgr = (DBManager) ctx.getAttribute("DBManager");

	    		Connection con = dbMgr.getConnection();
	    		PreparedStatement ps=null;

	                try

	                {

	                     ps=con.prepareStatement("update VEHICLE_USER_DETAILS set email= ?,country=?,password=?,DATEOFPURCHASE=? where LICENSE=? ");

	                    try

	                    {
      
	                        ps.setString(1,userEmail);

	                        ps.setString(2,country);

	                        ps.setString(3,userPassword);

	                        ps.setString(4,day+month+year);

	                        ps.setString(5,licencse);
	                      	                        
	                        int i= ps.executeUpdate();

	                     if(i<0)

	                     {

	                    	RequestDispatcher rd = ctx.getRequestDispatcher("/error.html");
	         				PrintWriter out = response.getWriter();
	         				out.println("<font color=green> Updation failed </font>");
	         				rd.include(request, response);
	         				return;

	                     }

	                    else
	                    {
	                    	RequestDispatcher rd = ctx.getRequestDispatcher("/Update.html");
	        				PrintWriter out = response.getWriter();
	        				out.println("<font color=green> Updation done! </font> </br>");
	        				/* PreparedStatement select=con.prepareStatement("select email,country from users where user_name=?");
	        				select.setString(1, userName);
	        				ResultSet rs=select.executeQuery();
	        				while(rs!=null && rs.next()){
	        					out.println("<font > Updated Email: </font>");
	        					out.print(rs.getString("email"));
	        					out.println("</br>");
	        					out.println("<font > Updated Country: </font>");
	        					out.print(rs.getString("country"));

	        					
	        				}
	        				*/
	        				
	        				//out.print(i + userName + email + country);
	        				rd.include(request, response);
	                    	
	                    	 return;
	                    }

	                    }

	                    finally

	                    {

	                        ps.close();

	                    }

	                }

	                finally

	                {

	                   // con.close();

	                }   

	            }

	        catch(Exception e)

	        {

	            e.getMessage();

	        }

	       }
	       }

	    catch(Exception e)

	    {

	        e.getMessage();

	    }

//		doGet(request, response);
	}

}
