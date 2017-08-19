package com.listner.data;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.main.data.DBManager;

/**
 * Application Lifecycle Listener implementation class DBConnectionListener
 *
 */
@WebListener
public class DBConnectionListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DBConnectionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	ServletContext context=arg0.getServletContext();
    	String user=context.getInitParameter("user");
    	String password=context.getInitParameter("password");
    	String url=context.getInitParameter("url");
    	String className=context.getInitParameter("classname");
    	//DBManager dbmanager=null;
    	try 
    	{
    		//create dbmanger object
    		DBManager dbmanager=new DBManager(user, password, url, className);
    		//dbmanager.setValues(user, password, url, className);
    		context.setAttribute("DBManager",dbmanager);
    		//HashMap activeUsers = new HashMap();
            //context.setAttribute("activeUsers", activeUsers);
		} 
    	catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
}
