package com.example.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + 
				id + ", Tipo" + Cliente.class.getName()));
	}
}
