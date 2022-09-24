package de.cirrus.musim;

import de.cirrus.musim.gui.DebugWindow;
import de.cirrus.musim.xmlparser.architekturparser;
import de.cirrus.musim.xmlparser.layoutparser;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Simulator extends Container {
   TimingAndControl TAC = new TimingAndControl();
   Map<Bus, BusPath> BusWithBuspath = new HashMap<Bus, BusPath>();
   Map<Bus, Path2D> BusWithPathtoDraw = new HashMap<Bus, Path2D>();
   Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap = new HashMap<ArchitectureObjectWithBusConnection, Point2D>();
   Map<Bus, HashSet<ArchitectureObjectWithBusConnection>> BusConnections = new HashMap<Bus, HashSet<ArchitectureObjectWithBusConnection>>();
   Map<String, ArchtekturObject> ArchtectureLookupTble = new HashMap<String, ArchtekturObject>();
   String architekturfile = null;
   String layoutfile = null;
   MouseListener ml = null;
   Rectangle preferedSize = new Rectangle();

   public Simulator() {
   }

   public Simulator(String architekturfile, String layoutfile) {
      this.architekturfile = architekturfile;
      this.layoutfile = layoutfile;
   }

   public void setup(String architekturfile, String layoutfile)
         throws ParserConfigurationException, SAXException, IOException {
      this.architekturfile = architekturfile;
      this.layoutfile = layoutfile;
      this.setup();
   }

   public void setup() throws ParserConfigurationException, SAXException, IOException {
      try {
         // File f = new File("res/font/Terminus.ttf");
         // Get Terminus.tff from maven resource folder
         InputStream is = Simulator.class.getResourceAsStream("/font/Terminus.ttf");
         // InputStream is = new FileInputStream(f);
         Font font = Font.createFont(0, is);
         font = font.deriveFont(16.0F);
         this.setFont(font);
      } catch (Exception var8) {
         Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, (String) null, var8);
      }

      this.setLayout((LayoutManager) null);
      this.removeAll();
      if (this.layoutfile != null && this.architekturfile != null) {
         this.BusWithBuspath = new HashMap();
         this.TAC = new TimingAndControl();
         this.ArchtMap = new HashMap();
         this.BusConnections = new HashMap();
         this.ArchtectureLookupTble = new HashMap();
         architekturparser.parseDocument(this.architekturfile, this.TAC, this.ArchtectureLookupTble);
         layoutparser.parseDocument(this.BusWithBuspath, this.layoutfile, this.ArchtMap, this.ArchtectureLookupTble);
         this.preferedSize = new Rectangle();
         Iterator it = this.ArchtMap.keySet().iterator();

         ArchitectureObjectWithBusConnection architectureObjectWithBusConnection;
         while (it.hasNext()) {
            architectureObjectWithBusConnection = (ArchitectureObjectWithBusConnection) it.next();
            Iterator it2 = architectureObjectWithBusConnection.getInvoldedBuses().iterator();

            while (it2.hasNext()) {
               Bus bus = (Bus) it2.next();
               if (this.BusConnections.containsKey(bus)) {
                  ((HashSet) this.BusConnections.get(bus)).add(architectureObjectWithBusConnection);
               } else {
                  HashSet<ArchitectureObjectWithBusConnection> hs = new HashSet();
                  hs.add(architectureObjectWithBusConnection);
                  this.BusConnections.put(bus, hs);
               }
            }
         }

         Rectangle ComponentBounds;
         for (it = this.ArchtMap.keySet().iterator(); it
               .hasNext(); this.preferedSize = this.preferedSize.union(ComponentBounds)) {
            architectureObjectWithBusConnection = (ArchitectureObjectWithBusConnection) it.next();
            Object c;
            if (architectureObjectWithBusConnection instanceof RAM) {
               c = new ScrollableComponentArchitecureAdapter((RAM) architectureObjectWithBusConnection);
               ((Component) c).setPreferredSize(new Dimension(((Component) c).getPreferredSize().width, 130));
               System.out.println("Simp" + ((Component) c).getPreferredSize());
            } else {
               c = new ComponentArchitectureAdapter(architectureObjectWithBusConnection);
            }

            this.add((Component) c);
            Insets insets = this.getInsets();
            Point2D point = (Point2D) this.ArchtMap.get(architectureObjectWithBusConnection);
            Dimension size = new Dimension(((Component) c).getPreferredSize());
            ComponentBounds = new Rectangle((int) point.getX() + insets.left, (int) point.getY() + insets.top,
                  size.width, size.height);
            ((Component) c).setBounds(ComponentBounds);
         }

         Rectangle bounds;
         for (it = this.BusWithBuspath.keySet().iterator(); it
               .hasNext(); this.preferedSize = this.preferedSize.union(bounds)) {
            Bus bus = (Bus) it.next();
            Path2D path2D = ((BusPath) this.BusWithBuspath.get(bus)).getPath();
            this.BusWithPathtoDraw.put(bus, path2D);
            bounds = new Rectangle(path2D.getBounds());
            bounds.grow(bus.getStrokeDimension(), bus.getStrokeDimension());
         }

         this.validate();
         this.setPreferredSize(this.preferedSize.getSize());
         this.getParent().repaint();
      } else {
         throw new IOException("Architecture or Layout File not set.");
      }
   }

   public void paint(Graphics g) {
      super.paint(g);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setFont(new Font("Dialog", 0, 12));
      g2d.setPaint(Color.black);
      g2d.setStroke(new BasicStroke(1.0F));
      Iterator it = this.BusConnections.keySet().iterator();

      while (true) {
         while (it.hasNext()) {
            Bus bus = (Bus) it.next();
            if (this.BusWithPathtoDraw.containsKey(bus)) {
               bus.setBusPath2D((Path2D) this.BusWithPathtoDraw.get(bus));
               bus.paint(g);
            } else {
               Path2D.Double path = new Path2D.Double();
               boolean firstpoint = true;
               Iterator it2 = ((HashSet) this.BusConnections.get(bus)).iterator();

               while (it2.hasNext()) {
                  ArchitectureObjectWithBusConnection architectureObjectWithBusConnection = (ArchitectureObjectWithBusConnection) it2
                        .next();

                  try {
                     Point2D absulut = (Point2D) this.ArchtMap.get(architectureObjectWithBusConnection);
                     Point2D point = architectureObjectWithBusConnection.getConnectionPoint(bus);
                     point.setLocation(absulut.getX() + point.getX(), absulut.getY() + point.getY());
                     if (firstpoint) {
                        path.moveTo(point.getX(), point.getY());
                        firstpoint = false;
                     } else {
                        path.lineTo(point.getX(), point.getY());
                     }
                  } catch (IOException var11) {
                     Logger.getLogger(DebugWindow.class.getName()).log(Level.SEVERE, (String) null, var11);
                  }
               }

               bus.setBusPath2D(path);
               bus.paint(g);
            }
         }

         return;
      }
   }

   public void MicroStep() throws IOException {
      this.resetAllStates();
      this.TAC.performStep();
      this.repaint();
   }

   public void resetAllStates() {
      this.resetArchitectureObjectStates();
      this.resetBusStates();
   }

   public void resetArchitectureObjectStates() {
      Iterator it = this.ArchtMap.keySet().iterator();

      while (it.hasNext()) {
         ArchitectureObjectWithBusConnection architectureObjectWithBusConnection = (ArchitectureObjectWithBusConnection) it
               .next();
         architectureObjectWithBusConnection.resetState();
      }

   }

   public void resetBusStates() {
      Iterator it = this.BusWithBuspath.keySet().iterator();

      while (it.hasNext()) {
         Bus bus = (Bus) it.next();
         bus.resetState();
      }

   }

   public void InstructionStep() throws IOException {
      this.TAC.performInstruction();
      this.resetAllStates();
      this.repaint();
   }

   public TimingAndControl getTimeingandControll() {
      return this.TAC;
   }

   public Collection<CommandPerformer.Entry> getCommands() {
      Collection<CommandPerformer.Entry> CommandCollection = new ArrayList();
      Iterator it = this.ArchtMap.keySet().iterator();

      while (it.hasNext()) {
         ArchitectureObjectWithBusConnection architectureObjectWithBusConnection = (ArchitectureObjectWithBusConnection) it
               .next();
         if (architectureObjectWithBusConnection instanceof CommandPerformer) {
            final CommandPerformer obj = (CommandPerformer) architectureObjectWithBusConnection;
            CommandCollection.add(new CommandPerformer.Entry() {
               public ArchitectureObjectWithBusConnection getPerformer() {
                  return obj.getPerformer();
               }

               public Collection<Command> getCommands() {
                  return obj.getCommands();
               }
            });
         }
      }

      return CommandCollection;
   }
}
