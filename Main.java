package practice_5_baby_blockchain;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Account account;
		Blockchain blockchain = Blockchain.initBlockchain();
		account = Account.genAccount(blockchain);
		blockchain.getTokenFromFaucet(account, 10);
		blockchain.getCoinDataBase().put(account, account.getBalance());	
		System.out.println("Account:" + account.toString());
		
		account = Account.genAccount(blockchain);
		blockchain.getTokenFromFaucet(account, 10);
		blockchain.getCoinDataBase().put(account, account.getBalance());	
		System.out.println("Account:" + account.toString());
		
		account = Account.genAccount(blockchain);
		blockchain.getTokenFromFaucet(account, 10);
		blockchain.getCoinDataBase().put(account, account.getBalance());	
		System.out.println("Account:" + account.toString());
		
		account = Account.genAccount(blockchain);
		blockchain.getTokenFromFaucet(account, 10);
		blockchain.getCoinDataBase().put(account, account.getBalance());	
		System.out.println("Account:" + account.toString());
		
		account = Account.genAccount(blockchain);
		blockchain.getTokenFromFaucet(account, 10);
		blockchain.getCoinDataBase().put(account, account.getBalance());	
		System.out.println("Account:" + account.toString());
		
		account = Account.genAccount(blockchain);
		blockchain.getTokenFromFaucet(account, 10);
		blockchain.getCoinDataBase().put(account, account.getBalance());	
		System.out.println("Account:" + account.toString());
		
		blockchain.showCoinDataBase();
		
	}
	
}
