import Algorithms.DWG_algo;
import Classes.DWG;
import Classes.NodeData;
import Classes.dwgFromJson;
import GUI.RunGui;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    private final static int NO_ARGUMENT = -1;
    public static void main(String[] args) throws IOException {
        String filename;
        try {
            filename = args[0];
            try{
                filename = "data\\" + filename;
                File file = new File(filename);
                FileReader reader = new FileReader(file);
                runGUI(filename);
            }
            catch(IOException e){
                System.out.println("No such file sorry");
            }
        }
        catch(ArrayIndexOutOfBoundsException ex){
            runGUI("" + NO_ARGUMENT);
        }
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGraph(String json_file) throws IOException {
        return getGrapgAlgo(json_file).getGraph();
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {

        // ****** Add your code here ******
        DirectedWeightedGraphAlgorithms ans;
        DWG_algo algorithms = new DWG_algo();
        algorithms.load(json_file);
        ans = (DirectedWeightedGraphAlgorithms) algorithms;
        // ********************************
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        // ****** Add your code here ******
        try{
            if(Integer.parseInt(json_file) == NO_ARGUMENT){
                String[] s = new String[0];
                RunGui.main(s);
            }
        }
        catch(NumberFormatException e){
            String[] ar = {json_file};
            RunGui.main(ar);
        }
        // ********************************
    }
}