package GUI;

import GUI.Images.Resource;

import javax.swing.*;
import java.awt.*;

/** This class creates a new window that shows the info of the game. */
public class Infos extends JFrame {

    private final JLabel infos;

    /** The constructor initialize the GUI components. */
    public Infos(){

        infos = new JLabel();
        infos();

        JFrame infoFrame = new JFrame();

        infoFrame.setSize(1000,500);
        infoFrame.setTitle("Infos");
        infoFrame.setResizable(false);
        infoFrame.getContentPane().setBackground(new Color(69, 43, 217));
        infoFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);

        infoFrame.add(infos, BorderLayout.CENTER);
        infoFrame.setVisible(true);
    }

    /** Sets the image with info of the game. */
    private void infos(){
        if (Resource.getURL("Infos.png") != null)
            infos.setIcon(new ImageIcon(Resource.getURL("Infos.png")));
    }
}
