package es.nicolas.uca.tpv.exceptions;

import java.sql.SQLException;

public class DataBaseException  extends SQLException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1165095926246004688L;

	public DataBaseException(String message) {
		super(message);
	}

}
