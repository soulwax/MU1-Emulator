package de.cirrus.musim;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Collection;

public interface ArchitectureObjectWithBusConnection extends ArchtekturObject {
   Color defaultBackgroundColor = Color.WHITE;
   Color activeColor = Color.YELLOW;

   Collection<Bus> getInvoldedBuses();

   Point2D getConnectionPoint(Bus var1) throws IOException;

   Orientation getOrientation();

   void setOrientation(Orientation var1);

   String getName();
}
