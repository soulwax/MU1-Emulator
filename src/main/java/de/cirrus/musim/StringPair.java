package de.cirrus.musim;

public class StringPair {
   String s1;
   String s2;

   public StringPair(String s1, String s2) {
      this.s1 = s1;
      this.s2 = s2;
   }

   public String getS1() {
      return this.s1;
   }

   public String getS2() {
      return this.s2;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof StringPair)) {
         return false;
      } else {
         StringPair sp = (StringPair) obj;
         return this.s1.equals(sp.getS1()) && this.s2.equals(sp.getS2());
      }
   }
}
