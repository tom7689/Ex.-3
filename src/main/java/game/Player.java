package game;

import java.awt.*;
import java.util.Comparator;

public class Player {
    private String name;
    private final Color color;
    private int aliveCells;

    public Player(Color pColor) {
        color = pColor;
    }
    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
    public void setName(String pName) {
        name = pName;
    }
    public void setAliveCells(int pCells) {
        aliveCells = pCells;
    }
    public int getAliveCells() {
        return aliveCells;
    }
    public static Comparator<Player> nameComparator = (o1, o2) -> {
        String playerName1 = o1.getName();
        String playerName2 = o2.getName();
        return playerName1.compareTo(playerName2);
    };
}
