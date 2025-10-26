import java.util.*;

public class DistanceVectorRouting {
    static final int INF = 999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];
        int[][] dist = new int[n][n];
        int[][] nextHop = new int[n][n];

        System.out.println("Enter cost matrix (use 999 for no direct link):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
                dist[i][j] = cost[i][j];
                if (i == j)
                    nextHop[i][j] = i;
                else if (cost[i][j] != INF)
                    nextHop[i][j] = j;
                else
                    nextHop[i][j] = -1;
            }
        }

        // Distance Vector Algorithm (Bellman-Ford update)
        boolean updated;
        do {
            updated = false;
            for (int i = 0; i < n; i++) { // for each node
                for (int j = 0; j < n; j++) { // for each destination
                    for (int k = 0; k < n; k++) { // for each possible intermediate
                        if (dist[i][k] + cost[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + cost[k][j];
                            nextHop[i][j] = nextHop[i][k];
                            updated = true;
                        }
                    }
                }
            }
        } while (updated); // repeat until no update

        // Print routing tables
        for (int i = 0; i < n; i++) {
            System.out.println("\nRouting table for node " + i + ":");
            System.out.println("Destination\tNextHop\tCost");
            for (int j = 0; j < n; j++) {
                System.out.println(j + "\t\t" + nextHop[i][j] + "\t\t" + dist[i][j]);
            }
        }
    }
}
