package de.cirrus.musim;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class BusStopOver {
   ArchitectureObjectWithBusConnection start;
   ArchitectureObjectWithBusConnection end;
   List<Point2D> stopovers = new ArrayList();

   public BusStopOver(ArchitectureObjectWithBusConnection start, ArchitectureObjectWithBusConnection end) {
      this.start = start;
      this.end = end;
   }

   public void addPoint(Point2D p) {
      this.stopovers.add(p);
   }

   public ArchitectureObjectWithBusConnection getEnd() {
      return this.end;
   }

   public ArchitectureObjectWithBusConnection getStart() {
      return this.start;
   }

   public List<Point2D> getStopovers() {
      return this.stopovers;
   }
}
