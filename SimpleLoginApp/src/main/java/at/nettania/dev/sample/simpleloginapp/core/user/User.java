package at.nettania.dev.sample.simpleloginapp.core.user;

import java.io.Serializable;

/**
 * This is a dummy class which represents an user of the application.
 * Note the {@link User#hashCode()} and {@link User#equals(Object)} methods, whoch defines that if username and password are
 * the same, the objects are equal.
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
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4753508683469842961L;
	
	private String name;
	private String username;
	private String password;
	
	public User() {
		this.name = "";
		this.username = "";
		this.password = "";
	}
	
	public User(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
