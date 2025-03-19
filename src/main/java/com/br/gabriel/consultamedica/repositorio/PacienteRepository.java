package com.br.gabriel.consultamedica.repositorio;

import com.br.gabriel.consultamedica.entidades.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Pacientes, Long> {

    List<Pacientes> findByNomeContainingIgnoreCase(String nome);

    Optional<Pacientes> findByCpf(String cpf);

    @Query("SELECT p FROM Pacientes p WHERE p.nome LIKE %:nome%")
    List<Pacientes> buscarPorNome(String nome);
}
