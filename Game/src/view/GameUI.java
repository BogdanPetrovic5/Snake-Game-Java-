package view;

import interfaces.IGameUI;
import controllers.GameController;
import models.Food;
import models.Snake;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameUI extends JFrame{
    private int _cols = 15;
    private int _rows = 15;
    private int _cellSize = 40;

    private int width = 600;
    private int height = 600;
    public GameUI() throws IOException {


        Snake _snake = new Snake(_rows, _cols, _cellSize, width, height);
        Food _food = new Food(_rows, _cols, _cellSize);

        GameController controller = new GameController(_snake,_food, _cols, _rows, _cellSize);

        GameArea gameArea = new GameArea(controller, _snake, _food);
        ControlPanel controlPanel = new ControlPanel(controller);

        StatusPanel statusPanel = new StatusPanel(controller);


        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setResizable(false);
        this.setLayout(new BorderLayout());


        this.add(gameArea, BorderLayout.WEST);
        this.add(statusPanel, BorderLayout.NORTH);
        this.add(controlPanel, BorderLayout.EAST);

        this.setVisible(true);
    }

}
