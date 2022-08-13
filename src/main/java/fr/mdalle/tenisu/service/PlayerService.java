package fr.mdalle.tenisu.service;

import fr.mdalle.tenisu.exception.ErrorStatus;
import fr.mdalle.tenisu.exception.TenisuException;
import fr.mdalle.tenisu.infra.PlayerRepository;
import fr.mdalle.tenisu.model.Player;
import fr.mdalle.tenisu.model.Stat;
import fr.mdalle.tenisu.model.StatFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
  private final PlayerRepository playerRepository;

  public List<Player> getPlayers() {
    return playerRepository.getPlayers().stream()
        .sorted(Comparator.comparingInt(player -> player.data().rank()))
        .toList();
  }

  public Player getPlayerbyId(String id) {
    try {
      return playerRepository.getPlayerById(Integer.parseInt(id));
    } catch (NumberFormatException e) {
      throw new TenisuException(ErrorStatus.NUMBER_FORMAT, id);
    }
  }

  public Stat getStats() {
    List<Player> players = playerRepository.getPlayers();

    if (players.isEmpty()) {
      throw new TenisuException(ErrorStatus.NO_PLAYERS);
    }

    return StatFactory.fromPlayers(players);
  }
}
