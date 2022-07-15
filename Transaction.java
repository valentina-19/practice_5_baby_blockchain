package practice_5_baby_blockchain;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Transaction {
	private String transactionID;
	private ArrayList<Operation> setOfOperations;
	private int nonce;
	
	public Transaction(ArrayList<Operation> setOfOperations, int nonce) throws UnsupportedEncodingException {
		super();
		this.transactionID = Hash.toSHA1(setOfOperations.toString() + nonce);
		this.setOfOperations = setOfOperations;
		this.nonce = nonce;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public ArrayList<Operation> getSetOfOperations() {
		return setOfOperations;
	}

	public void setSetOfOperations(ArrayList<Operation> setOfOperations) {
		this.setOfOperations = setOfOperations;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	
	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", setOfOperations=" + setOfOperations + ", nonce="
				+ nonce + "]";
	}
	
	// функци€ позвол€юща€ создать транзакцию со всеми необходимыми детал€ми
	public static Transaction createTransaction(ArrayList<Operation> setOfOperations, int nonce) throws Exception {
		String prevHash = "";
		 ArrayList<Transaction> setOfTransactions = new ArrayList<Transaction>();
		Block block = new Block(prevHash, setOfTransactions);
		if (block.isContainsTransaction(nonce)) {
			throw new Exception("Ѕлок уже содержит данную транзакцию");
		}
		return new Transaction (setOfOperations, nonce);
	}
}
