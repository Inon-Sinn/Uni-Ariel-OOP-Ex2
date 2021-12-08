package GUI.GraphPack;
import Classes.DWG;
import GUI.Constants;
import GUI.RunGui;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphPanel extends JPanel implements ActionListener {

    private Canvas myCanvas;
    // enter a directed weighted graph and initialize the nodes and edges
    public GraphPanel(DWG dwg) {
        this.setConstants();
        this.addComponentsToPane();

    }
    private void setConstants(){
        this.setLayout(null);
       // this.setBackground(Color.black);
        this.setVisible(true);
    }
    private void addComponentsToPane(){
        JButton goBackJButton = new JButton("GoBack");
        goBackJButton.setSize(100,50);
        goBackJButton.setLocation(Constants.SCREEN_DIMENSION.width-150,Constants.SCREEN_DIMENSION.height-150);
        goBackJButton.addActionListener(this);
        goBackJButton.setActionCommand("-1");

        this.add(goBackJButton);

        myCanvas = new Canvas(){
            public void paint(Graphics g){
                //g.setColor(Color.BLACK);
                //g.setFont(new Font("Bold", 1, 20));
                //g.drawString("This is a canvas",100 ,100);
                // setting up the image
                //File happy = new File("C:\\Users\\Inons\\IdeaProjects\\Ex2 - test\\src\\GUI\\support\\happy.jpeg");

                    //Image node = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Inons\\IdeaProjects\\Ex2 - test\\src\\GUI\\support\\happy.jpeg");
                   //g.drawImage(node,120,100,100,100,null);
                g.drawOval(100,100,100,100);

            }
        };
        myCanvas.setBackground(Color.green);
        this.add(myCanvas);
        myCanvas.setSize(Constants.SCREEN_DIMENSION.width*(3/4),Constants.SCREEN_DIMENSION.height);
        myCanvas.setVisible(true);
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
