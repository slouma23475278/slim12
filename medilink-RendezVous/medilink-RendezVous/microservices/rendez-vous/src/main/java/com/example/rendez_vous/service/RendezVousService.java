package com.example.rendez_vous.service;

import com.example.rendez_vous.entity.RendezVous;
import com.example.rendez_vous.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RendezVousService {
    @Autowired
    private RendezVousRepository rendezVousRepository;

    public List<RendezVous> findAll() {
        return rendezVousRepository.findAll();
    }

    public Optional<RendezVous> findById(Long id) {
        return rendezVousRepository.findById(id);
    }

    public RendezVous save(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    public Optional<RendezVous> update(Long id, RendezVous payload) {
        return rendezVousRepository.findById(id).map(existing -> {
            existing.setDate(payload.getDate());
            existing.setStatus(payload.getStatus());
            return rendezVousRepository.save(existing);
        });
    }

    public boolean deleteById(Long id) {
        if (!rendezVousRepository.existsById(id)) {
            return false;
        }
        rendezVousRepository.deleteById(id);
        return true;
    }
}
