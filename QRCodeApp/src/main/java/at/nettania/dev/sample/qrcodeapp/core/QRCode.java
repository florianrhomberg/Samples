package at.nettania.dev.sample.qrcodeapp.core;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import net.glxn.qrgen.image.ImageType;

/**
 * Implementation of QRCode. Use this class to create an arbitrary {@link QRCode} object.
 * Use {@link QRCodeUtil} to display the QRCode as PNG image in the browser.
 * This object is not persistent and will be created on the fly.
 * 
 * @author florian
 *
 */
public class QRCode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6368834871126835904L;
	
	private String name;
	private String qrCodeValue;
	private int width;
	private int height;
	
	/**
	 * Use this constructor to create an empty {@link QRCode} with the dimensions 250x250 and name qrcode
	 */
	public QRCode() {
		this.name = "qrcode";
		this.width = 100;
		this.height = 100;
	}
	
	/**
	 * Use this constructor to create a {@link QRCode} with the given value and the given name,
	 * which is used as filename if displayed in the browser in combination with {@link QRUtil}
	 * @param name
	 * @param qrCodeValue
	 */
	public QRCode(String name, String qrCodeValue) {
		this.name = name;
		this.qrCodeValue = qrCodeValue;
		this.width = 250;
		this.height = 250;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the qrCodeValue
	 */
	public String getQrCodeValue() {
		return qrCodeValue;
	}

	/**
	 * @param qrCodeValue the qrCodeContent to set
	 */
	public void setQrCodeValue(String qrCodeValue) {
		this.qrCodeValue = qrCodeValue;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Returns the filetyp of this 
	 * @return
	 */
	public String getFiletype() {
		return "image/png";
	}

	/**
	 * Return the QRCode as byte array in UTF-8 and as PNG image
	 * @return
	 */
	public byte[] getQRCode() {
		ByteArrayOutputStream stream = net.glxn.qrgen.QRCode.from(qrCodeValue).withSize(width, height).withCharset("UTF-8").to(ImageType.PNG).stream();
		return stream.toByteArray();
	}
}
