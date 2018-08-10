package es.nicolas.uca.tpv.ApiRestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.nicolas.uca.tpv.entity.Alergeno;
import es.nicolas.uca.tpv.entity.Componente;
import es.nicolas.uca.tpv.entity.Producto;
import es.nicolas.uca.tpv.exceptions.DataBaseException;
import es.nicolas.uca.tpv.exceptions.EntityNotFoundException;
import es.nicolas.uca.tpv.services.Interface.IntAlergenoService;

@RestController
@RequestMapping("/api/alergeno")

public class ARControllerAlergeno {
	@Autowired
	IntAlergenoService alergenoServ;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping()
	public List<Alergeno> getAllAlergenos() {
		List<Alergeno> alergenos = alergenoServ.getAll();
		return alergenos;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/{id}")
	public Alergeno getOneAlergeno(@PathVariable(value = "id") Long id) throws EntityNotFoundException {
		Alergeno alergeno = alergenoServ.getById(id);
		return alergeno;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/componentes/{id}")
	public List<Componente> getComponentesFromAlergeno(@PathVariable(value = "id") Long id) {
		return alergenoServ.getAllComponentesFromAlergeno(id);
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping(path = "/add", consumes = "application/json")
	public ResponseEntity<String> saveAlergeno(@RequestBody(required = true) Alergeno alergeno) {
		alergenoServ.saveAlergeno(alergeno);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAlergeno(@PathVariable(name = "id") Long id)
			throws EntityNotFoundException, DataBaseException {
		alergenoServ.deleteAlergenoById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/producto/{id}")
	public List<Producto> getProductosWithAlergeno(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
		List<Producto> productos = new ArrayList<Producto>();
		productos.addAll(alergenoServ.getProductosFromAlergeno(id));
		return productos;

	}

}
