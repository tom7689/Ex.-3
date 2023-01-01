package game;

import java.awt.*;

public class Cell {
    boolean alive;
    Color color;
    boolean newStatus;
    Color newColor;

    public Cell() {
        alive = false;
        color = null;
        newStatus = false;
        newColor = null;
    }
    public void setNextStatus(int neighbors)
    {
        if (neighbors < 2)
        {
            newStatus = false;
        }
        else if (neighbors > 3)
        {
            newStatus = false;
        }
        else if (neighbors == 3)
        {
            newStatus = true;
        }
        else if (neighbors == 2)
        {
            newStatus = alive;
        }
    }
    public boolean isAlive()
    {
        return alive;
    }
    public Color getColor()
    {
        return color;
    }
    public boolean getNextStatus()
    {
        return newStatus;
    }
    public void updateStatus()
    {
        alive = newStatus;
        color = newColor;
    }
    public void setColorStatus(Color color)
    {
        newColor = color;
    }
    public void toggleStatus(Player player)
    {
        if (alive)
        {
            alive = false;
        }
        else
        {
            alive = true;
            color = player.getColor();
        }
    }
}
