package br.com.xavier.matrix.exception;

/**
 * 
 * Signals that a index is not part of the Matrix.
 * 
 * @author Matheus Xavier
 *
 */
public final class IllegalMatrixIndex extends RuntimeException {

	private static final long serialVersionUID = -220465570739804127L;
	
	/**
     * Constructs an {@code IllegalMatrixIndex} with {@code null}
     * as its error detail message.
     */
	public IllegalMatrixIndex() {
		super();
	}
	
	/**
	 * Constructs an {@code IllegalMatrixIndex} with the specified detail message.
	 *
	 * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method)
	 */
	public IllegalMatrixIndex(String message) {
		super(message);
	}
	
	 /**
     * Constructs an {@code IllegalMatrixIndex} with the specified detail message and cause.
     *
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method). 
     * (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     *
     */
	public IllegalMatrixIndex(Throwable throwable) {
		super(throwable);
	}
	
	 /**
     * Constructs an {@code IllegalMatrixIndex} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for IO exceptions that are little more
     * than wrappers for other throwables.
     *
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     * (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     *
     */
	public IllegalMatrixIndex(String message, Throwable throwable) {
		super(message, throwable);
	}
}
