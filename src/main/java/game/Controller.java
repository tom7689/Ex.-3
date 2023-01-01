package game;

import gui.GameOfLifeGUI;
import library.PreLoader;
import listeners.ButtonListener;
import listeners.CellToggleListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final Grid gamegrid;
    private final GameOfLifeGUI gameOfLifeGUI;
    public Cell[][] survivalMatrix;
    private final Ruler ruler;
    private final List<Player> playerList;
    private Player activePlayer;
    private int generation;
    private boolean cellKilled = false;
    private boolean cellBorn = false;
    private static final Controller controllerInstance = new Controller(Grid.getGridInstance(), GameOfLifeGUI.getGameOfLifeGUI());

    private Controller(Grid grid, GameOfLifeGUI gui) {
        gamegrid = grid;
        gameOfLifeGUI = gui;
        ruler = new Ruler(this);
        playerList = new ArrayList<>();

        int gridX = gamegrid.sizeX / gamegrid.tileSize;
        int gridY = gamegrid.sizeY / gamegrid.tileSize;
        setGridSize(gridX, gridY);
        gamegrid.addMouseListener(new CellToggleListener(this));
        gameOfLifeGUI.step.addActionListener(new ButtonListener( this));

        playerList.add(new Player(Color.red));
        playerList.add(new Player(Color.blue));
        activePlayer = playerList.get(0);
        PreLoader pre = new PreLoader(this);
        pre.loadPreset(9, 8, "Glider");
        activePlayer = playerList.get(1);
        PreLoader pre2 = new PreLoader(this);
        pre2.loadPreset(18, 8, "Glider");
        setPlayer1Name(gameOfLifeGUI.getPlayer1Name());
        setPlayer2Name(gameOfLifeGUI.getPlayer2Name());
        playerList.sort(Player.nameComparator);
        activePlayer = playerList.get(0);
        updateValues();
        gameOfLifeGUI.activePlayerName.setText(activePlayer.getName());
    }
    public static Controller getControllerInstance() {
        return controllerInstance;
    }
    private void cellKilled() {
        cellKilled = true;
    }
    private void cellBorn() {
        cellBorn = true;
    }
    private void update() {
        cellKilled = false;
        cellBorn = false;
    }
    private int aliveNeighbours(int x, int y)
    {
        return ruler.conwayRules(x, y);
    }
    private void setGridSize(int x, int y)
    {
        if (x >= 3 || y >= 3)
        {
            survivalMatrix = new Cell[y][x];
            clear();
            gamegrid.tileSize = gamegrid.sizeX / x;
        }
    }
    private void clear()
    {
        for (int y = 0; y < survivalMatrix.length; y++)
        {
            for (int x = 0; x < survivalMatrix[0].length; x++)
            {
                survivalMatrix[y][x] = new Cell();
                gamegrid.setField(survivalMatrix[y][x], x,y);
            }
        }
    }
    public void setCell(int x, int y) {
        if (!cellKilled && survivalMatrix[y][x].isAlive() && survivalMatrix[y][x].getColor() != activePlayer.getColor()) {
            survivalMatrix[y][x].toggleStatus(activePlayer);
            gamegrid.setField(survivalMatrix[y][x], x,y);
            if (cellBorn) {
                changePlayer();
            } else {
                cellKilled();
            }
        } else if (!cellBorn && !survivalMatrix[y][x].isAlive()){
            survivalMatrix[y][x].toggleStatus(activePlayer);
            gamegrid.setField(survivalMatrix[y][x], x,y);
            if (cellKilled) {
                changePlayer();
            } else {
                cellBorn();
            }
        }
    }
    public void presetCell(int x, int y) {
        survivalMatrix[y][x].toggleStatus(activePlayer);
        gamegrid.setField(survivalMatrix[y][x], x,y);
    }
    public void stepForward() {
        for (int y = 0; y < survivalMatrix.length; y++)
        {
            for (int x = 0; x < survivalMatrix[0].length; x++)
            {
                survivalMatrix[y][x].setNextStatus(aliveNeighbours(x, y));

                survivalMatrix[y][x].setColorStatus(ruler.newColorRule(x, y));
                if(!survivalMatrix[y][x].getNextStatus()) {
                    (survivalMatrix[y][x]).setColorStatus(null);
                }
            }
        }
        // Update all Cells and paint them on the grid
        for (int y = 0; y < survivalMatrix.length; y++)
        {
            for (int x = 0; x < survivalMatrix[0].length; x++)
            {
                survivalMatrix[y][x].updateStatus();
                gamegrid.setField(survivalMatrix[y][x], x, y);
            }
        }

        // progress the generation counter and update the label
        generation++;
        updateValues();
    }
    private void updateValues() {
        playerList.get(0).setAliveCells(countRedCells());
        playerList.get(1).setAliveCells(countBlueCells());
        gameOfLifeGUI.generationValue.setText(generation + "");
        gameOfLifeGUI.player1Value.setText(playerList.get(0).getAliveCells() + "");
        gameOfLifeGUI.player2Value.setText(playerList.get(1).getAliveCells() + "");
        if (playerList.get(0).getAliveCells() == 0) {
            JOptionPane.showMessageDialog(gameOfLifeGUI.frame,"Blue Player won");
        }
        if (playerList.get(1).getAliveCells() == 0) {
            JOptionPane.showMessageDialog(gameOfLifeGUI.frame,"Red Player won");
        }
    }
    private int countRedCells() {
        int countR = 0;
        for (Cell[] matrix : survivalMatrix) {
            for (int x = 0; x < survivalMatrix[0].length; x++) {
                if (matrix[x].getColor() == Color.red) {
                    countR++;
                }
            }
        }
        return countR;
    }
    private int countBlueCells() {
        int countB = 0;
        for (Cell[] matrix : survivalMatrix) {
            for (int x = 0; x < survivalMatrix[0].length; x++) {
                if (matrix[x].getColor() == Color.blue) {
                    countB++;
                }
            }
        }
        return countB;
    }

    private void changePlayer() {
        stepForward();
        if (activePlayer == playerList.get(0)) {
            activePlayer = playerList.get(1);
            update();
        } else {
            activePlayer = playerList.get(0);
            update();
        }
        gameOfLifeGUI.activePlayerName.setText(activePlayer.getName());
    }
    private void setPlayer1Name(String pPlayerName) {
        playerList.get(0).setName(pPlayerName);
    }
    private void setPlayer2Name(String pPlayerName) {
        playerList.get(1).setName(pPlayerName);
    }
    public Grid getGamegrid() {
        return gamegrid;
    }
}
