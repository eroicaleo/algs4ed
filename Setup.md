# How to setup the environment with IntelliJ Idea on Windows.

1. download windows installer algs4.exe [here](http://algs4.cs.princeton.edu/windows/algs4.exe).
2. Run the installer. It will install everything under "C:\Users\your_user_name\algs4".
3. Follow the instruction [here](stackoverflow.com/questions/1051640/correct-way-to-add-external-jars-lib-jar-to-an-intellij-idea-project):
    * `File` -> `Project Structure` (hotkey is `ctrl`+`alt`+`shift`+`s`)
    * `Project Settings` -> `Modules` -> `Dependencies` -> `Add`
    * Select the `algs4.jar` from the installed directory
4. Run some test code like:

```java
public class Main {
    public static void main(String[] args) {
	    System.out.println("Hello world!");
        StdOut.println("Hello, World");
        StdOut.println("Hello, World");
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(0.5, 0.5);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.line(0.2, 0.2, 0.8, 0.2);
    }
}
```
