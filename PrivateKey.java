package practice_5_baby_blockchain;

import java.math.BigInteger;

//класс реализует секретный ключ
public class PrivateKey {
	
	private BigInteger d;
	private BigInteger n;
		
	public PrivateKey(BigInteger d, BigInteger n) {
		super();
		this.d = d;
		this.n = n;
	}

	public BigInteger getD() {
		return d;
	}

	public void setD(BigInteger d) {
		this.d = d;
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
		return "PrivateKey [d=" + getD() + ", n=" + getN() +  "]";
	}
	
}
