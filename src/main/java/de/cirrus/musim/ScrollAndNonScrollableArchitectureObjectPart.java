package de.cirrus.musim;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

public interface ScrollAndNonScrollableArchitectureObjectPart {
   void paintNonScrollableArchitectureObjectPart(Graphics var1);

   Dimension getNonScrollableArchitectureObjectPartDimension();

   Dimension getNonScrollableArchitectureObjectPartDimension(FontMetrics var1);

   void setPreferedNonScrollableArchitectureObjectPartDimension(Dimension var1);

   void paintScrollableArchitectureObjectPart(Graphics var1);

   Dimension getScrollableArchitectureObjectPartDimension();

   Dimension getScrollableArchitectureObjectPartDimension(FontMetrics var1);
}
