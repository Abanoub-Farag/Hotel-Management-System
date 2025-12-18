package Hotel_Management_System;

import java.awt.*;


public class ZZ_Replications {

    // Centring any window
    public static void centerWindow(javax.swing.JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }



}
