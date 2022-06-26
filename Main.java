package practice_5_baby_blockchain;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		KeyPair objV = KeyPair.genKeyPair();
		System.out.println(objV.toString());
		
		String message = "abcd";
		byte [] resSig = Signature.signData(objV.getPrivateKey(), message);
		
		for (int i = 0; i < resSig.length; i++) {
			System.out.print(resSig[i] + " ");
		}
		System.out.println();
		boolean check = Signature.verifySignature(resSig, message, objV.getPublicKey());
		System.out.print(Boolean.toString(check));
	}

}
