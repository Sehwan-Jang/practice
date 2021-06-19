package playground.dto;

import playground.domain.Player;

public class PlayerResponse {

    private Long id;
    private final String name;
    private final int backNumber;

    public PlayerResponse(String name, int backNumber) {
        this.name = name;
        this.backNumber = backNumber;
    }

    public PlayerResponse(Long id, String name, int backNumber) {
        this.id = id;
        this.name = name;
        this.backNumber = backNumber;
    }

    public static PlayerResponse of(Player player) {
        return new PlayerResponse(player.getId(), player.getName(), player.getBackNumber());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBackNumber() {
        return backNumber;
    }
}
