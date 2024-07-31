package com.reservahoteles.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservahoteles.entidades.Hotel;
import com.reservahoteles.repositorios.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	public List<Hotel> obtenerTodos(){
		return hotelRepository.findAll();
	}
	
	public Optional<Hotel> obtenerPorId(Long id){
		return hotelRepository.findById(id);
	}
	
	public Hotel guardar(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public void eliminar(Long id) {
		hotelRepository.deleteById(id);
	}
}
