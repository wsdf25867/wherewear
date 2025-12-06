# Available Tools

This document lists all the tools available to the AI assistant (Junie).

## File Operations

### `open`
Opens 100 lines of a specified file starting from a given line number.
- **Parameters:**
  - `path` (required): Full path to the file
  - `line_number` (optional): Line number to start viewing from

### `open_entire_file`
Opens and displays the entire content of a file.
- **Parameters:**
  - `path` (required): Full path to the file

### `create`
Creates a new file with specified content.
- **Parameters:**
  - `filename` (required): Full path for the new file
  - `content` (optional): Content of the new file

### `search_replace`
Edits code using search and replace approach.
- **Parameters:**
  - `file_path` (required): Full path to the file to modify
  - `search` (required): Block of lines to search for
  - `replace` (required): Lines to replace the found content with

### `rename_element`
Renames code elements (classes, functions, methods, variables, etc.) and updates all references across the codebase.
- **Parameters:**
  - `file_path` (required): Path to file containing the element
  - `line_number` (required): Line number where the element appears
  - `element_to_rename` (required): Name of the element to rename
  - `new_element_name` (required): New name for the element

### `undo_edit`
Reverts the last edit made to the project.
- **Parameters:** None

---

## Navigation & Search

### `get_file_structure`
Displays the code structure of a file by listing all definitions (classes, methods, functions) and import statements.
- **Parameters:**
  - `file` (required): Path to the file

### `search_project`
Searches for file names, symbol names, or exact text strings in files.
- **Parameters:**
  - `search_term` (required): The search term (literal string, 1-2 words)
  - `path` (optional): Directory or file path to restrict search

### `scroll_down`
Moves the view window down to show the next 100 lines of the currently open file.
- **Parameters:** None

### `scroll_up`
Moves the view window up to show the previous 100 lines of the currently open file.
- **Parameters:** None

---

## Terminal & Execution

### `bash`
Executes standard terminal commands (Bash) in the local terminal.
- **Parameters:**
  - `command` (required): Terminal command to execute
  - `background` (optional): Set to true for persistent background processes

### `run_test`
Runs unit tests in specified file(s) or directory.
- **Parameters:**
  - `path` (required): Full path to test file(s), FQN, or directory
  - `testName` (optional): Specific test name to run

### `build`
Initiates the build process for the project, compiling all source files.
- **Parameters:** None

---

## Communication & Session

### `answer`
Provides a comprehensive answer to a question and terminates the session.
- **Parameters:**
  - `full_answer` (required): Complete answer formatted as Markdown

### `ask_user`
Asks the user for help or clarification.
- **Parameters:**
  - `message` (required): Message explaining the problem

### `submit`
Submits the current code and terminates the session.
- **Parameters:** None

---

*Generated on: 2025-12-06*
