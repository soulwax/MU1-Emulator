package de.cirrus.musim;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

public class CompontentScrollAndNonScrollableArchitectureObjectPartAdapter {
   ScrollAndNonScrollableArchitectureObjectPart andNonScrollableArchitectureObjectPart;

   public CompontentScrollAndNonScrollableArchitectureObjectPartAdapter(
         ScrollAndNonScrollableArchitectureObjectPart andNonScrollableArchitectureObjectPart) {
      this.andNonScrollableArchitectureObjectPart = andNonScrollableArchitectureObjectPart;
   }

   public Container getScrollableArchitectureObjectPartAdapter() {
      return new ScrollableArchitectureObjectPart(this.andNonScrollableArchitectureObjectPart);
   }

   public Container getNonScrollableArchitectureObjectPartAdapter() {
      return new NonScrollableArchitectureObjectPart(this.andNonScrollableArchitectureObjectPart);
   }

   private class ScrollableArchitectureObjectPart extends Container {
      boolean setupedsize = false;
      ScrollAndNonScrollableArchitectureObjectPart sansaop;

      public ScrollableArchitectureObjectPart(ScrollAndNonScrollableArchitectureObjectPart sansaop) {
         this.sansaop = sansaop;
      }

      public Dimension getPreferredSize() {
         this.setupsize();
         return super.getPreferredSize();
      }

      public Dimension getMinimumSize() {
         this.setupsize();
         return super.getMinimumSize();
      }

      public void paint(Graphics g) {
         super.paint(g);
         this.sansaop.paintScrollableArchitectureObjectPart(g);
      }

      private void setupsize() {
         if (!this.setupedsize) {
            Dimension d = this.sansaop.getScrollableArchitectureObjectPartDimension();
            d = d.equals(new Dimension())
                  ? this.sansaop.getScrollableArchitectureObjectPartDimension(this.getFontMetrics(this.getFont()))
                  : d;
            this.setPreferredSize(d);
            this.setMinimumSize(d);
         }

      }
   }

   private class NonScrollableArchitectureObjectPart extends Container {
      boolean setupedsize = false;
      ScrollAndNonScrollableArchitectureObjectPart sansaop;

      public NonScrollableArchitectureObjectPart(ScrollAndNonScrollableArchitectureObjectPart sansaop) {
         this.sansaop = sansaop;
      }

      public Dimension getPreferredSize() {
         this.setupsize();
         return super.getPreferredSize();
      }

      public Dimension getMinimumSize() {
         this.setupsize();
         return super.getMinimumSize();
      }

      public void paint(Graphics g) {
         super.paint(g);
         this.sansaop.paintNonScrollableArchitectureObjectPart(g);
      }

      private void setupsize() {
         if (!this.setupedsize) {
            Dimension d = this.sansaop.getNonScrollableArchitectureObjectPartDimension();
            d = d.equals(new Dimension())
                  ? this.sansaop.getNonScrollableArchitectureObjectPartDimension(this.getFontMetrics(this.getFont()))
                  : d;
            this.setPreferredSize(d);
            this.setMinimumSize(d);
         }

      }
   }
}
