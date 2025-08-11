package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {

    private List<Point> body;
    public Direction currentDirection = null;
    public int cellSize;
    public Direction nextDirection = null;
    public int progress = 40;
    private boolean isMoving = true;
    Random randomStartingPoint = new Random();
    public int limit;
    public Snake(int rows, int col, int cellSize, int width, int height){
        body = new ArrayList<>();
        this.limit = rows;
        this.cellSize = cellSize;
        int randomX = randomStartingPoint.nextInt(rows) * this.cellSize;
        int randomY = randomStartingPoint.nextInt(rows) * this.cellSize;

        body.add(new Point(randomX, randomY));

    }

    public List<Point> getBody(){

        return body;
    }
    public void generateRandomPosition(){
        int randomX = randomStartingPoint.nextInt(limit) * this.cellSize;
        int randomY = randomStartingPoint.nextInt(limit) * this.cellSize;
        body.add(new Point(randomX, randomY));
    }


    public void setDirection(Direction newDirection){

        if(newDirection == currentDirection) return;

        if(newDirection == Direction.UP && currentDirection == Direction.DOWN) return;
        if(newDirection == Direction.DOWN && currentDirection == Direction.UP) return;
        if(newDirection == Direction.LEFT && currentDirection == Direction.RIGHT) return;
        if(newDirection == Direction.RIGHT && currentDirection == Direction.LEFT) return;


        currentDirection = newDirection;



    }
    public void startMoving(){
        isMoving = true;
    }

    public void stopMoving(){
        this.isMoving = false;
    }

    public void move() {
        if (!isMoving) return;





        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).x = body.get(i - 1).x;
            body.get(i).y = body.get(i - 1).y;
        }




        switch (currentDirection) {
            case UP -> body.getFirst().y -= cellSize;
            case DOWN -> body.getFirst().y += cellSize;
            case LEFT -> body.getFirst().x -= cellSize;

            case RIGHT -> body.getFirst().x += cellSize;
        }





    }

}
