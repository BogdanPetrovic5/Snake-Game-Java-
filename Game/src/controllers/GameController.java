package controllers;

import interfaces.IControlPanel;
import interfaces.IGameUI;
import models.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class GameController {
    private IControlPanel controlPanel;
    private int currentDirection;
    private Snake _snake;
    private Food _food;
    private int gridWidth;
    private int gridHeight;
    private final double GAME_SPEED = 0.3;
    private IGameUI _gameArea;

    public GameController(Snake _snake, Food food, int width, int height){

        this._snake = _snake;
        _food = food;
        gridHeight = height;
        gridWidth = width;


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

        if(Math.abs(snakeX - foodX) < 0.2 && Math.abs(snakeY - foodY) < 0.2){
            spawnFood();
            _snake.getBody().add(new Point((int)_snake.getBody().getLast().x, (int)_snake.getBody().getLast().y));
        }
    }
    public void enlargeSnake(){

    }
    public void spawnFood(){
        Random random = new Random();

        int foodX = random.nextInt(gridWidth);
        int foodY = random.nextInt(gridHeight);

        _food.setCoordinates(foodX, foodY);
    }
    public void checkCollisions(){
        if(_snake.getBody().getFirst().x <= -0.2 || _snake.getBody().getFirst().x >= (gridWidth - 0.8) || _snake.getBody().getFirst().y <= -0.2 || _snake.getBody().getFirst().y >= (gridHeight - 0.8)){
            _snake.stopMoving();

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
