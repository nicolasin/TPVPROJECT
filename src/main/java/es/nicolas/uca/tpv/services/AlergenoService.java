package es.nicolas.uca.tpv.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.nicolas.uca.tpv.Repository.AlergenoDAO;
import es.nicolas.uca.tpv.entity.Alergeno;
import es.nicolas.uca.tpv.entity.Componente;
import es.nicolas.uca.tpv.entity.ComponenteProducto;
import es.nicolas.uca.tpv.entity.Producto;
import es.nicolas.uca.tpv.exceptions.DataBaseException;
import es.nicolas.uca.tpv.exceptions.EntityNotFoundException;
import es.nicolas.uca.tpv.services.Interface.IntAlergenoService;

@Service
public class AlergenoService implements IntAlergenoService {

	@Autowired
	AlergenoDAO alergenoDAO;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional(readOnly = true)
	public List<Alergeno> getAll() {
		log.info("devolver lista de alergenos");
		return (List<Alergeno>) alergenoDAO.findAll();
	}

	@Override
	@Transactional
	public List<Componente> getAllComponentesFromAlergeno(Long alergenoID) {
		Alergeno alergeno = alergenoDAO.findOne(alergenoID);
		List<Componente> componentes = new ArrayList<Componente>();
		log.info("devolver lista de componentes del alergeno con id: " + alergenoID);
		for (Componente compo : alergeno.getComponentes()) {
			componentes.add(compo);
		}
		return componentes;

	}

	@Override
	@Transactional
	public Alergeno getById(Long id) throws EntityNotFoundException {
		Alergeno alergeno = alergenoDAO.findOne(id);
		if (alergeno != null) {
			log.info("devolver alergeno " + alergeno.getNombre());
			return alergeno;
		}
		throw new EntityNotFoundException(
				String.format("The %s with id: %d not found", Alergeno.class.getSimpleName(), id));
	}

	@Override
	@Transactional
	public Alergeno getByName(String name) throws EntityNotFoundException {
		Alergeno alergeno = alergenoDAO.findAlergenoByNombre(name);
		if (alergeno != null) {
			log.info("devolver alergeno " + alergeno.getNombre());
			return alergeno;
		}
		throw new EntityNotFoundException(
				String.format("The %s with name: %s not found", Alergeno.class.getSimpleName(), name));
	}

	@Override
	@Transactional
	public void saveAlergeno(Alergeno alergeno) {
		alergenoDAO.save(alergeno);
	}

	@Override
	@Transactional
	public void deleteAlergenoById(Long id) throws EntityNotFoundException, DataBaseException {
		if (!alergenoDAO.exists(id)) {
			throw new EntityNotFoundException(
					String.format("The %s with id: %d not found", Alergeno.class.getSimpleName(), id));
		} else if (!alergenoDAO.findOne(id).getComponentes().isEmpty()) {
			throw new DataBaseException(String.format("The %s with id: cant be delete, Constranits Exception",
					Alergeno.class.getSimpleName(), id));
		} else {
			alergenoDAO.delete(id);
			log.info("Eliminado el alergeno con id " + id);
		}

	}

	@Override
	public Set<Producto> getProductosFromAlergeno(Long id) throws EntityNotFoundException {
		if (!alergenoDAO.exists(id)) {
			throw new EntityNotFoundException(
					String.format("The %s with id: %d not found", Alergeno.class.getSimpleName(), id));
		} else {
			Alergeno alergeno = alergenoDAO.findOne(id);
			Set<Producto> productos = new HashSet<Producto>();

			for (Componente componente : alergeno.getComponentes()) {
				for (ComponenteProducto prod : componente.getProductos()) {
					productos.add(prod.getProducto());
				}
			}
			return productos;
		}

	}

}
