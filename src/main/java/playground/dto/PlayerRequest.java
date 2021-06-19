package playground.dto;

public class PlayerRequest {

    private String name;
    private int backNumber;

    public PlayerRequest() {}

    public PlayerRequest(String name, int backNumber) {
        this.name = name;
        this.backNumber = backNumber;
    }

    public String getName() {
        return name;
    }

    public int getBackNumber() {
        return backNumber;
    }
}
