package PC2;

public class LockBakery implements Lock {

	volatile private int[] turn;
	
	public LockBakery(int N) {
		turn = new int[N];
		for(int i = 0; i < N; i++) { turn[i] = 0; }
	}
	
	private int max(int[] a) {
		int m = a[0];
		for(int i = 1; i < a.length; i++) {
			if(a[i] > m) m = a[i];
		}
		return m;
	}
	
	private boolean comp(int a, int b, int c, int d) {
		return ( (a > c) || ( (a == c) && (b > d) ) );
	}
	
	@Override
	public void takeLock(int pid) {
		turn[pid] = 1; turn = turn;
		turn[pid] = max(turn) + 1; turn = turn;
		
		for(int j = 0; j < turn.length; j++) {
			if(j != pid) 
				while((turn[j]!= 0) && comp(turn[pid], pid, turn[j], j) ) {}
		}
	}

	@Override
	public void releaseLock(int pid) {
		turn[pid] = 0;
		turn = turn;
	}

}
