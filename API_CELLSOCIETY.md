Team 8 CellSociety API Analysis
======


## UI
### Internal
Interface and Drawer needed to be able to pass the root object back and forth. The InterfaceHandler had control over an Interface instance and was able to control it through methods such as update and reset. Most of the functionality of the UI was contained within the Interface which means that there wasn't a huge amount of need for public methods within. 
### External
The UI needs access to the Manager to get the Society to pass it to Drawer. 

## Simulation
### Internal 
The simulation had methods to get the neighbors of a cell, analyze whether the cell needed to be changed, create new cells and update them, generate and regenerate polygons. Additionally, there was functionality to generate the vertices. 

### External
Functionality was called to create new simulations, update the state of the simulations, move and scale shapes and points. Additionally, there is functionality to accept configurations from the XML Parser through a manager instance.  

## Configuration
### Internal
Throwing XML Exceptions
### External
Parse XML file, write out an XML file, return a Manager instance. 
