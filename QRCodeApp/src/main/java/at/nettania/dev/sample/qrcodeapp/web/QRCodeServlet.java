package at.nettania.dev.sample.qrcodeapp.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import at.nettania.dev.sample.qrcodeapp.core.QRCode;

/**
 * This class implements a servlet to display a QRCode
 * 
 * @author florian
 *
 */
@WebServlet(urlPatterns = "/qrcode/*", name = "QRCodeServlet", asyncSupported = true)
public class QRCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6031742898289759780L;
	private static Log log = LogFactory.getLog(QRCodeServlet.class);
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int width = 250;
		int height = 250;
		
		String filename = req.getPathInfo();
		String code = req.getParameter("code");
		String dimension = req.getParameter("dimension");
		if(dimension != null && dimension.matches("\\d+x\\d+")) {
			width = Integer.parseInt(dimension.split("x")[0]);
			height = Integer.parseInt(dimension.split("x")[1]);
		}
		
		
		if(code == null) {
			log.debug("Code parameter is missing.");
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			log.debug("Creating QRCode for filename "+filename);
			
			QRCode qrCode = new QRCode();
			qrCode.setWidth(width);
			qrCode.setHeight(height);
			qrCode.setQrCodeValue(code);
			qrCode.setName(filename);
			
			
			ByteArrayInputStream in = new ByteArrayInputStream(qrCode.getQRCode()); // The input Stream
			BufferedImage image = ImageIO.read(in);
			getImage(image, qrCode.getName(), qrCode.getFiletype(), res);
		}
	}
	
	private static void getImage(BufferedImage image, String filename, String type, HttpServletResponse res){
		try {
			String formatName = "JPEG";
			if(filename.contains(".")) {
				String[] split = filename.split("\\.");
				formatName = split[split.length-1];
			}
			
			log.debug("Filename: "+filename);
			log.debug("Type of image: "+formatName);
			
			// As default type we use RGB, because if the JPEG is of type CMYK a bug in Java leads to a wrong representation of the colors. 
			// RBG does not support transparency, but JPEG does not do it either, therefore it does not matter that we have no alpha channel.
			int imageType = BufferedImage.TYPE_INT_RGB;
			
			// We change the image type to ARGB to support transparent backgrounds for PNG
			if(formatName.toLowerCase().equals("png"))
				imageType = BufferedImage.TYPE_INT_ARGB;
			// We change the image type to ARGB to support transparent backgrounds for GIF
			if(formatName.toLowerCase().equals("gif"))
				imageType = BufferedImage.TYPE_INT_ARGB;
			
			
		    BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(), imageType);
		    convertedImage.getGraphics().drawImage(image, 0, 0, null);
		    convertedImage.getGraphics().dispose();
		    
			// Write the image with the java's ImageIO
			ImageIO.write(convertedImage, formatName, res.getOutputStream());
		}
		// if something happens an exception is thrown
		catch(Exception e){
			log.error(e);
		}
	}
}
