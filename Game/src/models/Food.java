package models;

import java.util.Random;

public class Food {
    private Point foodPosition;
    public FoodType foodType;
    public Food(int gridHeight, int gridWidth){
        Random random = new Random();

        int foodX = random.nextInt(gridWidth);
        int foodY = random.nextInt(gridHeight);
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
