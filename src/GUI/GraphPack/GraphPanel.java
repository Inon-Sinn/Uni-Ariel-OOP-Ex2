package GUI.GraphPack;
import Classes.DWG;
import Classes.GeoLoc;
import Classes.NodeData;
import GUI.Constants;
import GUI.RunGui;
import GUI.support.Arrow2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GraphPanel extends JPanel implements ActionListener {

    private Canvas myCanvas;
    private double x, y;
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
        goBackJButton.setSize(Constants.BUTTON_SIZE);
        goBackJButton.setLocation(Constants.TOOLS_X_ALIGNMENT,Constants.SCREEN_DIMENSION.height-150);
        goBackJButton.addActionListener(this);
        goBackJButton.setActionCommand("-1");
        this.add(goBackJButton);

        JLabel instructionsLabel = new JLabel("Please insert X values and press enter." +
                "\n Please enter Y values and then press enter." +
                "\n then press Add Node.");


        JTextField xTextField = new JTextField("Enter X");
        xTextField.setActionCommand("1");
        xTextField.setLocation(Constants.TOOLS_X_ALIGNMENT, 210);
        xTextField.setBackground(Color.blue);
        xTextField.setSize(Constants.TEXT_FIELD_SIZE);
        xTextField.addActionListener(this);
        this.add(xTextField);

        JTextField yTextField = new JTextField("Enter Y");
        yTextField.setActionCommand("2");
        yTextField.setLocation(Constants.TOOLS_X_ALIGNMENT, 170);
        yTextField.setBackground(Color.blue);
        yTextField.setSize(Constants.TEXT_FIELD_SIZE);
        yTextField.addActionListener(this);
        this.add(yTextField);


        JButton addNodeButton = new JButton("Add Node");
        addNodeButton.setActionCommand("0");
        addNodeButton.setSize(Constants.BUTTON_SIZE);
        addNodeButton.setLocation(Constants.TOOLS_X_ALIGNMENT, 100);
        addNodeButton.addActionListener(this);
        this.add(addNodeButton);

        myCanvas = new Canvas(){
            public void paint(Graphics g){
                g.setColor(Color.BLACK);
                g.setFont(new Font("Bold", 1, 20));
                g.drawString("This is a canvas",100 ,100);
//                g.setColor(Color.BLUE);
//                Shape elipse = new Ellipse2D.Double(100,100,Constants.NODE_WIDTH,Constants.NODE_HEIGHT);
                Graphics2D g1 = (Graphics2D) g;
                Iterator<Ellipse2D> iterator =  RunGui.getNodesIteratorFromDWG();
                while(iterator.hasNext()){
                    Ellipse2D node = iterator.next();
                    g1.fill(node);
                    g1.draw(node);
                }
                //Iterator<Arrow2D> iterator1 = RunGui.getEdgesIteratorFromDWG();
                Arrow2D arrow2D = new Arrow2D();
                g1.draw(arrow2D);

            }
        };
        myCanvas.setBackground(Color.green);
        myCanvas.setSize((Constants.CANVAS_WIDTH),(Constants.CANVAS_HEIGHT));
        //myCanvas.setSize(100,100);
        myCanvas.setLocation(10,16);
        myCanvas.setVisible(true);
        this.add(myCanvas);

    }
    private void paintNewNode(NodeData nodeData){

    }
    private void addNode(NodeData nodeData){
        RunGui.getDwg_algo().getGraph().addNode(nodeData);
        paintNewNode(nodeData);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(e.getActionCommand());
        /**
         * case -1 stands for user clicking to go back
         * case 0 stands for the user wanting to update the Node
         * case 1 stands for the user wanting to update the X value of the Node
         * case 2 stands for the user wanting to update the Y value of the Node
         *
         */
        switch(x){
            case -1:
                RunGui.swapFrame(1);
                break;
            case 0:
                NodeData node = new NodeData(RunGui.getDwg_algo().getGraph().nodeSize(), new GeoLoc(this.x, this.y,0));
                this.addNode(node);
                break;
            case 1:
                JTextField jt = (JTextField)e.getSource();
                try {
                    this.x = Double.parseDouble(jt.getText());
                    jt.setText("X is now:" + this.x);
                    break;
                }
                catch(NumberFormatException exception){
                    jt.setText("Bad Input, nothing changed, try again");
                    break;
                }
            case 2:
                JTextField jTextField = (JTextField)e.getSource();
                try {
                    this.y = Double.parseDouble(jTextField.getText());
                    jTextField.setText("Y is now:" + this.y);
                    break;
                }
                catch(NumberFormatException exception){
                    jTextField.setText("Bad Input, nothing changed, try again");
                    break;
                }
            default:
                break;
        }

    }
}
