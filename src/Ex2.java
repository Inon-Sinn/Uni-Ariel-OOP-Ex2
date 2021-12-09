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
    public static void main(String[] args) throws IOException {
       DWG dwg = (DWG) getGraph("C:\\Users\\Inons\\IdeaProjects\\Ex2 - test\\data\\G1.json");

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
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        String[] ar = null;
        RunGui.main(ar);
        // ********************************
    }
}