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
import java.awt.geom.Point2D;
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
        JTextField saveJTextField = new JTextField("enter save file name here");
        saveJTextField.setActionCommand("-2");
        saveJTextField.setLocation(Constants.TOOLS_X_ALIGNMENT, 170);
        saveJTextField.setBackground(Color.blue);
        saveJTextField.setSize(Constants.TEXT_FIELD_SIZE);
        saveJTextField.addActionListener(this);
        this.add(saveJTextField);

        JButton goBackJButton = new JButton("GoBack");
        goBackJButton.setSize(Constants.BUTTON_SIZE);
        goBackJButton.setLocation(Constants.TOOLS_X_ALIGNMENT,Constants.SCREEN_DIMENSION.height-150);
        goBackJButton.addActionListener(this);
        goBackJButton.setActionCommand("-1");
        this.add(goBackJButton);

        JLabel instructionsLabel = new JLabel("Please insert X values and press enter." +
                "\n Please enter Y values and then press enter." +
                "\n then press Add Node.");

        JButton addNodeButton = new JButton("Add Node");
        addNodeButton.setActionCommand("0");
        addNodeButton.setSize(Constants.BUTTON_SIZE);
        addNodeButton.setLocation(Constants.TOOLS_X_ALIGNMENT, 100);
        addNodeButton.addActionListener(this);
        this.add(addNodeButton);

        JTextField xTextField = new JTextField("Enter X");
        xTextField.setActionCommand("1");
        xTextField.setLocation(Constants.TOOLS_X_ALIGNMENT, 170);
        xTextField.setBackground(Color.blue);
        xTextField.setSize(Constants.TEXT_FIELD_SIZE);
        xTextField.addActionListener(this);
        this.add(xTextField);

        JTextField yTextField = new JTextField("Enter Y");
        yTextField.setActionCommand("2");
        yTextField.setLocation(Constants.TOOLS_X_ALIGNMENT, 190);
        yTextField.setBackground(Color.blue);
        yTextField.setSize(Constants.TEXT_FIELD_SIZE);
        yTextField.addActionListener(this);
        this.add(yTextField);

        JButton isConnectedButton = new JButton("Is Connected");
        isConnectedButton.setActionCommand("3");
        isConnectedButton.setLocation(Constants.TOOLS_X_ALIGNMENT, 250);
        isConnectedButton.setSize(Constants.BUTTON_SIZE);
        isConnectedButton.addActionListener(this);
        this.add(isConnectedButton);

        JButton centerButton = new JButton("IsCenter");
        centerButton.setActionCommand("10");
        centerButton.setLocation(Constants.TOOLS_X_ALIGNMENT, 320);
        centerButton.setSize(Constants.BUTTON_SIZE);
        centerButton.addActionListener(this);
        this.add(centerButton);

        JButton tspButton = new JButton("TSP");
        tspButton.setActionCommand("4");
        tspButton.setLocation(Constants.TOOLS_X_ALIGNMENT, 380);
        tspButton.setSize(Constants.BUTTON_SIZE);
        tspButton.addActionListener(this);
        this.add(tspButton);

        JButton shortButton = new JButton("Short Path");
        shortButton.setActionCommand("5");
        shortButton.setLocation(Constants.TOOLS_X_ALIGNMENT, 460);
        shortButton.setSize(Constants.BUTTON_SIZE);
        shortButton.addActionListener(this);
        this.add(shortButton);

        JTextField shortxTextField1 = new JTextField("Enter X for source");
        shortxTextField1.setActionCommand("1");
        shortxTextField1.setLocation(Constants.TOOLS_X_ALIGNMENT, 520);
        shortxTextField1.setBackground(Color.blue);
        shortxTextField1.setSize(Constants.TEXT_FIELD_SIZE);
        shortxTextField1.addActionListener(this);
        this.add(shortxTextField1);

        JTextField shortyTextField1 = new JTextField("Enter Y for source");
        shortyTextField1.setActionCommand("2");
        shortyTextField1.setLocation(Constants.TOOLS_X_ALIGNMENT, 540);
        shortyTextField1.setBackground(Color.blue);
        shortyTextField1.setSize(Constants.TEXT_FIELD_SIZE);
        shortyTextField1.addActionListener(this);
        this.add(shortyTextField1);

        JTextField shortxTextField2 = new JTextField("Enter X for destination");
        shortxTextField2.setActionCommand("1");
        shortxTextField2.setLocation(Constants.TOOLS_X_ALIGNMENT, 560);
        shortxTextField2.setBackground(Color.blue);
        shortxTextField2.setSize(Constants.TEXT_FIELD_SIZE);
        shortxTextField2.addActionListener(this);
        this.add(shortxTextField2);

        JTextField shortyTextField2 = new JTextField("Enter Y for destination");
        shortyTextField2.setActionCommand("2");
        shortyTextField2.setLocation(Constants.TOOLS_X_ALIGNMENT, 580);
        shortyTextField2.setBackground(Color.blue);
        shortyTextField2.setSize(Constants.TEXT_FIELD_SIZE);
        shortyTextField2.addActionListener(this);
        this.add(shortyTextField2);



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
                    //g1.fill(node);
                    g1.draw(node);
                }
//                Point2D.Double src = new Point2D.Double(100,100);
//                Point2D.Double dest = new Point2D.Double(50,50);
//                Arrow2D arrow2D = new Arrow2D(src,dest);
//                g1.draw(arrow2D);

                Iterator<Arrow2D> iterator1 = RunGui.getEdgesIteratorFromDWG();
                while(iterator1.hasNext()){
                    Arrow2D arrow = iterator1.next();
                    g1.draw(arrow);
                }
            }
        };
        myCanvas.setBackground(Color.green);
        myCanvas.setSize((Constants.CANVAS_WIDTH),(Constants.CANVAS_HEIGHT));
        //myCanvas.setSize(100,100);
        myCanvas.setLocation(10,16);
        myCanvas.setVisible(true);
        this.add(myCanvas);

    }
    private void addNode(NodeData nodeData){
        RunGui.getDwg_algo().getGraph().addNode(nodeData);
        myCanvas.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(e.getActionCommand());
        /**
         * case -2 stands for user clicking to save the graph
         * case -1 stands for user clicking to go back
         * case 0 stands for the user wanting to update the Node
         * case 1 stands for the user wanting to update the X value of the Node
         * case 2 stands for the user wanting to update the Y value of the Node
         * case 3 for is connected
         * case 4 for  tsp
         * case 5 for shortest path
         * case 10 for Center
         */
        switch(x){
            case -2:
                RunGui.getDwg_algo().save()
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
            case 3:
                //;
                if(RunGui.getDwg_algo().isConnected())
                    RunGui.ShowMessage("The Graph is Connected.");
                else
                    RunGui.ShowMessage("Graph is not Connected!");
                break;
            case 4:
                break;
            case 5:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                break;
        }

    }
}
