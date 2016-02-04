# Data Structures (and Algorithms) in Java

... and other stuff that I've created for fun (mostly).

## Graphs

Are fun.

```java

// Create a directed graph
DiGraph g = AdjacencyList.createDiGraph(8);

// Insert edges
g.insert(1, 2);
g.insert(1, 5);
g.insert(2, 3);
g.insert(2, 4);
g.insert(3, 4);
g.insert(3, 7);
g.insert(4, 5);
g.insert(4, 6);
g.insert(5, 6);
g.insert(6, 7);

// Get the topological order
int[] order = g.getTopologicalOrder();

assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6, 7, 0 }, order);

```
