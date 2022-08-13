package fr.mdalle.tenisu.controller;

import fr.mdalle.tenisu.infra.PlayerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PlayerControllerIntegrationTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private PlayerRepository playerRepository;

  @SneakyThrows
  @Test
  @DisplayName("It should return 200 when calling /players")
  void should_return_ok() {
    // When + Then
    mockMvc.perform(get("/players")).andExpect(status().isOk());
  }

  @SneakyThrows
  @Test
  @DisplayName("It should return 200 with list of 5 players when calling /players")
  void should_return_ok_with_full_list() {
    // When + Then
    mockMvc.perform(get("/players")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(5));
  }

  @SneakyThrows
  @Test
  @DisplayName("It should return 200 with list of 5 players sorted by rank when calling /players")
  void should_return_ok_with_sorted_list() {
    // When + Then
    mockMvc
        .perform(get("/players"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].data.rank").value(1))
        .andExpect(jsonPath("$[1].data.rank").value(2))
        .andExpect(jsonPath("$[2].data.rank").value(10))
        .andExpect(jsonPath("$[3].data.rank").value(21))
        .andExpect(jsonPath("$[4].data.rank").value(52));
  }

  @SneakyThrows
  @Test
  @DisplayName("It should return 400 when calling /players/id with unsupported id format")
  void should_return_bad_request() {
    // When + Then
    mockMvc.perform(get("/players/unknown")).andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  @DisplayName("It should return 404 when calling /players/id with unknown player id")
  void should_return_not_found() {
    // When + Then
    mockMvc.perform(get("/players/100")).andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  @DisplayName(
      "It should return 200 with the corresponding player in the body when calling /players/id with id corresponding to a player")
  void should_return_player_info() {
    // When + Then
    mockMvc.perform(get("/players/52")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(52));
  }

  @SneakyThrows
  @Test
  @DisplayName(
      "It should return 200 with the statistics")
  void should_return_statistics() {
    // When + Then
    mockMvc.perform(get("/players/stats")).andExpect(status().isOk())
            .andExpect(jsonPath("$.highestWinrateCountry.code").value("USA"))
            .andExpect(jsonPath("$.averageBmi").value(23.34))
            .andExpect(jsonPath("$.medianHeight").value(185.0));
  }

  // Ran this method last because need the database cleared for this test and lack of time
  @SneakyThrows
  @Test
  @DisplayName(
      "It should return 500 when fetching stats with no players in database")
  void should_return_error_when_fetching_stats_with_no_players() {
    // Given
    playerRepository.clear();

    // When + Then
    mockMvc.perform(get("/players/stats")).andExpect(status().isInternalServerError());
  }
}
