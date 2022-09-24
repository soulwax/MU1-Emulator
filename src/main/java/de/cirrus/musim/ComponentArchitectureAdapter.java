package de.cirrus.musim;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ComponentArchitectureAdapter extends Container {
   boolean setupedsize = false;
   ArchtekturObject ao;

   public ComponentArchitectureAdapter(ArchtekturObject ao) {
      this.ao = ao;
      this.addMouseListener(new MouseListener() {
         public void mouseClicked(MouseEvent e) {
         }

         public void mousePressed(MouseEvent e) {
         }

         public void mouseReleased(MouseEvent e) {
         }

         public void mouseEntered(MouseEvent e) {
         }

         public void mouseExited(MouseEvent e) {
         }
      });
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
      this.ao.paint(g);
   }

   private void setupsize() {
      if (!this.setupedsize) {
         Dimension d = this.ao.getDimension();
         d = d.equals(new Dimension()) ? this.ao.getDimension(this.getFontMetrics(this.getFont())) : d;
         this.setPreferredSize(d);
         this.setMinimumSize(d);
      }

   }
}
