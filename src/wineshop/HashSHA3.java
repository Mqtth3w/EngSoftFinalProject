/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
*
* The class {@code HashSHA3} defines a way to calculate hashes.
*
**/

public class HashSHA3 
{
	/**
	 * Calculate the hash.
	 *
	 * @param str the string to be hashed.
	 *
	**/
	public String executeHash(String str) throws NoSuchAlgorithmException
    {
        MessageDigest crypto = MessageDigest.getInstance("SHA3-256");
        crypto.update(str.getBytes(StandardCharsets.UTF_8));

        byte[] bytes = crypto.digest();
        BigInteger bi = new BigInteger(1, bytes);
        String digest = String.format("%0" + (bytes.length << 1) + "x", bi);

        return digest;
    }
	
	//try it, references https://lindevs.com/code-snippets/generate-sha3-512-hash-using-java
	
}