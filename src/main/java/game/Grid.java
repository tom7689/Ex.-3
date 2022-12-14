package game;

import gui.PaintImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Grid extends JPanel {
    public int sizeX;
    public int sizeY;
    public int tileSize;
    private final Color colorBG;
    private final Color colorGrid;
    private static final Grid gridInstance = new Grid();
    private Grid() {
        super();
        sizeX = 600;
        sizeY = 400;
        tileSize = 20;
        colorBG = Color.darkGray;
        colorGrid = Color.gray;
        this.setSize(sizeX, sizeY);
        this.setBackground(colorBG);
        setupPaintImage();
    }
    private void setupPaintImage()
    {
        PaintImage.drawnImage = new BufferedImage(sizeX, sizeY,
                BufferedImage.TYPE_INT_ARGB);

        Graphics initG = PaintImage.drawnImage.getGraphics();
        initG.create();
        initG.setColor(colorBG);
        initG.fillRect(0,0,sizeX,sizeY);
        repaint();
    }
    private void setupGrid(Graphics g)
    {
        g.setColor(colorGrid);

        //draw horizontal lines
        for (int x = 0; x < sizeX; x = x + tileSize)
        {
            g.drawLine(x, 0, x, sizeY);
        }

        //draw vertical lines
        for (int y = 0; y < sizeY; y = y + tileSize)
        {
            g.drawLine(0, y, sizeX, y);

        }
    }
    @Override
    public void setSize(int x, int y)
    {
        sizeY = y;
        sizeX = x;
    }
    @Override
    public Dimension getSize()
    {
        return new Dimension(sizeX, sizeY);
    }
    public void setField(Cell cell, int x, int y)
    {
        // calculate the actual position on the panel
        int tileX = x * tileSize;
        int tileY = y * tileSize;

        // get graphic context of the image
        Graphics g = PaintImage.drawnImage.getGraphics();

        if(cell.isAlive())
        {
            g.setColor(cell.getColor());
            g.fillRect(tileX, tileY, tileSize, tileSize);
        }
        else
        {
            g.setColor(colorBG);
            g.fillRect(tileX, tileY, tileSize, tileSize);
        }

        // paint the image on the panel
        repaint();
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(PaintImage.drawnImage,0,0, null);
        setupGrid(g);
    }
    public static Grid getGridInstance() {
        return gridInstance;
    }
}
