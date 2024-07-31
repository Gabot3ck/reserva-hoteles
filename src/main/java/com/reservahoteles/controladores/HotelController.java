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

import com.reservahoteles.entidades.Hotel;
import com.reservahoteles.servicios.HotelService;

@Controller
@RequestMapping("/hoteles")
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@GetMapping("/nuevo")
	public String mostrarFormularioCrear(Model model) {
		model.addAttribute("hotel", new Hotel());
		return "hotel/crear";
	}
	
	@PostMapping("/guardar")
	public String guardarHotel(@ModelAttribute("hotel") Hotel hotel) {
		hotelService.guardar(hotel);
		return "redirect:/hoteles";
	}
	
	@GetMapping
	public String listarHoteles(Model model) {
		List<Hotel> hoteles = hotelService.obtenerTodos();
		model.addAttribute("hoteles", hoteles);
		return "hotel/listar";
	}
	
	@GetMapping("/{id}")
	public String verHotel(@PathVariable Long id, Model model) {
		Hotel hotel = hotelService.obtenerPorId(id).orElse(null);
		model.addAttribute("hotel", hotel);
		return "hotel/ver";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarHotel(@PathVariable Long id, Model model) {
		hotelService.eliminar(id);
		return "redirect:/hoteles";
	}
}
