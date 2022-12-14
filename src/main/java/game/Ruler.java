package game;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Ruler {
    Controller controller;
    public Ruler(Controller parentController)
    {
        controller = parentController;
    }
    public int conwayRules(int posX, int posY)
    {
        int count = 0;
        int matrixX = controller.survivalMatrix[0].length;
        int matrixY = controller.survivalMatrix.length;

        for (int y = posY - 1; y <= posY + 1; y++)
        {
            for (int x = posX - 1; x <= posX + 1; x++)
            {
                if (controller.survivalMatrix[(y + matrixY) % matrixY][(x + matrixX) % matrixX].isAlive())
                {
                    count++;
                }
            }
        }

        count = count - (controller.survivalMatrix[posY][posX].isAlive() ? 1 : 0);

        return count;
    }
    public Color newColorRule(int posX, int posY)
    {
        List<Color> colorList = new LinkedList<>();
        Color colorSelect;

        int matrixX = controller.survivalMatrix[0].length;
        int matrixY = controller.survivalMatrix.length;

        boolean alive = controller.survivalMatrix[posY][posX].getNextStatus();

        for (int y = posY - 1; y <= posY + 1; y++)
        {
            for (int x = posX - 1; x <= posX + 1; x++)
            {
                if (controller.survivalMatrix[(y + matrixY) % matrixY][(x + matrixX) % matrixX].isAlive())
                {
                    colorSelect = controller.survivalMatrix[(y + matrixY) % matrixY][(x +
                            matrixX) % matrixX].getColor();

                    if (colorSelect != null)
                    {
                        colorList.add(colorSelect);
                    }
                }
            }
        }

        if (alive)
        {
            return countFreqColor(colorList);
        }
        return null;
    }

    private Color countFreqColor(List<Color> list)
    {
        if (list.size() == 0)
        {
            return null;
        }
        int countR = 0;
        int countB = 0;
        for (Color color : list)
        {
            if (color.equals(Color.red))
            {
                countR++;
            }
            else if (color.equals(Color.blue))
            {
                countB++;
            }
        }
        if (countR > countB) {
            return Color.red;
        } else {
            return Color.blue;
        }
    }
}
