package gui;

import game.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOfLifeGUI {
    protected static final Color primaryColor = Color.gray;
    protected static final Color secondaryColor = Color.lightGray;
    protected static final Color tertiaryColor = Color.darkGray;
    public JFrame frame;
    private final Grid gameGrid;
    public JLabel generationValue;
    public JLabel player1;
    public JLabel player1Value;
    public JLabel player2;
    public JLabel player2Value;
    public JButton step;
    public JLabel activePlayer;
    public JLabel activePlayerName;
    private static final GameOfLifeGUI gameOfLifeGUI = new GameOfLifeGUI(Grid.getGridInstance());

    private GameOfLifeGUI(Grid parentGrid)
    {
        gameGrid = parentGrid;

        initializeFrame();

        Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
        frame.setIconImage(icon);
    }
    public static GameOfLifeGUI getGameOfLifeGUI() {
        return gameOfLifeGUI;
    }

    public String getPlayer1Name() {
        return player1.getText();
    }
    public String getPlayer2Name() {
        return player2.getText();
    }
    private void initializeFrame() {
        frame = new JFrame("Game of Life");
        frame.getContentPane().setBackground(primaryColor);
        frame.setBounds(100, 100, 800, 460);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        gameGrid.setBounds(0, 0, 600, 400);
        frame.getContentPane().add(gameGrid);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        activePlayer = new JLabel("Active Player:");
        activePlayer.setForeground(tertiaryColor);
        activePlayer.setBounds(620, 25, 96, 14);
        frame.getContentPane().add(activePlayer);

        activePlayerName = new JLabel("");
        activePlayerName.setForeground(tertiaryColor);
        activePlayerName.setBounds(620, 50, 96, 14);
        frame.getContentPane().add(activePlayerName);

        player1 = new JLabel("Player 1:");
        player1.setForeground(tertiaryColor);
        player1.setBounds(620, 100, 96, 14);
        frame.getContentPane().add(player1);

        player1Value = new JLabel("0");
        player1Value.setForeground(tertiaryColor);
        player1Value.setBounds(620, 125, 96, 14);
        frame.getContentPane().add(player1Value);

        player2 = new JLabel("Player 2:");
        player2.setForeground(tertiaryColor);
        player2.setBounds(620, 175, 96, 14);
        frame.getContentPane().add(player2);

        player2Value = new JLabel("0");
        player2Value.setForeground(tertiaryColor);
        player2Value.setBounds(620, 200, 96, 14);
        frame.getContentPane().add(player2Value);

        JLabel generation = new JLabel("GENERATION:");
        generation.setForeground(tertiaryColor);
        generation.setBounds(620, 345, 96, 14);
        frame.getContentPane().add(generation);

        generationValue = new JLabel("0");
        generationValue.setForeground(tertiaryColor);
        generationValue.setBounds(620, 370, 96, 14);
        frame.getContentPane().add(generationValue);

        step = new JButton("STEP");
        step.setBackground(tertiaryColor);
        step.setForeground(secondaryColor);
        step.setBounds(620, 390, 96, 14);
        frame.getContentPane().add(step);

        Panel panel1 = new Panel();
        JLabel player11 = new JLabel("Player 1");
        panel1.add(player11, BorderLayout.WEST);
        String player1Name = JOptionPane.showInputDialog("Red Player enter your name");
        player1.setText(player1Name);

        Panel panel2 = new Panel();
        JLabel player22 = new JLabel("Player 2");
        panel2.add(player22, BorderLayout.WEST);
        String player2Name = JOptionPane.showInputDialog("Blue Player enter your name");
        player2.setText(player2Name);
    }
}
