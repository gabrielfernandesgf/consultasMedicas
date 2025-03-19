package com.br.gabriel.consultamedica.repositorio;


import com.br.gabriel.consultamedica.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findByNomeContainingIgnoreCase(String nome);

    List<Medico> findByEspecialidade(String especialidade);

    Optional<Medico> findByCrm(String crm);

    @Query("SELECT m FROM Medico m WHERE m.nome LIKE %:nome%")
    List<Medico> buscarPorNome(String nome);

}
