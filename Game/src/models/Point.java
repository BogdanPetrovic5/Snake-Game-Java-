package models;

public class Point {
    public int gridX;
    public int gridY;
    public double x;
    public double y;
    public  Point(int x, int y){
        this.gridX = x;
        this.gridY = y;
        this.x = gridX;
        this.y = gridY;
    }

    public int getIntX(){
        return (int)Math.round(x);

    }
    public int getIntY(){
        return (int)Math.round(y);

    }
}
