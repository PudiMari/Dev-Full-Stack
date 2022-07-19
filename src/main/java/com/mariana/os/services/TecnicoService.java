package com.mariana.os.services;

import java.util.List;
import java.util.Optional;

import com.mariana.os.domain.Pessoa;
import com.mariana.os.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariana.os.domain.Tecnico;
import com.mariana.os.dtos.TecnicoDTO;
import com.mariana.os.repositories.TecnicoRepository;
import com.mariana.os.services.exceptions.DataIntegratyViolationException;
import com.mariana.os.services.exceptions.ObjectNotFoundException;

import javax.validation.Valid;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {

        // Pode encontrar ou não
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        if (findByCPF(objDTO) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);

        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado!");
        }

        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);

        if (obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Técnico possui ordens de serviço, " +
                    "não pode ser deletado");
        }

        repository.deleteById(id);
    }

    private Pessoa findByCPF(TecnicoDTO objDTO) {
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());

        if (obj != null) {
            return obj;
        }
        return null;
    }


}
