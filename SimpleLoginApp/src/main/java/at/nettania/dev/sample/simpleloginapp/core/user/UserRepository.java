package at.nettania.dev.sample.simpleloginapp.core.user;

import java.util.HashSet;
import java.util.Iterator;

/**
 * This is our user "database".
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
public class UserRepository {
	
	private static HashSet<User> getUsers() {
		HashSet<User> users = new HashSet<User>();
		users.add(new User("John Doe", "john", "amanda"));
		users.add(new User("Amanda Doe", "amanda", "john"));
		
		return users;
	}
	
	public static User authenticate(String username, String password) {
		// We simple create a new user with this credentials and check the hash set if exists.
		User userToCheck = new User("", username, password);
		
		for (Iterator<User> it = getUsers().iterator(); it.hasNext(); ) {
	        User f = it.next();
	        if (f.equals(userToCheck))
	            return f;
	    }
		
		return null;
	}
}
