package com.xhc.thread;

//操作类Account
class Account {
	int money = 100;

	// 存钱的方法saveing()
	public void saving() {
		synchronized (this) {
			money++;
			System.out.println("向账户存了1元，账户还有余额" + money + "元");
			//唤醒线程执行
			notifyAll();
		}
	}

	// 取钱的方法 draw()
	public void draw() {
		synchronized (this) {
			try {				
				while(money<=0){
					this.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			money--;
			System.out.println("向账户取了1元，账户还有余额" + money + "元");
		}
	}
}