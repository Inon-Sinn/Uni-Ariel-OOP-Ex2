package GUI.MenuPack;

import GUI.Constants;

import javax.swing.*;
import java.awt.*;

public class MenuJFrame extends JFrame {
    private Dimension dimension = new Dimension(300,200);

    public MenuJFrame(){
        MenuPanel menuPanel = new MenuPanel();
        this.add(menuPanel);

        setConstants();
    }
    private void setConstants(){
        this.setTitle("Menu");
        this.setSize(this.dimension);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(Constants.SCREEN_DIMENSION.width/3, Constants.SCREEN_DIMENSION.height/3);
        this.setVisible(true);
        this.setResizable(false);
    }

}
