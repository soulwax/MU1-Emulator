package de.cirrus.musim;

import java.util.ArrayList;
import java.util.Iterator;

public class Instruction {
   String name;
   String OpCode;
   ArrayList<InstructionStep> instructionSteps = new ArrayList();
   ArrayList<InstructionCondition> conditions = new ArrayList();

   public Instruction(String name, String OpCode) {
      this.name = name;
      this.OpCode = OpCode;
   }

   public String getOpCode() {
      return this.OpCode;
   }

   public String getName() {
      return this.name;
   }

   public void addInstuctionStep(InstructionStep is) {
      this.instructionSteps.add(is);
   }

   public InstructionStep getStep(int step) {
      return (InstructionStep) this.instructionSteps.get(step);
   }

   public boolean hasStep(int step) {
      return this.instructionSteps.size() > step;
   }

   public void addInstructionCondition(InstructionCondition ic) {
      this.conditions.add(ic);
   }

   public boolean isConditionSatisfied() {
      boolean r = true;

      InstructionCondition instructionCondition;
      for (Iterator it = this.conditions.iterator(); it.hasNext(); r &= instructionCondition.isSatisfied()) {
         instructionCondition = (InstructionCondition) it.next();
      }

      return r;
   }
}
