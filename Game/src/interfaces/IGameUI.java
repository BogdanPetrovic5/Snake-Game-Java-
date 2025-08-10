package interfaces;


import java.io.IOException;

public interface IGameUI {
    void changeFood() throws IOException;
    void setSpeed(double option);
    void showGameOver();
    void startTimer();
}
