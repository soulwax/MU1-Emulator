package de.cirrus.musim;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Multiplexer implements ArchitectureObjectWithBusConnection {
   private ArchtekturObject.State currentState;
   int base = 20;
   Bus Output = null;
   Map<Integer, Bus> InputBuses = new HashMap<Integer, Bus>();
   Bus SelectedInput = null;
   Point2D Position = new Point2D.Double(100.0, 200.0);
   private String name = "";

   public Dimension getDimension(FontMetrics fm) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Multiplexer() {
   }

   public Multiplexer(Bus output) {
      this.Output = output;
   }

   public Multiplexer(Bus output, Map InputBuses) {
      this.Output = output;
      this.InputBuses = InputBuses;
   }

   public Map<Integer, Bus> getInputBuses() {
      return this.InputBuses;
   }

   public Bus getOutput() {
      return this.Output;
   }

   public void setOutput(Bus Output) {
      this.Output = Output;
   }

   public void selectBusNr(int NR) throws IOException {
      if (NR < 0) {
         throw new IOException("Negative Bus Nr.");
      } else if (NR > this.InputBuses.size() - 1) {
         throw new IOException("Cannt Select Bus, Nr. is bigger than available Busses");
      } else {
         this.SelectedInput = (Bus) this.InputBuses.get(NR);
         this.currentState = ArchtekturObject.State.Active;
      }
   }

   public Bus getSelectedInput() {
      return this.SelectedInput;
   }

   public void paint(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      Color oldColor = g2d.getColor();
      Color oldBackColor = g2d.getBackground();
      Stroke oldStroke = g2d.getStroke();
      Polygon p = new Polygon();
      p.addPoint(this.base - 1, 0);
      int x = 0;

      // for (Iterator it = this.InputBuses.values().iterator(); it.hasNext(); x += this.base) {
      //    Bus bus = (Bus) it.next();
      // }

      x = (int) ((double) x * 1.5);
      p.addPoint(x, 0);
      p.addPoint(x + this.base - 1, this.base - 1);
      p.addPoint(0, this.base - 1);
      g2d.setColor(defaultBackgroundColor);
      g2d.fillPolygon(p);
      g2d.setColor(oldColor);
      g2d.drawPolygon(p);

      try {
         if (this.currentState.equals(ArchtekturObject.State.Active)) {
            g2d.setColor(Color.RED);
         }

         g2d.setStroke(new BasicStroke(2.0F));
         Point2D start = this.getConnectionPoint(this.Output);
         Point2D end = this.getConnectionPoint(this.getSelectedInput());
         g2d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY() - 2);
      } catch (Exception var10) {
      }

      g2d.setColor(oldColor);
      g2d.setBackground(oldBackColor);
      g2d.setStroke(oldStroke);
   }

   public void addInputBus(Bus b, int channel) {
      this.InputBuses.put(channel, b);
   }

   public void performeAction() {
      this.Output.setValue(this.SelectedInput.getValue());
   }

   public Point2D getPosition() {
      return this.Position;
   }

   public void setPosition(Point2D Position) {
      this.Position = Position;
   }

   public Dimension getDimension() {
      return new Dimension((int) ((double) (this.base * this.InputBuses.size()) * 1.5 + (double) this.base) + 1,
            this.base + 1);
   }

   public ArchtekturObject.State getState() {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Collection<Bus> getInvoldedBuses() {
      ArrayList<Bus> buses = new ArrayList<Bus>();
      if (this.Output == null) return null;
      for (Bus bus : buses) {
         buses.add(bus);
      }
      return buses;
   }

   public Point2D getConnectionPoint(Bus b) throws IOException {
      Dimension d = this.getDimension();
      if (b == this.Output) {
         return new Point2D.Double(d.getWidth() / 2.0, 0.0);
      } else if (!this.InputBuses.containsValue(b)) {
         throw new IOException("Bus not Connected to Multiplexer.");
      } else {
         int i;
         for (i = 0; !((Bus) this.InputBuses.get(i)).equals(b); ++i) {
         }

         return new Point2D.Double((double) ((i + 1) * (d.width / (this.InputBuses.size() + 1))), (double) this.base);
      }
   }

   public boolean hasChannel(Integer i) {
      return this.InputBuses.containsKey(i);
   }

   public void resetState() {
      this.currentState = ArchtekturObject.State.Inactive;
   }

   public Orientation getOrientation() {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public void setOrientation(Orientation orientation) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
