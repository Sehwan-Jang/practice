package playground.domain;

public class Player {

    private long id;
    private final String name;
    private final int backNumber;

    public Player(String name, int backNumber) {
        this.name = name;
        this.backNumber = backNumber;
    }

    public Player(long id, String name, int backNumber) {
        this.id = id;
        this.name = name;
        this.backNumber = backNumber;
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
