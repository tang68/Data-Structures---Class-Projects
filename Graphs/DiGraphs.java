package graphs;
import java.util.*;
import java.util.Map.Entry;

public class DiGraphs<T> {

	private int[][] edges;
	private T[] labels;
	private int[] distances;
	
	@SuppressWarnings("unchecked")
	public DiGraphs(int n) {
		edges = new int[n][n];
		labels = (T[]) new Object[n];
		distances = new int[n];
	}
	
	public boolean isEdge(int source, int target) {
		return edges[source][target] > 0;
	}
	
	public void addEdge(int source, int target, int weight) {
		edges[source][target] = weight;
	}
	
	public void removeEdge(int source, int target) {
		edges[source][target] = 0;
	}
	
	public T getLabels(int vertex){
		return labels[vertex];
	}
	
	public int getWeight(int src, int trg) {
		return edges[src][trg];
	}
	
	
	public int[] neighbors(int vertex) {
		int arrSize = 0;
		for (int i = 0; i < edges.length; i++) {
			if (edges[vertex][i] > 0)
				arrSize++;
		}
		
		int[] nei = new int[arrSize];
		int indexNei = 0;
		for (int i = 0; i < edges.length; i++) {
			if (edges[vertex][i] > 0) {
				nei[indexNei] = i;
				indexNei++;
			}				
		}		
		return nei;
	}
	
	public void setLabel(int vertex, T newLabel) {
		labels[vertex] = newLabel;
	}
	
	public int size(){
		return labels.length;
	}
	
	public ArrayList<T> BFS(int vertex) {
		ArrayList<T> returnList = new ArrayList<>();
		Set<Integer> visitedVertex = new HashSet<>();
		Queue<Integer> vertexQueue = new LinkedList<>();
		returnList.add(getLabels(vertex));
		vertexQueue.add(vertex);
		visitedVertex.add(vertex);
		
		while (!vertexQueue.isEmpty()) {
			int frontVertex = vertexQueue.poll();
			int[] nei = neighbors(frontVertex);
			
			for(int neighbor: nei) {
				if (!visitedVertex.contains(neighbor)) {
					returnList.add(getLabels(neighbor));
					visitedVertex.add(neighbor);
					vertexQueue.add(neighbor);
				}
			}
		}
		return returnList;
	}
	
	public ArrayList<T> DFS(int vertex) {
		ArrayList<T> returnList = new ArrayList<>();
		Stack<Integer> vertexStack = new Stack<>();
		Set<Integer> visitedVertex = new HashSet<>();		
		returnList.add(getLabels(vertex));
		vertexStack.push(vertex);
		visitedVertex.add(vertex);
		
		while (!vertexStack.isEmpty()) {
			int topVertex = vertexStack.peek();
			int[] nei = neighbors(topVertex);
			boolean foundValidChild = false;
			
			for (int neighbor : nei) {
				if (!visitedVertex.contains(neighbor)) {
					returnList.add(getLabels(neighbor));
					vertexStack.push(neighbor);
					visitedVertex.add(neighbor);
					foundValidChild = true;
					break;
				}
			}
			
			if (!foundValidChild)
				vertexStack.pop();
		}
		return returnList;
	}
	
	public String[] dijistra(int source, int target) {
		int shortestDistance = 0;
		Map<Integer, Integer> pathMap = new HashMap<>();
		Queue<Integer> vertexQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {				
				return distances[o1] - distances[o2];
			}			
		});	
		
		for (int i = 0; i < labels.length; i++) {
			distances[i] = (i == source ? 0 : Integer.MAX_VALUE);
			vertexQueue.add(i);
		}
		
		while (!vertexQueue.isEmpty()) {
			Integer currentVertex = vertexQueue.poll();
			if (distances[currentVertex] == Integer.MAX_VALUE){
				break;
			}
			int[] neis = neighbors(currentVertex);
			for (int n : neis) {
				int alt = distances[currentVertex] + getWeight(currentVertex, n);
				if (vertexQueue.contains(n) && alt < distances[n]) {
					distances[n] = alt;
					pathMap.put(n, currentVertex);
					vertexQueue.remove(n);
					vertexQueue.add(n);
				}
			}
		}
		

		Stack<Integer> trace = new Stack<Integer>();
		trace.push(target);
		int current = target;
		while (current != source) {
			if (pathMap.containsKey(current)) {
				current = pathMap.get(current);
				trace.push(current);
			} else {
				System.out.println("No shortest path.");
				break;
			}
		}
		
		String[] returnString = new String[2];
		String path = "";
		while (!trace.isEmpty()) {
			int cur = trace.pop();
			if (!trace.isEmpty()) {
				int next = trace.peek();
				shortestDistance += getWeight(cur, next);
			}
			path += labels[cur] + " ";
		}
		returnString[0] = Integer.toString(shortestDistance);
		returnString[1] = path;
		
		return returnString;
	}
}


















