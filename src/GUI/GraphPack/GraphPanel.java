package GUI.GraphPack;
import Classes.DWG;
import Classes.EdgeData;
import Classes.GeoLoc;
import Classes.NodeData;
import GUI.Constants;
import GUI.RunGui;
import GUI.support.Arrow2D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
public class GraphPanel extends JPanel implements ActionListener, MouseListener {

    private Canvas myCanvas;
    private double x=-1, y=-1;
    private int ID = 0;
    private JTextArea instructionsLabel;
    private JTextField shortyTextField1, yTextField,saveJTextField,shortxTextField1,xTextField;
    private JButton addNodeButton, centerButton, shortButton, tspButton, isConnectedButton, goBackJButton;
    // enter a directed weighted graph and initialize the nodes and edges
    public GraphPanel() {
        this.setConstants();
        this.addComponentsToPane();
        addMouseListener(this);
    }
    private void setConstants(){
        this.setLayout(null);
        this.setVisible(true);
    }

    private void addNode(NodeData nodeData){
        RunGui.getDwg_algo().getGraph().addNode(nodeData);
        RunGui.setRange();
        myCanvas.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX() < Constants.CANVAS_WIDTH && e.getX() >= 10 &&
                e.getY()<Constants.CANVAS_HEIGHT && e.getY() >= 16){
            Point2D.Double ratioPoint = RunGui.convertFromCanvasToRatio(new Point2D.Double(e.getX(),e.getY()));
            Point2D.Double point = RunGui.convertFromRatioToRange(ratioPoint);
            xTextField.setText(point.x+"");
            yTextField.setText(point.y+"");
            instructionsLabel.setText("Updated selected x and y data by click! \n " +
                    "values selected are : \n X = " + point.x + "\n" +
                    " , Y = " + point.y);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
         * case 6 for Center
         */
        switch(x){
            case -2:
                JTextField tf = (JTextField) e.getSource();
                String file = tf.getText();
                RunGui.getDwg_algo().save(file);
                instructionsLabel.setText("You've received an output of file: " + file);
                break;
            case -1:
                RunGui.swapFrame(1);
                break;
            case 0:
                try{
                    this.x = Double.parseDouble(this.xTextField.getText());
                    this.y = Double.parseDouble(this.yTextField.getText());
                }
                catch(NumberFormatException exception){
                    xTextField.setText("Wrong input");
                    yTextField.setText("Wrong input");
                    x=-1;
                    y=-1;
                    break;
                }
                NodeData node = new NodeData(RunGui.getDwg_algo().getGraph().nodeSize(),
                        new GeoLoc(this.x, this.y,0));
                this.addNode(node);
                instructionsLabel.setText("You've added a New Node With \n values: X = " + this.x + "\n" +  ", Y = " + this.y);
                break;
            case 1:
                JTextField jt = (JTextField)e.getSource();
                try {
                    this.x = Double.parseDouble(jt.getText());
                    this.instructionsLabel.setText("Value of X has changed to :" + this.x);
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
                    this.instructionsLabel.setText("Value of Y has changed to :" + this.y);
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
                RunGui.ShowMessage("You've clicked tsp!");
                break;
            case 5:
                RunGui.ShowMessage("You've clicked Shortest Path!");
                break;
            case 6:
                RunGui.ShowMessage("You've clicked Center!");
            default:
                break;
        }

    }
    private void addComponentsToPane(){

        /**This is a JTextField used to save a copy of the Directed Weighted Graph to json */
        saveJTextField = new JTextField("enter save file name here");
        saveJTextField.setActionCommand("-2");
        saveJTextField.setLocation(Constants.JTEXT_X_ALIGNMENT, 50);
        saveJTextField.setBackground(Color.blue);
        saveJTextField.setSize(Constants.TEXT_FIELD_SIZE);
        saveJTextField.addActionListener(this);
        this.add(saveJTextField);

        /** This is a Go Back Button*/
        goBackJButton = new JButton("GoBack");
        goBackJButton.setSize(Constants.BUTTON_SIZE);
        goBackJButton.setLocation(Constants.BUTTONS_X_ALIGNMENT,Constants.SCREEN_DIMENSION.height-150);
        goBackJButton.addActionListener(this);
        goBackJButton.setActionCommand("-1");
        this.add(goBackJButton);

        /** This is a Text Area designed to show the last thing done*/
        instructionsLabel = new JTextArea();
        instructionsLabel.setSize(new Dimension(400,100));
        Font font = new Font("hah",Font.BOLD, 12);
        instructionsLabel.setFont(font);
        instructionsLabel.setFocusable(false);
        instructionsLabel.setLocation(Constants.JTEXT_X_ALIGNMENT + 20, Constants.SCREEN_DIMENSION.height - 150);
        instructionsLabel.setBackground(Color.red);
        instructionsLabel.setText("Please insert X values and press enter." +
                "\n Please enter Y values and then press enter." +
                "\n Then press Add Node.");
        instructionsLabel.setVisible(true);
        this.add(instructionsLabel);

        /** This button reads from two JTextfields  and adds a new Node to the graph*/
        addNodeButton = new JButton("Add Node");
        addNodeButton.setActionCommand("0");
        addNodeButton.setSize(Constants.BUTTON_SIZE);
        addNodeButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 100);
        addNodeButton.addActionListener(this);
        this.add(addNodeButton);

        /** These JText Fields designed to understand at which specific location the user
         * wants to add a new node. */
        xTextField = new JTextField("Enter only X");
        xTextField.setActionCommand("1");
        xTextField.setLocation(Constants.JTEXT_X_ALIGNMENT, 100);
        xTextField.setBackground(Color.blue);
        xTextField.setSize(Constants.TEXT_FIELD_SIZE);
        xTextField.addActionListener(this);
        this.add(xTextField);

        yTextField = new JTextField("Enter only Y");
        yTextField.setActionCommand("2");
        yTextField.setLocation(Constants.JTEXT_X_ALIGNMENT, 120);
        yTextField.setBackground(Color.blue);
        yTextField.setSize(Constants.TEXT_FIELD_SIZE);
        yTextField.addActionListener(this);
        this.add(yTextField);

        /**This Button runs the isConnected() function of the current graph's algorithm */
        isConnectedButton = new JButton("Is Connected");
        isConnectedButton.setActionCommand("3");
        isConnectedButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 250);
        isConnectedButton.setSize(Constants.BUTTON_SIZE);
        isConnectedButton.addActionListener(this);
        this.add(isConnectedButton);

        /**This Button runs the Center() function of the current graph's algorithm */
        centerButton = new JButton("IsCenter");
        centerButton.setActionCommand("6");
        centerButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 320);
        centerButton.setSize(Constants.BUTTON_SIZE);
        centerButton.addActionListener(this);
        this.add(centerButton);

        /**This Button runs the tsp() function of the current graph's algorithm */
        tspButton = new JButton("TSP");
        tspButton.setActionCommand("4");
        tspButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 380);
        tspButton.setSize(Constants.BUTTON_SIZE);
        tspButton.addActionListener(this);
        this.add(tspButton);

        /**This Button runs the ShortestPath() function of the current graph's algorithm */
        shortButton = new JButton("Short Path");
        shortButton.setActionCommand("5");
        shortButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 460);
        shortButton.setSize(Constants.BUTTON_SIZE);
        shortButton.addActionListener(this);
        this.add(shortButton);

        /**This JTextField Receives Source Node ID */
        shortxTextField1 = new JTextField("Enter Source ID");
        shortxTextField1.setActionCommand("1");
        shortxTextField1.setLocation(Constants.JTEXT_X_ALIGNMENT, 460);
        shortxTextField1.setBackground(Color.blue);
        shortxTextField1.setSize(Constants.TEXT_FIELD_SIZE);
        shortxTextField1.addActionListener(this);
        shortxTextField1.setFont(font);
        this.add(shortxTextField1);

        /**This JTextField Receives Dest Node ID */
        shortyTextField1 = new JTextField("Enter Dest ID");
        shortyTextField1.setActionCommand("2");
        shortyTextField1.setLocation(Constants.JTEXT_X_ALIGNMENT, 480);
        shortyTextField1.setBackground(Color.blue);
        shortyTextField1.setSize(Constants.TEXT_FIELD_SIZE);
        shortyTextField1.addActionListener(this);
        shortyTextField1.setFont(font);;
        this.add(shortyTextField1);

        /**
         * This iterates through all the edges and nodes and drawn them on the screen.
         */
        myCanvas = new Canvas(){
            public void paint(Graphics g){
                Graphics2D g1 = (Graphics2D) g;
                Iterator<api.NodeData> iterator = RunGui.getDwg_algo().getGraph().nodeIter();
                DWG dwg = (DWG) RunGui.getDwg_algo().getGraph();
                while(iterator.hasNext()){
                    // setting up the next Ellipse2D
                    NodeData nodeData = (Classes.NodeData)iterator.next();
                    GeoLoc geoLoc = (GeoLoc) nodeData.getLocation();
                    Point2D.Double point = RunGui.convertFromRangeToCanvas(new Point2D.Double(geoLoc.x(),geoLoc.y()));
                    Ellipse2D.Double node = new Ellipse2D.Double(point.x,point.y,Constants.NODE_WIDTH,Constants.NODE_HEIGHT);
                    // just setting x,y, width,height of the ellipse.

                    // drawing the ellipse with id
                    g1.drawString(""+nodeData.getKey(), (float)node.getX(), (float)node.getY());
                    //g1.fill(node);
                    g1.draw(node);
                }
                // setting up the directed edges to look like arrows
                Iterator<api.EdgeData> iterator1 = dwg.edgeIter();
                while(iterator1.hasNext()){
                    EdgeData edge =(EdgeData) iterator1.next();
                    GeoLoc srcNodeLoc = (GeoLoc) dwg.getNode(edge.getSrc()).getLocation();
                    GeoLoc destNodeLoc = (GeoLoc) dwg.getNode(edge.getDest()).getLocation();
                    Point2D.Double src = RunGui.convertFromRangeToCanvas(new Point2D.Double(srcNodeLoc.x(),srcNodeLoc.y()));
                    Point2D.Double dest = RunGui.convertFromRangeToCanvas(new Point2D.Double(destNodeLoc.x(),destNodeLoc.y()));
                    Arrow2D arrow = new Arrow2D(src,dest,edge.getWeight());

                    g1.draw(arrow);
                }
            }
        };
        myCanvas.setBackground(Color.green);
        myCanvas.setSize((Constants.CANVAS_WIDTH),(Constants.CANVAS_HEIGHT));
        myCanvas.setLocation(10,16);
        myCanvas.setVisible(true);
        myCanvas.addMouseListener(this);
        this.add(myCanvas);


    }


}
