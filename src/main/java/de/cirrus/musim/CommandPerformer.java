package de.cirrus.musim;

import java.util.Collection;

public interface CommandPerformer {
   Collection<Command> getCommands();

   ArchitectureObjectWithBusConnection getPerformer();

   public interface Entry {
      ArchitectureObjectWithBusConnection getPerformer();

      Collection<Command> getCommands();
   }
}
