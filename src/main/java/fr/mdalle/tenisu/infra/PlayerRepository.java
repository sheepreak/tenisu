package fr.mdalle.tenisu.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mdalle.tenisu.exception.ErrorStatus;
import fr.mdalle.tenisu.exception.TenisuException;
import fr.mdalle.tenisu.model.Player;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PlayerRepository {
  // Set serves as a database
  private final Set<Player> players = new HashSet<>();

  // Initializing the dataset when instanciating this bean
  public PlayerRepository(ObjectMapper objectMapper) throws IOException {
    players.addAll(
        Arrays.stream(
                objectMapper.readValue(
                    new ClassPathResource("dataset.json").getInputStream(), Player[].class))
            .toList());
  }

  // Clear method for testing, would not be present if database (would control the databse state through scripts on test methods)
  public void clear() {
    players.clear();
  }

  public List<Player> getPlayers() {
    return players.stream().toList();
  }

  public Player getPlayerById(int id) {
    return players.stream()
        .filter(player -> player.id() == id)
        .findFirst()
        .orElseThrow(() -> new TenisuException(ErrorStatus.NOT_FOUND, String.valueOf(id)));
  }
}
