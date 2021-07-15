package playground.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import playground.domain.Player;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PlayerRequest {
    private String name;

    private int backNumber;

    public PlayerRequest() {
    }

    public PlayerRequest(String name, int backNumber) {
        this.name = name;
        this.backNumber = backNumber;
    }

    public Player toEntity() {
        return new Player(this.name, this.backNumber);
    }

//    public String getName() {
//        return name;
//    }
//
//    public int getBackNumber() {
//        return backNumber;
//    }

}
