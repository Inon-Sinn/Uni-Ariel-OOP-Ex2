package GUI.support;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Arrow2D extends Path2D.Double{
    public Arrow2D(Point2D.Double sourceNode, Point2D.Double destNode){

        moveTo(sourceNode.x,sourceNode.y);
        lineTo(destNode.x,destNode.y);
        moveTo(destNode.x,destNode.y);
        double angle = -Math.PI + findArrowAngle(sourceNode,destNode);

        double angle1 = angle + Math.PI/6, angle2 = (angle - Math.PI/6);

        double dx1,dx2,dy1,dy2;
        if(angle1 < 0) {
            dx1 = 8 * Math.cos(angle1);
            dy1 = 8 * Math.sin(angle1);
        }
        else{
            dx1 = 8 * Math.sin(angle1);
            dy1 = 8 * Math.cos(angle1);
        }
        if(angle2 < 0){
            dx2 = 8 * Math.cos(angle2);
            dy2 = 8 * Math.sin(angle2);
        }
        else{
            dx2 = 8 * Math.sin(angle2);
            dy2 = 8 * Math.cos(angle2);
        }


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
