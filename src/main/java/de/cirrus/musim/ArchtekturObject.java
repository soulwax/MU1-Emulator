package de.cirrus.musim;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

public interface ArchtekturObject {
   void paint(Graphics var1);

   Dimension getDimension();

   Dimension getDimension(FontMetrics var1);

   void resetState();

   State getState();

   public static enum State {
      Active,
      Inactive;

      private State() {
      }
   }
}
