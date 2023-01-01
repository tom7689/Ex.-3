package game;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {

    Grid g = Grid.getGridInstance();

    @Test
    public void gridSizeIs400By600() {
        Dimension d = g.getSize();
        assertEquals(400, d.getHeight());
        assertEquals(600, d.getWidth());
    }

    @Test
    public void gridSizeIs500By700() {
        g.setSize(700, 500);
        Dimension d = g.getSize();
        assertEquals(500, d.getHeight());
        assertEquals(700, d.getWidth());
    }
}
