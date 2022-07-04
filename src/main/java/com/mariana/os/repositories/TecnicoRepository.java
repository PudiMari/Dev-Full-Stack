package com.mariana.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mariana.os.domain.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

	@Query("SELECT obj FROM Tecnico obj Where obj.cpf =:cpf")
	Tecnico findByCPF(@Param("cpf") String cpf);

}
