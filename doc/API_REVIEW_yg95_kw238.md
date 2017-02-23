## SLogo Team 8

### Part 1

1. If new features are to be added, new methods need to be added to the frontend or/and backend APIs (depending on the specific feature).

2. For the backend `evaluate(String input)` method, it encapsulates evaluation details from the frontend, and the frontend can call the method without worrying about how to implement evaluation. For the frontend methods, it encapsulates how updates should be displayed, and the backend can call these methods without worrying about details.

3. In the backend `evaluate(String input)` method, when the input is illegal, the method will call a `showError` method in the frontend to display error messages to the user.

4. There is no passing of large state objects between the frontend and backend, which improves efficiency and makes the contract between the frontend and backend clear.

### Part 2

5.  Five use cases:
  (1). To show returned text from command: the backend team evaluates the commands, and at the end of the last command show its return value by calling `frontEnd.showText(String value)`.
  
  (2). The pen shows or not: (team 6) in each state object there is a variable indicating whether the pen shows or not, and the frontend retrieves this information from the object and act accordingly. (team 8) The status of the pen is only remembered in the backend, and the backend calls different frontend methods `moveTo()` or `drawLine()` depends on the status.

  (3). Execute a command from history by clicking: the history is stored only at the frontend, and the frontend runs the history by passing the command to backend for interpretation.

  (4). foo...

  (5). bar...