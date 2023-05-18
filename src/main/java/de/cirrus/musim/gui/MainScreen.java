package de.cirrus.musim.gui;

import de.cirrus.musim.ArchitectureObjectWithBusConnection;
import de.cirrus.musim.Command;
import de.cirrus.musim.CommandPerformer;
import de.cirrus.musim.Simulator;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

public class MainScreen extends JFrame {
   Simulator s = new Simulator();
   JFrame instructionTableJframe = null;
   ExecutionLog executionLog = null;
   Collection<Component> dynamicly_addet = new ArrayList<Component>();
   private JMenu FileMenu;
   private JButton InstructionStep;
   private JMenuItem OpenFiles;
   private JMenuItem Reset;
   private Box.Filler filler1;
   private JMenu infoMenu;
   private JMenu jMenu1;
   private JMenuBar jMenuBar1;
   private JPanel jPanel2;
   private JButton microStep;
   private JMenu seperatorMenu;
   private JCheckBoxMenuItem showExecutionLog;
   private JCheckBoxMenuItem showInstructions;
   private JCheckBoxMenuItem showLog;
   private JMenu viewMenu;

   public MainScreen() {
      this.initComponents();
      this.setLocationRelativeTo((Component) null);
      this.getContentPane().add(this.s, "Center");
      this.initExecutionLog();
   }

   private void initComponents() {
      this.jPanel2 = new JPanel();
      this.filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
      this.microStep = new JButton();
      this.InstructionStep = new JButton();
      this.jMenuBar1 = new JMenuBar();
      this.FileMenu = new JMenu();
      this.OpenFiles = new JMenuItem();
      this.Reset = new JMenuItem();
      this.viewMenu = new JMenu();
      this.showLog = new JCheckBoxMenuItem();
      this.showInstructions = new JCheckBoxMenuItem();
      this.showExecutionLog = new JCheckBoxMenuItem();
      this.infoMenu = new JMenu();
      this.jMenu1 = new JMenu();
      this.seperatorMenu = new JMenu();
      this.setDefaultCloseOperation(3);
      this.setTitle("Architecture Simulator v0.9 (83) by Soulwax");
      this.jPanel2.setLayout(new GridLayout(1, 0));
      this.jPanel2.add(this.filler1);
      this.microStep.setText("Micro Step");
      this.microStep.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MainScreen.this.microStepActionPerformed(evt);
         }
      });
      this.jPanel2.add(this.microStep);
      this.InstructionStep.setText("Instruction");
      this.InstructionStep.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MainScreen.this.InstructionStepActionPerformed(evt);
         }
      });
      this.jPanel2.add(this.InstructionStep);
      this.getContentPane().add(this.jPanel2, "Last");
      this.FileMenu.setText("File");
      this.OpenFiles.setAccelerator(KeyStroke.getKeyStroke(79, 2));
      this.OpenFiles.setText("Open");
      this.OpenFiles.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MainScreen.this.OpenFilesActionPerformed(evt);
         }
      });
      this.FileMenu.add(this.OpenFiles);
      this.Reset.setAccelerator(KeyStroke.getKeyStroke(82, 2));
      this.Reset.setText("Reset");
      this.Reset.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MainScreen.this.ResetActionPerformed(evt);
         }
      });
      this.FileMenu.add(this.Reset);
      this.jMenuBar1.add(this.FileMenu);
      this.viewMenu.setText("View");
      this.showLog.setText("Show System Log");
      this.showLog.setEnabled(false);
      this.viewMenu.add(this.showLog);
      this.showInstructions.setAccelerator(KeyStroke.getKeyStroke(73, 2));
      this.showInstructions.setText("Show Instruction Table");
      this.showInstructions.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MainScreen.this.showInstructionsActionPerformed(evt);
         }
      });
      this.viewMenu.add(this.showInstructions);
      this.showExecutionLog.setAccelerator(KeyStroke.getKeyStroke(69, 2));
      this.showExecutionLog.setText("Show Execution Log");
      this.showExecutionLog.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MainScreen.this.showExecutionLogActionPerformed(evt);
         }
      });
      this.viewMenu.add(this.showExecutionLog);
      this.jMenuBar1.add(this.viewMenu);
      this.infoMenu.setText("Info");
      this.infoMenu.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent evt) {
            MainScreen.this.infoMenuMouseClicked(evt);
         }
      });
      this.infoMenu.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MainScreen.this.InfoMenuActionPerformed(evt);
         }
      });
      this.jMenuBar1.add(this.infoMenu);
      this.jMenu1.setText("License");
      this.jMenu1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent evt) {
            MainScreen.this.jMenu1MouseClicked(evt);
         }
      });
      this.jMenuBar1.add(this.jMenu1);
      this.seperatorMenu.setText("|");
      this.seperatorMenu.setEnabled(false);
      this.jMenuBar1.add(this.seperatorMenu);
      this.setJMenuBar(this.jMenuBar1);
      this.pack();
   }

   private void InfoMenuActionPerformed(ActionEvent evt) {
   }

   private void infoMenuMouseClicked(MouseEvent evt) {
      Information i = new Information(this, true);
      i.setLocationRelativeTo(this);
      i.setVisible(true);
   }

   private void OpenFilesActionPerformed(ActionEvent evt) {
      try {
         ChoosePathFilesDialog choosePathFilesDialog = new ChoosePathFilesDialog(this, true);
         choosePathFilesDialog.setLocationRelativeTo(this);
         choosePathFilesDialog.setVisible(true);
         if (choosePathFilesDialog.isfilesSet()) {
            this.s.setup(choosePathFilesDialog.getArchitectureFile(), choosePathFilesDialog.getLayoutFile());
            this.refreshCommands();
            this.validate();
            this.pack();
            this.updateInstructionTableJFrame();
         }
      } catch (Exception var3) {
         JOptionPane.showMessageDialog(this, "Unable to setup the simulator. \n" + var3.getMessage(), "Error",
               MessageType.ERROR.ordinal());
      }

   }

   private void microStepActionPerformed(ActionEvent evt) {
      try {
         this.s.MicroStep();
         this.updateExecutionLogJFrame();
      } catch (IOException var3) {
         JOptionPane.showMessageDialog(this, "Unable to perform a step. \n" + var3.getMessage(), "Error",
               MessageType.ERROR.ordinal());
      }

   }

   private void showInstructionsActionPerformed(ActionEvent evt) {
      JCheckBoxMenuItem MI = (JCheckBoxMenuItem) evt.getSource();
      this.showInstructionTableJFrame(MI.getState());
   }

   private void ResetActionPerformed(ActionEvent evt) {
      try {
         this.s.setup();
         this.refreshCommands();
         this.validate();
         this.executionLog.clearInstructions();
      } catch (Exception var3) {
         var3.printStackTrace();
         JOptionPane.showMessageDialog(this, "Unable to reset. \n" + var3.getMessage(), "Error",
               MessageType.ERROR.ordinal());
      }

   }

   private void InstructionStepActionPerformed(ActionEvent evt) {
      try {
         this.s.InstructionStep();
         this.updateExecutionLogJFrame();
      } catch (IOException var3) {
         JOptionPane.showMessageDialog(this, "Unable to perfom the instruction. \n" + var3.getMessage(), "Error",
               MessageType.ERROR.ordinal());
      }

   }

   private void showExecutionLogActionPerformed(ActionEvent evt) {
      JCheckBoxMenuItem MI = (JCheckBoxMenuItem) evt.getSource();
      this.showExecutionLogJFrame(MI.getState());
   }

   private void jMenu1MouseClicked(MouseEvent evt) {
      License l = new License(this, true);
      l.setLocationRelativeTo(this);
      l.setVisible(true);
   }

   private void showInstructionTableJFrame(boolean visibility) {
      if (this.instructionTableJframe == null) {
         this.instructionTableJframe = new JFrame("Architecture Simulator - Instruction Table");
         this.instructionTableJframe.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
               MainScreen.this.showInstructions.setState(false);
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
         });
      }

      this.updateInstructionTableJFrame();
      this.instructionTableJframe.setVisible(visibility);
      if (visibility) {
         Point insertPoint = this.getLocation();
         insertPoint.translate(30, 30);
         this.instructionTableJframe.setLocation(insertPoint);
      }

   }

   private void showExecutionLogJFrame(boolean visibility) {
      this.updateExecutionLogJFrame();
      this.executionLog.setVisible(visibility);
      if (visibility) {
         Point insertPoint = this.getLocation();
         insertPoint.translate(30, 30);
         this.executionLog.setLocation(insertPoint);
      }

   }

   private void updateExecutionLogJFrame() {
      if (this.executionLog != null) {
         this.executionLog.appedInstruction(this.s.getTimeingandControll().collectPerformedInstruction());
      }

   }

   private void updateInstructionTableJFrame() {
      if (this.instructionTableJframe != null) {
         this.instructionTableJframe.getContentPane().removeAll();
         JTable msct = new JTable(this.s.getTimeingandControll().generateInstuctionTable());
         this.instructionTableJframe.getContentPane().add(new JScrollPane(msct), "Center");
         this.instructionTableJframe.validate();
         this.instructionTableJframe.pack();
      }

   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            (new MainScreen()).setVisible(true);
         }
      });
   }

   private void refreshCommands() {
      Iterator it = this.dynamicly_addet.iterator();

      while (it.hasNext()) {
         Component component = (Component) it.next();
         component.getParent().remove(component);
      }

      this.dynamicly_addet.clear();
      it = this.s.getCommands().iterator();

      while (it.hasNext()) {
         CommandPerformer.Entry entry = (CommandPerformer.Entry) it.next();
         final ArchitectureObjectWithBusConnection aowbc = entry.getPerformer();
         JMenu jm = new JMenu(aowbc.getName());
         Collection<Command> commands = entry.getCommands();
         for (Command command : commands) {
            final JMenuItem item = new JMenuItem(command.getName());
            item.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  try {
                     command.execute(aowbc);
                  } catch (Exception var3) {
                     JOptionPane.showMessageDialog(item, "Unable to setup the simulator. \n" + var3.getMessage(),
                           "Error", MessageType.ERROR.ordinal());
                  }

               }
            });
            jm.add(item);
         }

         this.dynamicly_addet.add(jm);
         this.jMenuBar1.add(jm);
      }

   }

   private void initExecutionLog() {
      this.executionLog = new ExecutionLog("Architecture Simulator - Instructions Performed");
      this.executionLog.addWindowListener(new WindowListener() {
         public void windowOpened(WindowEvent e) {
         }

         public void windowClosing(WindowEvent e) {
            MainScreen.this.showExecutionLog.setState(false);
         }

         public void windowClosed(WindowEvent e) {
         }

         public void windowIconified(WindowEvent e) {
         }

         public void windowDeiconified(WindowEvent e) {
         }

         public void windowActivated(WindowEvent e) {
         }

         public void windowDeactivated(WindowEvent e) {
         }
      });
      this.executionLog.validate();
      this.executionLog.pack();
   }
}
