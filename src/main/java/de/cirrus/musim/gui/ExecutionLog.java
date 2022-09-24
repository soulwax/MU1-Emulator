package de.cirrus.musim.gui;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ExecutionLog extends JFrame {
   private JLabel jLabel1;
   private JScrollPane jScrollPane1;
   private JTextArea jTextArea1;

   public ExecutionLog() {
      this.initComponents();
   }

   public ExecutionLog(String Title) {
      super(Title);
      this.initComponents();
   }

   private void initComponents() {
      this.jLabel1 = new JLabel();
      this.jScrollPane1 = new JScrollPane();
      this.jTextArea1 = new JTextArea();
      this.jLabel1.setText("Performed Instructions:");
      this.jTextArea1.setColumns(10);
      this.jTextArea1.setEditable(false);
      this.jTextArea1.setRows(5);
      this.jScrollPane1.setViewportView(this.jTextArea1);
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout
                  .createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jLabel1).addComponent(this.jScrollPane1, -1, 127, 32767))
                  .addContainerGap()));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1)
                  .addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -1, 258, 32767)
                  .addContainerGap()));
      this.pack();
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            (new ExecutionLog()).setVisible(true);
         }
      });
   }

   public void appedInstruction(String collectPerformedInstruction) {
      if (collectPerformedInstruction != null && collectPerformedInstruction.length() != 0) {
         if (this.jTextArea1.getText().length() > 0) {
            this.jTextArea1.append("\n");
         }

         this.jTextArea1.append(collectPerformedInstruction);
      }
   }

   public void clearInstructions() {
      this.jTextArea1.setText("");
   }
}
