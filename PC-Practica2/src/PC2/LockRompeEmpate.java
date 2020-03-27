package PC2;

public class LockRompeEmpate implements Lock{
	
	private int n;
	volatile private int[] last;
	volatile private int[] in;
	
	public LockRompeEmpate(int N) {
		last = new int[N];
		in = new int[N];
		n = N;
		for (int i = 0; i < n; i++) last[i] = in[i] = -1;
	}

	@Override
	public void takeLock(int pid) {
		for (int i = 0; i < n; i++) {
			in[pid] = i;
			in = in;
			last[i] = pid;
			last = last;
			for (int k = 0; k < n; k++) {
				if (k != pid)
					while(in[k] >= in[pid] && last[i] == pid);
			}
		}
	}

	@Override
	public void releaseLock(int pid) {
		in[pid] = -1;
		in = in;
	}
	
}
