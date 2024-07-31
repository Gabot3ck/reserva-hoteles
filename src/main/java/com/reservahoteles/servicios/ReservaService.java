package com.reservahoteles.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservahoteles.entidades.Cliente;
import com.reservahoteles.entidades.Hotel;
import com.reservahoteles.entidades.Reserva;
import com.reservahoteles.repositorios.ClienteRepository;
import com.reservahoteles.repositorios.HotelRepository;
import com.reservahoteles.repositorios.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	public List<Reserva> obtenerTodas(){
		return  reservaRepository.findAll();
	}
	
	public Optional<Reserva> obtenerPorId(Long id){
		return reservaRepository.findById(id);
	}
	
	public Reserva guardar(Reserva reserva) {
		Cliente cliente = clienteRepository.findById(reserva.getCliente().getId()).orElseThrow();
		Hotel hotel = hotelRepository.findById(reserva.getHotel().getId()).orElseThrow();
		
		reserva.setCliente(cliente);
		reserva.setHotel(hotel);
		
		return reservaRepository.save(reserva);
	}
	
	public void eliminar(Long id) {
		reservaRepository.deleteById(id);
	}
	
	public boolean transferirReserva(Long idReserva, Integer idClienteDestino) {
		Optional<Reserva> reservaTransferencia = reservaRepository.findById(idReserva);
		
		if(reservaTransferencia.isPresent()) {
			Reserva reserva = reservaTransferencia.get();
			
			Optional<Cliente> clienteDestinoTransferencia = clienteRepository.findById(idClienteDestino);
			
			if(clienteDestinoTransferencia.isPresent()) {
				Cliente clienteDestino = clienteDestinoTransferencia.get();
				reserva.setCliente(clienteDestino);
				reservaRepository.save(reserva);
				return true;
			}
		}
		return false;
	}
}
