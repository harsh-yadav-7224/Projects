import java.util.*;

public class ShortestPath {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, e;
        System.out.print("Enter number of vertices: ");
        n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        e = sc.nextInt();

        int[][] graph = new int[n][n];

        // Initialize with large value
        for (int i = 0; i < n; i++)
            Arrays.fill(graph[i], 9999);

        System.out.println("Enter edges (u v weight):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph[u][v] = w;
            graph[v][u] = w;
        }

        System.out.print("Enter start node: ");
        int start = sc.nextInt();

        System.out.print("Enter end node: ");
        int end = sc.nextInt();

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];

        Arrays.fill(dist, 9999);
        Arrays.fill(parent, -1);

        dist[start] = 0;

        // Simple Dijkstra (without priority queue)
        for (int i = 0; i < n - 1; i++) {

            int min = 9999, u = -1;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 9999 && !visited[v]) {
                    if (dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                        parent[v] = u;
                    }
                }
            }
        }

        // Print path
        List<Integer> path = new ArrayList<>();
        for (int v = end; v != -1; v = parent[v])
            path.add(v);

        Collections.reverse(path);

        System.out.println("Shortest Distance: " + dist[end]);
        System.out.println("Path: " + path);

        sc.close();
    }
}