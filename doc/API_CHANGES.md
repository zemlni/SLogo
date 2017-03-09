### API Changes

#### External APIs between front-end and back-end

1. turtle commands:
Original:
```
moveTurtleT(double x, double y);
drawLine(double x0, double y0, double x1, double y1);
setAngle(double angle);
showTurtle();
hideTurtle();
```
Changed:
```
moveTurtle(int id, double x0, double y0, double x1, double y1, boolean penDown);
rotateTurtle(int id, double startAngle, double endAngle);
showTurtle(int id);
hideTurtle(int id);
```
Reasons for the change:  
  a. We need both start position/angle to allow animation.  
  b. We need an id to allow multiple turtles.  
  
2. text commands:
Original:
```
showError(String errorMsg);
```
Changed:
```
showError(String errorMsg, String bad);
appendText(String text);
```
The changes are made to allow more flexibility of text display, and using of resource files to display error prompt in different languages.

3. apis for commands in animation:
Added:
```
switchToQueueMode();
switchToInstantMode();
startEventGrouping();
endEventGrouping();
```
These are to allow for different priorities of the commands passed from backend, and for grouped events that shall be executed simultaneously.

