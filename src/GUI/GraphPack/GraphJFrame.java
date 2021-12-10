package GUI.GraphPack;

import Classes.DWG;
import GUI.Constants;

import javax.swing.*;
import java.awt.*;

public class GraphJFrame extends JFrame{

    public GraphJFrame(){
        GraphPanel myPanel = new GraphPanel();
        this.add(myPanel);

        setConstants();
        // set panel for nodes
    }
    private void setConstants(){
        this.setTitle("Directed Weighted Graph");
        this.setSize(Constants.SCREEN_DIMENSION);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
