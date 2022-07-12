package practice_5_baby_blockchain;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Account {

	private String accountID;
	private ArrayList<KeyPair> wallet;
	private int balance;
	
	public Account(String accountID, ArrayList<KeyPair> wallet, int balance) {
		super();
		this.accountID = accountID;
		this.wallet = wallet;
		this.balance = balance;
	}

	public String getAccountID() {
		return accountID;
	}

	public ArrayList<KeyPair> getWallet() {
		return wallet;
	}

	public static Account genAccount () {
		KeyPair keypair = KeyPair.genKeyPair();
		String accountID = keypair.getPublicKey().toString();
		Blockchain blockchain = new Blockchain();
		if(blockchain.isContainAccountID(accountID)) {
			return null;
		} else {
		ArrayList<KeyPair> wallet = new ArrayList<KeyPair>();
		if (!wallet.contains(keypair)) {
			wallet.add(keypair);
		}
		int balance = 0;	
		return new Account(accountID, wallet, balance);	
		}
			
	}
	
	public void addKeyPairToWallet (KeyPair keypair) {
		if (!wallet.contains(keypair)) {
			wallet.add(keypair);
		}
		
	}
	
	public void updateBalance (int balance) throws UnsupportedEncodingException {
		this.balance = balance;
	}
	
	public int getBalance () {
		return balance;
	}
	
	public void printBalance() {
		System.out.println(getBalance());
	}
	
	public byte [] signData (String message, int index) throws UnsupportedEncodingException {
		
		return Signature.signData(wallet.get(index).getPrivateKey(), message);

	}
	
	
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", wallet=" + wallet + ", balance=" + balance + "]";
	}

	public Operation createPaymentOp (Account recipient, int amount, int index) throws UnsupportedEncodingException {
		
		Operation resOperation = Operation.createOperation(this, recipient, amount);
		resOperation.setSignature(signData(resOperation.toString(), index));
		return resOperation;
	}
	
}
