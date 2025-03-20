package com.br.gabriel.consultamedica.controller;


import com.br.gabriel.consultamedica.entidades.Pacientes;
import com.br.gabriel.consultamedica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Pacientes> cadastrar(@RequestBody Pacientes paciente){
        return ResponseEntity.ok(pacienteService.cadastrarPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Pacientes>> listar() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pacientes> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPacientePorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Pacientes>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(pacienteService.buscarPacientePorNome(nome));
    }

    @GetMapping("/cpf")
    public ResponseEntity<Pacientes> buscarPorCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(pacienteService.buscarPacientePorCpf(cpf));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacientes> atualizar(@PathVariable Long id, @RequestBody Pacientes paciente){
        return ResponseEntity.ok(pacienteService.atualizarPaciente(id, paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }

}
