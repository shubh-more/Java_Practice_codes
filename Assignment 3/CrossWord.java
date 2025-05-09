package algs11;

public class CrossWord {
	private Square[][] puzzle;

	public CrossWord(boolean[][] blackSquares) {
		puzzle = new Square[blackSquares.length][blackSquares[0].length];
		int number = 1;
		for (int r = 0; r < blackSquares.length; r++) {
			for (int c = 0; c < blackSquares[0].length; c++) {
				if (blackSquares[r][c]) {
					puzzle[r][c] = new Square(true, 0);
				} else {
					if (tobeLabeled(r, c, blackSquares)) {
						puzzle[r][c] = new Square(false, number);
						number++;
					} else {
						puzzle[r][c] = new Square(false, 0);
					}
				}
			}
		}
	}
	private boolean tobeLabeled(int r, int c, boolean[][] blackSquares) {
		// TODO
		return  (!blackSquares[r][c]) && (r == 0 || c == 0 || blackSquares[r-1][c] || blackSquares[r][c-1]);
	}

	public void print() {
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++)
				System.out.print(puzzle[i][j].getLabel());
			System.out.println();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[][] test = new boolean[7][9];
		test[0][0] = true;
		test[0][3] = true;
		test[0][4] = true;
		test[0][5] = true;
		test[1][4] = true;
		for (int j = 6; j < 9; j++)
		test[2][j] = true;
		test[3][2] = true;
		test[3][6] = true;
		for (int j = 0; j < 3; j++)
		test[4][j] = true;
		test[5][4] = true;
		test[6][3] = true;
		test[6][4] = true;
		test[6][5] = true;
		test[6][8] = true;

		CrossWord c = new CrossWord(test);
		c.print();

	}

}
