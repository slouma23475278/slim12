package com.example.rendez_vous.Controller;

import com.example.rendez_vous.entity.RendezVous;
import com.example.rendez_vous.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rendezvous")
public class RendezVousController {
    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RendezVous>> listRendezVous() {
        return new ResponseEntity<>(rendezVousService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RendezVous> getById(@PathVariable Long id) {
        return rendezVousService.findById(id)
                .map(rendezVous -> new ResponseEntity<>(rendezVous, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RendezVous> create(@RequestBody RendezVous rendezVous) {
        return new ResponseEntity<>(rendezVousService.save(rendezVous), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RendezVous> update(@PathVariable Long id, @RequestBody RendezVous rendezVous) {
        Optional<RendezVous> updated = rendezVousService.update(id, rendezVous);
        return updated
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/notification-health", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> notificationHealth() {
        return new ResponseEntity<>(rendezVousService.checkNotificationService(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return rendezVousService.deleteById(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
