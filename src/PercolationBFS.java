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
		while(q.size() > 0) {
			Integer x = q.poll();
			int r = x / myGrid.length;
			int c = x % myGrid.length;
			if (inBounds(r+1,c) && isOpen(r+1,c)&& !isFull(r+1,c)) {
				myGrid[r+1][c] = FULL;
				q.add((r+1) * myGrid.length + c);
			}
			if (inBounds(r-1,c) && isOpen(r-1,c) && !isFull(r-1,c)) {
				myGrid[r-1][c] = FULL;
				q.add((r-1) * myGrid.length + c);
			}
			if (inBounds(r,c+1) && isOpen(r,c+1) && !isFull(r,c+1)) {
				myGrid[r][c+1] = FULL;
				q.add(r * myGrid.length + c + 1);
			}
			if (inBounds(r,c-1) && isOpen(r,c-1) && !isFull(r,c-1)) {
				myGrid[r][c-1] = FULL;
				q.add(r * myGrid.length + c-1);
			}
		}
	}
}

