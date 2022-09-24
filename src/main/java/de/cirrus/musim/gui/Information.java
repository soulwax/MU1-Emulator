package de.cirrus.musim.gui;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Information extends JDialog {
   private JLabel jLabel1;
   private JLabel jLabel10;
   private JLabel jLabel2;
   private JLabel jLabel3;
   private JLabel jLabel4;
   private JLabel jLabel5;
   private JLabel jLabel6;
   private JLabel jLabel7;
   private JLabel jLabel8;
   private JLabel jLabel9;
   private JPanel jPanel1;
   private JPanel jPanel2;
   private JTextField jTextField1;
   private JTextField jTextField2;
   private JTextField jTextField3;
   private JTextField jTextField4;
   private JTextField jTextField5;
   private JTextField jTextField6;
   private JTextField jTextField7;
   private JTextField jTextField8;

   public Information(Frame parent, boolean modal) {
      super(parent, modal);
      this.initComponents();
   }

   private void initComponents() {
      this.jPanel1 = new JPanel();
      this.jTextField7 = new JTextField();
      this.jTextField3 = new JTextField();
      this.jLabel8 = new JLabel();
      this.jTextField5 = new JTextField();
      this.jTextField4 = new JTextField();
      this.jTextField6 = new JTextField();
      this.jLabel2 = new JLabel();
      this.jLabel6 = new JLabel();
      this.jLabel5 = new JLabel();
      this.jTextField2 = new JTextField();
      this.jTextField1 = new JTextField();
      this.jLabel3 = new JLabel();
      this.jLabel1 = new JLabel();
      this.jLabel4 = new JLabel();
      this.jLabel9 = new JLabel();
      this.jPanel2 = new JPanel();
      this.jLabel7 = new JLabel();
      this.jTextField8 = new JTextField();
      this.jLabel10 = new JLabel();
      this.setDefaultCloseOperation(2);
      this.setTitle("Architecture Simulator - About");
      this.setResizable(false);
      this.jTextField7.setEditable(false);
      this.jTextField7.setText("83");
      this.jTextField3.setEditable(false);
      this.jTextField3.setText("***REMOVED***");
      this.jLabel8.setText("Software");
      this.jTextField5.setEditable(false);
      this.jTextField5.setText("aloko.de");
      this.jTextField4.setEditable(false);
      this.jTextField4.setText("oko@aloko.de");
      this.jTextField6.setEditable(false);
      this.jTextField6.setText("Mon, 6-May-2013 22:47:28 CEST");
      this.jLabel2.setText("version");
      this.jLabel6.setText("home");
      this.jLabel5.setText("build");
      this.jTextField2.setEditable(false);
      this.jTextField2.setText("0.9");
      this.jTextField1.setEditable(false);
      this.jTextField1.setText("Architecture Simulator");
      this.jLabel3.setText("build date");
      this.jLabel1.setText("author");
      this.jLabel4.setText("email");
      this.jLabel9.setText("Information about the Software:");
      GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
      this.jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
                  .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel9)
                        .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jLabel5)
                                    .addComponent(this.jLabel3).addComponent(this.jLabel1).addComponent(this.jLabel4)
                                    .addComponent(this.jLabel6).addComponent(this.jLabel8).addComponent(this.jLabel2))
                              .addGap(10, 10, 10)
                              .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(this.jTextField3, -1, 461, 32767)
                                    .addComponent(this.jTextField4, -1, 461, 32767)
                                    .addComponent(this.jTextField5, -1, 461, 32767)
                                    .addComponent(this.jTextField6, -1, 461, 32767)
                                    .addComponent(this.jTextField1, -1, 461, 32767)
                                    .addComponent(this.jTextField2, -1, 461, 32767)
                                    .addComponent(this.jTextField7, -1, 461, 32767))))
                  .addContainerGap()));
      jPanel1Layout
            .setVerticalGroup(
                  jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(
                              jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel9)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                          .addComponent(this.jLabel8).addComponent(this.jTextField1, -2, -1, -2))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                          .addComponent(this.jLabel2).addComponent(this.jTextField2, -2, -1, -2))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                          .addComponent(this.jLabel1).addComponent(this.jTextField3, -2, -1, -2))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                          .addComponent(this.jLabel4).addComponent(this.jTextField4, -2, -1, -2))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                          .addComponent(this.jLabel6).addComponent(this.jTextField5, -2, -1, -2))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                          .addComponent(this.jLabel3).addComponent(this.jTextField6, -2, -1, -2))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                          .addComponent(this.jLabel5).addComponent(this.jTextField7, -2, -1, -2))
                                    .addContainerGap(-1, 32767)));
      this.jLabel7.setText("Report issue");
      this.jTextField8.setEditable(false);
      this.jTextField8.setText("Architecture Simulator Mon, 6-May-2013 22:47:28 CEST v0.9 (83)");
      this.jLabel10.setText("Copy this");
      GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
      this.jPanel2.setLayout(jPanel2Layout);
      jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout
            .createSequentialGroup().addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel7)
                  .addGroup(jPanel2Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(this.jLabel10)
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jTextField8, -1, 463, 32767)))
            .addContainerGap()));
      jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel7)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.jTextField8, -2, -1, -2).addComponent(this.jLabel10))
                  .addContainerGap(-1, 32767)));
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING).addGap(0, 538, 32767)
                  .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addGap(0, 0, 32767)
                              .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(this.jPanel1, -2, -1, -2).addComponent(this.jPanel2, -2, -1, -2))
                              .addGap(0, 0, 32767))));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGap(0, 300, 32767).addGroup(
            layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(0, 7, 32767)
                  .addComponent(this.jPanel1, -2, -1, -2).addComponent(this.jPanel2, -2, -1, -2).addGap(0, 8, 32767))));
      this.pack();
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            Information dialog = new Information(new JFrame(), true);
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
