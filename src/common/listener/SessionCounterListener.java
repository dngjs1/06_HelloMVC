package common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCounterListener
 *
 */
@WebListener
public class SessionCounterListener implements HttpSessionListener {
	
	private static int activeSessions;

    /**
     * Default constructor. 
     */
    public SessionCounterListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         activeSessions++;
         System.out.println("create! : activeSessions:"+ activeSessions);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         activeSessions--;
         System.out.println("destroyed : session : "+ activeSessions);
    }
	
}
