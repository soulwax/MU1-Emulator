package de.cirrus.musim;

import de.cirrus.musim.gui.DebugWindow;
import de.cirrus.musim.gui.MainScreen;
import javax.swing.JFrame;

public class Main {
   public Main() {
   }

   public static void main(String[] args) {
      Object w;
      if (args.length == 1 && args[0].equals("-d")) {
         w = new DebugWindow();
      } else {
         w = new MainScreen();
      }

      ((JFrame) w).setDefaultCloseOperation(3);
      ((JFrame) w).setVisible(true);
   }
}
