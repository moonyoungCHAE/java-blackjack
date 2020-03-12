package blackjack.domain.user;

import blackjack.domain.result.ResultType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private static final String MAX_PLAYER_ERR_MSG = "플레이어는 최대 5명입니다.";
    private static final String NULL_ERR_MSG = "플레이어의 이름이 없습니다.";
    public static final int MAX_PLAYER = 5;

    private final List<Player> players;

    public Players(List<String> names) {
        Objects.requireNonNull(names, NULL_ERR_MSG);

        if (names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(MAX_PLAYER_ERR_MSG);
        }

        players = new ArrayList<>();

        for (String name : names) {
            players.add(new Player(name));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }


    public void computeResult(Dealer dealer) {
        for (Player player : players) {
            player.createResult(dealer);
        }
    }

    public List<ResultType> getResult() {
        return players.stream()
                .map(Player::getResultType)
                .collect(Collectors.toList());
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<String> showCardsInfo() {
        return players.stream()
                .map(Player::showCards)
                .collect(Collectors.toList());
    }
}
