package at.nettania.dev.sample.qrcodeapp.web;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import at.nettania.dev.sample.qrcodeapp.core.QRCodeUtil;

/**
 * This is a sample application which creates from a {@link TextField} input a QR-Code.
 * 
 * @author florian
 *
 */
@Theme("mytheme")
public class QrCodeUi extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1633256676992649404L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		Page.getCurrent().setTitle("QR-Code sample");
        
        //
        // UI components
        //
		final VerticalLayout layout = new VerticalLayout();
        final Image qrImage;
        
        //
        // Wrapper around the TextField
        //
        final HorizontalLayout wrapper = new HorizontalLayout();
        wrapper.setWidthUndefined();
        layout.addComponent(wrapper);
        layout.setComponentAlignment(wrapper, Alignment.TOP_CENTER);
        
        final Label caption = new Label("Enter an URL to display as QRCode:");
        wrapper.addComponent(caption);
        wrapper.setComponentAlignment(caption, Alignment.MIDDLE_CENTER);
        
        final TextField name = new TextField();
        name.setWidth(250, Unit.PIXELS);
        name.setPlaceholder("www.florian-rhomberg.net");
        wrapper.addComponent(name);
        name.focus();
        
        final Button button = new Button("Create QR-Code");
        wrapper.addComponents(button);
        
        //
        // The QR-Image
        //
        qrImage = new Image();
        layout.addComponent(qrImage);
        layout.setComponentAlignment(qrImage, Alignment.TOP_CENTER);
        
        button.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 7507056868576045294L;

			@Override
			public void buttonClick(ClickEvent event) {
	            qrImage.setSource(new ExternalResource(QRCodeUtil.getQrCodeUrl(Page.getCurrent().getLocation().toString(), name.getValue())));
			}});
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "QrCodeUiServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = QrCodeUi.class, productionMode = false)
    public static class QrCodeUiServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3594396700811845967L;
    }
}
