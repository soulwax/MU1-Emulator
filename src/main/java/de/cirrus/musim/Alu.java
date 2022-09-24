package de.cirrus.musim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Alu implements ArchitectureObjectWithBusConnection {
   int bit = 0;
   Bus BusA = null;
   Bus BusB = null;
   Bus Out = null;
   int base = 30;
   ALUFunction performedAluFunction = null;
   private String name = "";

   public Dimension getDimension(FontMetrics fm) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public void resetState() {
      this.performedAluFunction = null;
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

   private void selfcheck() throws IOException {
      if (this.BusA == null || this.BusB == null || this.Out == null) {
         throw new IOException("One of the Buses is null");
      }
   }

   public Alu() {
   }

   public Alu(int bit) {
      this.bit = bit;
   }

   public void performFunction(ALUFunction aluf) throws IOException {
      this.selfcheck();
      switch (aluf) {
         case AminusOne:
            this.Out.setValue(BinaryFunctions.subTwoK(this.BusA.getValue(), "1", this.bit, (Flag) null));
            break;
         case AminusB:
            this.Out
                  .setValue(BinaryFunctions.subTwoK(this.BusA.getValue(), this.BusB.getValue(), this.bit, (Flag) null));
            break;
         case AplusOne:
            this.Out.setValue(BinaryFunctions.increment(this.BusA.getValue(), this.base, (Flag) null));
            break;
         case AplusB:
            this.Out.setValue(BinaryFunctions.add(this.BusA.getValue(), this.BusB.getValue(), this.base, (Flag) null));
            break;
         case A:
            this.Out.setValue(this.BusA.getValue());
            break;
         case B:
            this.Out.setValue(this.BusB.getValue());
      }

      this.performedAluFunction = aluf;
   }

   public void paint(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      Color oldColor = g2d.getColor();
      g2d.setColor(defaultBackgroundColor);
      g2d.fillPolygon(this.drawPolygon());
      g2d.setColor(oldColor);
      FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
      if (this.performedAluFunction != null) {
         Rectangle2D bounds;
         switch (this.performedAluFunction) {
            case AminusOne:
               bounds = metrics.getStringBounds("A-1", g);
               g2d.drawString("A-1", (int) ((double) this.base * 0.75 - bounds.getWidth() / 2.0), this.base - 2);
               break;
            case AminusB:
               bounds = metrics.getStringBounds("A -", g);
               g2d.drawString("A -", (int) ((double) this.base * 0.75 - bounds.getWidth() / 2.0), this.base - 2);
               bounds = metrics.getStringBounds("B", g);
               g2d.drawString("B", (int) ((double) this.base * 3.25 - bounds.getWidth() / 2.0), this.base - 2);
               break;
            case AplusOne:
               bounds = metrics.getStringBounds("A+1", g);
               g2d.drawString("A+1", (int) ((double) this.base * 0.75 - bounds.getWidth() / 2.0), this.base - 2);
               break;
            case AplusB:
               bounds = metrics.getStringBounds("A +", g);
               g2d.drawString("A +", (int) ((double) this.base * 0.75 - bounds.getWidth() / 2.0), this.base - 2);
               bounds = metrics.getStringBounds("B", g);
               g2d.drawString("B", (int) ((double) this.base * 3.25 - bounds.getWidth() / 2.0), this.base - 2);
               break;
            case A:
               bounds = metrics.getStringBounds("A", g);
               g2d.drawString("A", (int) ((double) this.base * 0.75 - bounds.getWidth() / 2.0), this.base - 2);
               break;
            case B:
               bounds = metrics.getStringBounds("B", g);
               g2d.drawString("B", (int) ((double) this.base * 3.25 - bounds.getWidth() / 2.0), this.base - 2);
         }
      }

      g2d.drawPolygon(this.drawPolygon());
      g2d.setColor(oldColor);
   }

   private Polygon drawPolygon() {
      Polygon p = new Polygon();
      p.addPoint(this.base - 1, 0);
      p.addPoint(this.base * 3, 0);
      p.addPoint(this.base * 4 - 1, this.base - 1);
      p.addPoint(this.base * 3 - 1, this.base - 1);
      p.addPoint((int) ((double) this.base * 2.5), (int) ((double) this.base * 0.5));
      p.addPoint((int) ((double) this.base * 1.5) - 1, (int) ((double) this.base * 0.5));
      p.addPoint(this.base, this.base - 1);
      p.addPoint(0, this.base - 1);
      return p;
   }

   public Bus getBusA() {
      return this.BusA;
   }

   public void setBusA(Bus BusA) {
      this.BusA = BusA;
   }

   public Bus getBusB() {
      return this.BusB;
   }

   public void setBusB(Bus BusB) {
      this.BusB = BusB;
   }

   public Bus getOut() {
      return this.Out;
   }

   public void setOut(Bus Out) {
      this.Out = Out;
   }

   public Dimension getDimension() {
      Rectangle2D re = this.drawPolygon().getBounds2D();
      Dimension d = new Dimension((int) re.getWidth() + 1, (int) re.getHeight() + 1);
      return d;
   }

   public ArchtekturObject.State getState() {
      return this.performedAluFunction != null ? ArchtekturObject.State.Active : ArchtekturObject.State.Inactive;
   }

   public Collection<Bus> getInvoldedBuses() {
      ArrayList<Bus> t = new ArrayList();
      if (this.Out != null) {
         t.add(this.Out);
      }

      if (this.BusA != null) {
         t.add(this.BusA);
      }

      if (this.BusB != null) {
         t.add(this.BusB);
      }

      return t;
   }

   public Point2D getConnectionPoint(Bus b) throws IOException {
      if (b == this.Out) {
         return new Point2D.Double((double) (this.getDimension().width / 2), 0.0);
      } else if (b == this.BusA) {
         return new Point2D.Double((double) this.base * 0.5, (double) this.base);
      } else if (b == this.BusB) {
         return new Point2D.Double((double) (this.base * 3) + (double) this.base * 0.5, (double) this.base);
      } else {
         throw new IOException("Bus not Connected to ALU.");
      }
   }

   public static ALUFunction stringToALUfunction(String ALUFunctionstring) throws IOException {
      ALUFunction[] arr$ = Alu.ALUFunction.values();
      int len$ = arr$.length;

      for (int it = 0; it < len$; ++it) {
         ALUFunction aLUFunction = arr$[it];
         if (aLUFunction.toString().equals(ALUFunctionstring)) {
            return aLUFunction;
         }
      }

      throw new IOException(ALUFunctionstring + " is not a known ALU function");
   }

   public static enum ALUFunction {
      AplusB,
      AplusOne,
      AminusOne,
      AminusB,
      A,
      B;

      private ALUFunction() {
      }
   }
}
