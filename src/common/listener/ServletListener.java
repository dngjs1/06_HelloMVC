package common.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletListener
 *
 */
@WebListener
public class ServletListener implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public ServletListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         //System.out.println("client에게 응답을 완료 접속종료.");
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent arg0)  { 
         //System.out.println("client요청이 들어왔습니다.");
    }
	
}
