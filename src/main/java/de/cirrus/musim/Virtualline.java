package de.cirrus.musim;

public class Virtualline {
   private Oriantation oriantation;
   private int position;

   public Virtualline() {
      this.oriantation = Virtualline.Oriantation.horizontal;
      this.position = 0;
   }

   public Virtualline(int position, Oriantation oriantation) {
      this.oriantation = Virtualline.Oriantation.horizontal;
      this.position = 0;
      this.position = position;
      this.oriantation = oriantation;
   }

   public Oriantation getOriantation() {
      return this.oriantation;
   }

   public void setOriantation(Oriantation oriantation) {
      this.oriantation = oriantation;
   }

   public int getPosition() {
      return this.position;
   }

   public void setPosition(int position) {
      this.position = position;
   }

   public static enum Oriantation {
      horizontal,
      vertical;

      private Oriantation() {
      }
   }
}
