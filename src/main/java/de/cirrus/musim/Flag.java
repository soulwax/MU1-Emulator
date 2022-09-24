package de.cirrus.musim;

public class Flag {
   private Boolean flag;

   public Flag(boolean flag) {
      this.flag = flag;
   }

   public boolean isFlag() {
      return this.flag;
   }

   public void setFlag(boolean flag) {
      this.flag = flag;
   }

   public boolean equals(Object obj) {
      return this.flag.equals(obj);
   }

   public String toString() {
      Boolean var10000 = this.flag;
      return Boolean.toString(this.flag);
   }
}
