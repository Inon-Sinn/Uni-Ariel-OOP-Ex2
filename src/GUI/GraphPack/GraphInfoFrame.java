package GUI.GraphPack;

import Classes.EdgeData;
import GUI.Constants;
import GUI.RunGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;

public class GraphInfoFrame extends JFrame implements MouseWheelListener {

    public GraphInfoFrame() {
        setConstants();
        JTextArea informationLabel = new JTextArea("");
        informationLabel.setBounds(0, 0, this.getSize().width, this.getSize().height);
//        informationLabel.setVerticalTextPosition(JLabel.TOP);
//        informationLabel.setHorizontalTextPosition(JLabel.LEFT);
        informationLabel.setBackground(new Color(225,164,255));
        Font font = new Font("xd", Font.ITALIC, 14);
        informationLabel.setFont(font);
        informationLabel.setForeground(new Color(15,85,15));
        informationLabel.setOpaque(true);
        informationLabel.setVisible(true);
        informationLabel.setFocusable(false);
        Iterator<api.EdgeData> iterator = RunGui.getDwg_algo().getGraph().edgeIter();
        while (iterator.hasNext()) {
            EdgeData edge = (EdgeData) iterator.next();
            String str = informationLabel.getText() + "\n" + edge.toString();
            informationLabel.setText(str);
        }
        add(informationLabel);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setVisible(true);
        scrollBar.setSize(50, 100);
        scrollBar.setLocation(this.getSize().width - 80, this.getSize().height / 2);
        scrollBar.addMouseWheelListener(this);
        add(scrollBar);
    }

    private void setConstants() {
        this.setTitle("Information Directed Weighted Graph");
        this.setLayout(null);
        this.setSize(Constants.SCREEN_DIMENSION.width / 2, Constants.SCREEN_DIMENSION.height / 2);
        this.setLocation(Constants.SCREEN_DIMENSION.width / 3, Constants.SCREEN_DIMENSION.height / 3);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }
}
