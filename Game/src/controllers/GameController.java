package controllers;

import interfaces.IControlPanel;
import interfaces.IGameUI;
import interfaces.IStatusPanel;
import models.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class GameController {
    private IStatusPanel _statusPanel;
    private int _cellSize;
    private Snake _snake;
    private Food _food;
    private int gridWidth;
    private int gridHeight;
    private final double GAME_SPEED = 0.3;
    private IGameUI _gameArea;

    public GameController(Snake _snake, Food food, int width, int height, int cellSize){

        this._snake = _snake;
        _food = food;
        this._cellSize = cellSize;
        gridHeight = height * this._cellSize;
        gridWidth = width * this._cellSize;


    }
    public void startOver(){
        _snake.getBody().clear();
        _snake.generateRandomPosition();
        _statusPanel.resetScore();
        _gameArea.startTimer();
        _snake.startMoving();
        setDirection(null);
        spawnFood();
    }
    public void moveSnake(){
        if(_snake.currentDirection != null){
            _snake.move();
            isFoodEaten();
        }
    }
    public void isFoodEaten(){
        double epsilon = 0.01;

        double snakeX = _snake.getBody().getFirst().x;
        double snakeY = _snake.getBody().getFirst().y;

        double foodX = _food.getFoodPosition().x;
        double foodY = _food.getFoodPosition().y;

        if(foodX == snakeX && snakeY == foodY && (_snake.getBody().size() - 1) <= gridHeight * gridWidth){
            spawnFood();
            _snake.getBody().add(new Point((int)_snake.getBody().getLast().x, (int)_snake.getBody().getLast().y));
            if(_snake.getBody().size() == gridHeight * gridWidth){
                _gameArea.showGameOver("You Won!");
            }
            _statusPanel.updateScore();
        }
    }
    public void enlargeSnake(){

    }
    public void spawnFood(){
        Random random = new Random();

        int foodX;
        int foodY;
        boolean positionValid = false;
        do{
            foodX = random.nextInt(gridWidth / _cellSize) * this._cellSize;
            foodY = random.nextInt(gridHeight / _cellSize) * this._cellSize;

            positionValid = true;
            for (Point segment : _snake.getBody()) {
                if (segment.x == foodX && segment.y == foodY) {
                    positionValid = false;
                    break;
                }
            }
        }while (!positionValid);

        _food.setCoordinates(foodX, foodY);
    }
    public void checkCollisions(){
        Point head = _snake.getBody().getFirst();
        boolean endGame = false;
        for(int i = 2; i < _snake.getBody().size();i++){
            Point point = _snake.getBody().get(i);
            double headX = head.x;
            double headY = head.y;

            double bodyX = point.x;
            double bodyY = point.y;
            if(Math.abs(headX - bodyX) < 0.2 && Math.abs(headY - bodyY) < 0.2){

                _snake.stopMoving();
                endGame = true;
            }
        }

        if(_snake.getBody().getFirst().x <= -0.2 || _snake.getBody().getFirst().x >= (gridWidth - 0.8) || _snake.getBody().getFirst().y <= -0.2 || _snake.getBody().getFirst().y >= (gridHeight - 0.8)){
            _snake.stopMoving();
            endGame = true;
        }
        if(endGame){
            _gameArea.showGameOver("You Lost!");

        }
    }
    public void setDirection(Direction direction){

        _snake.setDirection(direction);
    }

    public void setGameUI(IGameUI _gameUI) {
        this._gameArea = _gameUI;
    }

    public void setFood(FoodType foodType){
        _food.setFoodType(foodType);
    }
    public void setSpeed(double speed){
        _gameArea.setSpeed(speed);
    }

    public void setControlPanel(IStatusPanel statusPanel) {
        this._statusPanel = statusPanel;
    }

    public void startGame(){
        _gameArea.startTimer();
    }
    public void changeFood() throws IOException {
        this._gameArea.changeFood();
    }

    public BufferedImage getFoodType() throws IOException {
        FoodType foodType = (_food.foodType) == null ? FoodType.APPLE : _food.foodType;

        switch (foodType){
            case FoodType.APPLE -> {
                return ImageIO.read(getClass().getResource("/apple.png"));
            }
            case FROG -> {
                return ImageIO.read(getClass().getResource("/toad.png"));
            }
            case MICE -> {
                return ImageIO.read(getClass().getResource("/mouse.png"));
            }
            default -> {
                return ImageIO.read(getClass().getResource("/apple.png"));
            }
        }
    }

}
