# Elevators System

Simple application with terminal-based UI to manage elevators.  

### building and running

in the project directory:
```
gradlew run
```

##Terminal user interface

The interface allows to call an elevator on a given floor and the
system will assign an elevator to the caller according to a certain
strategy. It also allows choosing destination in an elevator and
running the simulation of the system.

Print the status:  \
```STATUS```  \
Call an elevator, returns the elevator number of the assigned elevator: \
```CALL <floor number> <UP|DOWN>``` \
Choose a destination in an elevator: \
```DEST <elevator number> <floor number>``` \
Run the simulation: \
```SIM <number of steps>``` \
Get an elevator's floor, status, and destinations info: \
```INFO <elevator number>``` \
to exit type ```QUIT```


##Architecture and algorithms

The architecture allows to easily implement different approaches
of deciding which elevator to assign to a call and algorithms
of finding optimal order of destinations of an elevator.


### IElevatorsSystems
this interface is responsible for dispatching elevators and
choosing the optimal elevator to call and is the main interface 
of the system (calling, choosing destinations, returning the 
status of the elevators)

Code that should be common to most of its implementations 
is in abstract class ```ElevatorsSystem``` 

Current implementation ```LowestCostElevatorsSystem``` simply always 
dispatches the elevator with the lowest *cost* which is dependent on
the elevator implementation.

### IElevator

responsible for choosing the optimal path (order of destinations) 
and calculating the cost of a call

Current implementations are:

```FcfsElevator``` - most basic Fist-Come-First-Serve approach

```MinOverheadElevator``` - optimizes the path to minimize the cost  
The *cost* is defined by sum of squares of additional time before arrivig
of the users of the elevator after adding a new destination.

In order to achieve good time complexity a heuristic approach is used: 
the elevator maintains the order of its destinations and when a new 
destination is added considers adding it only between current destinations 
(or as a new first or last destination).

In the result the complexity of
adding a new destination (or calculating it's cost) is ___O(n)___ where n is
the number of current destinations. 
If the algorithm considered every permutation of destinations it would result 
in complexity of ___O(n!)___


### Issues

Unit tests suit is very limited. I would have implemented more tests, i.e. of the simulation
and the MinOverheadElevator class if I have had more time. 


