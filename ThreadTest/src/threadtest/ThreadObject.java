package threadtest;

/**
 *
 * Aaron Tolbert-Smith
 */
public class ThreadObject {

    private String name;
    private String type;
    private long burst;

    private long startTime;
    private long elapsedTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getBurst() {
        return burst;
    }

    public void setBurst(long burst) {
        this.burst = burst;
    }
}
