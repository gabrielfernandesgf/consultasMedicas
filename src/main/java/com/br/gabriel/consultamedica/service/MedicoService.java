package com.br.gabriel.consultamedica.service;


import com.br.gabriel.consultamedica.entidades.Medico;
import com.br.gabriel.consultamedica.repositorio.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastrarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public Medico buscarMedico(Long id) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        if (medicoOptional.isPresent()) {
            return medicoOptional.get();
        } else {
            throw new RuntimeException("Médico não encontrado");
        }
    }

    public List<Medico> buscarMedicoPorNome(String nome) {
        return medicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Medico> buscarPorEspecialidade(String especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }

    public Medico atualizarMedico(Long id, Medico medicoAtualizado) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);

        if (medicoOptional.isPresent()) {
            Medico m = medicoOptional.get();
            m.setNome(medicoAtualizado.getNome());
            m.setEspecialidade(medicoAtualizado.getEspecialidade());
            m.setCrm(medicoAtualizado.getCrm());
            return medicoRepository.save(m);
        } else {
            throw new RuntimeException("Médico não encontrado");
        }
    }

    public void deletarMedico(Long id) {
        if (medicoRepository.existsById(id)) {
            medicoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Médico não encontrado");
        }
    }

}
