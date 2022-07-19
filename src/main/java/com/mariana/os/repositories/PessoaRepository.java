package com.mariana.os.repositories;

import com.mariana.os.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    @Query("SELECT obj FROM Pessoa obj Where obj.cpf =:cpf")
    Pessoa findByCPF(@Param("cpf") String cpf);
}
