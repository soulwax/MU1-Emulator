package de.cirrus.musim;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BusPath {
   ArchitectureObjectWithBusConnection startfrom;
   List<BusStopOver> stopOvers = new ArrayList<BusStopOver>();
   Bus pathforbus;
   Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap;

   public BusPath(Bus b, Map<ArchitectureObjectWithBusConnection, Point2D> ArchtMap,
         ArchitectureObjectWithBusConnection startfrom) {
      this.startfrom = startfrom;
      this.ArchtMap = ArchtMap;
      this.pathforbus = b;
   }

   public void addStopOver(BusStopOver bso) {
      this.stopOvers.add(bso);
   }

   public void setStartfrom(ArchitectureObjectWithBusConnection startfrom) {
      this.startfrom = startfrom;
   }

   public Path2D getPath() throws IOException {
      List<BusStopOver> bsos = new ArrayList<BusStopOver>(this.stopOvers);
      List<BusStopOver> sorted = new ArrayList<BusStopOver>();
      boolean zeroloop = false;

      while (true) {
         while (bsos.size() > 0) {
            if (zeroloop) {
               throw new IOException("Bus path was not correctly created!... ");
            }

            zeroloop = true;

            for (int i = 0; i < bsos.size(); ++i) {
               BusStopOver bso = (BusStopOver) bsos.get(i);
               if (sorted.isEmpty()) {
                  if (bso.getStart() == this.startfrom) {
                     sorted.add(bso);
                     bsos.remove(bso);
                     zeroloop = false;
                     break;
                  }
               } else if (((BusStopOver) sorted.get(sorted.size() - 1)).getEnd() == bso.getStart()) {
                  sorted.add(bso);
                  bsos.remove(bso);
                  zeroloop = false;
                  break;
               }
            }
         }

         Path2D myPath = new Path2D.Double();
         Point2D absulut = (Point2D) this.ArchtMap.get(this.startfrom);
         Point2D p = this.startfrom.getConnectionPoint(this.pathforbus);
         myPath.moveTo(p.getX() + absulut.getX(), absulut.getY() + p.getY());
         Iterator it = sorted.iterator();

         while (it.hasNext()) {
            BusStopOver busStopOver = (BusStopOver) it.next();
            absulut = (Point2D) this.ArchtMap.get(busStopOver.getStart());
            p = busStopOver.getStart().getConnectionPoint(this.pathforbus);
            myPath.lineTo(p.getX() + absulut.getX(), absulut.getY() + p.getY());
            Iterator it2 = busStopOver.stopovers.iterator();

            while (it2.hasNext()) {
               Point2D point2D = (Point2D) it2.next();
               myPath.lineTo(point2D.getX(), point2D.getY());
            }

            absulut = (Point2D) this.ArchtMap.get(busStopOver.getEnd());
            p = busStopOver.getEnd().getConnectionPoint(this.pathforbus);
            myPath.lineTo(p.getX() + absulut.getX(), absulut.getY() + p.getY());
         }

         return myPath;
      }
   }
}
