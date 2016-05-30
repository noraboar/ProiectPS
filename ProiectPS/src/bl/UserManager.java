package bl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import models.User;
import dl.UserDAO;



public class UserManager {

	UserDAO userDAO=new UserDAO();

	public User login(String username, String password){
		User user=null;
		List<User> listaUseri= userDAO.getUser();
		Iterator<User> it=listaUseri.iterator();
		while (it.hasNext()) {
			User nextUser=it.next();
			String usernameDB = nextUser.getUsername();
			String passwordDB = nextUser.getPassword();
			if(username.equals(usernameDB) && passwordDB.equals(getMD5(password))){
				return nextUser;
			}
			
		}
		return user;
	}
	
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void createUser(User angajat){
		angajat.setPassword(getMD5(angajat.getPassword()));
		userDAO.addUser(angajat);
	}
	
	public void passwordChange(String username,String password){
		userDAO.updatePassword(username, getMD5(password));
	}
	
	public boolean verificareUsername(String username){
		List<User> listaUseri= userDAO.getUser();
		Iterator<User> it=listaUseri.iterator();
		while (it.hasNext()) {
			User nextUser=it.next();
			String usernameDB = nextUser.getUsername();
			if(username.equals(usernameDB)){
				return true;
			}
			
		}
		return false;
	}
}
