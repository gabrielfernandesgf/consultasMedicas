package com.br.gabriel.consultamedica.repositorio;


import com.br.gabriel.consultamedica.entidades.Consultas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ConsultasRepository extends JpaRepository<Consultas, Long> {

    List<Consultas> findByDataConsulta(LocalDate dataConsulta);

    List<Consultas> findByMedicoId(Long medicoId);

    List<Consultas> findByPacienteId(Long pacienteId);

    @Query("SELECT c FROM Consultas c WHERE c.status = 'AGENDADA'")
    List<Consultas> buscarConsultasAgendadas();

    @Query("SELECT c FROM Consultas c WHERE c.status = 'CANCELADA'")
    List<Consultas> buscarConsultasCanceladas();

    @Query("SELECT c FROM Consultas c WHERE c.status = 'REALIZADA'")
    List<Consultas> buscarConsultasRealizadas();

}
