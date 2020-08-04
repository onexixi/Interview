package GUI;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class FiveXFive extends JFrame {

    public static void main(String[] args) {
        FiveXFive f = new FiveXFive();
        f.launchFrame();

    }

    public void launchFrame() {
        // The width & Height of the window
        final int formWidth = 500;
        final int formHeight = 500;
        // The width & Height of the screen
        int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        // Set title, size, location & visible
//        this.setTitle("Hello World! I'm coming!");
        // width + left + right, height + top + bottom
        this.setSize(formWidth+8+8, formHeight+31+8);
        // set window in the middle
        this.setLocation((screenWidth-formWidth)/2, (screenHeight-formHeight)/2);
        this.setVisible(true);

        Insets insets = this.getInsets();
        System.out.println("top: " + insets.top);
        System.out.println("bottom: " + insets.bottom);
        System.out.println("left: " + insets.left);
        System.out.println("right: " + insets.right);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        g.drawLine(8, 31, 508, 531);
        g.drawLine(8, 531, 508, 31);
        for (int y = 31; y < (500+31); y += 100) {
            for (int x = 8; x < (500+8); x += 100) {
                g.drawRect(x, y, 100, 100);
            }
        }
    }
}