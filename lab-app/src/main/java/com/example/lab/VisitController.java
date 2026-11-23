package com.example.lab;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

@Entity
class Visit {
    @Id @GeneratedValue Long id;
    String pod;
    Instant at = Instant.now();
    Visit() {}
    Visit(String pod) { this.pod = pod; }
}

interface VisitRepository extends JpaRepository<Visit, Long> {}

@RestController
public class VisitController {
    private final VisitRepository repo;
    VisitController(VisitRepository repo) { this.repo = repo; }

    @GetMapping("/")
    Map<String, Object> hit() {
        String pod = System.getenv().getOrDefault("HOSTNAME", "local");
        repo.save(new Visit(pod));
        return Map.of("answeredBy", pod, "totalVisits", repo.count());
    }
}
