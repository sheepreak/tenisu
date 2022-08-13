package fr.mdalle.tenisu.service;

import fr.mdalle.tenisu.exception.ErrorStatus;
import fr.mdalle.tenisu.exception.TenisuException;
import fr.mdalle.tenisu.infra.PlayerRepository;
import fr.mdalle.tenisu.model.Data;
import fr.mdalle.tenisu.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class PlayerServiceTest {

  private final PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
  private PlayerService playerService = new PlayerService(playerRepository);

  @Test
  @DisplayName("Should return sorted list by rank")
  void should_return_sorted_list() {
    // Given
    Mockito.when(playerRepository.getPlayers()).thenReturn(givenUnsortedPlayers());

    // When
    List<Player> players = playerService.getPlayers();

    // Then
    Assertions.assertEquals(1, players.get(0).data().rank());
    Assertions.assertEquals(4, players.get(1).data().rank());
    Assertions.assertEquals(7, players.get(2).data().rank());
    Assertions.assertEquals(9, players.get(3).data().rank());
  }

  @Test
  @DisplayName("Should throw exception if invalid ID")
  void should_throw_exception_when_id_is_invalid() {
    // When + then
    TenisuException exception =
        Assertions.assertThrows(
            TenisuException.class, () -> playerService.getPlayerbyId("unknown"));
    Assertions.assertEquals(ErrorStatus.NUMBER_FORMAT, exception.getErrorStatus());
  }

  @Test
  @DisplayName("Should return player by id")
  void should_return_player_by_id() {
    // Given
    Mockito.when(playerRepository.getPlayerById(1)).thenReturn(givenPlayerById(1));

    // When
    Player player = playerService.getPlayerbyId("1");

    // Then
    Assertions.assertEquals(1, player.id());
  }

  private List<Player> givenUnsortedPlayers() {
    Player player1 =
        new Player(null, new Data(25, 180, null, 1233, 7, 75), null, 1, null, null, null, null);
    Player player2 =
        new Player(null, new Data(25, 180, null, 1233, 1, 75), null, 2, null, null, null, null);
    Player player3 =
        new Player(null, new Data(25, 180, null, 1233, 4, 75), null, 3, null, null, null, null);
    Player player4 =
        new Player(null, new Data(25, 180, null, 1233, 9, 75), null, 4, null, null, null, null);
    return List.of(player1, player2, player3, player4);
  }

  private Player givenPlayerById(int id) {
    return new Player(null, new Data(25, 180, null, 1233, 7, 75), null, id, null, null, null, null);
  }
}
