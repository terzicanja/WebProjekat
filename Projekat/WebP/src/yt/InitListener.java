package yt;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import yt.dao.ConnectionManager;

public class InitListener implements ServletContextListener {
	
	public void contextDestroyed(ServletContextEvent arg0)  { 
        ConnectionManager.close();
    }

    public void contextInitialized(ServletContextEvent event)  { 
        ConnectionManager.open();
    }

}
