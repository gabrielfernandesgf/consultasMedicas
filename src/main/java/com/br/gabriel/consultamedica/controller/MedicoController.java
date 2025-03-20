package com.br.gabriel.consultamedica.controller;


import com.br.gabriel.consultamedica.entidades.Medico;
import com.br.gabriel.consultamedica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> cadastrar(@RequestBody Medico medico){
        return ResponseEntity.ok(medicoService.cadastrarMedico(medico));
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listar() {
        return ResponseEntity.ok(medicoService.listarMedicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarMedico(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Medico>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(medicoService.buscarMedicoPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medico){
        return ResponseEntity.ok(medicoService.atualizarMedico(id, medico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }

}
