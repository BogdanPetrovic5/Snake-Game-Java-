package view;

import controllers.GameController;
import interfaces.IStatusPanel;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel implements IStatusPanel {
    public int foodEaten = 0;
    private GameController _controller;
    JLabel label;
    public StatusPanel(GameController controller){
        _controller = controller;
        label = new JLabel("You ate: " + foodEaten);
        label.setHorizontalAlignment(JLabel.CENTER);
        this.setPreferredSize(new Dimension(800, 50));
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.CENTER);
        this.setVisible(true);
        _controller.setControlPanel(this);
    }


    @Override
    public void updateScore() {
        foodEaten += 1;
        label.setText("You ate: " + foodEaten);
    }

    @Override
    public void resetScore() {
        foodEaten = 0;
        label.setText("You ate: " + foodEaten);
    }
}
