<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update</title>
</head>
<body>
<%@ page import = "java.sql.*" %>
  <%
  	String licencse = (String)session.getAttribute("licencse");	
      Connection conn = DriverManager.getConnection(
          "jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "system"); // <== Check!
      // Connection conn =
      //    DriverManager.getConnection("jdbc:odbc:eshopODBC");  // Access
      String sqlStr = "SELECT * FROM vehicle_info v left join vehicle_user_details vd on v.vehicle_number=vd.vehicle_number where vd.LICENSE=? ";
      PreparedStatement stmt = conn.prepareStatement(sqlStr);
      stmt.setString(1,licencse);
      ResultSet rset = stmt.executeQuery();
  %>

<div class="main">

<h2>Vehicle Register</h2>
<form action="Register" method="POST">

			<div id="textbox">
			Vehicle Number: <input name="vehiclenumber" type="text" value=<%= rset.getString("vehicle_number") %> /></br>
			</div>
            <div id="textbox">
			Vehicle Model: <input name ="model" type="text" /></br>
			</div>
			<div id="textbox">
            Vehicle Type:
			<input type="radio" name="type" value="Commercial" checked> Commercial
            <input type="radio" name="type" value="Personal"> Personal<br>
			</div>
			<div id="textbox">
			License Number: <input name="license" type="text" /></br>
			</div>
            <div id="textbox">
			Password: <input name="password" type="password" /></br>
			</div>
			<div id="textbox">
			Email: <input name="email" type="text" /></br>
			</div>
           
            <div id="textbox">
            Date of Purchase:
			<input type="text" name="day" size="2"/>
            <select name="month">
            <option value="Select birth month">Month</option>
 		 	<option value="January">January</option>
 		 	<option value="February">February</option>
  			<option value="March">March</option>
  			<option value="April">April</option>
  			<option value="May">May</option>
 		 	<option value="June">June</option>
  			<option value="July">July</option>
  			<option value="August">August</option>
  			<option value="September">September</option>
 		 	<option value="October">October</option>
  			<option value="November">November</option>
  			<option value="December">December</option>
  			
		</select>
<input type="text"  name="year"  size="4"/><br/>
</div>
<div id="textbox">

Country: <input type="text"  name="country"/><br/>
</div>
 <input type="submit" value="Register"/>


            
</form>
	
</div>
</body>
</html>