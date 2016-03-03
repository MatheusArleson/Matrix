package br.com.xavier.matrix.abstraction;

import java.lang.reflect.Array;

import br.com.xavier.matrix.abstraction.parser.AbstractMatrixParser;
import br.com.xavier.matrix.impl.parser.DefaultMatrixParser;
import br.com.xavier.matrix.interfaces.Matrix;
import br.com.xavier.matrix.interfaces.parser.MatrixParser;
import br.com.xavier.matrix.validation.MatrixValidator;

public abstract class AbstractMatrix<T> implements Matrix<T> {
	
	//XXX CLASS PROPERTIES
	private MatrixParser<T> matrixParser;
	
	private int columns;
	private int rows;
	protected T[][] matrix;
    
    //XXX CONSTRUCTOR
	public AbstractMatrix() {
		this(0, 0);
	}
	
	public AbstractMatrix(int columns, int rows) {
		this(columns, rows, null);
	}
	
	public AbstractMatrix(int columns, int rows, AbstractMatrixParser<T> parser) {
		MatrixValidator.checkInvalidRowColumnSize(columns);
		MatrixValidator.checkInvalidRowColumnSize(rows);
		
		this.matrixParser = parser;
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
	public void addColumAndRow() {
		T[][] newMatrix = fabricateMatrix(columns+1, rows+1);
		copyContent(newMatrix, -1, -1);
		
		this.columns = this.columns + 1;
		this.rows = this.rows + 1;
		this.matrix = newMatrix;
	}
	
	@Override
	public void removeColumn(int columnNumber) {
		MatrixValidator.checkInvalidColumnIndex(this, columnNumber);
		
		this.columns = this.columns - 1;
		T[][] newMatrix = fabricateMatrix(columns, rows);
		copyContent(newMatrix, columnNumber, -1);
		this.matrix = newMatrix;
	}
	
	@Override
	public void removeRow(int rowNumber) {
		MatrixValidator.checkInvalidRowIndex(this, rowNumber);
		
		this.rows = this.rows - 1;
		T[][] newMatrix = fabricateMatrix(columns, rows);
		copyContent(newMatrix, -1, rowNumber);
		this.matrix = newMatrix;
	}
	
	@Override
	public void removeColumAndRow(int colRowNumber) {
		MatrixValidator.checkInvalidColumnRowIndex(this, colRowNumber);
		
		T[][] newMatrix = fabricateMatrix(columns-1, rows-1);
		copyContent(newMatrix, colRowNumber, colRowNumber);
		
		this.columns = this.columns - 1;
		this.rows = this.rows - 1;
		
		this.matrix = newMatrix;
	}
	
	@Override
	public T get(int column, int row) {
		MatrixValidator.checkInvalidColumnIndex(this, column);
		MatrixValidator.checkInvalidRowIndex(this, row);
		
		return (T) matrix[column][row];
	}
	
	@Override
	public T set(int column, int row, T obj) {
		MatrixValidator.checkInvalidColumnIndex(this, column);
		MatrixValidator.checkInvalidColumnRowIndex(this, row);
		
		T previousObj = matrix[column][row];
		matrix[column][row] = obj;
		return previousObj;
	}
	
	@Override
	public boolean checkEmpty(T obj) {
		if(obj == null){
			return ( representsEmpty() == null );
		}
		
		return obj.equals(representsEmpty());
	}
	
	@Override
	public MatrixParser<T> getMatrixParser() {
		return matrixParser;
	}
	
	@Override
	public void setMatrixParser(MatrixParser<T> parser) {
		this.matrixParser = parser;
	}
	
	//XXX PROTECTED METHODS
	@SuppressWarnings("unchecked")
	protected T[][] fabricateMatrix(int columns, int rows){
		return (T[][]) Array.newInstance(Object.class, columns, rows);
	}
	
	//XXX PRIVATE METHODS
	private void copyContent(T[][] newMatrix, int removedColumn, int removedRow) {
		int p = 0;
		for (int i = 0; i < rows; ++i) {
			if (i == removedRow) {
				continue;
			}

			int q = 0;
			for (int j = 0; j < columns; ++j) {
				if (j == removedColumn) {
					continue;
				}

				newMatrix[p][q] = matrix[i][j];
				++q;
			}

			++p;
		}
	}
	
	@Override
	public String toString() {
		if(matrixParser == null){
			this.matrixParser = new DefaultMatrixParser<T>();
		}
		
		return matrixParser.toMatrixString(this);
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
