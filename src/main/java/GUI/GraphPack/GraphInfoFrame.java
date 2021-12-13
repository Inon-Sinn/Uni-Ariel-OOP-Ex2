package GUI.GraphPack;

import Classes.EdgeData;
import GUI.Constants;
import GUI.RunGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;

public class GraphInfoFrame extends JFrame{

    public GraphInfoFrame() {
        setConstants();

        // getting all the edge data
        Iterator<api.EdgeData> iterator = RunGui.getDwg_algo().getGraph().edgeIter();
        String str ="";
        while (iterator.hasNext()) {
            EdgeData edge = (EdgeData) iterator.next();
            str =str + "\n\n" + edge.toString();
        }
        // setting a textarea
        final JTextArea textArea = new JTextArea(10, 20);
        JScrollPane scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setText(str);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(46, 46, 61));
        Font font = new Font("xd", Font.ITALIC, 14);
        textArea.setFont(font);
        textArea.setForeground(new Color(182, 182, 182));
        textArea.setFocusable(false);
        add(scroll);
    }
    private void setConstants() {
        this.setTitle("Information Directed Weighted Graph");
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
