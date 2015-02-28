#Snake AI (Artificial Intelligence)

Implementation of a snake game.

###AI Strategies

- Breadth First Search
- Depth First Search
- A* search
	- 2 different heuristics
	- third heuristic that averages the 2 heuristics above

###State Space

The State space of the Snake game is dependent on the size of the playing field. That is, given a field with dimensions m and n, with o obstacles the state space is: m x n - o.

###Heuristics

A total of five heuristics were used for this assignment. One of which is a slight modification of another heuristic used.

###Manhattan Distance

The Manhattan Distance is a commonly used heuristic because it is very easy to understand, implement and is very effective in finding the shortest path between two points on a 2D grid. 

This heuristic finds the absolute distance between two points on a grid. The smaller the value, the better the path. 

	D = | x-position of snake - x-position of goal) | + | y-position of snake - y-position of goal |

###Tie Breaker Manhattan Distance

Similar to the manhattan distance, this heuristic adds a value p such that the score is nudged slightly in one direction to reduce the time spent looking at multiple equally good shortest paths.

	D = Manhattan Distance x (1.0 + p)

###Diagonal Distance

The Diagonal Distance is a common heuristic which was also chosen because it is simple to understand, implement and effective in finding the shortest path. The heuristic looks for the best diagonal distance from the starting point to the goal. The heuristic gets the maximum absolute value between vertical and horizontal distances.

	D = max( x-position of snake - x-position of goal, y-position of snake - y-position of goal)

###Conclusions

- AI speed (fastest to slowest): A*, Depth-First Search, Breadth-First Search
- A* Heuristics speed (fastest to slowest): Manhattan Distance with tie breaker, average, Diagonal Distance, Manhattan Distance, Euclidean Distance
- A* is better even when you increase the size of the board and when introducing obstacles


###Runtime Average Tests
####Average (based on 10 000 tests)

**Breadth-First Search**<br/>
0.000085 seconds

**Depth-First Search**<br/>
0.000077 seconds

**A* (Euclidean Distance)**<br/>
0.000042 seconds

**A* (Manhattan Distance Heuristic)**<br/>
0.000030 seconds

**A* (Diagonal Distance)**<br/>
0.000030 seconds

**A* (Average Heuristic)**<br/>
0.000027 seconds

**A* (Average with Tie Breaker)**<br/>
0.000023 seconds

**A* (Tie Breaker Manhattan Distance)**<br/>
0.000008 seconds



