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

    public Arrow2D(Point2D.Double src, Point2D.Double dest){
        moveTo(src.x,src.y);
        lineTo(dest.x,dest.y);
//        double angle = findArrowAngle(src,dest);
//        double angleP1 = angle - (7/6) *Math.PI, angleP2 = angle - (5/6) * Math.PI;
//        double dx1, dy1, dx2, dy2;
//        dx1 = 20*Math.sin(angleP1);
//        dy1 = 20*Math.cos(angleP1);
//        dx2 = 20*Math.sin(angleP2);
//        dy2 = 20*Math.cos(angleP2);
//        Point2D.Double point1 = new Point2D.Double(dest.x - dx1,dest.y - dy1),
//        point2 = new Point2D.Double(dest.x - dx2,dest.y - dy2);
//        moveTo(point1.x,point1.y);
//        lineTo(dest.x,dest.y);
//        moveTo(point2.x,point2.y);
//        lineTo(dest.x,dest.y);
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
