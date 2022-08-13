package fr.mdalle.tenisu.controller;

import fr.mdalle.tenisu.exception.TenisuException;
import fr.mdalle.tenisu.model.Player;
import fr.mdalle.tenisu.model.Stat;
import fr.mdalle.tenisu.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {
  private final PlayerService playerService;

  @GetMapping
  public List<Player> getPlayers() {
    return playerService.getPlayers();
  }

  @GetMapping("/{id}")
  public Player getPlayerById(@PathVariable String id) {
    try {
      return playerService.getPlayerbyId(id);
    } catch (TenisuException e) {
      throw new ResponseStatusException(e.getErrorStatus().getHttpStatus(), e.getMessage(), e);
    }
  }

  @GetMapping("/stats")
  public Stat getStats() {
    try {
      return playerService.getStats();
    } catch (TenisuException e) {
      throw new ResponseStatusException(e.getErrorStatus().getHttpStatus(), e.getMessage(), e);
    }
  }
}
