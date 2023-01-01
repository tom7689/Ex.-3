package game;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    Cell c = new Cell();
    Player p = new Player(Color.red);

    @Test
    public void cellIsInitiallyDead() {
        assertFalse(c.isAlive());
    }

    @Test
    public void cellIsDeadWith1Neighbour() {
        c.setNextStatus(1);
        c.updateStatus();
        assertFalse(c.isAlive());;
    }

    @Test
    public void cellStaysTheSameWith2Neighbours() {
        c.setNextStatus(2);
        c.updateStatus();
        assertFalse(c.isAlive());
        c.setNextStatus(3);
        c.updateStatus();
        c.setNextStatus(2);
        c.updateStatus();
        assertTrue(c.isAlive());
    }

    @Test
    public void cellIsAliveWith3Neighbours() {
        c.setNextStatus(3);
        c.updateStatus();
        assertTrue(c.isAlive());
    }

    @Test
    public void cellIsDeadWith4Neighbours() {
        c.setNextStatus(4);
        c.updateStatus();
        assertFalse(c.isAlive());
    }

    @Test
    public void toggleDeadToAlive() {
        c.toggleStatus(p);
        assertTrue(c.isAlive());
        c.toggleStatus(p);
        assertFalse(c.isAlive());
    }

    @Test
    public void updatesAliveAndColor() {
        c.setNextStatus(3);
        c.setColorStatus(Color.red);
        c.updateStatus();
        assertTrue(c.isAlive());
        assertEquals(Color.red, c.getColor());
    }

}
