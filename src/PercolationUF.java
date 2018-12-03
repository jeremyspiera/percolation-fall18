
public class PercolationUF implements IPercolate {

	boolean[][] myGrid;
	int myOpenCount = 0;
	private final int VTOP;
	private final int VBOTTOM;
	IUnionFind myFinder;


	public PercolationUF(int size, IUnionFind finder) {
		VTOP = size*size;
		VBOTTOM = size*size + 1;
		myGrid = new boolean [size][size];
		for (int i = 0; i<myGrid.length; i++){
			for (int j = 0; j<myGrid[i].length; j++){
				myGrid[i][j] = false;
			} 
		}
		myFinder = finder;
		myFinder.initialize(myGrid.length*myGrid.length+2);
	}

	@Override
	public void open(int row, int col) {
		if (! inBounds(row,col)) throw new IndexOutOfBoundsException();
		if (myGrid[row][col] == true)return;
		myGrid[row][col] = true;
		myOpenCount ++;
		if (row == 0) myFinder.union(num(row,col), VTOP);
		if (row == myGrid.length-1) myFinder.union(num(row,col),VBOTTOM);	
		if (inBounds(row+1,col) && isOpen(row+1,col))myFinder.union(num(row,col),num(row+1,col));
		if (inBounds(row-1,col) && isOpen(row-1,col))myFinder.union(num(row,col),num(row-1,col));
		if (inBounds(row,col+1) && isOpen(row,col+1))myFinder.union(num(row,col),num(row,col+1));
		if (inBounds(row,col-1) && isOpen(row,col-1))myFinder.union(num(row,col),num(row,col-1));
	}

	public int num (int row, int col) {
		return (myGrid.length * row) + col;
	}

	public boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) throw new IndexOutOfBoundsException();
		if (myGrid[row][col] == true)return true;
		return false;
	}

	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) throw new IndexOutOfBoundsException();
		if (myFinder.connected(num(row,col), VTOP)) return true;
		return false;
	}

	@Override
	public boolean percolates() {
		if (myFinder.connected(VTOP,VBOTTOM)) return true;
		return false;
	}

	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}



}
