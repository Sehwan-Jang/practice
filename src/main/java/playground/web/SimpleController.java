package playground.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playground.application.SimpleService;
import playground.dto.ErrorDto;
import playground.dto.PlayerRequest;
import playground.dto.PlayerResponse;

import java.net.URI;
import java.util.List;

@RestController
public class SimpleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SimpleService service;

    public SimpleController(SimpleService service) {
        this.service = service;
    }

    @PostMapping("/players")
    public ResponseEntity<Void> addPlayer(@RequestBody PlayerRequest playerRequest) {
        logger.debug("added Player {}!!", playerRequest.getName());
        Long id = this.service.addPlayer(playerRequest);
        return ResponseEntity.created(URI.create("/players/" + id)).build();
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<PlayerResponse> getPlayer(@PathVariable Long id) {
        PlayerResponse player = this.service.getPlayer(id);
        return ResponseEntity.ok(player);
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerResponse>> getPlayers() {

        List<PlayerResponse> players = this.service.getAllPlayers();
        return ResponseEntity.ok(players);
    }
}
