package practice_5_baby_blockchain;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Operation {
	
	private Account sender;
	private Account receiver;
	private int amount;
	private byte [] signature;
	
	public Operation(Account sender, Account receiver, int amount) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getReceiver() {
		return receiver;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
	
	//функция позволяющая создать операцию со всеми необходимыми деталями и подписью.
	public static Operation createOperation (Account sender, Account receiver, int amount) throws Exception {		
		return new Operation(sender, receiver, amount); 
	}
	
	@Override
	public String toString() {
		return "Operation [sender=" + sender.getAccountID() + ", receiver=" + receiver.getAccountID() + ", amount=" + amount + "]";
	}

	// функция выполняющая проверку операции
	public static boolean verifyOperation (Operation operation) throws UnsupportedEncodingException {
		if (operation.getAmount() > operation.getSender().getBalance()) {
			System.out.println("На Вашем счете недостаточно средств для перевода");
		} else {
			for (int i = 0; i < operation.getSender().getWallet().size(); i++) {
				if (!(Signature.verifySignature(operation.getSignature(), operation.toString(), operation.getSender().getWallet().get(i).getPublicKey()))) {
					return false;
				}			
			}
			
		} 
		return true;
	}
	
	
	
	
	

}
