package GUI;
import javax.swing.JFrame;
import java.awt.*;

public class RunGraph extends JFrame{
    private Dimension dimension = new Dimension(600,560);

    public RunGraph(){
        setConstants();
        // set panel for nodes
    }
    private void setConstants(){
        this.setTitle("Directed Weighted Graph");
        this.setSize(this.dimension);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args){
        RunGraph graph = new RunGraph();

    }
}
