package view;

import controllers.GameController;
import interfaces.IGameUI;
import models.Direction;
import models.Food;
import models.Point;
import models.Snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class GameArea extends JPanel implements IGameUI, ActionListener, KeyListener {

    Random randomStartingPoint;
    private GameController _gameController;
    private BufferedImage _snakeHead;
    private BufferedImage _foodIcon;

    private final int _cellSize = 40;
    private final int _rows = 15;
    private final int _col = 15;
    private Snake _snake;
    private Food _food;
    private Timer _gameTimer;

    public GameArea(GameController gameController, Snake snake, Food food){
        this.setPreferredSize(new Dimension(600,640));
        this._snake = snake;
        this._food = food;
        randomStartingPoint = new Random();
        this._gameController = gameController;
        _gameController.setGameUI(this);

        try {
            _snakeHead = ImageIO.read(getClass().getResource("/snake-face.png"));
            _foodIcon =  _gameController.getFoodType();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        _gameTimer = new Timer(100, this);
        _gameTimer.start();
    }
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        double angle = 0;
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics.setColor(Color.black);
        for(int i = 0; i < _col;i++){
            graphics.drawLine(i * _cellSize, 0, i * _cellSize, _rows * _cellSize);
        }
        for(int i = 0; i < _rows + 1;i++){
            graphics.drawLine(0, i * _cellSize, _col * _cellSize, i * _cellSize);
        }
        if(_snake.currentDirection != null){
            switch (_snake.currentDirection){
                case UP -> angle = Math.toRadians(180);
                case DOWN -> angle = Math.toRadians(0);
                case LEFT -> angle = Math.toRadians(90);
                case RIGHT -> angle = Math.toRadians(270);
            }
        }



        List<Point> snakeBody = _snake.getBody();

        for(int i = 0; i < snakeBody.size(); i++){
            Point segment = snakeBody.get(i);
            if(i == 0){
                Graphics2D rotateHead = (Graphics2D) graphics.create();
                double drawX = segment.x * _cellSize;
                double drawY = segment.y * _cellSize;
                double centerX = drawX + (double) _cellSize / 2;
                double centerY = drawY + (double) _cellSize / 2;
                rotateHead.rotate(angle, centerX, centerY);
                rotateHead.drawImage(_snakeHead, (int)drawX, (int)drawY, _cellSize, _cellSize, this);

            }else{
                double drawX = segment.x * _cellSize;
                double drawY = segment.y * _cellSize;
                graphics.setColor(Color.green);
                graphics.fillRect((int)drawX,(int)drawY, _cellSize, _cellSize);
            }

        }
        Point foodPosition = _food.getFoodPosition();
        double drawX = foodPosition.x * _cellSize;
        double drawY = foodPosition.y * _cellSize;
        graphics2D.drawImage(_foodIcon, (int)drawX, (int)drawY, _cellSize, _cellSize, this);

        graphics2D.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        _gameController.moveSnake();
        _gameController.checkCollisions();
        repaint();


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP -> _gameController.setDirection(Direction.UP);
            case KeyEvent.VK_DOWN -> _gameController.setDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT -> _gameController.setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> _gameController.setDirection(Direction.RIGHT);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void changeFood() throws IOException {
        _foodIcon = _gameController.getFoodType();
        repaint();
        setFocusable(true);
        requestFocusInWindow();

    }

    @Override
    public void showGameOver() {

    }
}
