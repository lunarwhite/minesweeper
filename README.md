# minesweeper

classical Windows game, bulid with Java Swing. || Windows 桌面扫雷游戏

```
.
│   README.md
├───doc
│       1-ProblemAnalysis.pdf
│       2-Design.pdf
│       3-ErrorHandling.pdf
├───image
└───src
    ├───bean
    │       HeroBean.java
    │       MineLabel.java
    ├───dialog
    │       AboutSweeping.java
    │       HeroDialog.java
    │       UserDefinedDialog.java
    ├───listener
    │       Listener.java
    │       UserDefinedListener.java
    ├───main
    │       MainFrame.java
    ├───panel
    │       BombjMenuBar.java
    │       BombjPanel.java
    │       FacejPanel.java
    ├───timer
    │       TimerListener.java
    └───tools
            LayBomb.java
            StaticTool.java
```

## 1 Overview
- test and run:
  - download latest [release](https://github.com/lunarwhite/minesweeper/releases/tag/beta) and unzip
  - double-click `minesweeper.jar` in folder with `\image`
- jdk: openjdk8-redhat
- IDE: eclipse

## 2 Documents
- 1-ProblemAnalysis.pdf
  - Analyzing the Problem
  - Requirements Specification
  - Prototype
- 2-Design.pdf
  - Data Structures
  - Algorithms
  - Modular organization
- 3-ErrorHandling.pdf

## 3 Tool-list
- Conditionals (if-else)
- Complex conditionals (nested if-else)
- Loops
- Nested loops
- User-defined methods
- User-defined methods with parameters
- User-defined methods with return values
- Use of additional libraries. Example: Random, GUI.
- Arrays
- 2D Arrays
- User-defined objects
- User-defined objects where more than one instance of the object is created
- Input validation: handle most common mistakes in input - and react usefully.
- User-defined objects where more than one instance of the object is created
- Use of flags other than to simply determine if a loop should repeat or not.
- Use of try-catch to handle at least two checked exceptions.

## 4 Reference
- https://math.hws.edu/javanotes/
- https://ocw.mit.edu/courses/civil-and-environmental-engineering/1-124j-foundations-of-software-engineering-fall-2000/lecture-notes/
- https://github.com/ChrisMayfield/ThinkJavaCode2
- https://github.com/lxf44944/minesweeper_java

## 5 Sceenshots

![expert-mode](res\Screenshot_3.png)

![game-over](res\Screenshot_4.png)

![easy-mode](res\Screenshot_5.png)
![win](res\Screenshot_1.png)