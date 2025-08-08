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
        String[] options = new String[]{"Apple", "Mice", "Frog"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setSelectedIndex(0);
        _gameController.setFood(FoodType.APPLE);
        _gameController.changeFood();
        comboBox.addActionListener(e->{
            int option = comboBox.getSelectedIndex();
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

        JLabel label = new JLabel("Choose food");

        comboBox.setSize(80, 20);

        this.add(label);
        this.add(comboBox);

    }

    @Override
    public void setFood(FoodType foodType) {
        this._gameController.setFood(foodType);
    }

    @Override
    public void setSpeed() {

    }

    @Override
    public void startGame() {

    }



}
