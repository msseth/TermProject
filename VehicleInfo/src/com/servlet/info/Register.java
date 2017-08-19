package com.servlet.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main.data.DBManager;

/**
 * Servlet implementation class Register
 */
@WebServlet(name="Register",urlPatterns={"/Register"})
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		
		//String vehiclename = request.getParameter("vehiclename");

		String vehiclenumber = request.getParameter("vehiclenumber");
		String model = request.getParameter("model");
		String type = request.getParameter("type");
		String license = request.getParameter("license");
		String country = request.getParameter("country");
		String userPassword = request.getParameter("password");
		String userEmail = request.getParameter("email");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String day = request.getParameter("day");
		// create connection with database
		ServletContext ctx = getServletContext();
		DBManager dbMgr = (DBManager) ctx.getAttribute("DBManager");

		Connection con = dbMgr.getConnection();
		String insrtQuery = "INSERT INTO vehicle_info(vehicle_id, vehicle_number, vehicle_model, vehicle_type) "
				+ " values (?,?,?,?)";
		String insrtuserQuery = "INSERT INTO vehicle_user_details(license, vehicle_number, password, email,dateofpurchase,country) "
				+ " values (?,?,?,?,?,?)";
		boolean success = false;
		PreparedStatement ps=null;
		PreparedStatement ps2=null;
		try {
			ps = con.prepareStatement(insrtQuery);
			ps.setString(1, vehiclenumber);
			ps.setString(2, vehiclenumber);
			ps.setString(3, model);
			ps.setString(4, type);
			//ps.setString(5, userCountry);
			success = ps.execute();
			
			
			ps2=con.prepareStatement(insrtuserQuery);
			ps2.setString(1, license);
			ps2.setString(2, vehiclenumber);
			ps2.setString(3, userPassword);
			ps2.setString(4, userEmail);
			ps2.setString(5, day+month+year);
			ps2.setString(6, country);
			ps2.executeQuery();
			
			ps.close();
			ps2.close();
			
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

		
		if(!success)
		{
			RequestDispatcher rd = ctx.getRequestDispatcher("/Login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=green> Vehicle Registred" + "please login</font>");
			rd.include(request, response);
		}
		else
		{
			RequestDispatcher rd = ctx.getRequestDispatcher("/Register.html");
			PrintWriter out = response.getWriter();
			//out.println("<font color=green> please try again</font>");
			rd.include(request, response);
		}

		//doGet(request, response);
	}

}
