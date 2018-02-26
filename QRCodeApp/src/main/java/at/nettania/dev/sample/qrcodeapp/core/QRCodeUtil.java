package at.nettania.dev.sample.qrcodeapp.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class implements static utility methods for {@link QRCode}
 * 
 * @author florian
 *
 */
public class QRCodeUtil {
	
	private static Log log = LogFactory.getLog(QRCodeUtil.class);
	
	public static String getQrCodeUrl(String url, String qrCodeContent) {
		QRCode code = new QRCode();
		code.setName("myqrcode");
		code.setQrCodeValue(qrCodeContent);
		code.setHeight(250);
		code.setWidth(250);
		
		return getQRCodeUrl(url, code);
	}

	/**
	 * Returns the URL to display this {@link QRCode} in the browser
	 * @param qrcode
	 * @return
	 */
	public static String getQRCodeUrl(String url, QRCode qrcode) {
		String uurl = "";
		try {
			uurl = url+"qrcode/{filename}.png?code={qrcode}&dimension={width}x{height}";
			uurl = uurl.replace("{filename}", qrcode.getName());
			uurl = uurl.replace("{qrcode}", URLEncoder.encode(qrcode.getQrCodeValue(), "UTF-8"));
			uurl = uurl.replace("{width}", qrcode.getWidth()+"");
			uurl = uurl.replace("{height}", qrcode.getHeight()+"");
			log.debug("URL: "+uurl);
		} catch (UnsupportedEncodingException e) {
			log.error("Unexpected error occured.", e);
		}
		
		return uurl;
	}
}
