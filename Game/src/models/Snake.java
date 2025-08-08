package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {

    private List<Point> body;
    public Direction currentDirection = null;
    public int cellSize;
    public Direction nextDirection = null;
    public double progress = 1.0;
    private boolean isMoving = true;

    public int limit;
    public Snake(int rows, int col, int cellSize){
        body = new ArrayList<>();
        Random randomStartingPoint = new Random();
        int randomX = randomStartingPoint.nextInt(col - 6) + 3;
        int randomY = randomStartingPoint.nextInt(rows - 6) + 3;
        this.cellSize = cellSize;
        this.limit = rows;
        body.add(new Point(randomX, randomY));
        body.add(new Point(randomX, randomY - 1));
        body.add(new Point(randomX, randomY - 2));
        body.add(new Point(randomX, randomY - 3));
    }

    public List<Point> getBody(){

        return body;
    }
    public void enlarge(){
        double lastSegmentX = body.getLast().x;
        double lastSegmentY = body.getLast().y;
        Point newSegment;
//        if(lastSegmentX == limit - 1 && lastSegmentY != limit - 1){
//            newSegment = new Point(lastSegmentX, lastSegmentY - 1);
//            body.add(newSegment);
//        }else if(lastSegmentX == limit - 1 && lastSegmentY == limit - 1){
//            newSegment = new Point(lastSegmentX - 1)
//        }

    }

    public void setDirection(Direction newDirection){

        if(newDirection == currentDirection) return;

        if(newDirection == Direction.UP && currentDirection == Direction.DOWN) return;
        if(newDirection == Direction.DOWN && currentDirection == Direction.UP) return;
        if(newDirection == Direction.LEFT && currentDirection == Direction.RIGHT) return;
        if(newDirection == Direction.RIGHT && currentDirection == Direction.LEFT) return;

//        if(progress < 1.0){
//            nextDirection = newDirection;
//            return;
//        }
        currentDirection = newDirection;



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
            case UP -> body.getFirst().y -= 1;
            case DOWN -> body.getFirst().y += 1;
            case LEFT -> body.getFirst().x -= 1;

            case RIGHT -> body.getFirst().x += 1;
        }




    }

}
