package interfaces;

import models.FoodType;

public interface IControlPanel {
    void setFood(FoodType foodType);
    void setSpeed(double speed);
    void startGame();

}
