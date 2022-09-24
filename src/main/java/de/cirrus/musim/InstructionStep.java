package de.cirrus.musim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InstructionStep {
   Map<Multiplexer, Integer> MultiPlexerChanel = new HashMap();
   ArrayList<Register> OutputEnable = new ArrayList();
   ArrayList<Register> ChipSelect = new ArrayList();
   Map<Alu, Alu.ALUFunction> AluFunctions = new HashMap();
   Map<RAM, Boolean> RAMChipSelect = new HashMap();
   Map<RAM, Boolean> RAMRnW = new HashMap();

   public InstructionStep() {
   }

   public void addRamChipselet(RAM r, boolean cs) {
      this.RAMChipSelect.put(r, cs);
   }

   public void addRamRnW(RAM r, boolean rnw) {
      this.RAMRnW.put(r, rnw);
   }

   public Map<RAM, Boolean> getRAMChipSelect() {
      return this.RAMChipSelect;
   }

   public Map<RAM, Boolean> getRAMRnW() {
      return this.RAMRnW;
   }

   public void addMultiplexerChanelSelect(Multiplexer mu, int ChanelSelect) {
      this.MultiPlexerChanel.put(mu, ChanelSelect);
   }

   public void addRegisterOutputEnanle(Register re) {
      this.OutputEnable.add(re);
   }

   public void addRegisterChipSelect(Register re) {
      this.ChipSelect.add(re);
   }

   public void addAluFunction(Alu alu, Alu.ALUFunction func) {
      this.AluFunctions.put(alu, func);
   }

   public Map<Alu, Alu.ALUFunction> getAluFunctions() {
      return this.AluFunctions;
   }

   public void setAluFunctions(Map<Alu, Alu.ALUFunction> AluFunctions) {
      this.AluFunctions = AluFunctions;
   }

   public ArrayList<Register> getChipSelect() {
      return this.ChipSelect;
   }

   private void setChipSelect(ArrayList<Register> ChipSelect) {
      this.ChipSelect = ChipSelect;
   }

   public Map<Multiplexer, Integer> getMultiPlexerChanel() {
      return this.MultiPlexerChanel;
   }

   private void setMultiPlexerChanel(Map<Multiplexer, Integer> MultiPlexerChanel) {
      this.MultiPlexerChanel = MultiPlexerChanel;
   }

   public ArrayList<Register> getOutputEnable() {
      return this.OutputEnable;
   }

   public void setOutputEnable(ArrayList<Register> OutputEnable) {
      this.OutputEnable = OutputEnable;
   }
}
