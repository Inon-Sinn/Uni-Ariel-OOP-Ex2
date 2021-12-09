package GUI.support;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class ArrowHead2D extends Path2D.Double{
    public ArrowHead2D( Point2D.Double head){
        moveTo(head.x,head.y);
        lineTo(head.x-6,head.y-4);
        moveTo(head.x,head.y);
        lineTo(head.x-6,head.y + 4);
    }

}
