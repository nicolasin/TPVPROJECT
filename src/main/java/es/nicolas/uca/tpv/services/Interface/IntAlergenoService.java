package es.nicolas.uca.tpv.services.Interface;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import es.nicolas.uca.tpv.entity.Alergeno;
import es.nicolas.uca.tpv.entity.Componente;
import es.nicolas.uca.tpv.entity.Producto;
import es.nicolas.uca.tpv.exceptions.DataBaseException;
import es.nicolas.uca.tpv.exceptions.EntityNotFoundException;

@Service
public interface IntAlergenoService {

	public List<Alergeno> getAll();
	
	public List<Componente> getAllComponentesFromAlergeno(Long alergenoID);
	
	public Alergeno getById(Long id)throws EntityNotFoundException;
	
	public Alergeno getByName(String name)throws EntityNotFoundException;
	
	public void saveAlergeno(Alergeno alergeno);
	
	public void deleteAlergenoById(Long id) throws EntityNotFoundException, DataBaseException;

	public Set<Producto> getProductosFromAlergeno(@PathVariable(value = "id") Long id) throws EntityNotFoundException;
}
