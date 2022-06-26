package practice_5_baby_blockchain;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Signature {
	
	// функция реализует цифровую подпись алгоритмом RSA
	public static byte [] signData (PrivateKey priv, String message) throws UnsupportedEncodingException {
		
		// переменная хранит захешированную строку
		String hash = Hash.toSHA1(message);
		// массив хранит сгенерированную цифровую подпись
		byte [] realSig = new byte [hash.length()];
		// реализация цифровой подписи 
		BigInteger temp;
		for (int i = 0; i < realSig.length; i++) {
			temp = new BigInteger(hash.substring(i, i+1), 16);
			temp = temp.pow(priv.getD().intValue());
			temp = temp.mod(priv.getN());
			realSig[i] = temp.byteValue();
		}
	
		// результат
		return realSig;
		
	}
	
	// функция выполняет верификацию цифровой подписи алгоритмом RSA
	public static boolean verifySignature (byte [] realSig, String message, PublicKey publicKey) throws UnsupportedEncodingException {
		BigInteger temp;
		// переменная хранит результат расшифровки цифровой подписи
		String resHash = "";
		for (int i = 0; i < realSig.length; i++) {
			temp = BigInteger.valueOf(realSig[i] & 0xFF);
			temp = temp.pow(publicKey.getE().intValue());
			temp = temp.mod(publicKey.getN());
			resHash = resHash + temp.toString(16);
		}		
		return resHash.compareTo(Hash.toSHA1(message)) == 0;
	}

}
