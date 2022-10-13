package db;

public class DataAccessException extends Exception {
	public DataAccessException(Exception e, String expl) {
		super(expl, e);
	}
}
