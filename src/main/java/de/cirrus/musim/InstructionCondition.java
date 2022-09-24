package de.cirrus.musim;

import de.cirrus.musim.xmlparser.ParseException;
import de.cirrus.musim.xmlparser.ParserFunctions;
import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InstructionCondition {
   conditionfunction todo;

   public InstructionCondition(Map<String, ArchtekturObject> ArchtectureLookupTble, Node node) throws ParseException {
      if (!node.getNodeName().equals("condition")) {
         throw new ParseException(
               "Trying to construct an instruction condition should be condition but got : " + node.getNodeName());
      } else {
         NodeList nl = node.getChildNodes();

         for (int i = 0; i < nl.getLength(); ++i) {
            Node tmp = nl.item(i);
            if (tmp.getNodeName().equals("register")) {
               try {
                  String name = ParserFunctions.getNameAtribute(tmp);
                  Register r = (Register) ArchtectureLookupTble.get(name);
                  if (r == null) {
                     throw new ParseException("Trying to construct an instruction: Register not found by name:" + name);
                  }

                  NodeList conditionfunctionstringlist = tmp.getChildNodes();

                  for (int j = 0; j < conditionfunctionstringlist.getLength(); ++j) {
                     Node conditionfunctionstring = conditionfunctionstringlist.item(j);
                     if (conditionfunctionstring.getNodeName().equals("equals")) {
                        this.todo = new equals(new RegisterValueAdapter(r),
                              ParserFunctions.getAttributeByName("value", conditionfunctionstring));
                     } else if (conditionfunctionstring.getNodeName().equals("equalsNot")) {
                        this.todo = new equalsNot(new RegisterValueAdapter(r),
                              ParserFunctions.getAttributeByName("value", conditionfunctionstring));
                     } else if (conditionfunctionstring.getNodeName().equals("biggerOrEqual2k")) {
                        this.todo = new biggerOrEqual2k(new RegisterValueAdapter(r),
                              ParserFunctions.getAttributeByName("value", conditionfunctionstring));
                     } else if (conditionfunctionstring.getNodeType() != 3
                           && conditionfunctionstring.getNodeType() != 8) {
                        throw new ParseException("Unknown condion type supportet is register : " + tmp.getNodeName());
                     }
                  }
               } catch (Exception var11) {
                  throw new ParseException("Trying to construct an instruction: " + var11.getMessage());
               }
            } else if (tmp.getNodeType() != 3 && tmp.getNodeType() != 8) {
               throw new ParseException("Unknown condion type supportet is register : " + tmp.getNodeName());
            }
         }

      }
   }

   public boolean isSatisfied() {
      return this.todo.verfiy();
   }

   private class equalsNot implements conditionfunction {
      ValueAdapter v;
      String s;

      public equalsNot(ValueAdapter va, String s) {
         this.v = va;
         this.s = s;
      }

      public boolean verfiy() {
         StringPair sp = BinaryFunctions.normalizeStringlengt(new StringPair(this.v.getValue(), this.s));
         return !sp.getS1().equals(sp.getS2());
      }
   }

   private class equals implements conditionfunction {
      ValueAdapter v;
      String s;

      public equals(ValueAdapter va, String s) {
         this.v = va;
         this.s = s;
      }

      public boolean verfiy() {
         StringPair sp = BinaryFunctions.normalizeStringlengt(new StringPair(this.v.getValue(), this.s));
         return sp.getS1().equals(sp.getS2());
      }
   }

   private class biggerOrEqual2k implements conditionfunction {
      ValueAdapter v;
      String s;

      public biggerOrEqual2k(ValueAdapter va, String s) {
         this.v = va;
         this.s = s;
      }

      public boolean verfiy() {
         StringPair sp = BinaryFunctions.normalizeStringlengt(new StringPair(this.v.getValue(), this.s));
         String result = BinaryFunctions.subTwoK(sp.getS1(), this.s, sp.getS1().length(), (Flag) null);
         boolean bigger = !result.startsWith("1");
         boolean equals = sp.getS1().equals(sp.getS2());
         return bigger | equals;
      }
   }

   private interface conditionfunction {
      boolean verfiy();
   }

   private class RegisterValueAdapter implements ValueAdapter {
      Register r;

      public RegisterValueAdapter(Register r) {
         this.r = r;
      }

      public String getValue() {
         return this.r.getValue();
      }
   }

   private interface ValueAdapter {
      String getValue();
   }
}
