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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
public class GraphPanel extends JPanel implements ActionListener, MouseListener {

    private Canvas myCanvas;
    private double x=-1, y=-1;
    private int ID = 0;
    private JTextArea instructionsLabel;
    private JTextField shortyTextField1, yTextField,saveJTextField,shortxTextField1,xTextField, tspJTextField
            ,ShortPathJTextField, edgeDataJTextField;
    private JButton addNodeButton, centerButton, shortButton, tspButton, isConnectedButton, goBackJButton
            ,addEdgeButton, saveJButton, infoFrameButton;
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
    private void addEdge(EdgeData edgeData){
        RunGui.getDwg_algo().getGraph().connect(edgeData.getSrc(), edgeData.getDest(), edgeData.getWeight());
        myCanvas.repaint();
    }
    private void deleteNode(int id){
        RunGui.getDwg_algo().getGraph().removeNode(id);
        RunGui.setRange();
        myCanvas.repaint();
    }
    private void deleteEdge(int src, int dest){
        RunGui.getDwg_algo().getGraph().removeEdge(src, dest);
        myCanvas.repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX() < Constants.CANVAS_WIDTH && e.getX() >= 10 &&
                e.getY()<Constants.CANVAS_HEIGHT && e.getY() >= 16){
            Point2D.Double ratioPoint = RunGui.convertFromCanvasToRatio(new Point2D.Double(e.getX(),e.getY()));
            Point2D.Double point = RunGui.convertFromRatioToRange(ratioPoint);
            DecimalFormat df = new DecimalFormat("#.######");
            xTextField.setText(df.format(point.x)+"," + df.format(point.y));
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
         * case 1 stands for the user wanting to update the X value and y value of a node
         * case 2 stands for the user wanting to delete specific node
         * case 3 for is connected
         * case 4 for  tsp
         * case 5 for shortest path
         * case 6 for Center
         *
         * case 8 for addEdge
         * case 9 for infoFrame
         * case 10 for deleteNode
         * case 11 for deleteEdge
         */
        switch(x){
            case -2:
                String file = saveJTextField.getText();
                RunGui.getDwg_algo().save(file);
                instructionsLabel.setText("You've received an output of file: " + file);
                break;
            case -1:
                RunGui.swapFrame(1);
                break;
            case 0:
                try{
                    String str = xTextField.getText().trim();
                    String[] s = str.split(",");
                    this.x = Double.parseDouble(s[0]);
                    this.y = Double.parseDouble(s[1]);
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
                try {
                    String str = xTextField.getText().trim();
                    String[] s = str.split(",");
                    this.x = Double.parseDouble(s[0]);
                    this.y = Double.parseDouble(s[1]);
                    this.instructionsLabel.setText("Value has changed to :" + this.x + "," + this.y);
                    break;
                }
                catch(NumberFormatException exception){
                    xTextField.setText("Bad Input, nothing changed, try again");
                    break;
                }
            case 2:
                try {
                    int id = Integer.parseInt(yTextField.getText());
                    this.instructionsLabel.setText("The ID selected is:" + id);
                    break;
                }
                catch(NumberFormatException exception){
                    yTextField.setText("Bad Input, nothing changed, try again");
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
                try{
                    String d =  this.tspJTextField.getText();
                    // weird problem does not remote spaces
                    String str = d.trim();
                    String[] cities = str.split(",");
                    int[] IDs = new int[cities.length];
                    int index=0;
                    // get ids of the cities
                    for(String city : cities){
                        IDs[index++] = Integer.parseInt(city);
                    }
                    ArrayList<api.NodeData> cityList = new ArrayList<>();
                    DWG dwg = (DWG) RunGui.getDwg_algo().getGraph();
                    // get list of nodes with the ids
                    for(int i : IDs){
                        if(dwg.getNode(i) == null){
                            throw new NumberFormatException("incorrect ID");
                        }
                        cityList.add(dwg.getNode(i));
                    }
                    cityList = (ArrayList<api.NodeData>) RunGui.getDwg_algo().tsp(cityList);
                    str = "";
                    // make return string
                    for (int i = 0; i < cityList.size(); i++) {
                        str = str + " -> " + cityList.get(i);
                    }
                    RunGui.ShowMessage("The Path returned is: \n" + str);
                }
                catch(NumberFormatException exception){
                    tspJTextField.setText(exception.getMessage());
                    instructionsLabel.setText("Incorrect input for Cities!!");
                }

                break;
            case 5:
                try{
                    String str = ShortPathJTextField.getText();
                    String s = str.trim();
                    String[] l = str.split(",");
                    if(l.length > 2 || l.length == 0){
                        throw new NumberFormatException("Cannot resolve source and Dest");
                    }
                    int[] IDS = {Integer.parseInt(l[0]), Integer.parseInt(l[1])};
                    ArrayList<api.NodeData> pathList = (ArrayList<api.NodeData>) RunGui.getDwg_algo().shortestPath(IDS[0], IDS[1]);
                    s = "";
                    for (int i = 0; i < pathList.size(); i++) {
                        s = s + " -> " + pathList.get(i).getKey();
                    }
                    RunGui.ShowMessage("The Shortest path is :\n" + s);
                }
                catch (NumberFormatException exep){
                    ShortPathJTextField.setText(exep.getMessage());
                    instructionsLabel.setText("Wrong input for ShortestPath!!");
                }
                break;
            case 6:
                int centerKey = RunGui.getDwg_algo().center().getKey();
                RunGui.ShowMessage("The ID of the Center node is : " + centerKey);
                break;
            case 8:
                String l = edgeDataJTextField.getText();
                String s = l.trim();
                String[] str = s.split(",");
                try{
                    if(str.length < 3 || str.length == 0 || str.length >= 4){
                        throw new NumberFormatException("Cant Determinate src,dest,weight");
                    }
                    int src = Integer.parseInt(str[0]),
                    dest = Integer.parseInt(str[1]);
                    DWG dwg = (DWG) RunGui.getDwg_algo().getGraph();
                    if(dwg.getNode(src) == null || dwg.getNode(dest) == null){
                        throw new NumberFormatException("Wrong ID for src or dest");
                    }
                    double weight = Double.parseDouble(str[2]);
                    int id = dwg.edgeSize();
                    addEdge(new EdgeData(src,dest,weight,id));
                    instructionsLabel.setText("Added Edge Successfully!!\n" +
                            "From Src = " + src + ", Dest = " + dest);
                }
                catch(NumberFormatException e1){
                    edgeDataJTextField.setText(e1.getMessage());
                    instructionsLabel.setText("Incorrect Input for src,dest,weight");
                }
                break;
            case 9:
                new GraphInfoFrame();
                break;
            case 10:
                try{
                    int ID = Integer.parseInt(yTextField.getText());
                    deleteNode(ID);
                    instructionsLabel.setText("You have Deleted Node : " + ID);
                }
                catch (NumberFormatException exce1){
                    yTextField.setText(exce1.getMessage());
                    instructionsLabel.setText("Wrong input");
                }
                break;
            case 11:
                String str2 = this.edgeDataJTextField.getText().trim();
                String[] l1 = str2.split(",");
                try{
                    if(l1.length == 3){
                        throw new NumberFormatException("weight is not needed");
                    }
                    else if(l1.length > 3){
                        throw new NumberFormatException("not valid input");
                    }
                    else if(l1.length < 2 || l1.length == 0){
                        throw new NumberFormatException("src or dest missing");
                    }
                    int src = Integer.parseInt(l1[0]),
                    dest = Integer.parseInt(l1[1]);
                    EdgeData edgeData = (EdgeData) RunGui.getDwg_algo().getGraph().getEdge(src,dest);
                    if(edgeData == null){
                        throw new NumberFormatException("Non existent edge try again");
                    }
                    deleteEdge(src,dest);
                    instructionsLabel.setText(" Edge from " + src + " to " +  dest + " Deleted Succesfully!!");
                }
                catch(NumberFormatException exce2){
                    this.edgeDataJTextField.setText(exce2.getMessage());
                    instructionsLabel.setText("Wrong data entered");
                }
                break;

            default:
                break;
        }

    }
    private void addComponentsToPane(){

        Font font = new Font("hah",Font.BOLD, 12);
        /**This Button used to save a copy of the Directed Weighted Graph to json */
        saveJButton = new JButton("Save Graph");
        saveJButton.setSize(Constants.BUTTON_SIZE);
        saveJButton.setLocation(Constants.BUTTONS_X_ALIGNMENT,20);
        saveJButton.addActionListener(this);
        saveJButton.setActionCommand("-2");
        this.add(saveJButton);

        /**This is a JTextField used to save a copy of the Directed Weighted Graph to json */
        saveJTextField = new JTextField("enter save file name here");
        saveJTextField.setLocation(Constants.JTEXT_X_ALIGNMENT, 20);
        saveJTextField.setSize(Constants.TEXT_FIELD_SIZE);
        saveJTextField.setFont(font);
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
        instructionsLabel.setFont(font);
        instructionsLabel.setForeground(new Color(146, 253, 93));
        instructionsLabel.setFocusable(false);
        instructionsLabel.setLocation(Constants.JTEXT_X_ALIGNMENT + 20, Constants.SCREEN_DIMENSION.height - 150);
        instructionsLabel.setBackground(Color.darkGray);
        instructionsLabel.setText("Please insert X values and press enter." +
                "\n Please enter Y values and then press enter." +
                "\n Then press Add Node.");
        instructionsLabel.setVisible(true);
        this.add(instructionsLabel);

        /** This button reads from two JTextfields  and adds a new Node to the graph*/
        addNodeButton = new JButton("Add Node");
        addNodeButton.setActionCommand("0");
        addNodeButton.setSize((Constants.BUTTON_SIZE.width),(Constants.BUTTON_SIZE.height/2));
        addNodeButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 100);
        addNodeButton.addActionListener(this);
        this.add(addNodeButton);

        /** This Button Deletes a node */
        addNodeButton = new JButton("Delete Node");
        addNodeButton.setActionCommand("10");
        addNodeButton.setSize((Constants.BUTTON_SIZE.width),(Constants.BUTTON_SIZE.height/2));
        addNodeButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 125);
        addNodeButton.addActionListener(this);
        this.add(addNodeButton);

        /** These JText Fields designed to understand at which specific location the user
         * wants to add a new node. */
        xTextField = new JTextField("Enter x,y separated by ,");
        xTextField.setActionCommand("1");
        xTextField.setLocation(Constants.JTEXT_X_ALIGNMENT, 100);
        xTextField.setFont(font);
        xTextField.setSize(Constants.TEXT_FIELD_SIZE);
        xTextField.addActionListener(this);
        this.add(xTextField);

        yTextField = new JTextField("Enter id for delete");
        yTextField.setActionCommand("2");
        yTextField.setLocation(Constants.JTEXT_X_ALIGNMENT, 120);
        yTextField.setFont(font);
        yTextField.setSize(Constants.TEXT_FIELD_SIZE);
        yTextField.addActionListener(this);
        this.add(yTextField);

        /**This Button supposed to add(AddEdge) a new Edge between two nodes */
        addEdgeButton = new JButton("Add Edge");
        addEdgeButton.setActionCommand("8");
        addEdgeButton.setSize(Constants.BUTTON_SIZE.width,Constants.BUTTON_SIZE.height/2);
        addEdgeButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 180);
        addEdgeButton.addActionListener(this);
        this.add(addEdgeButton);
        /** This Button supposed to deleteEdge  */
        addEdgeButton = new JButton("Delete Edge");
        addEdgeButton.setActionCommand("11");
        addEdgeButton.setSize(Constants.BUTTON_SIZE.width,Constants.BUTTON_SIZE.height/2);
        addEdgeButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 205);
        addEdgeButton.addActionListener(this);
        this.add(addEdgeButton);
        /**This JTextField supposed to receive src node id and destination node id (edgeDataJTextField) */
        edgeDataJTextField = new JTextField("Src,Dest,weight separated by ,");
        edgeDataJTextField.setActionCommand("9");
        edgeDataJTextField.setSize(Constants.TEXT_FIELD_SIZE);
        edgeDataJTextField.setFont(font);
        edgeDataJTextField.setLocation(Constants.JTEXT_X_ALIGNMENT, 180);
        edgeDataJTextField.addActionListener(this);
        this.add(edgeDataJTextField);

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

        /**This TextField represents the cities the user needs to apply to run tsp */
        tspJTextField = new JTextField("Enter Cities by ID separated by ,");
        tspJTextField.setActionCommand("7");
        tspJTextField.setLocation(Constants.JTEXT_X_ALIGNMENT, 380);
        tspJTextField.setSize(Constants.TEXT_FIELD_SIZE);
        tspJTextField.setFont(font);
        tspJTextField.addActionListener(this);
        this.add(tspJTextField);

        /**This Button runs the ShortestPath() function of the current graph's algorithm */
        shortButton = new JButton("Short Path");
        shortButton.setActionCommand("5");
        shortButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 460);
        shortButton.setSize(Constants.BUTTON_SIZE);
        shortButton.addActionListener(this);
        this.add(shortButton);
        /**This JTextField gets source id and dest id of the nodes the user wants shortpath algo to work on*/
        ShortPathJTextField = new JTextField("Enter SrcID,destID separated by , ");
        ShortPathJTextField.setActionCommand("8");
        ShortPathJTextField.setLocation(Constants.JTEXT_X_ALIGNMENT, 460);
        ShortPathJTextField.setSize(Constants.TEXT_FIELD_SIZE);
        ShortPathJTextField.addActionListener(this);
        ShortPathJTextField.setFont(font);
        this.add(ShortPathJTextField);

        /**This opens an information frome about the graph */
        infoFrameButton = new JButton("Info");
        infoFrameButton.setActionCommand("9");
        infoFrameButton.setLocation(Constants.BUTTONS_X_ALIGNMENT, 540);
        infoFrameButton.setSize(Constants.BUTTON_SIZE);
        infoFrameButton.addActionListener(this);
        this.add(infoFrameButton);


        /**
         * This iterates through all the edges and nodes and drawn them on the screen.
         */
        myCanvas = new Canvas(){
            public void paint(Graphics g){
                Graphics2D g1 = (Graphics2D) g;
                DWG dwg = (DWG) RunGui.getDwg_algo().getGraph();

                // setting up the directed edges to look like arrows
                Iterator<api.EdgeData> iterator1 = dwg.edgeIter();
                while(iterator1.hasNext()){
                    EdgeData edge =(EdgeData) iterator1.next();
                    GeoLoc srcNodeLoc = (GeoLoc) dwg.getNode(edge.getSrc()).getLocation();
                    GeoLoc destNodeLoc = (GeoLoc) dwg.getNode(edge.getDest()).getLocation();
                    Point2D.Double src = RunGui.convertFromRangeToCanvas(new Point2D.Double(srcNodeLoc.x(),srcNodeLoc.y()));
                    Point2D.Double dest = RunGui.convertFromRangeToCanvas(new Point2D.Double(destNodeLoc.x(),destNodeLoc.y()));
                    Arrow2D arrow = new Arrow2D(src,dest);

                    g1.setColor(new Color(253,234,66));
                    g1.draw(arrow);
                    g1.setColor(Color.ORANGE);
//                    DecimalFormat df = new DecimalFormat("#.####");
//                    df.setRoundingMode(RoundingMode.FLOOR);
//                    g1.drawString(df.format(edge.getWeight())+"",(float)(src.x+dest.x)/2,(float)(src.y+dest.y)/2);
                }

                Iterator<api.NodeData> iterator = RunGui.getDwg_algo().getGraph().nodeIter();
                while(iterator.hasNext()){
                    // setting up the next Ellipse2D
                    NodeData nodeData = (Classes.NodeData)iterator.next();
                    GeoLoc geoLoc = (GeoLoc) nodeData.getLocation();
                    Point2D.Double point = RunGui.convertFromRangeToCanvas(new Point2D.Double(geoLoc.x(),geoLoc.y()));
                    Ellipse2D.Double node = new Ellipse2D.Double(point.x,point.y,Constants.NODE_WIDTH,Constants.NODE_HEIGHT);
                    // just setting x,y, width,height of the ellipse.

                    // drawing the ellipse with id
                    g1.setColor(new Color(60,160,236));
                    g1.setFont(new Font("dhsah", Font.BOLD, Constants.STRING_SIZE));
                    g1.drawString(""+nodeData.getKey(), (float)node.getX(), (float)node.getY());
                    g1.fill(node);
                    g1.draw(node);
                }

            }
        };
        myCanvas.setBackground(Color.darkGray);
        myCanvas.setSize((Constants.CANVAS_WIDTH),(Constants.CANVAS_HEIGHT));
        myCanvas.setLocation(10,16);
        myCanvas.setVisible(true);
        myCanvas.addMouseListener(this);
        this.add(myCanvas);


    }


}
