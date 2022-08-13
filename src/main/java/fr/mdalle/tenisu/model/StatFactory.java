package fr.mdalle.tenisu.model;

import fr.mdalle.tenisu.exception.ErrorStatus;
import fr.mdalle.tenisu.exception.TenisuException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class StatFactory {

  private StatFactory() {}

  public static Stat fromPlayers(List<Player> players) {
    return new Stat(highestWinrateCountry(players), averageBmi(players), medianHeight(players));
  }

  private static Country highestWinrateCountry(List<Player> players) {
    Map<Country, Integer> winPerCountry =
        players.stream()
            .collect(Collectors.groupingBy(Player::country, Collectors.summingInt(Player::wins)));

    return winPerCountry.entrySet().stream()
        .max(Comparator.comparingInt(Map.Entry::getValue))
        .orElseThrow(() -> new TenisuException(ErrorStatus.COMPUTATION_ISSUE, "best country"))
        .getKey();
  }

  private static double averageBmi(List<Player> players) {
    return players.stream()
        .mapToDouble(Player::bmi)
        .average()
        .orElseThrow(() -> new TenisuException(ErrorStatus.COMPUTATION_ISSUE, "average BMI"));
  }

  private static double medianHeight(List<Player> players) {
    List<Player> sortedPlayers =
        players.stream().sorted(Comparator.comparingInt(player -> player.data().height())).toList();
    return sortedPlayers.size() % 2 != 0
        ? computeOddMedian(sortedPlayers)
        : computeEvenMedian(sortedPlayers);
  }

  private static double computeEvenMedian(List<Player> sortedPlayers) {
    return ((double) (sortedPlayers.get((sortedPlayers.size() / 2) - 1).data().height())
            + (double) (sortedPlayers.get(sortedPlayers.size() / 2).data().height()))
        / 2;
  }

  private static double computeOddMedian(List<Player> sortedPlayers) {
    return sortedPlayers.get(sortedPlayers.size() / 2).data().height();
  }
}
