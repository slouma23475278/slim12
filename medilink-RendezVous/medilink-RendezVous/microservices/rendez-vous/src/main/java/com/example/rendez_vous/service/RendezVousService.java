package com.example.rendez_vous.service;

import com.example.rendez_vous.client.NotificationClient;
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

    @Autowired(required = false)
    private NotificationClient notificationClient;

    public List<RendezVous> findAll() {
        return rendezVousRepository.findAll();
    }

    public Optional<RendezVous> findById(Long id) {
        return rendezVousRepository.findById(id);
    }

    public RendezVous save(RendezVous rendezVous) {
        RendezVous created = rendezVousRepository.save(rendezVous);
        notifyIfAvailable();
        return created;
    }

    public Optional<RendezVous> update(Long id, RendezVous payload) {
        return rendezVousRepository.findById(id).map(existing -> {
            existing.setDate(payload.getDate());
            existing.setStatus(payload.getStatus());
            RendezVous updated = rendezVousRepository.save(existing);
            notifyIfAvailable();
            return updated;
        });
    }

    public boolean deleteById(Long id) {
        if (!rendezVousRepository.existsById(id)) {
            return false;
        }
        rendezVousRepository.deleteById(id);
        return true;
    }

    private void notifyIfAvailable() {
        try {
            if (notificationClient != null) {
                notificationClient.ping();
            }
        } catch (Exception ignored) {
        }
    }
}
