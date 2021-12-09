package GUI.support;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Arrow2D extends Path2D.Double{
    public Arrow2D(Point2D.Double sourceNode, Point2D.Double destNode){

        moveTo(sourceNode.x,sourceNode.y);
        lineTo(destNode.x,destNode.y);
        moveTo(destNode.x,destNode.y);
        double angle = findArrowAngle(sourceNode,destNode);
        double angle1 = angle + Math.PI/6, angle2 = angle - Math.PI/6;
        double dx1 = 8 * Math.cos(angle1), dy1 = 8* Math.sin(angle1),
        dx2 = 8*Math.cos(angle2), dy2 = 8*Math.sin(angle2);
        Point2D.Double point1 = new Point2D.Double(destNode.x + dx1,destNode.y + dy1);
        Point2D.Double point2 = new Point2D.Double(destNode.x + dx2, destNode.y + dy2);
        lineTo(point1.x,point1.y);
        moveTo(destNode.x,destNode.y);
        lineTo(point2.x,point2.y);
        lineTo(point1.x,point1.y);
//        moveTo(0, 10);
//        lineTo(36, 10);
//        moveTo(36 - 16, 0);
//        lineTo(36, 10);
//        moveTo(36 - 16, 20);
//        lineTo(36, 10);

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
