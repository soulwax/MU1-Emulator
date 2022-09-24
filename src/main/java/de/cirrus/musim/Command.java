package de.cirrus.musim;

public interface Command {
   String getName();

   String getDescription();

   void execute(ArchitectureObjectWithBusConnection var1) throws Exception;
}
