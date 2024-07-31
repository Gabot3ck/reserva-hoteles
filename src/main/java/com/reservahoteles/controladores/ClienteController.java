package com.reservahoteles.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reservahoteles.entidades.Cliente;
import com.reservahoteles.servicios.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	
	@GetMapping("/nuevo")
	public String mostrarFormularioCrear(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "cliente/crear";
	}
	
	@GetMapping
	public String listarClientes(Model model) {
		List<Cliente> clientes = clienteService.obtenerTodos();
		model.addAttribute("clientes", clientes);
		return "cliente/listar";
	}
	
	@PostMapping("/guardar")
	public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
		clienteService.guardar(cliente);
		return "redirect:/clientes";
	}
	
	@GetMapping("/{id}")
	public String verCliente(@PathVariable Integer id, Model model) {
		Cliente cliente = clienteService.obtenerPorId(id).orElse(null);
		model.addAttribute("cliente", cliente);
		return "cliente/ver";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarCliente(@PathVariable Integer id, Model model) {
		clienteService.eliminar(id);
		return "redirect:/clientes";
	}

}
