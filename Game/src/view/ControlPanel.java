package view;

import controllers.GameController;
import interfaces.IControlPanel;
import models.Food;
import models.FoodType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ControlPanel extends JPanel implements IControlPanel {
    private GameController _gameController;

    public ControlPanel(GameController gameController) throws IOException {
        this.setPreferredSize(new Dimension(200, 640));
        this._gameController = gameController;
        String[] foodOptionsArr = new String[]{"Apple", "Mice", "Frog"};
        String[] speedOptionsArr = new String[]{"1s", "0.5s", "0.2s"};
        JComboBox<String> speedOptions = new JComboBox<>(speedOptionsArr);
        JComboBox<String> foodOptions = new JComboBox<>(foodOptionsArr);
        foodOptions.setSelectedIndex(0);
        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
        JButton startGame = new JButton("Start Game!");

        startGame.addActionListener(e ->{
            startGame();
        });
        JLabel logo = new JLabel();
        logo.setIcon(icon);
        _gameController.setFood(FoodType.APPLE);
        _gameController.changeFood();
        foodOptions.addActionListener(e->{
            int option = foodOptions.getSelectedIndex();
            switch (option){
                case 0 -> setFood(FoodType.APPLE);
                case 1 -> setFood(FoodType.MICE);
                case 2 -> setFood(FoodType.FROG);

            }
            try {
                _gameController.changeFood();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        speedOptions.addActionListener(e ->{
            int option = speedOptions.getSelectedIndex();
            switch (option){
                case 0 -> setSpeed(1.0);
                case 1 -> setSpeed(0.5);
                case 2 -> setSpeed(0.2);
            }
        });

        JLabel foodLabel = new JLabel("Choose food");
        JLabel speedLabel = new JLabel("Choose speed");
        foodOptions.setSize(80, 20);

        this.add(foodLabel);
        this.add(foodOptions);

        this.add(speedLabel);
        this.add(speedOptions);
        this.add(logo);
        this.add(startGame);
    }

    @Override
    public void setFood(FoodType foodType) {
        this._gameController.setFood(foodType);
    }

    @Override
    public void setSpeed(double speed) {
        _gameController.setSpeed(speed);
    }

    @Override
    public void startGame() {
        _gameController.startGame();
    }



}
