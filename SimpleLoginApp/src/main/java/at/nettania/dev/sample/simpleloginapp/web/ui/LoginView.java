package at.nettania.dev.sample.simpleloginapp.web.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import at.nettania.dev.sample.simpleloginapp.web.LoginAppUiController;

/**
 * This class implements the login view, which contains a simple form to enter username and password.
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
public class LoginView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3070811273936236288L;
	public static String VIEWNAME = "login";
	
	public LoginView(LoginAppUiController uicontroller) {
		this.setLocale(UI.getCurrent().getLocale());
		this.setSizeFull();
		this.setSpacing(false);
		this.setMargin(false);
		
		VerticalLayout loginWrapper = new VerticalLayout();
		loginWrapper.setWidth(500, Unit.PIXELS);
		loginWrapper.setSpacing(true);
		this.addComponent(loginWrapper);
		this.setComponentAlignment(loginWrapper, Alignment.MIDDLE_CENTER);
		
		TextField username = new TextField();
		username.setWidth(100, Unit.PERCENTAGE);
		username.setPlaceholder("Username");
		username.setIcon(VaadinIcons.USER);
		username.setStyleName("inline-icon");
		username.focus();
		loginWrapper.addComponent(username);
		
		PasswordField password = new PasswordField();
		password.setWidth(100, Unit.PERCENTAGE);
		password.setPlaceholder("Password");
		password.setIcon(VaadinIcons.LOCK);
		password.setStyleName("inline-icon");
		loginWrapper.addComponent(password);
		
		NativeButton loginButton = new NativeButton("Login");
		loginWrapper.addComponent(loginButton);
		loginWrapper.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
		loginButton.addClickListener(listener -> uicontroller.login(username, password));
		loginButton.addShortcutListener(new ShortcutListener("", ShortcutAction.KeyCode.ENTER, null) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8664364871536035397L;

			@Override
			public void handleAction(Object sender, Object target) {
				uicontroller.login(username, password);
			}});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		Page.getCurrent().setTitle("SimpleLoginApp - Login");
    }
}
