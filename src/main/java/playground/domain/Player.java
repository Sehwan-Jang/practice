package playground.domain;

public class Player {
    private static final long DEFAULT_ID = 0;

    private long id;
    private final String name;
    private final int backNumber;

    public Player(String name, int backNumber) {
        this(DEFAULT_ID, name, backNumber);
    }

    public Player(long id, String name, int backNumber) {
        validateName(name);
        this.id = id;
        this.name = name;
        this.backNumber = backNumber;
    }

    public void validateName(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("이름은 5자를 넘을 수 없습니다.");
        }
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
