package at.nettania.dev.sample.simpleloginapp.web;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import at.nettania.dev.sample.simpleloginapp.web.ui.LoginView;
import at.nettania.dev.sample.simpleloginapp.web.ui.MainView;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class SimpleLoginApp extends UI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8272040115893003478L;
	
	private Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    		//
		// Create an instance of the LoginAppUiController and add it to the session
		//
		LoginAppUiController uicontroller;
		
		// If a session already exists, there must be an attribute containing the LoginAppUiController
		if(UI.getCurrent().getSession().getAttribute(LoginAppUiController.SESSION_ATTRIBUTE) != null)
			uicontroller = (LoginAppUiController) UI.getCurrent().getSession().getAttribute(LoginAppUiController.SESSION_ATTRIBUTE);
		// Create a new instance of the FrontendUiController, which means the user must enter his/her credentials
		else
			uicontroller = new LoginAppUiController(this);
		
		//
		// Create the navigator
		//
		this.navigator = new Navigator(this, this);
		this.navigator.addView(LoginView.VIEWNAME, new LoginView(uicontroller));
		this.navigator.addView(MainView.VIEWNAME, new MainView(uicontroller));
		
		//
		// Enter the right view
		//
		if(uicontroller.isAuthenticated())
			this.navigator.navigateTo(MainView.VIEWNAME);
		else
			this.navigator.navigateTo(LoginView.VIEWNAME);
    }

    @WebServlet(urlPatterns = "/*", name = "SimpleLoginAppServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = SimpleLoginApp.class, productionMode = false)
    public static class SimpleLoginAppServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4683186830720335264L;
    }
}
