package GUI;

import GUI.GraphPack.GraphJFrame;
import GUI.MenuPack.MenuJFrame;

public class RunGui {
    private static String Json;
    private static MenuJFrame Menu;
    private static GraphJFrame GraphJFrame;
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
    public static void setJson(String json){
        Json = json;
    }
    public static String getJson(){
        return Json;

    }
}
