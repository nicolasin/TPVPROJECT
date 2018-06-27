package es.nicolas.uca.tpv.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PrincipalController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String paginaPrincipal() {
		return "redirect:/admin";
	}
	
}
