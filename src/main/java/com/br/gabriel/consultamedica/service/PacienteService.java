package com.br.gabriel.consultamedica.service;

import com.br.gabriel.consultamedica.entidades.Pacientes;
import com.br.gabriel.consultamedica.repositorio.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Pacientes cadastrarPaciente(Pacientes paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<Pacientes> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Pacientes buscarPacientePorId(Long id) {
        Optional<Pacientes> pacientesOptional = pacienteRepository.findById(id);
        if (pacientesOptional.isPresent()) {
            return pacientesOptional.get();
        } else {
            throw new RuntimeException("Paciente não encontrado");
        }
    }

    public List<Pacientes> buscarPacientePorNome(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Pacientes buscarPacientePorCpf(String cpf) {
        Optional<Pacientes> pacienteOptional = pacienteRepository.findByCpf(cpf);
        if (pacienteOptional.isPresent()) {
            return pacienteOptional.get();
        } else {
            throw new RuntimeException("Paciente não encontrado");
        }
    }

    public Pacientes atualizarPaciente(Long id, Pacientes pacienteAtualizado) {
        Optional<Pacientes> pacienteOptional = pacienteRepository.findById(id);

        if (pacienteOptional.isPresent()) {
            Pacientes paciente = pacienteOptional.get();
            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setCpf(pacienteAtualizado.getCpf());
            paciente.setDataNascimento(pacienteAtualizado.getDataNascimento());
            paciente.setTelefone(pacienteAtualizado.getTelefone());
            return pacienteRepository.save(paciente);
        } else {
            throw new RuntimeException("Paciente não encontrado");
        }
    }

    public void deletarPaciente(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Paciente não encontrado");
        }
    }

}
