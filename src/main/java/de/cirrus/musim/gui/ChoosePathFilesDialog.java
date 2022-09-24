package de.cirrus.musim.gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import org.jdesktop.layout.GroupLayout;

public class ChoosePathFilesDialog extends JDialog {
   private boolean filesSet = false;
   private JTextField architecturetxt;
   private JButton browseArchitecture;
   private JButton browseLayout;
   private JButton jButton3;
   private JLabel jLabel1;
   private JLabel jLabel2;
   private JLabel jLabel3;
   private JTextField layouttxt;

   public boolean isfilesSet() {
      return this.filesSet;
   }

   public String getArchitectureFile() {
      return this.architecturetxt.getText();
   }

   public String getLayoutFile() {
      return this.layouttxt.getText();
   }

   public ChoosePathFilesDialog(Frame parent, boolean modal) {
      super(parent, modal);
      this.initComponents();
      this.architecturetxt.setText(System.getProperty("user.dir"));
      this.layouttxt.setText(System.getProperty("user.dir"));
   }

   private void initComponents() {
      this.jLabel1 = new JLabel();
      this.jLabel2 = new JLabel();
      this.architecturetxt = new JTextField();
      this.browseArchitecture = new JButton();
      this.layouttxt = new JTextField();
      this.browseLayout = new JButton();
      this.jButton3 = new JButton();
      this.jLabel3 = new JLabel();
      this.setDefaultCloseOperation(2);
      this.setTitle("Architecture Simulator - Open files");
      this.jLabel1.setText("Architecture:");
      this.jLabel2.setText("Layout:");
      this.architecturetxt.setText("architecturetxt");
      this.architecturetxt.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            ChoosePathFilesDialog.this.architecturetxtActionPerformed(evt);
         }
      });
      this.browseArchitecture.setText("Browse");
      this.browseArchitecture.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            ChoosePathFilesDialog.this.browseArchitectureActionPerformed(evt);
         }
      });
      this.layouttxt.setText("layouttxt");
      this.browseLayout.setText("Browse");
      this.browseLayout.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            ChoosePathFilesDialog.this.browseLayoutActionPerformed(evt);
         }
      });
      this.jButton3.setText("Setup");
      this.jButton3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            ChoosePathFilesDialog.this.jButton3ActionPerformed(evt);
         }
      });
      this.jLabel3.setText("Please, select the architecture and the corresponding layout file.");
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(1).add((GroupLayout.Group) layout.createSequentialGroup()
            .addContainerGap()
            .add((GroupLayout.Group) layout.createParallelGroup(1).add(2, (GroupLayout.Group) layout
                  .createSequentialGroup()
                  .add((GroupLayout.Group) layout
                        .createParallelGroup(1).add((Component) this.jLabel1).add((Component) this.jLabel2))
                  .addPreferredGap(0)
                  .add((GroupLayout.Group) layout.createParallelGroup(1).add(this.layouttxt, -1, 563, 32767)
                        .add(this.architecturetxt, -1, 563, 32767))
                  .addPreferredGap(0)
                  .add((GroupLayout.Group) layout.createParallelGroup(2, false).add(1, this.jButton3, -1, -1, 32767)
                        .add(1, this.browseLayout, -1, -1, 32767).add(1, (Component) this.browseArchitecture)))
                  .add((Component) this.jLabel3))
            .addContainerGap()));
      layout.setVerticalGroup(layout.createParallelGroup(1).add(2,
            (GroupLayout.Group) layout.createSequentialGroup().addContainerGap().add((Component) this.jLabel3)
                  .addPreferredGap(0, -1, 32767)
                  .add((GroupLayout.Group) layout.createParallelGroup(3).add((Component) this.jLabel1)
                        .add(this.architecturetxt, -2, -1, -2).add((Component) this.browseArchitecture))
                  .addPreferredGap(1)
                  .add((GroupLayout.Group) layout.createParallelGroup(3).add((Component) this.jLabel2)
                        .add(this.layouttxt, -2, -1, -2).add((Component) this.browseLayout))
                  .add(18, 18, 18).add((Component) this.jButton3).addContainerGap()));
      this.pack();
   }

   private void browseArchitectureActionPerformed(ActionEvent evt) {
      this.openfile(ChoosePathFilesDialog.Option.Architecture);
   }

   private void jButton3ActionPerformed(ActionEvent evt) {
      if (this.validateFiles()) {
         this.setVisible(false);
      }

   }

   private void architecturetxtActionPerformed(ActionEvent evt) {
   }

   private void browseLayoutActionPerformed(ActionEvent evt) {
      this.openfile(ChoosePathFilesDialog.Option.Layout);
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            ChoosePathFilesDialog dialog = new ChoosePathFilesDialog(new JFrame(), true);
            dialog.addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent e) {
                  System.exit(0);
               }
            });
            dialog.setVisible(true);
         }
      });
   }

   private void openfile(final Option o) {
      JFileChooser chooser = new JFileChooser("Select file");
      chooser.setDialogType(0);
      chooser.setFileSelectionMode(2);
      chooser.setFileFilter(new FileFilter() {
         public boolean accept(File f) {
            String name = f.getName();
            return f.isDirectory() | name.endsWith(".architecture.xml") | name.endsWith(".layout.xml");
         }

         public String getDescription() {
            return "*.architecture.xml or *.layout.xml";
         }
      });
      File file = new File(System.getProperty("user.dir"));
      chooser.setCurrentDirectory(file);
      chooser.addPropertyChangeListener(new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent e) {
            if (e.getPropertyName().equals("SelectedFileChangedProperty")) {
               File tmpfile = (File) e.getNewValue();
               if (tmpfile == null) {
                  return;
               }

               if (tmpfile.isDirectory()) {
                  return;
               }

               if (o == ChoosePathFilesDialog.Option.Architecture) {
                  ChoosePathFilesDialog.this.architecturetxt.setText(tmpfile.getPath());
               } else if (o == ChoosePathFilesDialog.Option.Layout) {
                  ChoosePathFilesDialog.this.layouttxt.setText(tmpfile.getPath());
               }
            }

         }
      });
      chooser.setVisible(true);
      chooser.showOpenDialog((Component) null);
      chooser.setVisible(false);
   }

   private boolean validateFiles() {
      File ArchitectureFile = new File(this.architecturetxt.getText());
      File LayoutFile = new File(this.layouttxt.getText());
      this.filesSet = true;
      if (ArchitectureFile == null || LayoutFile == null || LayoutFile.isDirectory() || ArchitectureFile.isDirectory()
            || !LayoutFile.canRead() || !ArchitectureFile.canRead()) {
         JOptionPane.showMessageDialog(this, "Choose an architecure and layout file.", "Error",
               MessageType.ERROR.ordinal());
         this.filesSet = false;
      }

      return this.filesSet;
   }

   private static enum Option {
      Architecture,
      Layout;

      private Option() {
      }
   }
}
