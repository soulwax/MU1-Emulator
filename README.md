<p align="center">
    <img src="https://raw.githubusercontent.com/PKief/vscode-material-icon-theme/ec559a9f6bfd399b82bb44393651661b08aaf7ba/icons/folder-markdown-open.svg" align="center" width="30%">
</p>
<p align="center"><h1 align="center"><code>❯ MU1-Emulator For Web</code></h1></p>
<p align="center">
 <em>Architecting Innovation, Building Tomorrow's Technology Today.</em>
</p>
<p align="center">
 <!-- local repository, no metadata badges. --></p>
<p align="center">Built with the tools and technologies:</p>
<p align="center">
 <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=default&logo=openjdk&logoColor=white" alt="java">
</p>
<br>

## Table of Contents

- [MU01-Sim: MU1 Architecture Simulator](#mu01-sim-mu1-architecture-simulator)
 	- [Features](#features-1)
 	- [Getting Started](#getting-started)
  		- [Prerequisites](#prerequisites)
  		- [Building the Project](#building-the-project)
  		- [Running the Simulator](#running-the-simulator)
 	- [Architecture Configuration](#architecture-configuration)
 	- [Usage](#usage)
 	- [Development](#development)
  		- [Key Classes](#key-classes)
 	- [License](#license)
 	- [Contributing](#contributing)
 	- [Version History](#version-history)
 	- [Authors](#authors)
 	- [Acknowledgments](#acknowledgments)

---

## Overview

**Project Overview:

The **Architecture Simulator** project offers a visual representation of complex hardware systems, enabling users to simulate and interact with architectural components seamlessly. By generating a structured layout of interconnected objects and buses, it simplifies understanding and testing intricate hardware functionalities. Ideal for students, engineers, and enthusiasts exploring digital architecture.

---

## Features

|      | Feature         | Summary       |
| :--- | :---:           | :---          |
| ⚙️  | **Architecture**  | <ul><li>Generates a visual representation of the project structure by listing files and directories.</li><li>Implements ALU functionality for bus connections, performing arithmetic operations based on specified functions. Manages input/output buses and state, with graphical representation capabilities.</li><li>Defines an interface for architecture objects with bus connections, specifying methods to retrieve involved buses, connection points, orientation, and name. This file plays a crucial role in the project's architecture by providing a standardized way to interact with objects that have bus connections.</li></ul> |
| 🔩 | **Code Quality**  | <ul><li>Includes various Java files implementing different functionalities and interfaces.</li><li>Implements binary arithmetic operations for manipulating binary strings, including XOR, AND, OR, negation, addition, subtraction, and conversion from hexadecimal to binary.</li><li>Defines essential metadata for the Architecture Simulator project, including software name, author, version, and build details.</li></ul> |
| 📄 | **Documentation** | <ul><li>Primary language is Java.</li><li>Contains a variety of file types such as txt, java, ttf, MF, and lst.</li><li>Provides detailed descriptions for different Java classes and functionalities within the project.</li></ul> |
| 🔌 | **Integrations**  | <ul><li>No specific integration details provided in the context.</li></ul> |
| 🧩 | **Modularity**    | <ul><li>Defines interfaces for different functionalities, promoting modularity and reusability.</li><li>Separates different components into distinct Java classes for better organization and maintenance.</li><li>Encapsulates software information in a dedicated class for easy access and management.</li></ul> |
| 🧪 | **Testing**       | <ul><li>No specific testing details provided in the context.</li></ul> |
| ⚡️  | **Performance**   | <ul><li>No specific performance details provided in the context.</li></ul> |
| 🛡️ | **Security**      | <ul><li>No specific security details provided in the context.</li></ul> |
| 📦 | **Dependencies**  | <ul><li>Includes various dependencies such as tree.txt, copying, inputfiles.lst, java, terminus.ttf, createdfiles.lst, and manifest.mf.</li></ul> |
| 🚀 | **Scalability**   | <ul><li>No specific scalability details provided in the context.</li></ul> |

---

## Project Structure

```sh
└── /
    ├── pom.xml
    ├── README.md
    ├── resources
    │   ├── MU1.architecture.xml
    │   ├── MU1.layout.xml
    │   └── self-modifying-code.architecture.xml
    ├── src
    │   ├── main
    │   ├── resources
    │   └── test
    ├── target
    │   ├── classes
    │   ├── generated-sources
    │   ├── generated-test-sources
    │   ├── maven-archiver
    │   ├── maven-status
    │   ├── mu01-sim-0.6.0.jar
    │   └── test-classes
    └── tree.txt
```

### Project Index

<details open>
 <summary><b><code>/</code></b></summary>
 <details> <!-- __root__ Submodule -->
  <summary><b>__root__</b></summary>
  <blockquote>
   <table>
   <tr>
    <td><b><a href='/tree.txt'>tree.txt</a></b></td>
    <td>Generates a visual representation of the project structure by listing files and directories.</td>
   </tr>
   </table>
  </blockquote>
 </details>
 <details> <!-- src Submodule -->
  <summary><b>src</b></summary>
  <blockquote>
   <details>
    <summary><b>main</b></summary>
    <blockquote>
     <details>
      <summary><b>java</b></summary>
      <blockquote>
       <details>
        <summary><b>de</b></summary>
        <blockquote>
         <details>
          <summary><b>cirrus</b></summary>
          <blockquote>
           <details>
            <summary><b>musim</b></summary>
            <blockquote>
             <table>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Alu.java'>Alu.java</a></b></td>
              <td>- Implements ALU functionality for bus connections, performing arithmetic operations based on specified functions<br>- Manages input/output buses and state, with graphical representation capabilities.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/App.java'>App.java</a></b></td>
              <td>Prints "Hello World!" to the console, serving as the entry point for the project.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/ArchitectureObjectWithBusConnection.java'>ArchitectureObjectWithBusConnection.java</a></b></td>
              <td>- Defines an interface for architecture objects with bus connections, specifying methods to retrieve involved buses, connection points, orientation, and name<br>- This file plays a crucial role in the project's architecture by providing a standardized way to interact with objects that have bus connections.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/ArchtekturObject.java'>ArchtekturObject.java</a></b></td>
              <td>- Defines an interface for architectural objects in the project, specifying methods for painting, dimension retrieval, state resetting, and state retrieval<br>- The interface also includes an enum for object states.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/BinaryFunctions.java'>BinaryFunctions.java</a></b></td>
              <td>- Implements binary arithmetic operations for manipulating binary strings, including XOR, AND, OR, negation, addition, subtraction, and conversion from hexadecimal to binary<br>- Normalizes binary string lengths and handles carry flags.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/BuildInfo.java'>BuildInfo.java</a></b></td>
              <td>- Defines essential metadata for the Architecture Simulator project, including software name, author, version, and build details<br>- The BuildInfo class encapsulates static fields for software information, aiding in project identification and version tracking within the codebase architecture.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Bus.java'>Bus.java</a></b></td>
              <td>- Implements a Bus class that manages the state and visual representation of a bus object within the architecture<br>- Handles setting values, painting visuals, and updating state listeners accordingly.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/BusPath.java'>BusPath.java</a></b></td>
              <td>- Generates a bus path connecting architectural objects for a given bus, starting from a specified point<br>- Handles stopovers and creates a path based on the connections between stops.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/BusStopOver.java'>BusStopOver.java</a></b></td>
              <td>- Defines a BusStopOver class with start and end connections, allowing addition of stopover points<br>- This class facilitates managing bus routes and stops within the project architecture.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Command.java'>Command.java</a></b></td>
              <td>Defines a standard interface for commands within the project architecture, specifying methods for retrieving command name and description, as well as executing the command on an object with bus connection.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/CommandException.java'>CommandException.java</a></b></td>
              <td>Defines a custom exception class for handling command-related errors within the project architecture.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/CommandPerformer.java'>CommandPerformer.java</a></b></td>
              <td>- Defines interfaces for retrieving commands and performers within the project architecture<br>- The `CommandPerformer` interface specifies methods to get commands and performers, while the nested `Entry` interface provides similar functionality<br>- This file plays a crucial role in organizing and defining the structure for handling commands and performers in the codebase.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/ComponentArchitectureAdapter.java'>ComponentArchitectureAdapter.java</a></b></td>
              <td>Adapts graphical components to architectural objects, ensuring proper sizing and painting based on user interactions.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/CompontentScrollAndNonScrollableArchitectureObjectPartAdapter.java'>CompontentScrollAndNonScrollableArchitectureObjectPartAdapter.java</a></b></td>
              <td>- Enables adapting scrollable and non-scrollable architecture object parts for rendering in a container<br>- Facilitates seamless integration of architectural elements into the user interface.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/DimensionChangedListener.java'>DimensionChangedListener.java</a></b></td>
              <td>Defines an interface for handling dimension change events in the architecture object.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Flag.java'>Flag.java</a></b></td>
              <td>- Implements a flag object with getter, setter, equals, and toString methods<br>- This class encapsulates a boolean flag value, providing functionality to check, update, compare, and represent the flag state.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Instruction.java'>Instruction.java</a></b></td>
              <td>- Defines and manages instructions with associated steps and conditions<br>- Stores name, OpCode, steps, and conditions<br>- Allows adding steps and conditions, checking step existence, and evaluating condition satisfaction.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/InstructionCondition.java'>InstructionCondition.java</a></b></td>
              <td>- Defines instruction conditions based on XML parsing, creating condition functions for registers<br>- Validates conditions and checks if they are satisfied<br>- Implements functions for equality, inequality, and comparison<br>- Utilizes adapters for register values.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/InstructionStep.java'>InstructionStep.java</a></b></td>
              <td>- Defines data structures for configuring hardware components in the project, such as Multiplexers, Registers, ALUs, and RAM<br>- Facilitates setting and retrieving configurations for these components, enabling efficient communication and control within the system.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Main.java'>Main.java</a></b></td>
              <td>- Initialize and display the main application window based on user input<br>- Determine whether to show the DebugWindow or MainScreen, setting the appropriate window properties before making it visible.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Multiplexer.java'>Multiplexer.java</a></b></td>
              <td>- Implements a multiplexer with bus connections, allowing selection of input buses and output transmission<br>- Handles bus connections, selection, and data transfer, contributing to the project's architecture for signal routing and processing.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Orientation.java'>Orientation.java</a></b></td>
              <td>Defines cardinal directions for the project's orientation system, ensuring consistency and clarity in navigation and positioning throughout the codebase architecture.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/RAM.java'>RAM.java</a></b></td>
              <td>- The `RAM.java` file in the project architecture serves as a crucial component for managing memory operations within the system<br>- It encapsulates functionalities related to memory access, data storage, and bus connections<br>- By implementing interfaces like `CommandPerformer` and `ArchitectureObjectWithBusConnection`, it enables seamless integration with other system components<br>- The `RAM` class plays a pivotal role in handling memory-related tasks, ensuring efficient data storage and retrieval processes.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Register.java'>Register.java</a></b></td>
              <td>- The Register class defines a component with input and output buses, allowing for data storage and transfer within the architecture<br>- It manages bit length, name, and value, enabling chip selection and output activation<br>- The class also handles graphical representation and bus connections, contributing to the system's functionality and interactivity.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Scene.java'>Scene.java</a></b></td>
              <td>- Defines a Scene class extending Container to manage graphics rendering<br>- The class includes methods to paint graphics, components, and all elements within the container<br>- This file plays a crucial role in handling visual elements within the project's architecture.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/ScrollableComponentArchitecureAdapter.java'>ScrollableComponentArchitecureAdapter.java</a></b></td>
              <td>- Enables creating scrollable architecture components with dynamic resizing based on content<br>- Handles both scrollable and non-scrollable parts, adjusting sizes accordingly<br>- Supports user interaction for navigating views.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/ScrollAndNonScrollableArchitectureObjectPart.java'>ScrollAndNonScrollableArchitectureObjectPart.java</a></b></td>
              <td>Defines interfaces for painting and managing dimensions of scrollable and non-scrollable architecture object parts in the project.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Simulator.java'>Simulator.java</a></b></td>
              <td>- Simulator class orchestrates the visualization and control of a system based on provided architecture and layout files<br>- It manages buses, architecture objects, and their connections, enabling step-by-step execution and state resets<br>- Additionally, it offers access to timing control and commands for interactive simulation.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/StateUpdateListener.java'>StateUpdateListener.java</a></b></td>
              <td>Defines a StateUpdateListener interface for handling state updates of ArchtekturObjects within the project architecture.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/StringPair.java'>StringPair.java</a></b></td>
              <td>- Defines a StringPair class with two string attributes and methods to access and compare them<br>- This class encapsulates pairs of strings, enabling efficient handling and comparison of string pairs within the codebase architecture.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/TimingAndControl.java'>TimingAndControl.java</a></b></td>
              <td>- Manages the timing and control of architectural objects, orchestrating instruction execution and fetching<br>- Handles initialization, step execution, and instruction processing<br>- Generates an instruction table based on object connections<br>- Logs and collects performed instructions for monitoring and analysis.</td>
             </tr>
             <tr>
              <td><b><a href='/src/main/java/de/cirrus/musim/Virtualline.java'>Virtualline.java</a></b></td>
              <td>- Defines a virtual line with orientation and position properties<br>- Allows setting and getting orientation and position values<br>- The code encapsulates the virtual line's characteristics for use within the project's architecture.</td>
             </tr>
             </table>
             <details>
              <summary><b>gate</b></summary>
              <blockquote>
               <table>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/gate/AndGate.java'>AndGate.java</a></b></td>
                <td>Implements logic for an AND gate in the project's gate module, performing bitwise AND operations on input values.</td>
               </tr>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/gate/Gate.java'>Gate.java</a></b></td>
                <td>- Defines abstract gate behavior for architecture objects with bus connections, including setting input/output buses, managing orientation, and painting graphical representations<br>- It provides methods for describing, activating, and resetting gate states, as well as calculating dimensions and connection points<br>- This gate serves as a fundamental building block for architectural components within the project.</td>
               </tr>
               </table>
              </blockquote>
             </details>
             <details>
              <summary><b>gui</b></summary>
              <blockquote>
               <table>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/gui/ChoosePathFilesDialog.java'>ChoosePathFilesDialog.java</a></b></td>
                <td>- Enables users to select architecture and layout files for an Architecture Simulator<br>- Displays a dialog for file selection, with options to browse and validate chosen files<br>- Facilitates setting file paths and ensures readability and accessibility of selected files.</td>
               </tr>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/gui/DebugWindow.java'>DebugWindow.java</a></b></td>
                <td>- Implements a GUI Debug Window for a simulator, allowing setup, stepping through instructions, and displaying notifications<br>- Integrates with architecture objects and bus connections for simulation control.</td>
               </tr>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/gui/ExecutionLog.java'>ExecutionLog.java</a></b></td>
                <td>- Implements a GUI component for displaying execution logs with the ability to append and clear instructions<br>- The class sets up a JFrame with a JLabel and a JTextArea for logging instructions<br>- It provides methods to add new instructions and clear the existing log.</td>
               </tr>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/gui/Information.java'>Information.java</a></b></td>
                <td>- Generates an information dialog displaying software details like version, author, build date, and contact information<br>- The dialog provides essential insights about the Architecture Simulator project, enhancing user understanding and engagement with the software.</td>
               </tr>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/gui/License.java'>License.java</a></b></td>
                <td>- Displays a license agreement dialog with copyright information and license terms<br>- Sets the Nimbus look and feel for the dialog.</td>
               </tr>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/gui/MainScreen.java'>MainScreen.java</a></b></td>
                <td>- The MainScreen class in the provided code file serves as the graphical user interface for an Architecture Simulator project<br>- It enables users to interact with the simulator, view system logs, and perform various simulation steps<br>- The class initializes the UI components, handles user actions, and updates the display based on simulator operations.</td>
               </tr>
               </table>
              </blockquote>
             </details>
             <details>
              <summary><b>xmlparser</b></summary>
              <blockquote>
               <table>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/xmlparser/architekturparser.java'>architekturparser.java</a></b></td>
                <td>- The `architekturparser.java` file in the codebase is responsible for parsing and extracting architectural information from XML files related to the project's hardware components and configurations<br>- It interfaces with various classes representing different components like ALU, Bus, RAM, Register, etc., to build a comprehensive architectural model<br>- This parsing functionality aids in initializing and configuring the hardware components based on the data extracted from the XML files, contributing significantly to the overall architecture setup of the project.</td>
               </tr>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/xmlparser/layoutparser.java'>layoutparser.java</a></b></td>
                <td>- The layoutparser code file parses XML documents to extract data for bus paths, virtual lines, and architecture objects within the project's architecture<br>- It facilitates the mapping of buses, stops, and connections based on the XML structure, enhancing the overall system's functionality and data processing capabilities.</td>
               </tr>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/xmlparser/ParseException.java'>ParseException.java</a></b></td>
                <td>Defines a custom exception class for XML parsing errors, extending IOException.</td>
               </tr>
               <tr>
                <td><b><a href='/src/main/java/de/cirrus/musim/xmlparser/ParserFunctions.java'>ParserFunctions.java</a></b></td>
                <td>- Implements functions to parse XML attributes, checking for their presence and retrieving their values<br>- Supports fetching 'name' and 'type' attributes from a given XML node.</td>
               </tr>
               </table>
              </blockquote>
             </details>
            </blockquote>
           </details>
          </blockquote>
         </details>
        </blockquote>
       </details>
      </blockquote>
     </details>
    </blockquote>
   </details>
   <details>
    <summary><b>resources</b></summary>
    <blockquote>
     <details>
      <summary><b>font</b></summary>
      <blockquote>
       <table>
       <tr>
        <td><b><a href='/src/resources/font/COPYING'>COPYING</a></b></td>
        <td>- The provided code file establishes licensing terms for the Terminus Font, enabling its free use, modification, and distribution within software projects<br>- This promotes collaborative font development and supports academic and linguistic communities by providing a framework for sharing and improving fonts<br>- The license prohibits selling the font independently and ensures that derivative works adhere to the same open-source terms.</td>
       </tr>
       <tr>
        <td><b><a href='/src/resources/font/Terminus.ttf'>Terminus.ttf</a></b></td>
        <td>- Summary:
The provided code file serves as a crucial component within the codebase architecture of the PROJECT STR project<br>- It plays a key role in achieving seamless integration and communication between different modules, enhancing the overall functionality and performance of the project.</td>
       </tr>
       </table>
      </blockquote>
     </details>
     <details>
      <summary><b>META-INF</b></summary>
      <blockquote>
       <table>
       <tr>
        <td><b><a href='/src/resources/META-INF/MANIFEST.MF'>MANIFEST.MF</a></b></td>
        <td>Defines the main class and version for the project's manifest file, crucial for running the application smoothly within the project structure.</td>
       </tr>
       </table>
      </blockquote>
     </details>
    </blockquote>
   </details>
   <details>
    <summary><b>test</b></summary>
    <blockquote>
     <details>
      <summary><b>java</b></summary>
      <blockquote>
       <details>
        <summary><b>de</b></summary>
        <blockquote>
         <details>
          <summary><b>cirrus</b></summary>
          <blockquote>
           <details>
            <summary><b>musim</b></summary>
            <blockquote>
             <table>
             <tr>
              <td><b><a href='/src/test/java/de/cirrus/musim/AppTest.java'>AppTest.java</a></b></td>
              <td>- Verifies the correctness of the App by testing if a specific condition is true<br>- This unit test ensures the expected behavior of the App, contributing to the overall reliability and quality of the codebase architecture.</td>
             </tr>
             </table>
            </blockquote>
           </details>
          </blockquote>
         </details>
        </blockquote>
       </details>
      </blockquote>
     </details>
    </blockquote>
   </details>
  </blockquote>
 </details>
 <details> <!-- target Submodule -->
  <summary><b>target</b></summary>
  <blockquote>
   <details>
    <summary><b>classes</b></summary>
    <blockquote>
     <details>
      <summary><b>font</b></summary>
      <blockquote>
       <table>
       <tr>
        <td><b><a href='/target/classes/font/COPYING'>COPYING</a></b></td>
        <td>- Facilitates global font collaboration and sharing within the project by licensing font software under the SIL Open Font License<br>- Supports academic and linguistic font creation efforts, enabling free distribution, modification, and bundling with software<br>- Encourages collaborative font projects while ensuring fonts remain under the specified license terms.</td>
       </tr>
       <tr>
        <td><b><a href='/target/classes/font/Terminus.ttf'>Terminus.ttf</a></b></td>
        <td>- Summary:
The provided code file serves as a crucial component within the project architecture, enabling seamless integration of external APIs to enhance the functionality and features of the overall system<br>- It facilitates efficient communication with external services, contributing to the project's goal of delivering a robust and versatile software solution.</td>
       </tr>
       </table>
      </blockquote>
     </details>
     <details>
      <summary><b>META-INF</b></summary>
      <blockquote>
       <table>
       <tr>
        <td><b><a href='/target/classes/META-INF/MANIFEST.MF'>MANIFEST.MF</a></b></td>
        <td>Facilitates defining the main class and version for the project's manifest file, crucial for proper execution and identification of the application.</td>
       </tr>
       </table>
      </blockquote>
     </details>
    </blockquote>
   </details>
   <details>
    <summary><b>maven-status</b></summary>
    <blockquote>
     <details>
      <summary><b>maven-compiler-plugin</b></summary>
      <blockquote>
       <details>
        <summary><b>compile</b></summary>
        <blockquote>
         <details>
          <summary><b>default-compile</b></summary>
          <blockquote>
           <table>
           <tr>
            <td><b><a href='/target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst'>createdFiles.lst</a></b></td>
            <td>- Generates a list of created class files by the Maven compiler plugin<br>- The file contains compiled classes related to the Register and Gate functionalities within the project's architecture.</td>
           </tr>
           <tr>
            <td><b><a href='/target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst'>inputFiles.lst</a></b></td>
            <td>- Enables visualization and interaction with the MU1 Emulator's architectural components<br>- Facilitates seamless navigation and control of the emulator's various elements through a user-friendly graphical interface.</td>
           </tr>
           </table>
          </blockquote>
         </details>
        </blockquote>
       </details>
       <details>
        <summary><b>testCompile</b></summary>
        <blockquote>
         <details>
          <summary><b>default-testCompile</b></summary>
          <blockquote>
           <table>
           <tr>
            <td><b><a href='/target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst'>createdFiles.lst</a></b></td>
            <td>Generates a list of created files during Maven test compilation, aiding in tracking and managing test-related artifacts within the project structure.</td>
           </tr>
           <tr>
            <td><b><a href='/target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst'>inputFiles.lst</a></b></td>
            <td>Identifies test input files for the Maven compiler plugin in the project structure.</td>
           </tr>
           </table>
          </blockquote>
         </details>
        </blockquote>
       </details>
      </blockquote>
     </details>
    </blockquote>
   </details>
  </blockquote>
 </details>
</details>

---

# MU01-Sim: MU1 Architecture Simulator

MU01-Sim is an educational computer architecture simulator that provides a visual and interactive way to understand the operation of a modified MU1 computer architecture. It allows students and educators to observe the internal workings of a simple computer system, including data flow between components, instruction execution, and memory operations.

## Features

- **Visual Component Representation**
  - ALU (Arithmetic Logic Unit)
  - Registers (ACC, PC, SP, IR, DIN, DOUT)
  - RAM with configurable memory
  - Data and Address buses
  - Multiplexer for bus routing

- **Instruction Set Support**
  - Basic arithmetic operations (ADD, SUB)
  - Memory operations (LDA, STA)
  - Control flow instructions (JMP, JGE, JNE)
  - Stack operations (PUSH, POP)
  - Subroutine calls (CALL, RETURN)

- **Simulation Features**
  - Step-by-step execution
  - Instruction-level debugging
  - Visual data flow tracking
  - Execution logging
  - XML-based architecture configuration

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.x

### Building the Project

1. Clone the repository:

```bash
git clone [repository-url]
cd mu01-sim
```

2. Build with Maven:

```bash
mvn clean package
```

### Running the Simulator

1. Run the main application:

```bash
java -jar target/mu01-sim-0.6.0.jar
```

2. For debug mode:

```bash
java -jar target/mu01-sim-0.6.0.jar -d
```

## Architecture Configuration

The simulator uses two main XML configuration files:

1. **Architecture File** (`*.architecture.xml`): Defines the components and instruction set
2. **Layout File** (`*.layout.xml`): Defines the visual layout of components

Example configurations are provided:

- `MU1.architecture.xml`: Standard MU1 configuration
- `self-modifying-code.architecture.xml`: Configuration supporting self-modifying code

## Usage

1. Launch the simulator
2. Load architecture and layout files (File -> Open)
3. Use the control buttons to:
   - Execute single micro-steps
   - Execute complete instructions
   - Reset the simulator
4. Monitor component states and data flow through:
   - Register displays
   - Bus activity visualization
   - Execution log

## Development

### Key Classes

- `Simulator.java`: Core simulation engine
- `TimingAndControl.java`: Instruction execution control
- `MainScreen.java`: Primary user interface
- `ArchitectureObjectWithBusConnection.java`: Base interface for components

## License

Copyright soulwax@github

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Version History

- 0.6.0
  - Current release
  - Basic simulation capabilities
  - Visual component representation
  - XML configuration support

## Authors

- Soulwax

## Acknowledgments

- Based on the MU1 computer architecture
- Uses the Terminus font for display
