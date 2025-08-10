package view;

import controllers.GameController;
import interfaces.IControlPanel;
import interfaces.IGameUI;
import models.*;
import models.Point;

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


public class GameArea extends JPanel implements IGameUI, ActionListener, KeyListener {
    private Timer logicTimer;
    private Timer renderTimer;
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
    public int speed = 175;
    public GameArea(GameController gameController, Snake snake, Food food){
        this.setPreferredSize(new Dimension(600,600));
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

//        _gameTimer = new Timer(speed, this);
//        _gameTimer.start();
    }
    @Override
    public void setSpeed(double option){
        if(option == 1.0) speed = 300;
        else if(option == 0.5) speed = 175;
        else if(option == 0.2) speed = 120;


        setFocusable(true);
        requestFocusInWindow();
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
                double drawX = segment.x ;
                double drawY = segment.y;
                double centerX = drawX + (double) _cellSize / 2;
                double centerY = drawY + (double) _cellSize / 2;
                rotateHead.rotate(angle, centerX, centerY);
                rotateHead.drawImage(_snakeHead, (int)drawX, (int)drawY, _cellSize, _cellSize, this);
//                graphics.setColor(new Color(2, 145, 48));
//                graphics.fillRect((int)drawX,(int)drawY, _cellSize, _cellSize);

            }else{
                double drawX = segment.x;
                double drawY = segment.y;
                graphics.setColor(Color.green);
                graphics.fillRect((int)drawX,(int)drawY, _cellSize, _cellSize);
            }

        }
        Point foodPosition = _food.getFoodPosition();
        double drawX = foodPosition.x;
        double drawY = foodPosition.y;
        graphics2D.drawImage(_foodIcon, (int)drawX, (int)drawY, _cellSize, _cellSize, this);

        graphics2D.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        if(_gameTimer != null){
//            _gameController.moveSnake();
//            _gameController.checkCollisions();
//            repaint();
//        }



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

    @Override
    public void startTimer() {
        renderTimer = new Timer(100, e -> {
            _gameController.moveSnake();
            _gameController.checkCollisions();
            repaint();
        });
        renderTimer.start();
        setFocusable(true);
        requestFocusInWindow();
    }
}
