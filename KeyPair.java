package practice_5_baby_blockchain;

import java.math.BigInteger;
import java.util.Random;

public class KeyPair {
	
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public KeyPair(PrivateKey privateKey, PublicKey publicKey) {
		super();
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
	// функция проверяет являются ли числа взаимопростыми
		public static boolean isCoprimeNumber(BigInteger a, BigInteger b) {
			
			// цикл ищет общие делители чисел а и b
			BigInteger one = new BigInteger("1");
			BigInteger zero = new BigInteger("0");
			for (BigInteger i = new BigInteger("2"); i.compareTo(a.min(b)) <= 0; i=i.add(one)) {
				// если числа имеют общие делители, функция возвращает ложь
				if ((a.mod(i).compareTo(zero) == 0) && (b.mod(i).compareTo(zero) == 0)) {
					return false;
				}
			}
			// функция возвращает истину
			return true;	
		}
	

	public static KeyPair genKeyPair() {
		        PrivateKey privateKeyValue;
		        PublicKey publicKeyValue;
				KeyPair result;
				Random rnd = new Random();
				int bitLength = 4;
				BigInteger f = new BigInteger("ff", 16);
		        // случайные простые числа p и q
				/*BigInteger p = new BigInteger(bitLength, 1000, rnd);
				
				BigInteger q;
				do{
					q = new BigInteger(bitLength, 1000, rnd);
				}while(p.compareTo(q) == 0 || p.multiply(q).compareTo(f) < 0);*/
				
				// поскольку диапазон типа byte от 0 до 255, подбираем p и q такими, чтобы n не превышало 255
				BigInteger p = new BigInteger("11");
				BigInteger q = new BigInteger("23");
				// нахождение числа n
				BigInteger n = p.multiply(q); 
				// нахождение значения функции Эйлера
				BigInteger one = new BigInteger("-1");
				BigInteger eulerValue = (p.add(one)).multiply(q.add(one));
				
				// нахождение открытого ключа
				BigInteger e;
				one = new BigInteger("1");
				
				do {
					e = new BigInteger(bitLength, rnd);
					
				}while(!((e.compareTo(one) == 1) && (isCoprimeNumber(e, eulerValue))));
				
				// нахождение закрытого ключа
				BigInteger k = new BigInteger("0");
				BigInteger zero = new BigInteger("0");
				do {
					k=k.add(one);
				}while(((eulerValue.multiply(k).add(one).mod(e).compareTo(zero)) != 0) || (eulerValue.multiply(k).add(one).divide(e).compareTo(e) == 0));
								
				BigInteger d = eulerValue.multiply(k).add(one).divide(e);				
				privateKeyValue = new PrivateKey(d,n);
				publicKeyValue = new PublicKey(e,n);
				result = new KeyPair(privateKeyValue, publicKeyValue);
				return result;
		
	}
	
// переопределенная функция toString
	@Override
	public String toString() {
		return "KeyPair [privateKey=" + getPrivateKey() + ", publicKey=" + getPublicKey() + "]";
	}
	
	
}
