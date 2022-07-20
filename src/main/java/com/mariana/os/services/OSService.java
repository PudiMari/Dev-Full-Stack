package com.mariana.os.services;

import com.mariana.os.domain.OS;
import com.mariana.os.repositories.OSRepository;
import com.mariana.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OSService {

    @Autowired
    private OSRepository repository;

    public OS findById(Integer id) {
        Optional<OS> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + OS.class.getName()));
    }

    public List<OS> findAll(){
        return repository.findAll();
    }
}
