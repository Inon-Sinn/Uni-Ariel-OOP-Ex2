package GUI.GraphPack;
import Classes.DWG;
import GUI.RunGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphPanel extends JPanel implements ActionListener {

    private Canvas myCanvas;
    // enter a directed weighted graph and initialize the nodes and edges
    public GraphPanel(DWG dwg) {
        this.setConstants();
        this.addComponentsToPane();

    }
    private void setConstants(){
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setVisible(true);
    }
    private void addComponentsToPane(){
        JButton goBackJButton = new JButton("GoBack");
        goBackJButton.setSize(100,50);
        goBackJButton.setLocation(460,460);
        goBackJButton.addActionListener(this);
        goBackJButton.setActionCommand("-1");

        this.add(goBackJButton);


    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(e.getActionCommand());
        /**
         * case -1 stands for user clicking to go back
         */
        switch(x){
            case -1:
                RunGui.swapFrame(1);
                break;
            default:
                break;
        }

    }
}
