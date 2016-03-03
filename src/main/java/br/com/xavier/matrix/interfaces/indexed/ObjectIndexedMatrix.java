package br.com.xavier.matrix.interfaces.indexed;

import java.util.Set;

import br.com.xavier.matrix.interfaces.Matrix;

public interface ObjectIndexedMatrix<O, M extends Matrix<T>, T> {
	
	public void clear();
	public void clearMatrixData();
	public T get(O columnObj, O rowObj);
	public T set(O columnObj, O rowObj, T value);
	public boolean checkEmpty(T obj);
	public T representsEmpty();
	
	public Set<O> getColumnsObjects();
	public void addColumn(O columnObj);
	public void removeColumn(O columnObj);
	public int getColumnCount();
	public boolean isColumnObject(O obj);
	public boolean isValidForColumn(O obj);
	public int getMatrixColumnNumber(O obj);
	
	public Set<O> getRowsObjects();
	public void addRow(O rowObj);
	public void removeRow(O rowObj);
	public int getRowCount();
	public boolean isRowObject(O obj);
	public boolean isValidForRow(O obj);
	public int getMatrixRowNumber(O obj);
	
	public void addColumAndRow(O colRowObj);
	public void removeColumAndRow(O colRowObj);
	
}
