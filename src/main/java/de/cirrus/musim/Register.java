package de.cirrus.musim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Register implements ArchitectureObjectWithBusConnection {
   Bus Input = null;
   Bus Output = null;
   private int bitLength = 0;
   private String name = "";
   private Long Value = 0L;
   private Dimension lastDimension = new Dimension(0, 0);
   private Orientation orientation;
   private boolean ChipSelected;
   private boolean OutputEnabled;

   public void resetState() {
      this.ChipSelected = false;
      this.OutputEnabled = false;
   }

   public Register() {
      this.orientation = Orientation.north;
      this.ChipSelected = false;
      this.OutputEnabled = false;
   }

   public Dimension getDimension(FontMetrics metrics) {
      String description = this.getName() + ": ";
      String string1 = "1";
      String string0 = "0";
      int bitwidth = metrics.stringWidth(string0) > metrics.stringWidth(string1) ? metrics.stringWidth(string0)
            : metrics.stringWidth(string1);
      bitwidth *= this.bitLength;
      int hgt = metrics.getHeight();
      int adv = metrics.stringWidth(description);
      adv += bitwidth;
      Dimension sizeofbox = new Dimension(adv + 2 + 1, hgt + 4 + 1);
      this.lastDimension = sizeofbox;
      return sizeofbox;
   }

   public Register(int bitLength) {
      this.orientation = Orientation.north;
      this.ChipSelected = false;
      this.OutputEnabled = false;
      this.bitLength = bitLength;
   }

   public Register(int bitLength, String name) {
      this.orientation = Orientation.north;
      this.ChipSelected = false;
      this.OutputEnabled = false;
      this.bitLength = bitLength;
      this.name = name;
   }

   public Register(int bitLength, String name, Bus Input, Bus Output) {
      this.orientation = Orientation.north;
      this.ChipSelected = false;
      this.OutputEnabled = false;
      this.bitLength = bitLength;
      this.name = name;
      this.Input = Input;
      this.Output = Output;
   }

   public int getBitLength() {
      return this.bitLength;
   }

   public void setBitLength(int bitLength) {
      this.bitLength = bitLength;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getValue() {
      String result = BinaryFunctions.normalizeStringlengt(Long.toBinaryString(this.Value), this.bitLength);
      return result;
   }

   private void setValue(String BinaryValue) {
      this.Value = Long.parseLong(BinaryValue, 2);
   }

   public void paint(Graphics g) {
      Rectangle virtualBounds = new Rectangle();
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice[] gs = ge.getScreenDevices();

      for (int j = 0; j < gs.length; ++j) {
         GraphicsDevice gd = gs[j];
         GraphicsConfiguration[] gc = gd.getConfigurations();

         for (int i = 0; i < gc.length; ++i) {
            virtualBounds = virtualBounds.union(gc[i].getBounds());
         }
      }

      Graphics2D g2d = (Graphics2D) g;
      Font f = g2d.getFont();
      String description = this.getName() + ": ";
      FontMetrics metrics = g2d.getFontMetrics(f);
      String string1 = "1";
      String string0 = "0";
      int bitwidth = metrics.stringWidth(string0) > metrics.stringWidth(string1) ? metrics.stringWidth(string0)
            : metrics.stringWidth(string1);
      bitwidth *= this.bitLength;
      int hgt = metrics.getHeight();
      int adv = metrics.stringWidth(description);
      adv += bitwidth;
      Dimension sizeofbox = new Dimension(adv + 2, hgt + 4);
      Color old = g2d.getColor();
      if (this.ChipSelected & this.OutputEnabled) {
         g2d.setColor(Color.YELLOW);
      } else if (this.ChipSelected) {
         g2d.setColor(Color.RED);
      } else if (this.OutputEnabled) {
         g2d.setColor(Color.GREEN);
      } else {
         g2d.setColor(defaultBackgroundColor);
      }

      g2d.fillRect(0, 0, sizeofbox.width, sizeofbox.height);
      g2d.setColor(old);
      g2d.drawRect(0, 0, sizeofbox.width, sizeofbox.height);
      g2d.drawString(description + this.getValue(), 1, hgt);
   }

   public Bus getInput() {
      return this.Input;
   }

   public Bus getOutput() {
      return this.Output;
   }

   public void setInput(Bus Input) {
      this.Input = Input;
   }

   public void setOutput(Bus Output) {
      this.Output = Output;
   }

   public void chipEnable() {
      this.setValue(this.Input.getValue());
      this.ChipSelected = true;
   }

   public void outputEnable() {
      this.Output.setValue(this.getValue());
      this.OutputEnabled = true;
   }

   public Dimension getDimension() {
      return new Dimension();
   }

   public ArchtekturObject.State getState() {
      return this.OutputEnabled | this.ChipSelected ? ArchtekturObject.State.Active : ArchtekturObject.State.Inactive;
   }

   public Collection<Bus> getInvoldedBuses() {
      ArrayList<Bus> t = new ArrayList();
      if (this.Input != null) {
         t.add(this.Input);
      }

      if (this.Output != null) {
         t.add(this.Output);
      }

      return t;
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

   public Orientation getOrientation() {
      return this.orientation;
   }

   public void setOrientation(Orientation orientation) {
      this.orientation = orientation;
   }
}
