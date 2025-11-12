# Edge Removal from an MST
by: Mukashev Sultanbek

group: SE-2422

This project demonstrates building a Minimum Spanning Tree (MST) using Kruskal's algorithm, removing an edge from the MST, showing the resulting components, and finding a replacement edge to reconnect the components.

## Requirements
- Java 17
- Maven

## How to build and run
1. Clone repository:
   ```bash
   git clone https://github.com/Lattuse/daa_bonus_task/tree/master
   cd daa_bonus_task
   ```
2. Build:

   ```bash
   mvn clean package
   ```

   This produces `target/daa_bonus_task-1.0-SNAPSHOT.jar`.

3. Run:

   ```bash
   java -jar target/daa_bonus_task-1.0-SNAPSHOT.jar
   ```

The program uses a built-in example graph (you can modify `Main.java` to test other graphs). It will print:

* Original graph edges
* MST edges (before removal)
* Which MST edge is removed
* Components after removal
* Replacement edge chosen to reconnect components
* New MST edges after reconnection

## Notes

* The sample implementation uses Kruskal's algorithm to build the MST.
* On edge removal, the program finds components by DFS and scans all original edges to find the minimum-weight edge crossing the cut (efficient O(E) for finding replacement in this simple approach).
