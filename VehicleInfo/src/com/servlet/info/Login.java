package com.servlet.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		String licencse = request.getParameter("license");
		String userPassword = request.getParameter("password");
		
		
		String selectQuery = "select vehicle_number from vehicle_user_details "
							+ "where license=? and password=?";
		
		ServletContext ctx = getServletContext();
		DBManager dbMgr = (DBManager) ctx.getAttribute("DBManager");

		Connection con = dbMgr.getConnection();
		PreparedStatement ps=null;
		try {
			 ps = con.prepareStatement(selectQuery);
			ps.setString(1, licencse);
			ps.setString(2, userPassword);
			ResultSet results = ps.executeQuery();
			String vehicle_number="";
			while(results!=null && results.next()){
				vehicle_number = results.getString("vehicle_number");
			}
			if(vehicle_number!=""){
				HttpSession session=request.getSession();
				if(session.isNew())
				{
					session.setAttribute("licencse", licencse);
				}
				RequestDispatcher rd = ctx.getRequestDispatcher("/Update.html");
				PrintWriter out = response.getWriter();
				out.println("<font color=green> Welcome!!! </font>");
				out.print(licencse);
				/*PreparedStatement select=con.prepareStatement("select email,country from users where user_name=?");
				select.setString(1, userName);
				ResultSet rs=select.executeQuery();
				while(rs!=null && rs.next()){
					out.println("</br>");
					out.println("<font > Email: </font>");
					out.print(rs.getString("email"));
					out.println("</br>");
					out.println("<font > Country: </font>");
					out.print(rs.getString("country")); 
				}
			*/
				
				rd.include(request, response);
			}
			
			else{
				RequestDispatcher rd = ctx.getRequestDispatcher("/Login.html");
				//PrintWriter out = response.getWriter();
				//out.println("<font color=green> User Not Found! </font>");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		//doGet(request, response);
	}

}
