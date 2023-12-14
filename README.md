# Notepad Application Readme

## Overview
This Java application is a simple Notepad that provides basic text editing functionalities, file operations, and undo/redo capabilities using the Memento design pattern. The application is built using Java Swing for the graphical user interface.

## Features
- **Text Area:** A large text area for editing and entering text.
- **Undo/Redo:** Buttons to undo and redo text changes.
- **File Operations:**
  - Open: Allows users to open and load content from existing text files.
  - Save: Enables users to save the current content to a new or existing text file.
- **File List:** Displays a list of available files for quick access.

## Implementation Details
- **Memento Design Pattern:** The application uses the Memento design pattern to implement undo and redo functionalities. The `Mementor` class encapsulates the state of the text area, and the `CareTaker` class manages the history of states.
  
- **File Handling:** The application reads a list of existing files from a file named `fileList.txt` and displays them in a list. Users can open, edit, and save files through the graphical interface.

- **Graphical User Interface (GUI):**
  - **Header:** Displays the application title and undo/redo buttons.
  - **Text Area:** The main area for text editing, with scroll functionality.
  - **File Area:** Contains sections for opening files, choosing from the file list, and saving files. Displays status messages.
  - **Footer:** Provides buttons for saving changes and clearing the text area.

## Usage
1. **Opening a File:**
   - Enter the desired file name in the "Open a file" section.
   - Click the "Open File" button to load the file content into the text area.

2. **Saving a File:**
   - Enter the desired file name in the "Create & Save to file" section.
   - Click the "Save" button to save the current content to the specified file.

3. **Undo/Redo:**
   - Use the "Undo" and "Redo" buttons to revert or redo changes in the text area.

4. **Clearing the Text Area:**
   - Click the "Clear" button to reset the text area and input fields.

## Requirements
- Java Development Kit (JDK) installed on the system.

## How to Run
1. Compile the code using a Java compiler.
2. Run the compiled program, and the Notepad application GUI will be displayed.

## Note
- The application assumes that there is a directory path "C:\Users\HP\Desktop\DataStructures\Structures\" with appropriate file permissions for file read and write operations.

Feel free to explore and enhance the code for additional features or improvements! If you have any questions or encounter issues, please reach out for assistance.
