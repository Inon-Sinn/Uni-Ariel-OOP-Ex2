package GUI;

import java.awt.*;

public class Constants {
    public static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    //public static Dimension WindowSize = RunGui.getFrameSize();
    public static final double WIDTH_HEIGHT_RATIO = SCREEN_DIMENSION.width/SCREEN_DIMENSION.height;
    public static final int CANVAS_WIDTH = SCREEN_DIMENSION.width - 400;
    public static final int CANVAS_HEIGHT = SCREEN_DIMENSION.height - 100;
    public static final Dimension BUTTON_SIZE = new Dimension(100,50);
    public static final int NODE_WIDTH = 14;
    public static final int NODE_HEIGHT = 14;
    public static final int STRING_SIZE = 15;
    public static final int BUTTONS_X_ALIGNMENT = Constants.SCREEN_DIMENSION.width-380;
    public static final int JTEXT_X_ALIGNMENT = Constants.SCREEN_DIMENSION.width - 280;
    public static final Dimension TEXT_FIELD_SIZE = new Dimension(200, 20);

}
