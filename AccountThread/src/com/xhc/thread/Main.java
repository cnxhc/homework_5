package com.xhc.thread;

public class Main {
	/*
	 * 运行取款和存款的线程
	 */
	public static void main(String[] args) {
		Account account = new Account();
		Thread thread1=new Thread(new DrawThread(account));
		Thread thread2=new Thread(new SavingThread(account));
		thread1.start();
		thread2.start();
	}

}