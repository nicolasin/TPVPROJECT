package es.nicolas.uca.tpv.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import es.nicolas.uca.tpv.Repository.AlergenoDAO;
import es.nicolas.uca.tpv.Repository.CategoriaDAO;
import es.nicolas.uca.tpv.Repository.ComponenteDAO;
import es.nicolas.uca.tpv.Repository.ProductoDAO;
import es.nicolas.uca.tpv.entity.Alergeno;
import es.nicolas.uca.tpv.entity.Categoria;
import es.nicolas.uca.tpv.entity.Componente;
import es.nicolas.uca.tpv.entity.Producto;
import es.nicolas.uca.tpv.services.Interface.IUploadFileService;

@Controller
@SessionAttributes("alergeno")

public class AdminControllers {
	private static final String upload_images = "/imagenes";
	private static final String FolderAlergeno = "/alergenos/";
	private static final String FolderCategoria = "/categorias/";

	@Autowired
	private AlergenoDAO alergenoDAO;
	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private CategoriaDAO categoriaDAO;
	@Autowired
	private ComponenteDAO componenteDAO;

	// PRINCIPAL
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String paginaPrincipalAdmin(Model model) {
		model.addAttribute("productos", productoDAO.findAll());
		return "admin/admin_index";
	}

	// PRODUCTOS
	@RequestMapping(value = "/admin/producto", method = RequestMethod.GET)
	public String paginaPrincipalAdminProducto(Model model) {
		List<Producto> productos = (List<Producto>) productoDAO.findAll();
		if (productos.isEmpty()) {
			model.addAttribute("info", "No exite ningun atributo");
		}
		model.addAttribute("productos", productoDAO.findAll());
		return "admin/admin_producto";
	}

	@RequestMapping(value = "/admin/producto/{id}", method = RequestMethod.GET)
	public String mostrarProducto(@PathVariable(value = "id") Long id, Model model) {
		Producto producto = productoDAO.findOne(id);
		if (producto == null) {
			model.addAttribute("error", "No existe el producto con id " + id);
			return "admin/admin_producto";
		}
		model.addAttribute("producto", productoDAO.findOne(id));
		return "admin/admin_producto";
	}

	@RequestMapping(value = "/admin/producto/alergeno/{id}")
	public String mostrarProductosConUnAlergeno(@PathVariable(value = "id") Long id, Model model) {

		Alergeno alergeno = alergenoDAO.findOne(id);
		if (alergeno == null) {
			model.addAttribute("error", "No existe el alergeno con id " + id);
			return "admin/admin_producto";
		}
		List<Producto> productos = (List<Producto>) productoDAO.findAll();
		List<Producto> productosConAlergeno = new ArrayList<Producto>();

		for (Producto prod : productos) {
			if (prod.getAlergenosFromProducto().contains(alergeno)) {
				productosConAlergeno.add(prod);
			}
		}
		if (productosConAlergeno.isEmpty()) {
			model.addAttribute("info", "No existen productos con este alergeno");
		}
		Map<Producto, Set<Componente>> prodComp = new HashMap<>();
		for (Producto prod : productosConAlergeno) {
			prodComp.put(prod, prod.getComponentes());
		}
		model.addAttribute("productos", productosConAlergeno);
		return "admin/admin_producto";
	}

	@RequestMapping(value = "/admin/producto/categoria/{id}")
	public String mostrarProductosPorCategoria(@PathVariable(value = "id") Long id, Model model) {
		Categoria categoria = categoriaDAO.findOne(id);
		List<Producto> productos = (List<Producto>) productoDAO.findAll();
		List<Producto> productosCategorias = productos.stream().filter(x -> x.getCategorias().contains(categoria))
				.collect(Collectors.toList());

		model.addAttribute("productos", productosCategorias);
		return "admin/admin_producto";
	}

	// ALERGENOS
	@RequestMapping(value = "/admin/alergeno", method = RequestMethod.GET)
	public String indexAlergeno(Model model) {
		model.addAttribute("alergeno", new Alergeno());
		model.addAttribute("titulo", "Alergenos");
		model.addAttribute("alergenos", alergenoDAO.findAll());
		return "admin/admin_alergeno";
	}

	@RequestMapping(value = "/admin/alergeno/add", method = RequestMethod.POST)
	public String addAlergeno(Alergeno alergeno, Model model, @RequestParam("file") MultipartFile foto,
			SessionStatus status) {

		if (!foto.isEmpty() && alergeno != null) {
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copyTo(FolderAlergeno, foto);
			} catch (IOException e) {
				model.addAttribute("error", "Error al guardar la foto");
				e.printStackTrace();
				return "admin/admin_alergeno";
			}
			alergeno.setFoto(upload_images + FolderAlergeno + uniqueFilename);
			alergenoDAO.save(alergeno);
			model.addAttribute("success", "Alergeno guardado con exito");
			status.setComplete();
		} else {
			model.addAttribute("error", "Error al guardar el alergeno, necesita foto y nombre");

		}
		model.addAttribute("alergeno", new Alergeno());
		model.addAttribute("titulo", "Alergenos");
		model.addAttribute("alergenos", alergenoDAO.findAll());
		return "admin/admin_alergeno";
	}

	@RequestMapping(value = "/admin/alergeno/delete", method = RequestMethod.POST)
	public String deleteAlergeno(@RequestParam("id") Long id, Model model) {
		Alergeno alergeno = alergenoDAO.findOne(id);
		if (alergeno == null) {
			model.addAttribute("error", "No existe ningun alergeno con esta id");
		} else if (!alergeno.getComponentes().isEmpty()) {
			model.addAttribute("warning", "No se puede eliminar este alergeno, esta asociado a varios componentes");
		} else {
			model.addAttribute("success", "alergeno eliminado con exito");
			alergenoDAO.delete(id);
		}

		model.addAttribute("alergeno", new Alergeno());
		model.addAttribute("titulo", "Alergenos");
		model.addAttribute("alergenos", alergenoDAO.findAll());
		return "admin/admin_alergeno";
	}

	// CATEGORIAS
	@RequestMapping(value = "/admin/categoria", method = RequestMethod.GET)
	public String indexCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("titulo", "categorias");
		model.addAttribute("categorias", categoriaDAO.findAll());
		return "admin/admin_categoria";
	}

	@RequestMapping(value = "/admin/categoria/add", method = RequestMethod.POST)
	public String addCategoria(Categoria categoria, Model model, @RequestParam("file") MultipartFile foto,
			SessionStatus status) {

		if (!foto.isEmpty() && categoria != null) {
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copyTo(FolderCategoria, foto);
			} catch (IOException e) {
				model.addAttribute("error", "Error al guardar la foto");
				e.printStackTrace();
				return "admin/admin_categoria";
			}
			categoria.setFoto(upload_images + FolderCategoria + uniqueFilename);
			categoriaDAO.save(categoria);
			status.setComplete();
		} else {
			model.addAttribute("error", "Error al guardar la Categoria, necesita foto y nombre");

		}

		model.addAttribute("categoria", new Categoria());
		model.addAttribute("titulo", "categorias");
		model.addAttribute("categorias", categoriaDAO.findAll());
		return "admin/admin_categoria";
	}

	@RequestMapping(value = "/admin/categoria/delete", method = RequestMethod.POST)
	public String deletecategoria(@RequestParam("id") Long id, Model model) {
		Categoria categoria = categoriaDAO.findOne(id);
		if (categoria == null) {
			model.addAttribute("error", "No existe ningun alergeno con esta id");
		} else {
			categoriaDAO.delete(id);
			model.addAttribute("success", "alergeno eliminado con exito");
		}
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("titulo", "categorias");
		model.addAttribute("categorias", categoriaDAO.findAll());
		return "admin/admin_categoria";
	}

	// COMPONENTES
	@GetMapping(value = "/admin/componente")
	public String indexComponente(Model model) {
		model.addAttribute("alergenos", alergenoDAO.findAll());
		model.addAttribute("componentes", componenteDAO.findAll());
		return "admin/admin_componente";
	}

	@PostMapping("/admin/componente/buscar")
	public String buscarComponentes(@RequestParam("id") Long id, @RequestParam("nombre") String nombre, Model model) {
		List<Componente> componentes = (List<Componente>) componenteDAO.findAll();
		List<Componente> componentesFiltrados = new ArrayList<Componente>();
		if (id > 0) {
			Alergeno alergeno = alergenoDAO.findOne(id);
			componentesFiltrados.addAll(componentes.stream()
					.filter(x -> x.getAlergenos().contains(alergeno)
							&& x.getNombre().toLowerCase().contains(nombre.toLowerCase()))
					.collect(Collectors.toList()));
		} else {
			componentesFiltrados
					.addAll(componentes.stream().filter(x -> x.getNombre().toLowerCase().contains(nombre.toLowerCase()))
							.collect(Collectors.toList()));
		}

		model.addAttribute("alergenos", alergenoDAO.findAll());
		model.addAttribute("componentes", componentesFiltrados);
		return "admin/admin_componente";
	}

	@GetMapping("/admin/componente/add")
	public String agregarComponente(Model model) {
		model.addAttribute("componente", new Componente());
		model.addAttribute("alergenos", alergenoDAO.findAll());
		return "layouts/componente_formulario";
	}

	@PostMapping("/admin/componente/alter")
	public String modifyComponente(@RequestParam("id") Long id, Model model) {
		model.addAttribute("componente", componenteDAO.findOne(id));
		model.addAttribute("alergenos", alergenoDAO.findAll());
		return "layouts/componente_formulario";

	}

	@PostMapping("/admin/componente/save")
	public String saveComponente(Componente componente, @RequestParam("alergenosCheck") Long[] alergenosId,
			Model model) {
		System.out.println(componente.getNombre() + " " + componente.getStock());
		for (Long id : alergenosId) {
			if (id > 0) {
				Alergeno alergeno = alergenoDAO.findOne(id);
				System.out.println(alergeno.getNombre());
				componente.addAlergeno(alergeno);
			}

		}
		componenteDAO.save(componente);
		model.addAttribute("success", "Componente guardado con exito");
		model.addAttribute("componentes", componenteDAO.findAll());
		model.addAttribute("alergenos", alergenoDAO.findAll());
		return "admin/admin_componente";
	}
	@PostMapping("/admin/componente/delete")
	public String deleteComponente(@RequestParam("id")Long id, Model model) {
		Componente componente = componenteDAO.findOne(id);
		if(!componente.getProductos().isEmpty()) {
			model.addAttribute("error", "No se puede eliminar el componente, está siendo usado por un producto");
		}else {
			componenteDAO.delete(id);
			model.addAttribute("success", "Componente eliminado con exito");
		}
		
		model.addAttribute("componentes", componenteDAO.findAll());
		model.addAttribute("alergenos", alergenoDAO.findAll());
		return "admin/admin_componente";
	}

}
