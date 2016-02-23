package br.com.xavier.matrix.abstraction;

public abstract class SquareMatrix<G> extends AbstractMatrix<G> {
	
	//XXX CONSTRUCTOR
	public SquareMatrix(int size) {
		super(size, size);
	}

	//XXX OVERRIDE METHODS
	@Override
	public void addColumn() {
		throw new UnsupportedOperationException("Method now allowed.");
	}

	@Override
	public void addRow() {
		throw new UnsupportedOperationException("Method now allowed.");
	}

	@Override
	public void removeColumn(int columnNumber) {
		throw new UnsupportedOperationException("Method now allowed.");
	}
	
	@Override
	public void removeRow(int rowNumber) {
		throw new UnsupportedOperationException("Method now allowed.");
	}
}
