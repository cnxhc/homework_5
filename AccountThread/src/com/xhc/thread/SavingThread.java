package com.xhc.thread;
/*
 * 定义存款的线程
 */
class SavingThread implements Runnable{
	Account account;
	public SavingThread(Account account){
		this.account=account;
	}
	public void run(){
		for(int i=0;i<1000;i++){
			account.saving();
		}
	}
}