import java.util.Scanner;

public class BroadcastTree {
    static final int INF = 9999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        System.out.print("Enter number of hosts in subnet: ");
        n = sc.nextInt();

        int[][] cost = new int[n][n];

        System.out.println("Enter cost matrix (use 9999 for no direct connection):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        boolean[] selected = new boolean[n];
        int[] minEdge = new int[n];
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            minEdge[i] = INF;
            parent[i] = -1;
            selected[i] = false;
        }

        minEdge[0] = 0;

        System.out.println("\nEdges in the Broadcast Tree:");
        int totalCost = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = -1;

            for (int j = 0; j < n; j++) {
                if (!selected[j] && (u == -1 || minEdge[j] < minEdge[u]))
                    u = j;
            }

            selected[u] = true;

            for (int v = 0; v < n; v++) {
                if (cost[u][v] < minEdge[v] && !selected[v]) {
                    minEdge[v] = cost[u][v];
                    parent[v] = u;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            System.out.println("Host " + (parent[i] + 1) + " --> Host " + (i + 1) + 
                               "   Cost: " + cost[i][parent[i]]);
            totalCost += cost[i][parent[i]];
        }

        System.out.println("\nTotal cost of Broadcast Tree: " + totalCost);
        sc.close();
    }
}