package com.xhc.thread;
/*
 * 定义取钱的线程
 */
class DrawThread implements Runnable{
	Account account;
	public DrawThread(Account account){
		this.account=account;
	}
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
			account.draw();
		}
	}
}