
# Mac mode

## Search and Find
* `Double Shift`: Search anywhere.
* `Alt+F7`: find all usage of a class, method and variable in the project.
* just type: speed search in the any tree view.
* `Cmd+Shift+A`: Find Action.
* `Ctrl+Alt+F7`: bring forward the list of all usages of a class, method or
  variable across the whole project, and quickly jump to the selected usage.

## Definition, Declaration and Documentation
* `F1`: quick documentation.
* `Cmd+G`: go to declaration. Can be used in code completion pop-up list.
* `Cmd+O`：open any classes. Can be used in code completion pop-up list.
    * Can use `*` (any pattern) and space (end of pattern) in the search pattern.
    * Can use CamelWord to search, just need to type some prefix. For example,
      I want to search `MergeSort`, I just need to type `MeSo`.
* Confirmed in Mac
* `Ctrl+Shift+I`: quick definition for a variable/method/variable.
    * Can also be used in code completion drop down list.
    * Can also preview a picture.
* `Shift+F1`: Open definition in browser.
* `Alt+Q`: Context info, see the declaration of the current method without the
  need to scroll to it.

## Compile and Debug
* `Cmd+F9`: Make the project.
* `Cmd+Shift+F9`: Compile current file.
* `Ctrl+Shift+D`: Debug the code.
* `Ctrl+Shift+R`: run the code.
* Confirmed in Mac
* right-click breakpoints: bring up more details.
* `Ctrl+Shift+F10`: run current code.
* `Alt+F8` in debug mode: evaluate the value of highlighted expression.
* hold `Alt` in debug mode: To quickly evaluate the value of any expression.
* `Ctrl+F8`: Toggle breakpoints.
* `Alt+Shift+F10`: access Run/Debug drop down.

## Code Completion
* `Shift+F6`: Rename classes, methods and variables across the whole project.
* `Ctrl+O`: Override methods of base class.
* `Ctrl+Shift+Space (w&M)`: SmartType code completion, helps to find methods and
  variables that are suitable in the current context, by analyzing the expected
  type of the whole expression.
    * It can work with `new`. You just need to type `new` and type this.
    * In can work with type cast. You just need to type `()` and type this.
    * To accept current highlighted one, you can use `,`, `.`, `;`, and ` `. The
      selected name will be followed by these characters.
* `Cmd+N`: generate getter and setter methods for any fields of your class.
* `Ctrl+Space`: code completion.
* Confirmed in Mac
* `Alt+Slash`: auto completion.
  * Can suggest variable names, File | Setting | Code Style
  * Can be used in search text in current file
* `Ctrl+I`: Implement methods of the interface that the current class implements
  or of the abstract base class.
* `Ctrl+Alt+T(win)`, `Cmd+Alt+T(mac)`:
  Surround code by `try/catch`, `if/else`, and `for/while` etc.
* `Ctrl+Alt+V(win)`, `Cmd+Alt+V(mac)`: Extract variable refactoring. Here is an example:
  ```java
  myEditorPane.setBorder(BoarderFactory.createEmptyBorder(5,5,5,5));
  // Highlight the code inside braket and Refractor to
  Border emptyBorder = BoarderFactory.createEmptyBorder(5,5,5,5);
  myEditorPane.setBorder(emptyBorder);
  ```
* Live Templates: File | Setting | Live Templates `Ctrl+Alt+S`.
    * `Ctrl+J`: complete any valid live template abbreviation.
* `Alt+Shift+P`: If the cursor is between the parentheses of a method call,
  brings up a list of valid parameters. Can be used in code completion pop-up list.
* `Alt+Enter`: automatically fix the errors or bugs.
* `Refactor | Copy`: create a class which is a copy of the selected class. Useful
  when a class is similar to another class.
* `Ctrl+Shift+Enter`: to complete a current statement such as `if`, `do-while`,
  `try-catch`, `return` (or a method call) into a syntactically correct construct
  (e.g. add curly braces).

## Navigation

* `Cmd+F12`: Navigate|File Structure, navigate in the currently edited file.
* `Alt+F1`: To quickly select the currently edited element (class, file, method
  or field) in any view.
* Confirmed in Mac
* `Alt+Home`: Navigation bar.
* `Ctrl+Shift+Backspace`: brings you back to last edited position.
  Navigation | Last Edit Location
* `Alt+Up`, `Alt+Down`: Move between methods.
* `Ctrl+H`: see the inheritance hierarchy for a selected class. Navigate | Type
  Hierarchy.
* `Ctrl+Alt+Shift+N`: open any symbol, Navigate | Symbol
* `Cmd+Alt+B`: navigate to the implementation of an abstract method.

## View and Windows

* `Cmd+0`: show messages.
* `Cmd+1`: project tree.
* `Cmd+7`：file Structure, like `Cmd+F12`, but in the left pane.
* `Shift+left-click` or `mid-click` or `Cmd+w`: close tabs in the editor and the tool
  windows of IntelliJ.
* `Escape, Escape`: focus on editor.
* `Shift+Escapse`：focus on editor and close current tool window.
* `F12`: move focus from editor to last focused tool window.
* Confirmed in Mac
* `Ctrl+BackQuote`: quickly change color scheme view mode and etc.

## Edit
* `Ctrl+Shift+J`: Join two lines.
* `F2`, `Shift+F2`: jump between highlighted syntax errors.
* Confirmed in Mac
* `Ctrl+Alt+W (win), Alt+up (mac)`: extend selection, from word to line to paragraph.
* `Ctrl+Alt+/(win)`, `Cmd+Alt+/(mac)`: `/* */` comment code
* `Ctrl+/(win)`, `Cmd+/(mac)`: `//` comment code
* `Alt+;`: `//` comment code
* `Alt+Home, Alt+Insert`: create new Java classes.
* `Ctrl+Shift+F7`: quickly highlight the usage of some variables in the current
  file, `F3` and `Shift+F3` to navigate the highlighted usages. `Escape, Escape`
  to remove highlighting. This can highlight multiple variables.
    * place the carat at the `implements` keyword in the class definition, and
      press the combination, and select the desired interface from the list.
    * To view all exit points of a method, place the caret at one of them, e.g.
      the return statement and press the combination.
* `Ctrl+Alt+Up`, `Ctrl+Alt+Down`: jump between compiler error or search operation
  results.
* `Ctrl+Shift+Down`, `Ctrl+Shift+Up`: move a block of codes up and down.
* `Cmd+Del`: delete one line.

## Misc.
* `Alt+Shift+C`: quickly review your recent changes to the project.
