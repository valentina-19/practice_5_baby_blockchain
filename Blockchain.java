package practice_5_baby_blockchain;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Blockchain {
	private HashMap <Account, Integer> coinDataBase;
	private ArrayList <Block> blockHistory;
	private ArrayList <Transaction> txDatabase;
	private int faucetCoins;
	private Random rnd;
	
	
	public Random getRnd() {
		return rnd;
	}
	
	public HashMap<Account, Integer> getCoinDataBase() {
		return coinDataBase;
	}
	public void setCoinDataBase(HashMap<Account, Integer> coinDataBase) {
		this.coinDataBase = coinDataBase;
	}
	public ArrayList<Block> getBlockHistory() {
		return blockHistory;
	}
	public void setBlockHistory(ArrayList<Block> blockHistory) {
		this.blockHistory = blockHistory;
	}
	public ArrayList<Transaction> getTxDatabase() {
		return txDatabase;
	}
	public void setTxDatabase(ArrayList<Transaction> txDatabase) {
		this.txDatabase = txDatabase;
	}
	public int getFaucetCoins() {
		return faucetCoins;
	}
	public void setFaucetCoins(int faucetCoins) {
		this.faucetCoins = faucetCoins;
	}
	
	public Blockchain() {
		super();
		this.coinDataBase = new HashMap <>();
		this.blockHistory = new ArrayList <Block>();
		this.txDatabase = new ArrayList <Transaction> ();
		this.faucetCoins = 10000;
		this.rnd = new Random();
	}
	
	// функци€ позвол€юща€ проинициализировать блокчейн
	public static Blockchain initBlockchain() throws Exception {
		//создание объекта класса Blockchain
		Blockchain blockchain = new Blockchain();
		//создание списка операций, которые содержит транзакци€
		ArrayList<Operation> setOfOperations = new ArrayList<Operation>();
		// создание транзакции, котора€ содержит список операций
		int nonce = 0;
		Transaction e = new Transaction(setOfOperations, nonce);
		// создание списка транзакций, которые содержит блок
		ArrayList<Transaction> setOfTransactions = new ArrayList<Transaction>();
		// добавление транзакции в список
		setOfTransactions.add(e);
		// создание ссылки на предыдущий блок
		String prevHash = ""; 
		// инициализаци€ нулевого блока
		Block genesisBlock = new Block(prevHash, setOfTransactions);
		// добавление нулевого блока в историю транзакций
		blockchain.blockHistory.add(genesisBlock);
		// возвращение объекта типа Blockchain
		return blockchain;
	}
	
	// функци€ позвол€юща€ получить тестовые монеты с крана
	public void getTokenFromFaucet(Account account, int amount) throws Exception {
		account.updateBalance(amount);
		this.faucetCoins = this.faucetCoins - amount;
		if (this.faucetCoins < amount) {
			throw new Exception("Ќедостаточно монет в кране");
		}
	}
	
	// функци€ позвол€юща€ выполнить проверку и добавить блок в историю
	public void validateBlock(Block block) throws Exception {
		if (block.getPrevHash().compareTo(this.blockHistory.get(blockHistory.size() - 1).getBlockID()) < 0) {
			throw new Exception ("Ѕлок не содержит ссылку на на последний актуальный блок в истории");
		} else {
			for (int i = 0; i < this.txDatabase.size(); i++) {
				if (txDatabase.contains(block.getSetOfTransactions().get(i))) {
					throw new Exception ("“ранзакци€ содержитс€ в истории");
				} else if (! txDatabase.contains(block.getSetOfTransactions().get(i).getSetOfOperations().get(i).verifyOperation(block.getSetOfTransactions().get(i).getSetOfOperations().get(i)))){
					throw new Exception ("ќпераци€ не €вл€етс€ подписанной");
				}
			}
		}
	}
	
	// функци€ позвол€юща€ получить текущее состо€ни€ аккаунтов и балансов
	public void showCoinDataBase() {
		System.out.println(this.getCoinDataBase());
	}
	
	
	

}
