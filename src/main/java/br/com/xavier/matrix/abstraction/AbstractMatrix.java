package br.com.xavier.matrix.abstraction;

import java.lang.reflect.Array;

import br.com.xavier.matrix.impl.parser.DefaultMatrixParser;
import br.com.xavier.matrix.interfaces.Matrix;
import br.com.xavier.matrix.interfaces.parser.MatrixParser;
import br.com.xavier.matrix.util.messages.Util;

public abstract class AbstractMatrix<T> implements Matrix<T> {
	
	//XXX CLASS PROPERTIES
	private MatrixParser<T> matrixParser;
	
	private int columns;
	private int rows;
	protected T[][] matrix;
    
    //XXX CONSTRUCTOR
	public AbstractMatrix(int columns, int rows) {
		this.matrixParser = new DefaultMatrixParser<T>();
		this.columns = columns;
		this.rows = rows;
		this.matrix = fabricateMatrix(columns, rows);
		clear();
	}
	
	//XXX OVERRIDE METHODS
	@Override
	public void clear() {
		for (int column = 0; column < matrix.length; column++) {
			for (int row = 0; row < matrix[column].length; row++) {
				matrix[column][row] = representsEmpty();
			}
		}
	}
	
	@Override
	public void addColumn() {
		this.columns = this.columns + 1;
		T[][] newMatrix = fabricateMatrix(columns, rows);
		copyContent(newMatrix, -1, -1);
		this.matrix = newMatrix;
	}

	@Override
	public void addRow() {
		this.rows = this.rows + 1;
		T[][] newMatrix = fabricateMatrix(columns, rows);
		copyContent(newMatrix, -1, -1);
		this.matrix = newMatrix;
	}
	
	@Override
	public void removeColumn(int columnNumber) {
		Util.checkInvalidColumnIndex(this, columnNumber);
		
		this.columns = this.columns - 1;
		T[][] newMatrix = fabricateMatrix(columns, rows);
		copyContent(newMatrix, columnNumber, -1);
		this.matrix = newMatrix;
	}
	
	@Override
	public void removeRow(int rowNumber) {
		Util.checkInvalidRowIndex(this, rowNumber);
		
		this.rows = this.rows - 1;
		T[][] newMatrix = fabricateMatrix(columns, rows);
		copyContent(newMatrix, -1, rowNumber);
		this.matrix = newMatrix;
	}
	
	@Override
	public void removeColumAndRow(int colRowNumber) {
		Util.checkInvalidColumnRowIndex(this, colRowNumber);
		
		this.columns = this.columns - 1;
		this.rows = this.rows - 1;
		T[][] newMatrix = fabricateMatrix(columns, rows);
		copyContent(newMatrix, colRowNumber, colRowNumber);
		this.matrix = newMatrix;
	}
	
	@Override
	public T get(int column, int row) {
		Util.checkInvalidColumnIndex(this, column);
		Util.checkInvalidRowIndex(this, row);
		
		return (T) matrix[column][row];
	}
	
	@Override
	public T set(int column, int row, T obj) {
		Util.checkInvalidColumnIndex(this, column);
		Util.checkInvalidColumnRowIndex(this, row);
		
		T previousObj = matrix[column][row];
		matrix[column][row] = obj;
		return previousObj;
	}
	
	@Override
	public boolean checkEmpty(T obj) {
		if(obj == null && representsEmpty() == null){
			return true;
		}
		return obj.equals(representsEmpty());
	}
	
	//XXX PROTECTED METHODS
	@SuppressWarnings("unchecked")
	protected T[][] fabricateMatrix(int columns, int rows){		
		return (T[][]) Array.newInstance(Object.class, columns, rows);
	}
	
	//XXX PRIVATE METHODS
	private void copyContent(T[][] newMatrix, int removedColumn, int removedRow) {
		for (int column = 0; column < matrix.length; column++) {
			if(column == removedColumn){
				continue;
			}
			
			for (int row = 0; row < matrix[column].length; row++) {
				if(row == removedRow){
					continue;
				}
				
				newMatrix[column][row] = matrix[column][row];
			}
		}
	}
	
	@Override
	public String toString() {
		return matrixParser.toString(this);
	}
	
	//XXX GETTERS
	@Override
	public int getColumnCount() {
		return columns;
	}
	
	@Override
	public int getRowCount() {
		return rows;
	}
}
