package com.br.gabriel.consultamedica.controller;


import com.br.gabriel.consultamedica.entidades.Consultas;
import com.br.gabriel.consultamedica.entidades.Medico;
import com.br.gabriel.consultamedica.service.ConsultaService;
import com.br.gabriel.consultamedica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consultas> agendar(@RequestBody Consultas consulta) {
        return ResponseEntity.ok(consultaService.agendarConsulta(consulta));
    }

    @GetMapping
    public ResponseEntity<List<Consultas>> listar() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultas> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarConsultaPorId(id));
    }

    @GetMapping("/data")
    public ResponseEntity<List<Consultas>> buscarPorData(@RequestParam LocalDate data) {
        return ResponseEntity.ok(consultaService.buscarConsultasPorData(data));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Consultas>> buscarPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(consultaService.buscarConsultasPorMedico(medicoId));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Consultas>> buscarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaService.buscarConsultasPorPaciente(pacienteId));
    }

    @GetMapping("/agendadas")
    public ResponseEntity<List<Consultas>> listarConsultasAgendadas() {
        return ResponseEntity.ok(consultaService.listarConsultasAgendadas());
    }


    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }


}
