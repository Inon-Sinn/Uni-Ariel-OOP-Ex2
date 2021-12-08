import Classes.DWG;
import Classes.dwgFromJson;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    public static void main(String[] args) throws IOException {
       DWG dwg = (DWG) getGraph("C:\\Users\\Alex\\Desktop\\Ex22\\data\\G1.json");

    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGraph(String json_file) throws IOException {
        Gson gson = new Gson();
        FileReader r = new FileReader(json_file);
        JsonReader reader = new JsonReader(r);
        dwgFromJson dwg = gson.fromJson(reader, dwgFromJson.class);
        DWG jsonDWG = new DWG();
        for (int i = 0; i < dwg.getNodes().size(); i++) {
            jsonDWG.addNode(dwg.getNodes().get(i));
        }
        for (int i = 0; i < dwg.getEdges().size(); i++) {
            jsonDWG.connect(dwg.getEdges().get(i));
        }
        return jsonDWG;
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