package com.mariana.os.services;

import com.mariana.os.domain.Cliente;
import com.mariana.os.domain.OS;
import com.mariana.os.domain.Tecnico;
import com.mariana.os.domain.enuns.Prioridade;
import com.mariana.os.domain.enuns.Status;
import com.mariana.os.dtos.OSDTO;
import com.mariana.os.repositories.OSRepository;
import com.mariana.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OSService {

    @Autowired
    private OSRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id) {
        Optional<OS> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + OS.class.getName()));
    }

    public List<OS> findAll() {
        return repository.findAll();
    }

    public OS create(OSDTO obj) {
        return fromDTO(obj);
    }


    public OS update(@Valid OSDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }

    private OS fromDTO(OSDTO obj) {
        OS newObj = new OS();
        newObj.setId(obj.getId());
        newObj.setObservacoes(obj.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));

        Tecnico tec = tecnicoService.findById(obj.getTecnico());

        Cliente cli = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tec);
        newObj.setCliente(cli);

        //Atualiza a data ao finalizar a ordem de serviço
        if (newObj.getStatus().getCod().equals(2)){
            newObj.setDataFechamento(LocalDateTime.now());
        }

        return repository.save(newObj);

    }
}
