package eventstickets.helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserHelper {
	public static String getMD5(String expression) {
		String result = expression;
	    if(expression != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(expression.getBytes());
				BigInteger hash = new BigInteger(1, md.digest());
		        result = hash.toString(16);
		        while(result.length() < 32) {
		            result = "0" + result;
		        }
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
	    }
	    return result;
	}
}
