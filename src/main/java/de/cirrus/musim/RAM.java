package de.cirrus.musim;

import de.cirrus.musim.xmlparser.ParseException;
import de.cirrus.musim.xmlparser.ParserFunctions;
import de.cirrus.musim.xmlparser.architekturparser;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RAM
      implements CommandPerformer, ArchitectureObjectWithBusConnection, ScrollAndNonScrollableArchitectureObjectPart {
   int bit;
   boolean ChipSelect = false;
   boolean ReadNotWrite = true;
   SortedMap<Long, Long> ramTable = new TreeMap();
   Bus DataBus = null;
   Bus AdressBus = null;
   Dimension currentSchrollDimension = new Dimension(0, 0);
   Dimension currentNonSchrollDimention = new Dimension(0, 0);
   Dimension preferedNonSchrollDimention = new Dimension(0, 0);
   private List<DimensionChangedListener> dimensionChangedListeners = new ArrayList();
   private ArchtekturObject.State currentState;
   private viewBase currentViewBase;
   private int fiexedAdressSize;
   private String name;
   final int base;
   final int triangle;
   final int maxheightNotScroll;
   Command reloadRam;

   public Orientation getOrientation() {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public void setOrientation(Orientation orientation) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public void paintNonScrollableArchitectureObjectPart(Graphics g) {
      Graphics2D gd = (Graphics2D) g;
      AffineTransform at = gd.getTransform();
      Color oldColor = gd.getColor();
      gd.translate(0, 127);
      gd.rotate(4.71238898038469);
      Polygon p = new Polygon();
      p.addPoint(9, 0);
      p.addPoint(116, 0);
      p.addPoint(126, 19);
      p.addPoint(0, 19);
      gd.setColor(defaultBackgroundColor);
      gd.fillPolygon(p);
      gd.setColor(oldColor);
      gd.draw(p);
      gd.drawLine(63, 0, 63, 19);
      String address = "Address";
      String data = "Data";
      int datawidth = gd.getFontMetrics().stringWidth("Data");
      int addresswidth = gd.getFontMetrics().stringWidth("Address");
      gd.drawString("Data", 93 - datawidth / 2, 15);
      gd.drawString("Address", 31 - addresswidth / 2, 15);
      gd.setTransform(at);
   }

   public Dimension getNonScrollableArchitectureObjectPartDimension() {
      return new Dimension(20, 127);
   }

   public Dimension getNonScrollableArchitectureObjectPartDimension(FontMetrics fm) {
      return new Dimension(20, 127);
   }

   public void paintScrollableArchitectureObjectPart(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setStroke(new BasicStroke(1.0F));
      Font f = g2d.getFont();
      String string1 = "";
      String string0 = "";

      for (int i = 0; i < this.bit; ++i) {
         string1 = string1 + "1";
         string0 = string0 + String.valueOf(i % 2);
      }

      FontMetrics metrics = g2d.getFontMetrics(f);
      int hgt = metrics.getHeight();
      int adv = metrics.stringWidth(string0) > metrics.stringWidth(string1) ? metrics.stringWidth(string0)
            : metrics.stringWidth(string1);
      Dimension valueDimension = new Dimension(adv + 2, hgt + 2);
      AffineTransform at = g2d.getTransform();
      long lastadress;
      if (this.fiexedAdressSize > 0) {
         lastadress = (long) (this.fiexedAdressSize - 1);
      } else {
         lastadress = (Long) this.ramTable.lastKey();
      }

      Dimension adressDimension = new Dimension(
            metrics.stringWidth(Long.toString(lastadress, this.currentViewBase.index)) + 2, hgt + 2);
      int loops = 0;

      for (Iterator it = this.ramTable.keySet().iterator(); it.hasNext(); ++loops) {
         Long long1 = (Long) it.next();
         g2d.drawRect(6, 0, valueDimension.width + adressDimension.width, valueDimension.height);
         g2d.drawRect(6, 0, adressDimension.width, valueDimension.height);
         g2d.drawString(Long.toString(long1, this.currentViewBase.index()),
               6 + adressDimension.width - metrics.stringWidth(Long.toString(long1, this.currentViewBase.index())),
               hgt - 1);
         g2d.drawString(Long.toString((Long) this.ramTable.get(long1), this.currentViewBase.index()),
               6 + valueDimension.width + adressDimension.width - (metrics
                     .stringWidth(Long.toString((Long) this.ramTable.get(long1), this.currentViewBase.index())) + 1),
               hgt - 1);
         g2d.translate(0, valueDimension.height);
      }

      g2d.drawRect(6, 0, valueDimension.width, valueDimension.height / 4);
      g2d.setTransform(at);
      g2d.drawRect(0, 0, 6, valueDimension.height * loops);
      this.updateDimension(
            new Dimension(valueDimension.width + adressDimension.width + 1 + 6, valueDimension.height * loops));
   }

   public Dimension getScrollableArchitectureObjectPartDimension() {
      return new Dimension();
   }

   public Dimension getScrollableArchitectureObjectPartDimension(FontMetrics fm) {
      return this.calcDimenstion(fm);
   }

   public void setPreferedNonScrollableArchitectureObjectPartDimension(Dimension d) {
      this.preferedNonSchrollDimention = d;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Collection<Command> getCommands() {
      ArrayList<Command> commands = new ArrayList();
      commands.add(this.reloadRam);
      return commands;
   }

   public ArchitectureObjectWithBusConnection getPerformer() {
      return this;
   }

   public void nextViewBase() {
      int size = RAM.viewBase.values().length;

      int selected;
      for (selected = 0; selected < size && !RAM.viewBase.values()[selected].equals(this.currentViewBase); ++selected) {
      }

      this.currentViewBase = RAM.viewBase.values()[(selected + 1) % size];
      this.fireDimensionChanged();
   }

   public RAM() {
      this.currentState = ArchtekturObject.State.Inactive;
      this.currentViewBase = RAM.viewBase.hex;
      this.fiexedAdressSize = -1;
      this.name = "";
      this.base = 20;
      this.triangle = 10;
      this.maxheightNotScroll = 127;
      this.reloadRam = new Command() {
         public String getName() {
            return "Load Ram";
         }

         public String getDescription() {
            return "Loads the Ram from a File";
         }

         public void execute(ArchitectureObjectWithBusConnection aowbc) throws Exception {
            if (aowbc instanceof RAM) {
               RAM r = (RAM) aowbc;
               JFileChooser chooser = new JFileChooser("Select file");
               chooser.setDialogType(0);
               chooser.setFileSelectionMode(2);
               File file = new File(System.getProperty("user.dir"));
               chooser.setCurrentDirectory(file);
               chooser.setFileFilter(new FileFilter() {
                  public boolean accept(File f) {
                     return f.isDirectory() | f.getName().endsWith(".ram.xml");
                  }

                  public String getDescription() {
                     return "*.ram.xml";
                  }
               });
               SortedMap<Long, Long> new_ram_values = new TreeMap();
               chooser.addPropertyChangeListener(new PropertyChangeListener() {
                  public void propertyChange(PropertyChangeEvent e) {
                     if (e.getPropertyName().equals("SelectedFileChangedProperty")) {
                        File tmpfile = (File) e.getNewValue();
                        if (tmpfile == null) {
                           return;
                        }

                        if (tmpfile.isDirectory()) {
                           return;
                        }
                     }

                  }
               });
               chooser.setVisible(true);
               int result = chooser.showOpenDialog((Component) null);
               if (chooser.getSelectedFile() != null) {
                  Document doc = architekturparser.readFile(chooser.getSelectedFile().getAbsolutePath());
                  Node node = doc.getFirstChild();
                  NodeList nl = node.getChildNodes();
                  long j = -1L;

                  for (int i = 0; i < nl.getLength(); ++i) {
                     Node tmp = nl.item(i);
                     if (tmp.getNodeName().equals("value")) {
                        if (ParserFunctions.hasAttribute("address", tmp)) {
                           j = Long.valueOf(ParserFunctions.getAttributeByName("address", tmp), 16);
                        } else {
                           ++j;
                        }

                        new_ram_values.put(j, Long.parseLong(tmp.getFirstChild().getTextContent(), 16));
                     } else if (tmp.getNodeType() != 3 && tmp.getNodeType() != 8) {
                        throw new ParseException("Error while reading Ram file.  Unexpected Node:" + tmp.getNodeName());
                     }
                  }

                  r.setRamTable(new_ram_values);
               }

            }
         }
      };
   }

   public RAM(int bit) {
      this.currentState = ArchtekturObject.State.Inactive;
      this.currentViewBase = RAM.viewBase.hex;
      this.fiexedAdressSize = -1;
      this.name = "";
      this.base = 20;
      this.triangle = 10;
      this.maxheightNotScroll = 127;
      this.reloadRam = new Command() {
         public String getName() {
            return "Load Ram";
         }

         public String getDescription() {
            return "Loads the Ram from a File";
         }

         public void execute(ArchitectureObjectWithBusConnection aowbc) throws Exception {
            if (aowbc instanceof RAM) {
               RAM r = (RAM) aowbc;
               JFileChooser chooser = new JFileChooser("Select file");
               chooser.setDialogType(0);
               chooser.setFileSelectionMode(2);
               File file = new File(System.getProperty("user.dir"));
               chooser.setCurrentDirectory(file);
               chooser.setFileFilter(new FileFilter() {
                  public boolean accept(File f) {
                     return f.isDirectory() | f.getName().endsWith(".ram.xml");
                  }

                  public String getDescription() {
                     return "*.ram.xml";
                  }
               });
               SortedMap<Long, Long> new_ram_values = new TreeMap();
               chooser.addPropertyChangeListener(new PropertyChangeListener() {
                  public void propertyChange(PropertyChangeEvent e) {
                     if (e.getPropertyName().equals("SelectedFileChangedProperty")) {
                        File tmpfile = (File) e.getNewValue();
                        if (tmpfile == null) {
                           return;
                        }

                        if (tmpfile.isDirectory()) {
                           return;
                        }
                     }

                  }
               });
               chooser.setVisible(true);
               int result = chooser.showOpenDialog((Component) null);
               if (chooser.getSelectedFile() != null) {
                  Document doc = architekturparser.readFile(chooser.getSelectedFile().getAbsolutePath());
                  Node node = doc.getFirstChild();
                  NodeList nl = node.getChildNodes();
                  long j = -1L;

                  for (int i = 0; i < nl.getLength(); ++i) {
                     Node tmp = nl.item(i);
                     if (tmp.getNodeName().equals("value")) {
                        if (ParserFunctions.hasAttribute("address", tmp)) {
                           j = Long.valueOf(ParserFunctions.getAttributeByName("address", tmp), 16);
                        } else {
                           ++j;
                        }

                        new_ram_values.put(j, Long.parseLong(tmp.getFirstChild().getTextContent(), 16));
                     } else if (tmp.getNodeType() != 3 && tmp.getNodeType() != 8) {
                        throw new ParseException("Error while reading Ram file.  Unexpected Node:" + tmp.getNodeName());
                     }
                  }

                  r.setRamTable(new_ram_values);
               }

            }
         }
      };
      this.bit = bit;
   }

   public RAM(int Bit, Bus DataBus, Bus AdressBus) {
      this.currentState = ArchtekturObject.State.Inactive;
      this.currentViewBase = RAM.viewBase.hex;
      this.fiexedAdressSize = -1;
      this.name = "";
      this.base = 20;
      this.triangle = 10;
      this.maxheightNotScroll = 127;
      this.reloadRam = new Command() {
         public String getName() {
            return "Load Ram";
         }

         public String getDescription() {
            return "Loads the Ram from a File";
         }

         public void execute(ArchitectureObjectWithBusConnection aowbc) throws Exception {
            if (aowbc instanceof RAM) {
               RAM r = (RAM) aowbc;
               JFileChooser chooser = new JFileChooser("Select file");
               chooser.setDialogType(0);
               chooser.setFileSelectionMode(2);
               File file = new File(System.getProperty("user.dir"));
               chooser.setCurrentDirectory(file);
               chooser.setFileFilter(new FileFilter() {
                  public boolean accept(File f) {
                     return f.isDirectory() | f.getName().endsWith(".ram.xml");
                  }

                  public String getDescription() {
                     return "*.ram.xml";
                  }
               });
               SortedMap<Long, Long> new_ram_values = new TreeMap();
               chooser.addPropertyChangeListener(new PropertyChangeListener() {
                  public void propertyChange(PropertyChangeEvent e) {
                     if (e.getPropertyName().equals("SelectedFileChangedProperty")) {
                        File tmpfile = (File) e.getNewValue();
                        if (tmpfile == null) {
                           return;
                        }

                        if (tmpfile.isDirectory()) {
                           return;
                        }
                     }

                  }
               });
               chooser.setVisible(true);
               int result = chooser.showOpenDialog((Component) null);
               if (chooser.getSelectedFile() != null) {
                  Document doc = architekturparser.readFile(chooser.getSelectedFile().getAbsolutePath());
                  Node node = doc.getFirstChild();
                  NodeList nl = node.getChildNodes();
                  long j = -1L;

                  for (int i = 0; i < nl.getLength(); ++i) {
                     Node tmp = nl.item(i);
                     if (tmp.getNodeName().equals("value")) {
                        if (ParserFunctions.hasAttribute("address", tmp)) {
                           j = Long.valueOf(ParserFunctions.getAttributeByName("address", tmp), 16);
                        } else {
                           ++j;
                        }

                        new_ram_values.put(j, Long.parseLong(tmp.getFirstChild().getTextContent(), 16));
                     } else if (tmp.getNodeType() != 3 && tmp.getNodeType() != 8) {
                        throw new ParseException("Error while reading Ram file.  Unexpected Node:" + tmp.getNodeName());
                     }
                  }

                  r.setRamTable(new_ram_values);
               }

            }
         }
      };
      this.bit = Bit;
      this.DataBus = DataBus;
      this.AdressBus = AdressBus;
   }

   public RAM(int Bit, Bus DataBus, Bus AdressBus, int adressSize) {
      this.currentState = ArchtekturObject.State.Inactive;
      this.currentViewBase = RAM.viewBase.hex;
      this.fiexedAdressSize = -1;
      this.name = "";
      this.base = 20;
      this.triangle = 10;
      this.maxheightNotScroll = 127;
      this.reloadRam = new Command() {
         public String getName() {
            return "Load Ram";
         }

         public String getDescription() {
            return "Loads the Ram from a File";
         }

         public void execute(ArchitectureObjectWithBusConnection aowbc) throws Exception {
            if (aowbc instanceof RAM) {
               RAM r = (RAM) aowbc;
               JFileChooser chooser = new JFileChooser("Select file");
               chooser.setDialogType(0);
               chooser.setFileSelectionMode(2);
               File file = new File(System.getProperty("user.dir"));
               chooser.setCurrentDirectory(file);
               chooser.setFileFilter(new FileFilter() {
                  public boolean accept(File f) {
                     return f.isDirectory() | f.getName().endsWith(".ram.xml");
                  }

                  public String getDescription() {
                     return "*.ram.xml";
                  }
               });
               SortedMap<Long, Long> new_ram_values = new TreeMap();
               chooser.addPropertyChangeListener(new PropertyChangeListener() {
                  public void propertyChange(PropertyChangeEvent e) {
                     if (e.getPropertyName().equals("SelectedFileChangedProperty")) {
                        File tmpfile = (File) e.getNewValue();
                        if (tmpfile == null) {
                           return;
                        }

                        if (tmpfile.isDirectory()) {
                           return;
                        }
                     }

                  }
               });
               chooser.setVisible(true);
               int result = chooser.showOpenDialog((Component) null);
               if (chooser.getSelectedFile() != null) {
                  Document doc = architekturparser.readFile(chooser.getSelectedFile().getAbsolutePath());
                  Node node = doc.getFirstChild();
                  NodeList nl = node.getChildNodes();
                  long j = -1L;

                  for (int i = 0; i < nl.getLength(); ++i) {
                     Node tmp = nl.item(i);
                     if (tmp.getNodeName().equals("value")) {
                        if (ParserFunctions.hasAttribute("address", tmp)) {
                           j = Long.valueOf(ParserFunctions.getAttributeByName("address", tmp), 16);
                        } else {
                           ++j;
                        }

                        new_ram_values.put(j, Long.parseLong(tmp.getFirstChild().getTextContent(), 16));
                     } else if (tmp.getNodeType() != 3 && tmp.getNodeType() != 8) {
                        throw new ParseException("Error while reading Ram file.  Unexpected Node:" + tmp.getNodeName());
                     }
                  }

                  r.setRamTable(new_ram_values);
               }

            }
         }
      };
      this.bit = Bit;
      this.DataBus = DataBus;
      this.AdressBus = AdressBus;
      this.fiexedAdressSize = adressSize;
   }

   public Bus getAdressBus() {
      return this.AdressBus;
   }

   public void setAdressBus(Bus AdressBus) {
      this.AdressBus = AdressBus;
   }

   public boolean isChipSelect() {
      return this.ChipSelect;
   }

   public void setChipSelect(boolean ChipSelect) throws IOException {
      this.ChipSelect = ChipSelect;
      this.action();
   }

   public Bus getDataBus() {
      return this.DataBus;
   }

   public void setDataBus(Bus DataBus) {
      this.DataBus = DataBus;
   }

   public boolean isReadNotWrite() {
      return this.ReadNotWrite;
   }

   public void setReadNotWrite(boolean ReadNotWrite) throws IOException {
      this.ReadNotWrite = ReadNotWrite;
      this.action();
   }

   public SortedMap<Long, Long> getRamTable() {
      return this.ramTable;
   }

   public void setRamTable(SortedMap<Long, Long> ramTable) {
      this.ramTable = ramTable;
   }

   public Collection<Bus> getInvoldedBuses() {
      Collection<Bus> t = new ArrayList();
      if (this.AdressBus != null) {
         t.add(this.AdressBus);
      }

      if (this.DataBus != null) {
         t.add(this.DataBus);
      }

      return t;
   }

   public Point2D getConnectionPoint(Bus b) throws IOException {
      if (b == this.AdressBus) {
         return new Point2D.Double(0.0, 93.0);
      } else if (b == this.DataBus) {
         return new Point2D.Double(0.0, 31.0);
      } else {
         throw new IOException("Bus not Connected to ram.");
      }
   }

   public void paint(Graphics g) {
      Graphics2D gd = (Graphics2D) g;
      AffineTransform at = gd.getTransform();
      this.paintNonScrollableArchitectureObjectPart(gd);
      gd.translate(this.currentNonSchrollDimention.width, this.currentNonSchrollDimention.height);
      this.paintScrollableArchitectureObjectPart(gd);
      gd.setTransform(at);
   }

   public Dimension getDimension() {
      Dimension NonScrollDimension = this.getNonScrollableArchitectureObjectPartDimension();
      Dimension ScrollDimension = this.getScrollableArchitectureObjectPartDimension();
      return new Dimension(NonScrollDimension.width + ScrollDimension.width,
            NonScrollDimension.height + ScrollDimension.height);
   }

   public ArchtekturObject.State getState() {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   private void action() throws IOException {
      if (this.ChipSelect) {
         if (this.ReadNotWrite) {
            int oldSize = this.ramTable.size();
            this.ramTable.put(Long.parseLong(this.AdressBus.getValue(), 2), Long.parseLong(this.DataBus.getValue(), 2));
            if (oldSize != this.ramTable.size()) {
               this.fireDimensionChanged();
            }
         }

         if (!this.ReadNotWrite) {
            Long adress = Long.parseLong(this.AdressBus.getValue(), 2);
            if (!this.ramTable.containsKey(adress)) {
               throw new IOException("RAM has not requested adress: " + adress);
            }

            String value = Long.toBinaryString((Long) this.ramTable.get(adress));
            this.DataBus.setValue(value);
         }
      }

   }

   public void setValue(long adress, long value) {
      this.ramTable.put(adress, value);
   }

   public Dimension getDimension(FontMetrics fm) {
      return this.currentSchrollDimension.equals(new Dimension()) ? this.calcDimenstion(fm)
            : this.currentSchrollDimension;
   }

   private Dimension calcDimenstion(FontMetrics fm) {
      String string1 = "";
      String string0 = "";

      int hgt;
      for (hgt = 0; hgt < this.bit; ++hgt) {
         string1 = string1 + "1";
         string0 = string0 + "0";
      }

      hgt = fm.getHeight();
      int adv = fm.stringWidth(string0) > fm.stringWidth(string1) ? fm.stringWidth(string0) : fm.stringWidth(string1);
      long lastadress;
      if (this.fiexedAdressSize > 0) {
         lastadress = (long) (this.fiexedAdressSize - 1);
      } else {
         lastadress = (Long) this.ramTable.lastKey();
      }

      Dimension adressDimension = new Dimension(
            fm.stringWidth(Long.toString(lastadress, this.currentViewBase.index)) + 2, hgt + 2);
      Dimension size = new Dimension(adv + 2, hgt + 2);
      this.updateDimension(
            new Dimension(size.width + adressDimension.width + 7, size.height * this.ramTable.size() + 1));
      return this.currentSchrollDimension;
   }

   private void updateDimension(Dimension newDimension) {
      boolean hastobeupadated = !this.currentSchrollDimension.equals(newDimension);
      if (hastobeupadated) {
         this.currentSchrollDimension = newDimension;
         this.fireDimensionChanged();
      }

   }

   public void resetState() {
      this.currentState = ArchtekturObject.State.Inactive;
   }

   public void addDimensionChangedListeners(DimensionChangedListener Listener) {
      if (Listener != null) {
         this.dimensionChangedListeners.add(Listener);
      }

   }

   private void fireDimensionChanged() {
      Iterator it = this.dimensionChangedListeners.iterator();

      while (it.hasNext()) {
         DimensionChangedListener dimensionChangedListener = (DimensionChangedListener) it.next();
         dimensionChangedListener.OnDimensionChanged(this);
      }

   }

   private static enum viewBase {
      bin(2),
      dez(10),
      hex(16);

      private final int index;

      private viewBase(int index) {
         this.index = index;
      }

      public int index() {
         return this.index;
      }
   }
}
