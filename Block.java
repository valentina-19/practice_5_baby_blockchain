package practice_5_baby_blockchain;

import java.util.ArrayList;

public class Block {
	private String blockID;
	private String prevHash;
	private ArrayList<Transaction> setOfTransactions;
	
	public Block(String prevHash, ArrayList<Transaction> setOfTransactions) {
		super();
		this.prevHash = prevHash;
		this.setOfTransactions = setOfTransactions;
		this.blockID = prevHash + setOfTransactions.toString();
	}
	
	public String getBlockID() {
		return blockID;
	}
	public void setBlockID(String blockID) {
		this.blockID = blockID;
	}
	public String getPrevHash() {
		return prevHash;
	}
	public void setPrevHash(String prevHash) {
		this.prevHash = prevHash;
	}
	public ArrayList<Transaction> getSetOfTransactions() {
		return setOfTransactions;
	}
	public void setSetOfTransactions(ArrayList<Transaction> setOfTransactions) {
		this.setOfTransactions = setOfTransactions;
	}
	
	// функция создания нового блока в цепочке блоков
	public static Block createBlock(String prevHash, ArrayList<Transaction> setOfTransactions) {
		
		return new Block(prevHash, setOfTransactions);
	}
	
	// функция проверяет транзакцию на уникальность
	public boolean isContainsTransaction(int nonce) {
		for (int i = 0; i < setOfTransactions.size(); i++) {
			if (setOfTransactions.get(i).getNonce() == nonce) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Block [blockID=" + blockID + ", prevHash=" + prevHash + ", setOfTransactions=" + setOfTransactions
				+ "]";
	}
	
	
	

}
