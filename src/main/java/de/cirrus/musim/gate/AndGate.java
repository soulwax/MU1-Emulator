package de.cirrus.musim.gate;

import de.cirrus.musim.BinaryFunctions;

public class AndGate extends Gate {
   public AndGate(int size) {
      this.size = size;
   }

   public void action() {
      super.action();
      this.Output.setValue(BinaryFunctions.and(this.Input.getValue(), BinaryFunctions.hexToBinary(this.mask)));
   }

   public String getDescription() {
      return "AND";
   }

   public String getName() {
      throw new UnsupportedOperationException("Not supported yet.");
   }
}
