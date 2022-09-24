package de.cirrus.musim.gui;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class License extends JDialog {
   private JScrollPane jScrollPane1;
   private JTextArea jTextArea1;

   public License(Frame parent, boolean modal) {
      super(parent, modal);
      this.initComponents();
   }

   private void initComponents() {
      this.jScrollPane1 = new JScrollPane();
      this.jTextArea1 = new JTextArea();
      this.setDefaultCloseOperation(2);
      this.jTextArea1.setColumns(46);
      this.jTextArea1.setEditable(false);
      this.jTextArea1.setRows(16);
      this.jTextArea1.setText(
            "   \n   Copyright 2012 ***REMOVED***\n\n   Licensed under the Apache License, Version 2.0 (the \"License\");\n   you may not use this file except in compliance with the License.\n   You may obtain a copy of the License at\n\n       http://www.apache.org/licenses/LICENSE-2.0\n\n   Unless required by applicable law or agreed to in writing, software\n   distributed under the License is distributed on an \"AS IS\" BASIS,\n   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n   See the License for the specific language governing permissions and\n   limitations under the License.");
      this.jScrollPane1.setViewportView(this.jTextArea1);
      this.getContentPane().add(this.jScrollPane1, "Center");
      this.pack();
   }

   public static void main(String[] args) {
      try {
         UIManager.LookAndFeelInfo[] arr$ = UIManager.getInstalledLookAndFeels();
         int len$ = arr$.length;

         for (int it = 0; it < len$; ++it) {
            UIManager.LookAndFeelInfo info = arr$[it];
            if ("Nimbus".equals(info.getName())) {
               UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException var5) {
         Logger.getLogger(License.class.getName()).log(Level.SEVERE, (String) null, var5);
      } catch (InstantiationException var6) {
         Logger.getLogger(License.class.getName()).log(Level.SEVERE, (String) null, var6);
      } catch (IllegalAccessException var7) {
         Logger.getLogger(License.class.getName()).log(Level.SEVERE, (String) null, var7);
      } catch (UnsupportedLookAndFeelException var8) {
         Logger.getLogger(License.class.getName()).log(Level.SEVERE, (String) null, var8);
      }

      EventQueue.invokeLater(new Runnable() {
         public void run() {
            License dialog = new License(new JFrame(), true);
            dialog.addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent e) {
                  System.exit(0);
               }
            });
            dialog.setVisible(true);
         }
      });
   }
}
