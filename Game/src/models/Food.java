package models;

import java.util.Random;

public class Food {
    private Point foodPosition;
    public FoodType foodType;
    public Food(int rows, int cells, int cellSize){
        Random random = new Random();

        int foodX = random.nextInt(cells) * cellSize;
        int foodY = random.nextInt(rows) * cellSize;
        foodPosition = new Point(foodX, foodY);
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public void setCoordinates(int x, int y){

        foodPosition.x = x;
        foodPosition.y = y;
    }
    public Point getFoodPosition(){
        return foodPosition;
    }

}
