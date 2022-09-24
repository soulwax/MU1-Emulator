package de.cirrus.musim;

import de.cirrus.musim.gate.AndGate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TimingAndControl {
   private Collection<ArchtekturObject> SceneObjects = new ArrayList<ArchtekturObject>();
   private boolean initialized = false;
   private int step = 0;
   private boolean dofetch = true;
   private Instruction currentInstruction = null;
   private Instruction fetch = null;
   private int OpCodePosition;
   private int OpCodelength = 0;
   private Register InstructionRegister;
   private StringBuilder PerformedInstructionsNameBuffer = new StringBuilder(2048);
   private SortedMap<String, Instruction> instructions = new TreeMap<String, Instruction>(new Comparator<String>() {
      public int compare(String o1, String o2) {
         if (!BinaryFunctions.isBinaryString(o1)) {
            return -1;
         } else if (!BinaryFunctions.isBinaryString(o2)) {
            return 1;
         } else {
            BinaryFunctions.normalizeStringlengt(new StringPair(o1, o2));
            if (o1.equals(o2)) {
               return 0;
            } else {
               return Integer.valueOf(o1, 2) < Integer.valueOf(o2, 2) ? -1 : 1;
            }
         }
      }
   });

   public TimingAndControl() {
   }

   public void performStep() throws IOException {
      if (!this.initialized) {
         this.initialize();
      }

      if (this.dofetch) {
         this.performFetch();
      } else if (this.step == 0 & !this.currentInstruction.isConditionSatisfied()) {
         this.performFetch();
      } else {
         InstructionStep instructionStep = this.currentInstruction.getStep(this.step);
         this.performInstructionStep(instructionStep);
         ++this.step;
         if (!this.currentInstruction.hasStep(this.step)) {
            this.dofetch = true;
            this.step = 0;
         }
      }

   }

   public void performInstruction() throws IOException {
      this.performStep();

      while (this.currentInstruction.hasStep(this.step + 1)) {
         this.performStep();
      }

   }

   public void add(ArchtekturObject e) {
      this.SceneObjects.add(e);
      this.initialized = false;
   }

   public void initialize() throws IOException {
      if (this.InstructionRegister == null) {
         throw new IOException("Instruction Register for Timeing And Controll ist nor set.!");
      } else if (this.OpCodePosition != 0 && this.OpCodelength != 0) {
         if (this.fetch == null) {
            throw new IOException("No Fetch Instruction available");
         } else {
            this.initialized = true;
            this.step = 0;
         }
      } else {
         throw new IOException("Not initalized opcode position and opcode length.");
      }
   }

   public Collection<ArchtekturObject> getSceneObjects() {
      return this.SceneObjects;
   }

   public void addInstruction(Instruction i) {
      this.instructions.put(i.OpCode, i);
   }

   public void setInstructionRegister(Register r) {
      if (r != null) {
         this.InstructionRegister = r;
      }
   }

   public Register getInstructionRegister() {
      return this.InstructionRegister;
   }

   private void performInstructionStep(InstructionStep instructionStep) throws IOException {
      Iterator it = instructionStep.getOutputEnable().iterator();

      while (true) {
         Register register;
         do {
            if (!it.hasNext()) {
               it = instructionStep.getMultiPlexerChanel().keySet().iterator();

               while (it.hasNext()) {
                  Multiplexer multiplexer = (Multiplexer) it.next();
                  multiplexer.selectBusNr((Integer) instructionStep.getMultiPlexerChanel().get(multiplexer));
                  multiplexer.performeAction();
               }

               it = instructionStep.getAluFunctions().keySet().iterator();

               while (it.hasNext()) {
                  Alu alu = (Alu) it.next();
                  alu.performFunction((Alu.ALUFunction) instructionStep.getAluFunctions().get(alu));
               }

               it = instructionStep.getRAMRnW().keySet().iterator();

               RAM ram;
               while (it.hasNext()) {
                  ram = (RAM) it.next();
                  ram.setReadNotWrite((Boolean) instructionStep.getRAMRnW().get(ram));
               }

               it = instructionStep.getRAMChipSelect().keySet().iterator();

               while (it.hasNext()) {
                  ram = (RAM) it.next();
                  ram.setChipSelect((Boolean) instructionStep.getRAMChipSelect().get(ram));
               }

               it = instructionStep.getChipSelect().iterator();

               while (it.hasNext()) {
                  register = (Register) it.next();
                  register.chipEnable();
               }

               return;
            }

            register = (Register) it.next();
            register.outputEnable();
         } while (!register.getName().equals("IR"));

         Iterator it2 = this.SceneObjects.iterator();

         while (it2.hasNext()) {
            ArchtekturObject archtekturObject = (ArchtekturObject) it2.next();
            if (archtekturObject instanceof AndGate) {
               ((AndGate) archtekturObject).action();
            }
         }
      }
   }

   private Instruction findInstructionFromRegister() throws IOException {
      String RegisterValue;
      for (RegisterValue = this.InstructionRegister.getValue(); this.OpCodePosition + 1 > RegisterValue
            .length(); RegisterValue = "0" + RegisterValue) {
      }

      String stringInstruction = RegisterValue.substring(RegisterValue.length() - (this.OpCodePosition + 1),
            RegisterValue.length() - (this.OpCodePosition + 1) + this.OpCodelength);
      Instruction I = (Instruction) this.instructions.get(stringInstruction);
      if (I == null) {
         throw new IOException("Unkonw Instruction! " + stringInstruction);
      } else {
         return I;
      }
   }

   public int getOpCodePosition() {
      return this.OpCodePosition;
   }

   public void setOpCodePosition(int OpCodePosition) {
      this.OpCodePosition = OpCodePosition;
   }

   public int getOpCodelength() {
      return this.OpCodelength;
   }

   public void setOpCodelength(int OpCodelength) {
      this.OpCodelength = OpCodelength;
   }

   private void performFetch() throws IOException {
      InstructionStep instructionStep = this.fetch.getStep(this.step);
      this.performInstructionStep(instructionStep);
      ++this.step;
      if (!this.fetch.hasStep(this.step)) {
         this.dofetch = false;
         if (this.currentInstruction != null) {
            this.logPerforedInstructionName(this.currentInstruction.getName());
         }

         this.currentInstruction = this.findInstructionFromRegister();
         this.step = 0;
      }

   }

   public Instruction getFetch() {
      return this.fetch;
   }

   public void setFetch(Instruction fetch) {
      this.fetch = fetch;
   }

   public AbstractTableModel generateInstuctionTable() {
      Map<ArchitectureObjectWithBusConnection, Integer> architectureObjectWithBusConnectionColumnIndex = new HashMap<ArchitectureObjectWithBusConnection, Integer>();
      SortedMap<Integer, String> columnNamebyIndex = new TreeMap<Integer, String>();
      int lastFreeColumnIndex = 0;
      columnNamebyIndex.put(lastFreeColumnIndex, "Instruction");
      ++lastFreeColumnIndex;
      columnNamebyIndex.put(lastFreeColumnIndex, "Op. code");
      ++lastFreeColumnIndex;
      Iterator<ArchtekturObject> it = this.SceneObjects.iterator();

      while (it.hasNext()) {
         ArchtekturObject archtekturObject = (ArchtekturObject) it.next();
         if (archtekturObject instanceof ArchitectureObjectWithBusConnection) {
            ArchitectureObjectWithBusConnection curentArchitectureObjectWithBusConnection = (ArchitectureObjectWithBusConnection) archtekturObject;
            if (archtekturObject instanceof Register) {
               Register reg = (Register) curentArchitectureObjectWithBusConnection;
               architectureObjectWithBusConnectionColumnIndex.put(reg, lastFreeColumnIndex);
               columnNamebyIndex.put(lastFreeColumnIndex, reg.getName() + "_oe");
               ++lastFreeColumnIndex;
               columnNamebyIndex.put(lastFreeColumnIndex, reg.getName() + "_ie");
               ++lastFreeColumnIndex;
            } else if (archtekturObject instanceof RAM) {
               RAM ram = (RAM) curentArchitectureObjectWithBusConnection;
               architectureObjectWithBusConnectionColumnIndex.put(ram, lastFreeColumnIndex);
               columnNamebyIndex.put(lastFreeColumnIndex, ram.getName() + "_ie");
               ++lastFreeColumnIndex;
               columnNamebyIndex.put(lastFreeColumnIndex, ram.getName() + "_RnW");
               ++lastFreeColumnIndex;
            } else if (archtekturObject instanceof Multiplexer) {
               Multiplexer mux = (Multiplexer) curentArchitectureObjectWithBusConnection;
               architectureObjectWithBusConnectionColumnIndex.put(mux, lastFreeColumnIndex);
               columnNamebyIndex.put(lastFreeColumnIndex, mux.getName());
               ++lastFreeColumnIndex;
            } else if (archtekturObject instanceof Alu) {
               Alu alu = (Alu) curentArchitectureObjectWithBusConnection;
               architectureObjectWithBusConnectionColumnIndex.put(alu, lastFreeColumnIndex);
               columnNamebyIndex.put(lastFreeColumnIndex, alu.getName() + "_func");
               ++lastFreeColumnIndex;
            }
         }
      }

      DefaultTableModel actm = new DefaultTableModel(1, 0);
      actm.setColumnIdentifiers(columnNamebyIndex.values().toArray());
      Vector<String> EmpryRows = new Vector<String>();

      for (int i = 0; i < lastFreeColumnIndex; ++i) {
         EmpryRows.add("");
      }

      Iterator it2 = this.instructions.values().iterator();

      while (true) {
         String Instructionname;
         Instruction instruction;
         do {
            if (!it2.hasNext()) {
               return actm;
            }

            instruction = (Instruction) it2.next();
            Instructionname = instruction.getName();
         } while (Instructionname.equals("fetch"));

         Vector RowData = new Vector(EmpryRows);
         RowData.set(0, Instructionname);
         RowData.set(1, instruction.getOpCode());

         int i;
         InstructionStep is;
         for (i = 0; this.getFetch().hasStep(i); ++i) {
            is = this.getFetch().getStep(i);
            this.PutInstructionSetpIntoVector(is, RowData, architectureObjectWithBusConnectionColumnIndex);
            actm.addRow(RowData);
            RowData = new Vector(EmpryRows);
         }

         for (i = 0; instruction.hasStep(i); ++i) {
            RowData = new Vector(EmpryRows);
            is = instruction.getStep(i);
            this.PutInstructionSetpIntoVector(is, RowData, architectureObjectWithBusConnectionColumnIndex);
            actm.addRow(RowData);
         }
      }
   }

   private void logPerforedInstructionName(String Name) {
      if (Name != null) {
         if (Name.equals("")) {
            Name = "[unknown]";
         }

         if (this.PerformedInstructionsNameBuffer.length() > 0) {
            Name = "" + Name;
         }

         System.out.println("capacty : " + this.PerformedInstructionsNameBuffer.capacity());
         System.out.println("length : " + this.PerformedInstructionsNameBuffer.length());
         if (this.PerformedInstructionsNameBuffer.length() + Name.length() >= this.PerformedInstructionsNameBuffer
               .capacity()) {
            this.PerformedInstructionsNameBuffer.delete(0,
                  this.PerformedInstructionsNameBuffer.indexOf("", Name.length()));
         }

         this.PerformedInstructionsNameBuffer.append(Name);
      }
   }

   public String collectPerformedInstruction() {
      String out = this.PerformedInstructionsNameBuffer.toString();
      this.PerformedInstructionsNameBuffer.delete(0, this.PerformedInstructionsNameBuffer.length());
      return out;
   }

   private void PutInstructionSetpIntoVector(InstructionStep is, Vector RowData,
         Map<ArchitectureObjectWithBusConnection, Integer> architectureObjectWithBusConnectionColumnIndex) {
      Iterator it = is.getOutputEnable().iterator();

      Register register;
      while (it.hasNext()) {
         register = (Register) it.next();
         RowData.set((Integer) architectureObjectWithBusConnectionColumnIndex.get(register), "1");
      }

      it = is.getChipSelect().iterator();

      while (it.hasNext()) {
         register = (Register) it.next();
         RowData.set((Integer) architectureObjectWithBusConnectionColumnIndex.get(register) + 1, "1");
      }

      it = is.getRAMChipSelect().entrySet().iterator();

      RAM ram;
      Map.Entry aluentry;
      while (it.hasNext()) {
         aluentry = (Map.Entry) it.next();
         ram = (RAM) aluentry.getKey();
         RowData.set((Integer) architectureObjectWithBusConnectionColumnIndex.get(ram),
               (Boolean) aluentry.getValue() ? "1" : "0");
      }

      it = is.getRAMRnW().entrySet().iterator();

      while (it.hasNext()) {
         aluentry = (Map.Entry) it.next();
         ram = (RAM) aluentry.getKey();
         RowData.set((Integer) architectureObjectWithBusConnectionColumnIndex.get(ram) + 1,
               (Boolean) aluentry.getValue() ? "1" : "0");
      }

      it = is.getMultiPlexerChanel().entrySet().iterator();

      while (it.hasNext()) {
         aluentry = (Map.Entry) it.next();
         Multiplexer mux = (Multiplexer) aluentry.getKey();
         RowData.set((Integer) architectureObjectWithBusConnectionColumnIndex.get(mux),
               ((Integer) aluentry.getValue()).toString());
      }

      it = is.getAluFunctions().entrySet().iterator();

      while (it.hasNext()) {
         aluentry = (Map.Entry) it.next();
         RowData.set((Integer) architectureObjectWithBusConnectionColumnIndex.get(aluentry.getKey()),
               ((Alu.ALUFunction) aluentry.getValue()).toString());
      }

   }
}
