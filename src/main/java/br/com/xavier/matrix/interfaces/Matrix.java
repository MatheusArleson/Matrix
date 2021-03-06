package br.com.xavier.matrix.interfaces;

import br.com.xavier.matrix.interfaces.parser.MatrixParser;

public abstract interface Matrix<T> {
	
	public void clear();
	public void addColumn();
	public void addRow();
	public void addColumAndRow();
	public void removeColumn(int columnNumber);
	public void removeRow(int rowNumber);
	public void removeColumAndRow(int colRowNumber);
	public T get(int column, int row);
	public T set(int column, int row, T obj);
	public boolean checkEmpty(T obj);
	public T representsEmpty();
	public void setRepresentsEmpty(T obj);
	public int getColumnCount();
	public int getRowCount();
	
	public MatrixParser<T> getMatrixParser();
	public void setMatrixParser(MatrixParser<T> parser);
	
}
