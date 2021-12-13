package Algorithms;

import Classes.NodeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class RunTime_DWG_algoTest {
    DWG_algo algo = new DWG_algo();

    @Test
    void isConnected() {
        Runnable runnable  = new Runnable() {
            @Override
            public void run() {
                algo.isConnected();
            }
        };
        run(runnable,200);
    }

    @Test
    void shortestPath() {
        Runnable runnable  = new Runnable() {
            @Override
            public void run() {
                algo.shortestPath(0,1);
            }
        };
        run(runnable,100);
    }

    @Test
    void center() {
        Runnable runnable  = new Runnable() {
            @Override
            public void run() {
                algo.center();
            }
        };
        run(runnable,4);
    }

    @Test
    void tsp() {
        Runnable runnable  = new Runnable() {
            @Override
            public void run() {
                ArrayList<api.NodeData> cities = new ArrayList<>();
                Iterator<api.NodeData> iter = algo.getGraph().nodeIter();
                for (int i = 0; i < 5 && iter.hasNext(); i++) {
                    cities.add(iter.next());
                }
                algo.tsp(cities);
            }
        };
        run(runnable,4);
    }

    /**Case 0 for G1.json, case 1 for G2.json, case 2 for G3.json.
     * case 3 for 1000 Nodes, case 4 for 10000 Nodes, case 5 for 100000 Nodes */
    void setUp(int caseNum) {
        switch(caseNum){
            case 0:
                algo.load("data\\G1.json");
                break;
            case 1:
                algo.load("data\\G2.json");
                break;
            case 2:
                algo.load("data\\G3.json");
                break;
            case 3:
                algo.load("data\\1000Nodes.json");
                break;
            case 4:
                algo.load("data\\10000Nodes.json");
                break;
            case 5:
                algo.load("data\\100000Nodes.json");
                break;
        }


    }
    void run(Runnable funcFromAlgo, int iterations){

        double[] timesForjson = new double[5];
        int k=0;
        while(k < 5) {
            long timeToLoad = System.currentTimeMillis();
            setUp(k);
            System.out.println("Time to load is : " + (System.currentTimeMillis()- timeToLoad));
            long timeToRun200 = System.currentTimeMillis();
            for (int i = 0; i < iterations; i++) {
                long curTime = System.currentTimeMillis();
                funcFromAlgo.run();
                long curDT = (System.currentTimeMillis() - curTime);
                timesForjson[k] += curDT;
            }
            System.out.println("Time to iterate " + iterations + " : " + (System.currentTimeMillis() - timeToRun200));
            timesForjson[k] = timesForjson[k] / iterations;
            System.out.println("Number of Nodes is :" + algo.getGraph().nodeSize());
            System.out.print("Running time is: " + timesForjson[k] + "\n\n");
            k+=1;
        }


    }
}