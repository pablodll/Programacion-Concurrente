package PC2;

import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket implements Lock {
	
	volatile private AtomicInteger number;
	volatile private int next;
	volatile int[] turn;
	
	public LockTicket(int N) {
		this.number = new AtomicInteger(1);
		this.next = 1;
		this.turn = new int[N];
		for (int i = 0; i < N; i++) turn[i] = 0;
		turn = turn;
	}
	
	@Override
	public void takeLock(int pid) {
		turn[pid] = number.getAndAdd(1);
		turn = turn;
		while(turn[pid] != next) {}
	}

	@Override
	public void releaseLock(int pid) {
		next = next + 1;
	}

}
