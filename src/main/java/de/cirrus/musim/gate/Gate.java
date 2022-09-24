package de.cirrus.musim.gate;

import de.cirrus.musim.ArchitectureObjectWithBusConnection;
import de.cirrus.musim.ArchtekturObject;
import de.cirrus.musim.Bus;
import de.cirrus.musim.Orientation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Gate implements ArchitectureObjectWithBusConnection {
   protected Bus Output = null;
   protected String mask = "";
   protected Bus Input = null;
   private Orientation orientation;
   private Dimension lastDimension;
   private ArchtekturObject.State currentState;
   int size;

   public Gate() {
      this.orientation = Orientation.north;
      this.lastDimension = new Dimension();
      this.currentState = ArchtekturObject.State.Inactive;
   }

   public abstract String getDescription();

   public void action() {
      this.currentState = ArchtekturObject.State.Active;
   }

   public Point2D getConnectionPoint(Bus b) throws IOException {
      if (b == this.Input) {
         switch (this.orientation) {
            case north:
               return new Point2D.Double((double) (this.lastDimension.width / 2), (double) this.lastDimension.height);
            case east:
               return new Point2D.Double(0.0, (double) (this.lastDimension.height / 2));
            case south:
               return new Point2D.Double((double) (this.lastDimension.width / 2), 0.0);
            case west:
               return new Point2D.Double((double) this.lastDimension.width, (double) (this.lastDimension.height / 2));
            default:
               throw new AssertionError();
         }
      } else if (b == this.Output) {
         switch (this.orientation) {
            case north:
               return new Point2D.Double((double) (this.lastDimension.width / 2), 0.0);
            case east:
               return new Point2D.Double((double) this.lastDimension.width, (double) (this.lastDimension.height / 2));
            case south:
               return new Point2D.Double((double) (this.lastDimension.width / 2), (double) this.lastDimension.height);
            case west:
               return new Point2D.Double(0.0, (double) (this.lastDimension.height / 2));
            default:
               throw new AssertionError();
         }
      } else {
         throw new IOException("Bus not Connected to Register");
      }
   }

   public Dimension getDimension(FontMetrics metrics) {
      String string1 = "1";
      String string0 = "0";
      int bitwidth = metrics.stringWidth(string0) > metrics.stringWidth(string1) ? metrics.stringWidth(string0)
            : metrics.stringWidth(string1);
      bitwidth *= this.size;
      int hgt = metrics.getHeight();
      int adv = metrics.stringWidth(this.getDescription() + " ");
      adv += bitwidth;
      Dimension sizeofbox = new Dimension(adv + 2 + 1, hgt + 4 + 1);
      this.lastDimension = sizeofbox;
      return sizeofbox;
   }

   public Dimension getDimension() {
      return new Dimension();
   }

   public Collection<Bus> getInvoldedBuses() {
      Collection<Bus> bs = new ArrayList();
      bs.add(this.Input);
      bs.add(this.Output);
      return bs;
   }

   public ArchtekturObject.State getState() {
      return this.currentState;
   }

   public void paint(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      Font f = g2d.getFont();
      FontMetrics metrics = g2d.getFontMetrics(f);
      String string1 = "1";
      String string0 = "0";
      int bitwidth = metrics.stringWidth(string0) > metrics.stringWidth(string1) ? metrics.stringWidth(string0)
            : metrics.stringWidth(string1);
      bitwidth *= this.size;
      int hgt = metrics.getHeight();
      int adv = metrics.stringWidth(this.getDescription() + " ");
      adv += bitwidth;
      Dimension sizeofbox = new Dimension(adv + 2, hgt + 4);
      Color old = g2d.getColor();
      g2d.setColor(defaultBackgroundColor);
      g2d.fillRect(0, 0, sizeofbox.width, sizeofbox.height);
      g2d.setColor(old);
      g2d.drawRect(0, 0, sizeofbox.width, sizeofbox.height);
      g2d.drawString(this.getDescription() + " " + this.mask, 1, hgt);
   }

   public void resetState() {
      this.currentState = ArchtekturObject.State.Inactive;
   }

   public void setInput(Bus Input) {
      this.Input = Input;
   }

   public void setMask(String mask) {
      this.mask = mask;
   }

   public void setOutput(Bus Output) {
      this.Output = Output;
   }

   public Orientation getOrientation() {
      return this.orientation;
   }

   public void setOrientation(Orientation orientation) {
      this.orientation = orientation;
   }
}
