package GUI.support;

import GUI.RunGui;
import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Arrow2D extends Path2D.Double{
    private double weight;

    public Arrow2D(double distance,double weight){


    }
    private double findArrowAngle(Point2D.Double sourceNode, Point2D.Double destNode){
        double deltaY = destNode.y - sourceNode.y
                ,deltaX = destNode.x - sourceNode.x;
        double angle;
        if(deltaX != 0) {
            angle = Math.atan(deltaY / deltaX);
            return angle;
        }
        else{
         return Math.PI/2;
        }
    }


}
