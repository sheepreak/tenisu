package fr.mdalle.tenisu.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatFactoryTest {

    @Test
    @DisplayName("Should return best winning rate country")
    void should_return_best_country() {
        // Given
        List<Player> players = givenUnsortedPlayers();

        // When
        Stat stat = StatFactory.fromPlayers(players);

        // Then
        assertEquals("winner", stat.highestWinrateCountry().code());
    }

    @Test
    @DisplayName("Should return average BMI")
    void should_return_average_bmi() {
        // Given
        List<Player> players = givenUnsortedPlayers();

        // When
        Stat stat = StatFactory.fromPlayers(players);

        // Then
        assertEquals(22.15, stat.averageBmi());
    }

    @Test
    @DisplayName("Should return median height even players")
    void should_return_even_players_median_height() {
        // Given
        List<Player> players = givenUnsortedPlayers();

        // When
        Stat stat = StatFactory.fromPlayers(players);

        // Then
        assertEquals(185, stat.medianHeight());
    }

    @Test
    @DisplayName("Should return median height odd players")
    void should_return_odd_players_median_height() {
        // Given
        List<Player> players = givenUnsortedOddPlayers();

        // When
        Stat stat = StatFactory.fromPlayers(players);

        // Then
        assertEquals(180, stat.medianHeight());
    }

    private List<Player> givenUnsortedPlayers() {
        Player player1 =
                new Player(new Country("winner", ""), new Data(25, 170, List.of(1,1,1,1,1), 1233, 7, 75000), null, 1, null, null, null, null);
        Player player2 =
                new Player(new Country("winner", ""), new Data(25, 180, List.of(1,0,0,0,0), 1233, 1, 75000), null, 2, null, null, null, null);
        Player player3 =
                new Player(new Country("loser1", ""), new Data(25, 190, List.of(1,1,1,1,1), 1233, 4, 75000), null, 3, null, null, null, null);
        Player player4 =
                new Player(new Country("loser2", ""), new Data(25, 200, List.of(1,1,1,1,1), 1233, 9, 75000), null, 4, null, null, null, null);
        return List.of(player1, player2, player3, player4);
    }

    private List<Player> givenUnsortedOddPlayers() {
        Player player1 =
                new Player(new Country("winner", ""), new Data(25, 160, List.of(1,1,1,1,1), 1233, 7, 75000), null, 1, null, null, null, null);
        Player player2 =
                new Player(new Country("winner", ""), new Data(25, 170, List.of(1,0,0,0,0), 1233, 1, 75000), null, 2, null, null, null, null);
        Player player3 =
                new Player(new Country("loser1", ""), new Data(25, 180, List.of(1,1,1,1,1), 1233, 4, 75000), null, 3, null, null, null, null);
        Player player4 =
                new Player(new Country("loser2", ""), new Data(25, 190, List.of(1,1,1,1,1), 1233, 9, 75000), null, 4, null, null, null, null);
        Player player5 =
                new Player(new Country("loser2", ""), new Data(25, 200, List.of(1,1,1,1,1), 1233, 9, 75000), null, 5, null, null, null, null);
        return List.of(player1, player2, player3, player4, player5);
    }
}