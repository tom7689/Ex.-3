package game;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    Player p1 = new Player(Color.red);

    @Test
    public void newPlayerHasColorRed() {
        assertEquals(Color.red, p1.getColor());
    }

    @Test
    public void playerNameIsSaved() {
        p1.setName("John");
        assertEquals("John", p1.getName());
    }

    @Test
    public void playerCellsAreSaved() {
        p1.setAliveCells(5);
        assertEquals(5, p1.getAliveCells());
    }
}
