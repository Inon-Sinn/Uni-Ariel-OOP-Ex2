package GUI.GraphPack;

import Classes.DWG;

import javax.swing.*;
import java.awt.*;

public class GraphJFrame extends JFrame{
    private Dimension dimension = new Dimension(600,560);

    public GraphJFrame(){
        GraphPanel myPanel = new GraphPanel(new DWG());
        this.add(myPanel);

        setConstants();
        // set panel for nodes
    }
    private void setConstants(){
        this.setTitle("Directed Weighted Graph");
        this.setSize(this.dimension);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
