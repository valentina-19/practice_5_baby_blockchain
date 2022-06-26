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
	
	// ������� ��������� �������� �� ����� ��������������
		public static boolean isCoprimeNumber(BigInteger a, BigInteger b) {
			
			// ���� ���� ����� �������� ����� � � b
			BigInteger one = new BigInteger("1");
			BigInteger zero = new BigInteger("0");
			for (BigInteger i = new BigInteger("2"); i.compareTo(a.min(b)) <= 0; i=i.add(one)) {
				// ���� ����� ����� ����� ��������, ������� ���������� ����
				if ((a.mod(i).compareTo(zero) == 0) && (b.mod(i).compareTo(zero) == 0)) {
					return false;
				}
			}
			// ������� ���������� ������
			return true;	
		}
	

	public static KeyPair genKeyPair() {
		        PrivateKey privateKeyValue;
		        PublicKey publicKeyValue;
				KeyPair result;
				Random rnd = new Random();
				int bitLength = 4;
				BigInteger f = new BigInteger("ff", 16);
		        // ��������� ������� ����� p � q
				/*BigInteger p = new BigInteger(bitLength, 1000, rnd);
				
				BigInteger q;
				do{
					q = new BigInteger(bitLength, 1000, rnd);
				}while(p.compareTo(q) == 0 || p.multiply(q).compareTo(f) < 0);*/
				
				// ��������� �������� ���� byte �� 0 �� 255, ��������� p � q ������, ����� n �� ��������� 255
				BigInteger p = new BigInteger("11");
				BigInteger q = new BigInteger("23");
				// ���������� ����� n
				BigInteger n = p.multiply(q); 
				// ���������� �������� ������� ������
				BigInteger one = new BigInteger("-1");
				BigInteger eulerValue = (p.add(one)).multiply(q.add(one));
				
				// ���������� ��������� �����
				BigInteger e;
				one = new BigInteger("1");
				
				do {
					e = new BigInteger(bitLength, rnd);
					
				}while(!((e.compareTo(one) == 1) && (isCoprimeNumber(e, eulerValue))));
				
				// ���������� ��������� �����
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
	
// ���������������� ������� toString
	@Override
	public String toString() {
		return "KeyPair [privateKey=" + getPrivateKey() + ", publicKey=" + getPublicKey() + "]";
	}
	
	
}
