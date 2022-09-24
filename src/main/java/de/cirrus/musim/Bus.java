package de.cirrus.musim;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Bus implements ArchtekturObject {
   private final int StrokeDimension = 2;
   Collection<StateUpdateListener> stateChangedListenes = new ArrayList();
   String Value = "";
   ArchtekturObject.State currentState;
   Path2D BusPath2D;

   public int getStrokeDimension() {
      return 2;
   }

   public Dimension getDimension(FontMetrics fm) {
      return this.getDimension();
   }

   public void resetState() {
      this.currentState = ArchtekturObject.State.Inactive;
      this.fireStateUpdate();
   }

   public Bus() {
      this.currentState = ArchtekturObject.State.Inactive;
      this.BusPath2D = null;
   }

   public void paint(Graphics g) {
      if (this.BusPath2D != null) {
         Graphics2D g2d = (Graphics2D) g;
         Stroke oldStroke = g2d.getStroke();
         Color oldColor = g2d.getColor();
         if (this.currentState.equals(ArchtekturObject.State.Active)) {
            g2d.setColor(Color.RED);
         }

         g2d.setStroke(new BasicStroke(2.0F));
         g2d.draw(this.BusPath2D);
         g2d.setStroke(oldStroke);
         g2d.setColor(oldColor);
      }
   }

   public void setValue(String Value) {
      this.Value = Value;
      this.currentState = ArchtekturObject.State.Active;
      this.fireStateUpdate();
   }

   public String getValue() {
      return this.Value;
   }

   public Dimension getDimension() {
      if (this.BusPath2D == null) {
         return new Dimension();
      } else {
         Rectangle r = this.BusPath2D.getBounds();
         return new Dimension(r.width + 2, r.height + 2);
      }
   }

   public ArchtekturObject.State getState() {
      return this.currentState;
   }

   public Path2D getBusPath2D() {
      return this.BusPath2D;
   }

   public void setBusPath2D(Path2D BusPath2D) {
      this.BusPath2D = BusPath2D;
   }

   private void fireStateUpdate() {
      Iterator it = this.stateChangedListenes.iterator();

      while (it.hasNext()) {
         StateUpdateListener stateActiveListener = (StateUpdateListener) it.next();
         stateActiveListener.OnStateUpdate(this, this.currentState);
      }

   }
}
