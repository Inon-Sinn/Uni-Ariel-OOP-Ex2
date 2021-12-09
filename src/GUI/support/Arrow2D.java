package GUI.support;

import java.awt.geom.Path2D;

public class Arrow2D extends Path2D.Double{
    public Arrow2D(){
        moveTo(0, 10);
        lineTo(36, 10);
        moveTo(36 - 16, 0);
        lineTo(36, 10);
        moveTo(36 - 16, 20);
        lineTo(36, 10);

    }

}
