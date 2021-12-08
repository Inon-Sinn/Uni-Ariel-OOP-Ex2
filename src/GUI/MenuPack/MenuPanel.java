package GUI.MenuPack;

import GUI.GraphPack.*;
import GUI.RunGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    private String selectedJsonFile;
    private JLabel label;
    public MenuPanel(){
        this.selectedJsonFile = "";
        this.setLayout(null);
        this.setBackground(Color.white);
        addComponentsToPane();

    }
    private void addComponentsToPane(){
        Font font = new Font("haha", Font.ITALIC, 15);
        this.label = new JLabel("Select A Json and click the load button");
        label.setFont(font);
        label.setLocation(0,10);
        label.setBackground(Color.blue);
        label.setSize(300,15);
        //label.setVisible(true);
        this.add(label);

        String[] jsons = {"G1.json", "G2.json", "G3.json"};
        JComboBox selectedJsons = new JComboBox(jsons);
        selectedJsons.setSelectedIndex(2);
        selectedJsons.addActionListener(this);
        selectedJsons.setActionCommand("0");
        selectedJsons.setSize(280,20);
        selectedJsons.setLocation(4,40);
        this.add(selectedJsons);

        JButton loadButton = new JButton("Load Json");
        loadButton.addActionListener(this);
        loadButton.setActionCommand("1");
        loadButton.setSize(100,50);
        loadButton.setLocation(90,100);
        this.add(loadButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(e.getActionCommand());
        /**
         *  case 0 stands for JsonComboBox
         *  case 1 stands for LoadButton
         */
        switch(x) {
            case 0:
                JComboBox cb = (JComboBox) e.getSource();
                RunGui.setJson((String) cb.getSelectedItem());
                this.label.setText("You have Selected: " + RunGui.getJson());
                break;
            case 1:
                RunGui.swapFrame(0);
                break;
        }
    }
}
