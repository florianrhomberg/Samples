package at.nettania.dev.sample.simpleloginapp.web.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import at.nettania.dev.sample.simpleloginapp.web.LoginAppUiController;

/**
 * This class implements the main view, which is entered if the user is authenticated.
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
public class MainView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3070811273936236288L;
	public static String VIEWNAME = "main";
	
	public MainView(LoginAppUiController uicontroller) {
		this.setLocale(UI.getCurrent().getLocale());
		this.setSizeFull();
		this.setSpacing(false);
		this.setMargin(false);
		
		VerticalLayout wrapper = new VerticalLayout();
		wrapper.setWidth(500, Unit.PIXELS);
		wrapper.setSpacing(true);
		this.addComponent(wrapper);
		this.setComponentAlignment(wrapper, Alignment.MIDDLE_CENTER);
		
		Label info = new Label("This is the main view of our application, you can reload and close the browser tab, until the session"
				+ " does not expire, or you press the logout button, you will always stay on this view.");
		info.setContentMode(ContentMode.HTML); // Set to html to allow line breaks.
		info.setWidth(100, Unit.PERCENTAGE);
		wrapper.addComponent(info);
		
		NativeButton logoutButton = new NativeButton("Logout");
		wrapper.addComponent(logoutButton);
		wrapper.setComponentAlignment(logoutButton, Alignment.MIDDLE_CENTER);
		logoutButton.addClickListener(listener -> uicontroller.logout());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		Page.getCurrent().setTitle("SimpleLoginApp - Main");
    }
}
