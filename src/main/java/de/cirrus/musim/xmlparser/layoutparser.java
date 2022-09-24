package de.cirrus.musim.xmlparser;

import de.cirrus.musim.ArchitectureObjectWithBusConnection;
import de.cirrus.musim.ArchtekturObject;
import de.cirrus.musim.Bus;
import de.cirrus.musim.BusPath;
import de.cirrus.musim.BusStopOver;
import de.cirrus.musim.Orientation;
import de.cirrus.musim.Virtualline;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class layoutparser {
   public layoutparser() {
   }

   public static void parseDocument(Map<Bus, BusPath> BusPath, String layoutfile,
         Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap,
         Map<String, ArchtekturObject> ArchtectureLookupTble)
         throws ParserConfigurationException, ParseException, SAXException, IOException {
      Document doc = readFile(layoutfile);
      parseDocument(BusPath, ArchtMap, doc, ArchtectureLookupTble);
   }

   private static Document readFile(String filename) throws ParserConfigurationException, SAXException, IOException {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = factory.newDocumentBuilder();
      Document doc = parser.parse(new File(filename));
      return doc;
   }

   private static void parseDocument(Map<Bus, BusPath> BusPath,
         Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap, Document doc,
         Map<String, ArchtekturObject> ArchtectureLookupTble) throws ParseException {
      Node N = doc.getFirstChild();
      Map<String, Virtualline> virtuallines = new HashMap();
      if (!N.getNodeName().equals("layout")) {
         throw new ParseException("First file node is not a 'layout'-container. / ");
      } else {
         NodeList nl = doc.getElementsByTagName("virtuallines");

         int i;
         for (i = 0; i < nl.getLength(); ++i) {
            NodeList n2 = nl.item(i).getChildNodes();

            for (int j = 0; j < n2.getLength(); ++j) {
               Node virtuallineNode = n2.item(j);
               if (virtuallineNode.getNodeType() != 3 && virtuallineNode.getNodeType() != 8
                     && virtuallineNode.getNodeName().equals("virtualline")) {
                  parseVirtualLine(virtuallineNode, virtuallines);
               }
            }
         }

         nl = doc.getElementsByTagName("anchorfor");
         if (nl.getLength() == 0) {
            throw new ParseException("No achor containter found. ");
         } else {
            Node buspathsNode;
            for (i = 0; i < nl.getLength(); ++i) {
               buspathsNode = nl.item(i);
               parseAnchor(buspathsNode, virtuallines, ArchtMap, ArchtectureLookupTble);
            }

            nl = doc.getElementsByTagName("buspaths");

            for (i = 0; i < nl.getLength(); ++i) {
               buspathsNode = nl.item(i);
               parseBuspaths(buspathsNode, BusPath, virtuallines, ArchtMap, ArchtectureLookupTble);
            }

         }
      }
   }

   private static void parseVirtualLine(Node virtuallineNode, Map<String, Virtualline> virtuallines)
         throws ParseException {
      String virtualLineName = ParserFunctions.getNameAtribute(virtuallineNode);
      String virtualLineOrientation = ParserFunctions.getAttributeByName("orientation", virtuallineNode);
      String virtualLinePosition = ParserFunctions.getAttributeByName("position", virtuallineNode);

      int postition;
      try {
         postition = Integer.parseInt(virtualLinePosition);
      } catch (Exception var9) {
         throw new ParseException("The virtual line position:" + virtualLinePosition + " is not a valid Number.");
      }

      Virtualline.Oriantation or;
      try {
         or = Virtualline.Oriantation.valueOf(virtualLineOrientation);
      } catch (Exception var8) {
         throw new ParseException(
               "The virtual line orientation:" + virtualLineOrientation + " is not a valid orientation.");
      }

      Virtualline vl = new Virtualline(postition, or);
      virtuallines.put(virtualLineName, vl);
   }

   private static void parseAnchor(Node anchorNode, Map<String, Virtualline> virtuallines,
         Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap,
         Map<String, ArchtekturObject> ArchtectureLookupTble) throws ParseException {
      NodeList nl = anchorNode.getChildNodes();

      for (int i = 0; i < nl.getLength(); ++i) {
         Node node = nl.item(i);
         if (node.getNodeType() != 3 && node.getNodeType() != 8) {
            parseArchitectureObject(node, virtuallines, ArchtMap, ArchtectureLookupTble);
         }
      }

   }

   private static void parseArchitectureObject(Node item, Map<String, Virtualline> virtuallines,
         Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap,
         Map<String, ArchtekturObject> ArchtectureLookupTble) throws ParseException {
      String name = ParserFunctions.getNameAtribute(item);
      ArchtekturObject aowbc = (ArchtekturObject) ArchtectureLookupTble.get(name);
      if (aowbc == null) {
         throw new ParseException("Layout: Component: " + name + " not found in architecture.");
      } else {
         if (ParserFunctions.hasAttribute("orientation", item)) {
            String orientationstring = ParserFunctions.getAttributeByName("orientation", item);

            try {
               ((ArchitectureObjectWithBusConnection) aowbc).setOrientation(Orientation.valueOf(orientationstring));
            } catch (IllegalArgumentException var10) {
               String acceptedValues = "";

               for (int i = 0; i < Orientation.values().length; ++i) {
                  if (i == 0) {
                     acceptedValues = Orientation.values()[i].toString();
                  } else {
                     acceptedValues = acceptedValues + " or " + Orientation.values()[i].toString();
                  }
               }

               throw new ParseException("Layout: Component: " + name + " -> Invalid orientation: " + orientationstring
                     + ". Should be " + acceptedValues);
            }
         }

         new Point2D.Double(0.0, 0.0);
         Point2D pd = parseCoordinates(item, virtuallines);
         if (aowbc instanceof ArchitectureObjectWithBusConnection) {
            ArchtMap.put((ArchitectureObjectWithBusConnection) aowbc, pd);
         }

      }
   }

   private static int parseCoordinate(Node node, Map<String, Virtualline> virtuallines) throws ParseException {
      NodeList nl = node.getChildNodes();

      for (int i = 0; i < nl.getLength(); ++i) {
         Node tmpNode = nl.item(i);
         if (tmpNode.getNodeType() != 3 && tmpNode.getNodeType() != 8) {
            String virtuallinename;
            if (tmpNode.getNodeName().equals("number")) {
               virtuallinename = ParserFunctions.getAttributeByName("value", tmpNode);

               try {
                  int value = Integer.parseInt(virtuallinename);
                  return value;
               } catch (Exception var8) {
                  throw new ParseException("Value " + virtuallinename + " is not a valid number for a coordinate.");
               }
            }

            if (tmpNode.getNodeName().equals("virtualline")) {
               virtuallinename = ParserFunctions.getNameAtribute(tmpNode);
               Virtualline Vl = (Virtualline) virtuallines.get(virtuallinename);
               if (Vl == null) {
                  throw new ParseException("Virtual line name: " + virtuallinename + "not found.");
               }

               return Vl.getPosition();
            }
         }
      }

      throw new ParseException("Couldn't find coordinate value.");
   }

   private static void parseBuspaths(Node buspathsNode, Map<Bus, BusPath> BusPath,
         Map<String, Virtualline> virtuallines, Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap,
         Map<String, ArchtekturObject> ArchtectureLookupTble) throws ParseException {
      NodeList nl = buspathsNode.getChildNodes();

      for (int i = 0; i < nl.getLength(); ++i) {
         Node tmpNode = nl.item(i);
         if (tmpNode.getNodeType() != 3 && tmpNode.getNodeType() != 8 && tmpNode.getNodeName().equals("buspath")) {
            parseBuspath(tmpNode, BusPath, virtuallines, ArchtMap, ArchtectureLookupTble);
         }
      }

   }

   private static void parseBuspath(Node buspathnode, Map<Bus, BusPath> BusPath, Map<String, Virtualline> virtuallines,
         Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap,
         Map<String, ArchtekturObject> ArchtectureLookupTble) throws ParseException {
      String Busname = ParserFunctions.getAttributeByName("for", buspathnode);
      Bus bus = (Bus) ArchtectureLookupTble.get(Busname);
      if (bus == null) {
         throw new ParseException("Buspath -> Bus:" + Busname + "not found in architecture.");
      } else {
         BusPath busPath = new BusPath(bus, ArchtMap, (ArchitectureObjectWithBusConnection) null);
         NodeList nl = buspathnode.getChildNodes();

         for (int i = 0; i < nl.getLength(); ++i) {
            Node tmpNode = nl.item(i);
            if (tmpNode.getNodeType() != 3 && tmpNode.getNodeType() != 8) {
               if (tmpNode.getNodeName().equals("start")) {
                  parseBuspathStart(busPath, tmpNode, virtuallines, ArchtMap, ArchtectureLookupTble);
               } else if (tmpNode.getNodeName().equals("stopover")) {
                  parseBuspathStopover(busPath, tmpNode, virtuallines, ArchtMap, ArchtectureLookupTble);
               }
            }
         }

         BusPath.put(bus, busPath);
      }
   }

   private static void parseBuspathStart(BusPath busPath, Node startNode, Map<String, Virtualline> virtuallines,
         Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap,
         Map<String, ArchtekturObject> ArchtectureLookupTble) throws ParseException {
      String StartingObject = ParserFunctions.getAttributeByName("from", startNode);
      ArchitectureObjectWithBusConnection objectWithBusConnection = (ArchitectureObjectWithBusConnection) ArchtectureLookupTble
            .get(StartingObject);
      if (objectWithBusConnection == null) {
         throw new ParseException("Buspath -> Start: from " + StartingObject + "not found in architecture.");
      } else {
         busPath.setStartfrom(objectWithBusConnection);
      }
   }

   private static void parseBuspathStopover(BusPath busPath, Node StopOverNode, Map<String, Virtualline> virtuallines,
         Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap,
         Map<String, ArchtekturObject> ArchtectureLookupTble) throws ParseException {
      String fromObject = ParserFunctions.getAttributeByName("from", StopOverNode);
      ArchitectureObjectWithBusConnection fromObjectWithBusConnection = (ArchitectureObjectWithBusConnection) ArchtectureLookupTble
            .get(fromObject);
      if (fromObjectWithBusConnection == null) {
         throw new ParseException("Buspath -> Stopover: from " + fromObject + "not found in architecture.");
      } else {
         String toObject = ParserFunctions.getAttributeByName("to", StopOverNode);
         ArchitectureObjectWithBusConnection toObjectWithBusConnection = (ArchitectureObjectWithBusConnection) ArchtectureLookupTble
               .get(toObject);
         if (toObjectWithBusConnection == null) {
            throw new ParseException("Buspath -> Stopover: to " + toObject + "not found in architecture.");
         } else {
            BusStopOver BTO = new BusStopOver(fromObjectWithBusConnection, toObjectWithBusConnection);
            NodeList nl = StopOverNode.getChildNodes();

            for (int i = 0; i < nl.getLength(); ++i) {
               Node tmpNode = nl.item(i);
               if (tmpNode.getNodeType() != 3 && tmpNode.getNodeType() != 8
                     && tmpNode.getNodeName().equals("stopoverhere")) {
                  parseBuspathStopoverhere(BTO, tmpNode, virtuallines, ArchtMap, ArchtectureLookupTble);
               }
            }

            busPath.addStopOver(BTO);
         }
      }
   }

   private static void parseBuspathStopoverhere(BusStopOver BTO, Node stopover, Map<String, Virtualline> virtuallines,
         Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap,
         Map<String, ArchtekturObject> ArchtectureLookupTble) throws ParseException {
      try {
         BTO.addPoint(parseCoordinates(stopover, virtuallines));
      } catch (ParseException var6) {
         throw new ParseException("Can't parse Stopoverhere couse: " + var6.toString());
      }
   }

   private static Point2D parseCoordinates(Node Coordinates, Map<String, Virtualline> virtuallines)
         throws ParseException {
      Point2D pd = new Point2D.Double(0.0, 0.0);
      NodeList nl = Coordinates.getChildNodes();

      for (int i = 0; i < nl.getLength(); ++i) {
         Node node = nl.item(i);
         if (node.getNodeType() != 3 && node.getNodeType() != 8) {
            if (node.getNodeName().equals("x")) {
               pd.setLocation((double) parseCoordinate(node, virtuallines), pd.getY());
            } else if (node.getNodeName().equals("y")) {
               pd.setLocation(pd.getX(), (double) parseCoordinate(node, virtuallines));
            } else {
               if (!node.getNodeName().equals("point")) {
                  throw new ParseException("Unexceptet Nodename " + node.getNodeName() + "");
               }

               String Xs = ParserFunctions.getAttributeByName("x", node);
               String Ys = ParserFunctions.getAttributeByName("y", node);

               try {
                  int x = Integer.parseInt(Xs);
                  int y = Integer.parseInt(Ys);
                  pd = new Point2D.Double((double) x, (double) y);
               } catch (Exception var10) {
                  throw new ParseException("Can't parse point: " + Xs + " or " + Ys + "not a valid number.");
               }
            }
         }
      }

      return pd;
   }
}
