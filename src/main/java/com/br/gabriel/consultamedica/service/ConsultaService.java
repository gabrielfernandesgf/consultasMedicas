package com.br.gabriel.consultamedica.service;


import com.br.gabriel.consultamedica.entidades.Consultas;
import com.br.gabriel.consultamedica.repositorio.ConsultasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultasRepository consultasRepository;

    public Consultas agendarConsulta(Consultas consulta) {
        return consultasRepository.save(consulta);
    }

    public List<Consultas> listarConsultas() {
        return consultasRepository.findAll();
    }

    public Consultas buscarConsultaPorId(Long id) {
        Optional<Consultas> consultaOptional = consultasRepository.findById(id);
        if (consultaOptional.isPresent()) {
            return consultaOptional.get();
        } else {
            throw new RuntimeException("Consulta não encontrado");
        }
    }

    public List<Consultas> buscarConsultasPorData(LocalDate data) {
        return consultasRepository.findByDataConsulta(data);
    }

    public List<Consultas> buscarConsultasPorMedico(Long medicoId){
        return consultasRepository.findByMedicoId(medicoId);
    }

    public List<Consultas> buscarConsultasPorPaciente(Long pacienteId){
        return consultasRepository.findByPacienteId(pacienteId);
    }

    public List<Consultas> listarConsultasAgendadas() {
        return consultasRepository.buscarConsultasAgendadas();
    }

    public void cancelarConsulta(Long id) {
        Optional<Consultas> consultaOptional = consultasRepository.findById(id);

        if (consultaOptional.isPresent()) {
            Consultas consulta = consultaOptional.get();
            LocalDateTime agora = LocalDateTime.now();
            LocalDateTime dataHoraConsulta = LocalDateTime.of(consulta.getDataConsulta(), consulta.getHoraConsulta());

            if (dataHoraConsulta.isBefore(agora)) {
                throw new RuntimeException("Não é possivel cancelar a consulta que já passou.");
            }

            consulta.setStatus(Consultas.StatusConsulta.CANCELADA);
            consultasRepository.save(consulta);
        } else {
            throw new RuntimeException("Consulta não encontrada.");
        }
    }

}
