package practice_5_baby_blockchain;

import java.math.BigInteger;

// класс реализует публичный ключ
public class PublicKey {
	
	private BigInteger e;
	private BigInteger n;
	
	public PublicKey(BigInteger e, BigInteger n) {
		super();
		this.e = e;
		this.n = n;
	}
	
	public BigInteger getE() {
		return e;
	}
	public void setE(BigInteger e) {
		this.e = e;
	}
	public BigInteger getN() {
		return n;
	}
	public void setN(BigInteger n) {
		this.n = n;
	}

	// переопределенная функция toString
	@Override
	public String toString() {
		return "PublicKey [e=" + getE() + ", n=" + getN() + "]";
	}
		
}
