package es.nicolas.uca.tpv.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javassist.NotFoundException;

public class EntityNotFoundException extends NotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6295218079073345908L;
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public EntityNotFoundException(String msg) {
		super(msg);
		log.error(msg);
	}
	
}
