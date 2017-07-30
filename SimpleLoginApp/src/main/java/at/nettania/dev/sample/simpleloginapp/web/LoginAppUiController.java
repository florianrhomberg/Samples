package at.nettania.dev.sample.simpleloginapp.web;

import java.io.Serializable;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import at.nettania.dev.sample.simpleloginapp.core.user.User;
import at.nettania.dev.sample.simpleloginapp.core.user.UserRepository;
import at.nettania.dev.sample.simpleloginapp.web.ui.MainView;

/**
 * 
 * 
 * This class implements the application controller which we will add to
 * the Vaadin session to make it available for all views.
 *
 * <p>========================================</p>
 *                                
 *                loginapp<br>              
 *            created on 30.07.2017          
 *                                                                     
 * <p>========================================</p>
 *
 * @author florian
 * @version
 *
 */
public class LoginAppUiController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7570570535339156683L;
	public static String SESSION_ATTRIBUTE = "loginAppUiController";
	
	private User user; // The current user of this instance of the application
	private SimpleLoginApp app; // Our application class
	
	public LoginAppUiController(SimpleLoginApp app) {
		this.app = app;
	}
	
	public void login(TextField username, PasswordField password) {
		// If the username and password combination exists the repository will return the user
		this.user = UserRepository.authenticate(username.getValue(), password.getValue());
		
		// The user has been authenticated therefore we continue to the main application view.
		if(this.user != null) {
			// After login you need to add the current controller to the session.
			UI.getCurrent().getSession().setAttribute(SESSION_ATTRIBUTE, this);
		
			// Finally navigate to the main view of the application
			this.app.getNavigator().navigateTo(MainView.VIEWNAME);
		} else {
			Notification.show("This combination of username and password does not exists", Type.ERROR_MESSAGE);
			username.clear();
			password.clear();
			username.focus();
		}
	}
	
	/**
	 * This method checks if the {@link User} is authenticated.
	 * @return
	 */
	public boolean isAuthenticated() {
		if(user != null)
			return true;
		else
			return false;
	}
	
	/**
	 * This method implements the logout procedure of the application
	 */
	public void logout() {
		// Close the session, this means we destroy the session including this instance of the LoginAppUiController
        UI.getCurrent().getSession().close();
		
        // Go to the login page 
        UI.getCurrent().getPage().reload();
	}
}
