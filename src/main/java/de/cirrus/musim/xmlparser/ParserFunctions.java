package de.cirrus.musim.xmlparser;

import org.w3c.dom.Node;

public class ParserFunctions {
   public ParserFunctions() {
   }

   public static boolean hasAttribute(String attributeName, Node src) {
      boolean result = false;
      if (src != null) {
         try {
            result = getAttributeByName(attributeName, src) != null;
         } catch (Exception var4) {
         }
      }

      return result;
   }

   public static String getAttributeByName(String attributeName, Node src) throws ParseException {
      Node AtributeNode = src.getAttributes().getNamedItem(attributeName);
      if (AtributeNode != null) {
         return AtributeNode.getNodeValue();
      } else {
         throw new ParseException("Node " + src.getNodeName() + " has no " + attributeName + " atribute!");
      }
   }

   public static String getNameAtribute(Node node) throws ParseException {
      return getAttributeByName("name", node);
   }

   public static String getTypeAtribute(Node node) throws ParseException {
      return getAttributeByName("type", node);
   }
}
