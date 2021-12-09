package GUI.support;

import Classes.NodeData;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyNode extends Ellipse2D.Double{
    int ID;
    public MyNode(int ID, NodeData nodeData, Dimension dimension){
        this.width = dimension.width;
        this.height = dimension.height;
        this.ID = ID;
        this.x = nodeData.getLocation().x();
        this.y = nodeData.getLocation().y();
    }



}
