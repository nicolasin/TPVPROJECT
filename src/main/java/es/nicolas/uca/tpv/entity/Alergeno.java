package es.nicolas.uca.tpv.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PreDestroy;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.nicolas.uca.tpv.services.UploadFileServiceImpl;
import es.nicolas.uca.tpv.services.Interface.IUploadFileService;

@Entity
@Table(name = "alergenos")
public class Alergeno implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -7148442811240495379L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String foto;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "alergenos", fetch=FetchType.LAZY)
	private Set<Componente> componentes = new HashSet<>();

	public Alergeno() {

	}

	public Alergeno(String nombre, String foto) {
		this.nombre = nombre;
		this.foto = foto;
	}
	
	public Set<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(Set<Componente> componentes) {
		this.componentes = componentes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@PreDestroy
	private void eliminarFotoBD() {

		IUploadFileService uploadFileService = new UploadFileServiceImpl();
		uploadFileService.delete(foto);
	}

	public void addComponente(Componente componente) {
		if(!componentes.contains(componente)) {
			componentes.add(componente);
			for(Componente comp: componentes) {
				comp.addAlergeno(this);
			}
		}
		
	}

	public void removeComponente(Componente componente) {
		if(componentes.contains(componente)) {
			for(Componente comp : componentes) {
				comp.removeAlergeno(this);
			}
			componentes.remove(componente);		
	}
		
	}
	
	
}
