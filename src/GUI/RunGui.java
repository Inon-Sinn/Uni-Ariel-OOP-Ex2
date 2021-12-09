package GUI;

import Algorithms.DWG_algo;
import Classes.DWG;
import Classes.EdgeData;
import Classes.GeoLoc;
import Classes.NodeData;
import GUI.GraphPack.GraphJFrame;
import GUI.MenuPack.MenuJFrame;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import GUI.support.Arrow2D;
import api.GeoLocation;
import org.w3c.dom.Node;
import org.w3c.dom.ranges.Range;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class RunGui {
    private static String Json;
    private static MenuJFrame Menu;
    private static GraphJFrame GraphJFrame;
    private static Double Maximumx=null,Minimumx=null,Maximumy=null,Minimumy=null;
    private static DWG_algo dwg_algo = new DWG_algo();
    public static void main(String[] args){
        Menu = new MenuJFrame();
    }

    /**
     *
     * @param x
     * x = 0 stands for switching from menu to graph
     * x = 1 stands for switching from graph to menu
     */

    public static void swapFrame(int x){
      switch(x){
          case 0:
              terminate(0);
              // load with json in constructor
              GraphJFrame = new GraphJFrame();
              break;
          case 1:
              terminate(1);
              Menu = new MenuJFrame();
              break;
          default:
              break;
        }
    }

    /**
     * @param program
     * program = 0 stands for terminating the menu frame
     * program = 1 stands for terminating the graph frame
     */

    public static void terminate(int program){
        switch(program){
            case 0:
                Menu.dispose();
                break;
            case 1:
                GraphJFrame.dispose();
                break;
                // if case is not 0 or 1 do nothing.
            default:
                break;
        }

    }

    /**
     * This function parses the num given to the ratio of 0 - 1
     * @param xNum are the candidates for parsing
     * @return returns values from 0 to 1
     */
    public static double ratioX(double xNum){
        // take the range of the list
        // divide num by him
        if(Maximumy == null || Minimumy == null ||
        Maximumx == null || Minimumx == null){
        setRange();
        }
        double rangeX = Maximumx - Minimumx,
                diffMax = (Maximumx - xNum);
        if(rangeX != 0)
            return diffMax/rangeX;
        else{
            return 0;
        }
    }
    // the same for y
    public static double ratioY(double yNum){
        // take the range of the list
        // divide num by him
        if(Maximumy == null || Minimumy == null ||
                Maximumx == null || Minimumx == null){
            setRange();
        }
        double rangeY = Maximumy - Minimumy,
                diffMax = (Maximumy - yNum);
        if(rangeY != 0)
            return diffMax/rangeY ;
        else{
            return 0;
        }
    }

    private static void setRange(){
        Double minimumX=null, maximumX=null,
                minimumY=null, maximumY=null;
        Iterator<api.NodeData> iterator = dwg_algo.getGraph().nodeIter();
        while(iterator.hasNext()){
            // if the values are null then initialize with the first node
            if(minimumX == null || maximumX == null ||
                    minimumY == null || maximumY == null){
                GeoLoc geoLoc = (GeoLoc) iterator.next().getLocation();
                minimumX = Double.valueOf(geoLoc.x()); maximumX = Double.valueOf(minimumX);
                minimumY = Double.valueOf(geoLoc.y()); maximumY = Double.valueOf(minimumY);
            }
            // if they are not null
            else {
                GeoLoc geoLoc = (GeoLoc) iterator.next().getLocation();
                Double curX = Double.valueOf(geoLoc.x()),
                        curY = Double.valueOf(geoLoc.y());
                minimumX = minimumX <= curX ? minimumX : curX;
                maximumX = maximumX >= curX ? maximumX : curX;
                minimumY = minimumY <= curY ? minimumY : curY;
                maximumY = maximumY >= curY ? maximumY : curY;
            }
        }
        Minimumx = minimumX;
        Maximumx = maximumX;
        Minimumy = minimumY;
        Maximumy = maximumY;
    }
    public static Iterator<Ellipse2D> getNodesIteratorFromDWG(){
        Iterator<api.NodeData> iterator = dwg_algo.getGraph().nodeIter();
        ArrayList<Ellipse2D> nodes = new ArrayList<Ellipse2D>();
        while(iterator.hasNext()){
            GeoLoc nodeLoc = (GeoLoc) iterator.next().getLocation();
            Point2D.Double curPoint = convertFromRangeToCanvas(new Point2D.Double(nodeLoc.x(), nodeLoc.y()));
            Ellipse2D node = new Ellipse2D.Double(curPoint.x, curPoint.y,
                    Constants.NODE_WIDTH,Constants.NODE_HEIGHT);
            nodes.add(node);
        }
        return nodes.iterator();
    }
    public static Point2D.Double convertFromRangeToCanvas(Point2D.Double point){
        double xRatio = ratioX(point.x),
                yRatio = ratioY(point.y);
        double xPos = Constants.NODE_WIDTH + xRatio * (Constants.CANVAS_WIDTH - 2*Constants.NODE_WIDTH),
                yPos = Constants.NODE_HEIGHT + yRatio * (Constants.CANVAS_HEIGHT - 2*Constants.NODE_HEIGHT);
        return new Point2D.Double(xPos,yPos);
    }

    public static Iterator<Arrow2D> getEdgesIteratorFromDWG(){
        Iterator<api.EdgeData> iterator = dwg_algo.getGraph().edgeIter();
        DWG dwg = (DWG) dwg_algo.getGraph();
        ArrayList<Arrow2D> arrow2DS = new ArrayList<Arrow2D>();
        while(iterator.hasNext()){
            EdgeData edgeData = (EdgeData) iterator.next();
            GeoLoc srcLoc = (GeoLoc) dwg.getNode(edgeData.getSrc()).getLocation();
            GeoLoc destLoc = (GeoLoc) dwg.getNode(edgeData.getDest()).getLocation();
            Point2D.Double srcPoint = convertFromRangeToCanvas(new Point2D.Double(srcLoc.x(),srcLoc.y()));
            Point2D.Double destPoint = convertFromRangeToCanvas(new Point2D.Double(destLoc.x(),destLoc.y()));
            arrow2DS.add(new Arrow2D(srcPoint,destPoint));
        }
        return arrow2DS.iterator();
    }

    public static void setJson(String json){
        Json = json;
    }
    public static String getJson(){
        return Json;
    }
    public static void setDwg_algo(DWG_algo dwg1){
        dwg_algo = dwg1;
    }
    public static DWG_algo getDwg_algo(){
        return dwg_algo;
    }
    public static Dimension getFrameSize(){
        return GraphJFrame.getSize();
    }
}
