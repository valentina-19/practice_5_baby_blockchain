package practice_5_baby_blockchain;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

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
	
	// функция генерации уникальной ключевой пары
	public static KeyPair genUniqueKeyPair (Blockchain blockchain) {
		boolean isUnique;	
		KeyPair keypair;
		ArrayList<KeyPair> wallet;
		int balance = 0;
		do {
			isUnique = false;	
			keypair = KeyPair.genKeyPair(blockchain.getRnd());
			wallet = new ArrayList<KeyPair>();
			Account account;
			KeyPair keyPairTemp;
			Iterator<Account> accountIterator = blockchain.getCoinDataBase().keySet().iterator();
			for (int i = 0; i < blockchain.getCoinDataBase().keySet().size(); i++) {
				account = accountIterator.next();
						
				for (int j = 0; j < account.getWallet().size(); j++) {
					keyPairTemp = account.getWallet().get(j);
					if (keyPairTemp.getPublicKey().toString().compareTo(keypair.getPublicKey().toString()) == 0) {
						isUnique = true;
						break;
					}
				}
				if (isUnique) {
					break;
				}
			}		
				
		} while(isUnique);
		
		return keypair;

	}

	// функция позволяющая выполнить создание аккаунта.
	public static Account genAccount (Blockchain blockchain) throws Exception {
		KeyPair keypair = genUniqueKeyPair (blockchain);
		String accountID = keypair.getPublicKey().toString();
		ArrayList<KeyPair> wallet = new ArrayList<KeyPair>();
		int balance = 0;
		wallet.add(keypair);
		return new Account(accountID, wallet, balance);	
			
	}
	
	// функция позволяющая добавить в кошелек новую ключевую пару
	public void addKeyPairToWallet (KeyPair keypair) {
		if (!wallet.contains(keypair)) {
			wallet.add(keypair);
		}
		
	}
	
	// функция позволяющая обновить состояние баланса пользователя.
	public void updateBalance (int balance) throws UnsupportedEncodingException {
		this.balance = balance;
	}
	
	public int getBalance () {
		return balance;
	}
	
	// вывод баланса аккаунта на экран
	public void printBalance() {
		System.out.println(getBalance());
	}
	
	// функция позволяющая подписать произвольные данные пользователем
	public byte [] signData (String message, int index) throws UnsupportedEncodingException {
		
		return Signature.signData(wallet.get(index).getPrivateKey(), message);

	}
	
	
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", wallet=" + wallet + ", balance=" + balance + "]";
	}

	// функция которая позволяет создать операцию платежа от имени этого аккаунта получателю
	public Operation createPaymentOp (Account recipient, int amount, int index) throws Exception {
		
		Operation resOperation = Operation.createOperation(this, recipient, amount);
		resOperation.setSignature(signData(resOperation.toString(), index));
		return resOperation;
	}
	
}
