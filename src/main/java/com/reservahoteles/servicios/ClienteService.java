package com.reservahoteles.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservahoteles.entidades.Cliente;
import com.reservahoteles.repositorios.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> obtenerTodos(){
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> obtenerPorId(Integer id){
		return clienteRepository.findById(id);
	}
	
	public Cliente guardar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void eliminar(Integer id) {
		clienteRepository.deleteById(id);
	}
}
