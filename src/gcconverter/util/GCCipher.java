package gcconverter.util;

/**
 *
 * @author stuchlanek
 */
public class GCCipher {

    public static String encrypt(String what) {
	if (what == null) {
	    return null;
	}
	boolean encrypt = true;	// to crypt or not (useful for [comment])
	char[] chars = what.toCharArray();
	for (int i = 0; i < chars.length; i++) {
	    if (chars[i] == '[')  encrypt = false;
	    else if (chars[i] == ']') encrypt = true;
	    int dec = chars[i];
	    if (encrypt) {
		if (dec >= 65 && dec <= 90) {
		    dec -= 65;
		    dec = (dec + 13) % 26;
		    dec += 65;
		} else if (dec >= 97 && dec <= 122) {
		    dec -= 97;
		    dec = (dec + 13) % 26;
		    dec += 97;
		}
	    }
	    chars[i] = (char) dec;
	}
	return new String(chars);
    }
    
    public static String decrypt(String what) {
        return encrypt(what);
    }
}
