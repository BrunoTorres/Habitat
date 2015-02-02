package pt.uminho.lei.dss.db;

/**
 * PersistenceException
 *
 * @author Benjamim Sonntag
 */
public class PersistenceException extends Exception {
    private static final long serialVersionUID = 612126036422642289L;

    /**
     * Creates a new instance of <code>PersistenceException</code> without detail message.
     */
    public PersistenceException() {
    }

    /**
     * Constructs an instance of <code>PersistenceException</code> with the specified
     * detail message.
     * @param msg the detail message.
     */
    public PersistenceException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>PersistenceException</code> with the specified
     * detail message and cause.
     * @param msg the detail message.
     * @param cause the cause
     */
    public PersistenceException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    /**
     * Constructs an instance of <code>PersistenceException</code> with the specified
     * cause.
     * @param cause the cause
     */
    public PersistenceException(Throwable cause) {
        super(cause);
    }
    
}
