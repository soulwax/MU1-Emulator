package de.cirrus.musim.gui;

import de.cirrus.musim.ArchitectureObjectWithBusConnection;
import de.cirrus.musim.Bus;
import de.cirrus.musim.Simulator;
import de.cirrus.musim.TimingAndControl;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DebugWindow extends JFrame {
   JTextArea notificatinArea = new JTextArea(6, 30);
   JButton setupbtn = new JButton("[Setup]");
   JButton button = new JButton("Step");
   JButton Instruction = new JButton("Instuctions");
   JTextField archfile = new JTextField(System.getProperty("user.dir") + "/res/mu0-2.architecture.xml");
   JTextField layoutfile = new JTextField(System.getProperty("user.dir") + "/res/mu0-2.layout.xml");
   TimingAndControl TAC = new TimingAndControl();
   Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap = new HashMap<ArchitectureObjectWithBusConnection, Point2D>();
   Map<Bus, HashSet<ArchitectureObjectWithBusConnection>> BusConnections = new HashMap<Bus, HashSet<ArchitectureObjectWithBusConnection>>();
   Simulator s = null;

   public DebugWindow() {
      Container pane = this.getContentPane();
      this.s = new Simulator(this.archfile.getText(), this.layoutfile.getText());
      pane.setLayout(new BorderLayout());
      JPanel jp = new JPanel();
      BoxLayout b = new BoxLayout(jp, 0);
      jp.setLayout(b);
      jp.add(this.button);
      jp.add(this.setupbtn);
      jp.add(this.Instruction);
      JPanel jp1 = new JPanel();
      BoxLayout b1 = new BoxLayout(jp1, 1);
      jp1.setLayout(b1);
      jp1.add(this.archfile);
      jp1.add(this.layoutfile);
      jp.add(jp1);
      pane.add(jp, "First");
      pane.add(this.s, "Center");
      JScrollPane scrollPane = new JScrollPane(this.notificatinArea);
      pane.add(scrollPane, "Last");
      this.Instruction.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JFrame g = new JFrame();
            JTable msct = new JTable(DebugWindow.this.s.getTimeingandControll().generateInstuctionTable());
            g.getContentPane().add(new JScrollPane(msct), "Center");
            g.validate();
            g.pack();
            g.setVisible(true);
         }
      });
      this.button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               DebugWindow.this.s.MicroStep();
            } catch (IOException var3) {
               DebugWindow.this.notificatinArea
                     .setText(DebugWindow.this.notificatinArea.getText() + "Fail: \n" + var3.getMessage() + "\n");
            }

            ((JButton) e.getSource()).getRootPane().repaint();
         }
      });
      this.setupbtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               DebugWindow.this.notificatinArea.setText(
                     DebugWindow.this.notificatinArea.getText() + "\n" + "Trying to setup simulator..." + "\n");
               DebugWindow.this.s.setup(DebugWindow.this.archfile.getText(), DebugWindow.this.layoutfile.getText());
               DebugWindow.this.notificatinArea
                     .setText(DebugWindow.this.notificatinArea.getText() + "\n" + "Success!" + "\n");
            } catch (ParserConfigurationException var3) {
               DebugWindow.this.notificatinArea
                     .setText(DebugWindow.this.notificatinArea.getText() + "Fail: \n" + var3.getMessage() + "\n");
            } catch (SAXException var4) {
               DebugWindow.this.notificatinArea
                     .setText(DebugWindow.this.notificatinArea.getText() + "Fail: \n" + var4.getMessage() + "\n");
            } catch (IOException var5) {
               DebugWindow.this.notificatinArea
                     .setText(DebugWindow.this.notificatinArea.getText() + "Fail: \n" + var5.getMessage() + "\n");
            }

         }
      });
      this.setSize(900, 450);
      this.setLocation(400, 300);
   }

   public static void main(String[] args) {
      JFrame w = new DebugWindow();
      w.setDefaultCloseOperation(3);
      w.setVisible(true);
   }
}
