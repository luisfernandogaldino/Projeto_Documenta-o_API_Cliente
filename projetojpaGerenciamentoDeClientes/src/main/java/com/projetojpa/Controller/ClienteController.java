package com.projetojpa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.Service.ClienteService;
import com.projetojpa.entities.Cliente;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cliente", description = "API REST DE GERENCIAMENTO DE CLIENTE")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza cliente por ID")

	public ResponseEntity<Cliente> buscaClienteControlId(@PathVariable Long id) {
		Cliente cliente = clienteService.buscaClienteId(id);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os cliente")

	public ResponseEntity<List<Cliente>> buscaTodosClienteControl() {
		List<Cliente> Cliente = clienteService.buscaTodosCliente();
		return ResponseEntity.ok(Cliente);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um cliente")

	public ResponseEntity<Cliente> salvaClienteControl(@RequestBody @Valid Cliente cliente) {
		Cliente salvaCliente = clienteService.salvaCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCliente);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera um cliente")

	public ResponseEntity<Cliente> alteraClienteControl(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
		Cliente alteraCLiente = clienteService.alterarCliente(id, cliente);
		if (alteraCLiente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um cliente")
	public ResponseEntity<Cliente> apagaClienteControl(@PathVariable Long id) {
		boolean apagar = clienteService.apagarCliente(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
