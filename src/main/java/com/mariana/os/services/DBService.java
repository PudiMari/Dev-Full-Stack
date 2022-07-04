package com.mariana.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariana.os.domain.Cliente;
import com.mariana.os.domain.OS;
import com.mariana.os.domain.Tecnico;
import com.mariana.os.domain.enuns.Prioridade;
import com.mariana.os.domain.enuns.Status;
import com.mariana.os.repositories.ClienteRepository;
import com.mariana.os.repositories.OSRepository;
import com.mariana.os.repositories.TecnicoRepository;

@Service
public class DBService {

	// Persistência pra instância do repositório
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Mariana Dias", "144.785.300-84", "(65)993434767");
		Tecnico t2 = new Tecnico(null, "Greicemara Oliveira", "641.760.040-88", "(65)992434767");
		Cliente c1 = new Cliente(null, "Katia Lorena", "598.508.200-80", "(65)992394134");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
