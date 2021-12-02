import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

import Classes.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    public static void main(String[] args) throws IOException {
        //System.out.println(getGraph("C:\\Users\\yaron\\IdeaProjects\\Uni-Ariel-OOP-Ex2\\data\\G1.json"));
        System.out.println("fuck u");
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGraph(String json_file) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Path.of(json_file));
        DWG g = gson.fromJson(reader, DWG.class);
        reader.close();
        return (DirectedWeightedGraph) g;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        //
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
        //
        // ********************************
    }
}