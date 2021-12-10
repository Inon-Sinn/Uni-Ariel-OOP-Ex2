package GUI.MenuPack;

import GUI.GraphPack.*;
import GUI.RunGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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

        File Data = new File("data");
        File[] jFiles = Data.listFiles();
        String[] jSons_names = new String[jFiles.length];
        int i=0;
        for (File object : jFiles) {
            if(object.isFile())
            jSons_names[i++] = object.getName();
        }
        JComboBox selectedJsons = new JComboBox(jSons_names);
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
                String json_file = (String) cb.getSelectedItem();
                RunGui.setJson(json_file);
                RunGui.getDwg_algo().load("data\\" + json_file);
                this.label.setText("You have Selected: " + RunGui.getJson());
                break;
            case 1:
                RunGui.swapFrame(0);
                break;
        }
    }
}
