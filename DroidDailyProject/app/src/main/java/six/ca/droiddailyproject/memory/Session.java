package six.ca.droiddailyproject.memory;

import six.ca.droiddailyproject.SixApplication;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-03-02.
 */

public class Session {
    private static volatile Session session;
    private SixApplication appContext;

    public void logout(){
        appContext.startActivity(null);
        System.out.println("Session logout...");
    }

    public static Session getSession(){
        if( session == null){
            synchronized (Session.class){
                if(session == null){
                    session = new Session();
                }
            }
        }
        return session;
    }

    private Session(){
        appContext = SixApplication.getAppContext();
    }
}
