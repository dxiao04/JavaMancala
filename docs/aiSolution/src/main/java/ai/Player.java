package ai;
public class Player {
    private String name;
    private int id;

    public Player(){
        this.name = null;
        this.id = -1;
    }
    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}