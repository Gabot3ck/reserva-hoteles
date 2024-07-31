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
import org.springframework.web.bind.annotation.RequestParam;

import com.reservahoteles.entidades.Cliente;
import com.reservahoteles.entidades.Hotel;
import com.reservahoteles.entidades.Reserva;
import com.reservahoteles.servicios.ClienteService;
import com.reservahoteles.servicios.HotelService;
import com.reservahoteles.servicios.ReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private HotelService hotelService;
	
	
	@GetMapping("/nueva")
	public String mostrarHome(Model model) {
		model.addAttribute("reserva", new Reserva());
		
		List<Cliente> clientes = clienteService.obtenerTodos();
		List<Hotel> hoteles = hotelService.obtenerTodos();
		
		model.addAttribute("clientes", clientes);
		model.addAttribute("hoteles", hoteles);
		return "reserva/crear";
	}
	
	@PostMapping("/guardar")
	public String guardarReserva(@ModelAttribute("reserva") Reserva reserva) {
		reservaService.guardar(reserva);
		return "redirect:/reservas";
	}
	
	@GetMapping
	public String listarReservas(Model model) {
		List<Reserva> reservas = reservaService.obtenerTodas();
		model.addAttribute("reservas", reservas);
		return "reserva/listar";
	}
	
	@GetMapping("/transferir/{id}")
	public String mostrarFormTransferir(@PathVariable Long id, Model model) {
		Reserva reserva = reservaService.obtenerPorId(id).orElseThrow(null);
		List<Cliente> clientes = clienteService.obtenerTodos();
		
		model.addAttribute("reserva",reserva);
		model.addAttribute("clientes",clientes);
		
		return "reserva/transferir";
	}
	
	@PostMapping("/transferir")
	public String transferirReserva(@RequestParam Long idReserva, @RequestParam Integer clienteDestino) {
		String retornoVista = null;
		if(reservaService.transferirReserva(idReserva, clienteDestino)) {
			retornoVista = "redirect:/reservas";
		} else {
			System.out.println("no se puede transferir reserva");
		}
		return retornoVista;
	}
	
	@GetMapping("/{id}")
	public String verResrva(@PathVariable Long id, Model model) {
		Reserva reserva = reservaService.obtenerPorId(id).orElse(null);
		model.addAttribute(reserva);
		return "reserva/ver";
	}
	

}
