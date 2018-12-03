import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{

	public PercolationBFS(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void dfs(int row, int col) {
		Queue<Integer> q = new LinkedList<>(); 
		myGrid[row][col] = FULL;
		q.add(row*myGrid.length+col);
		int [] rr = {-1,0,1};
		int [] cc = {-1,0,1};
		while(q.size() > 0) {
			Integer x = q.poll();
			int r = x / myGrid.length;
			int c = x % myGrid.length;
			for (int i = 0; i < rr.length; i++) {
				for (int j = 0; j < cc.length; j++) {
					if (i == 0 || j == 0) {
						if (inBounds(r+i,c+j) && isOpen(r+i,c+j)&& !isFull(r+i,c+j)) {
							myGrid[r+i][c+j] = FULL;
							q.add((r+i) * myGrid.length + c+j);
						}
					}
				}
			}
		}
	}
}

