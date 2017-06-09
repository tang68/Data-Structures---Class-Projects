package graphs;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Project3 {

	public static void main(String[] args) {
		
		Scanner scan;
		Map<String, String> fullMap = new HashMap<>();
		Map<String, String> names = new HashMap<>();
		Map<String, Integer> numbers = new HashMap<>();
		DiGraphs<String> graph = new DiGraphs<>(21);
		
		try {
			File city = new File("C:\\Users\\Ryan Tang\\OneDrive\\Documents\\Eclipse\\CS241\\Graphs\\graphs\\city.dat");
			File road = new File("C:\\Users\\Ryan Tang\\OneDrive\\Documents\\Eclipse\\CS241\\Graphs\\graphs\\road.dat");		
			scan = new Scanner(city);
			while (scan.hasNext()) {
				String cityInfo = scan.nextLine();
				int cityNumber = Integer.parseInt(cityInfo.substring(0, 2).trim());
				String cityCode = cityInfo.substring(4, 6);	
				String cityNames = cityInfo.substring(10, 25).trim();			
				fullMap.put(cityCode, cityInfo);
				names.put(cityCode, cityNames);
				numbers.put(cityCode, cityNumber);
				graph.setLabel(cityNumber, cityCode);
			}
						
			scan = new Scanner(road);
			while (scan.hasNext()) {
				int fromCity = scan.nextInt();
				int toCity = scan.nextInt();
				int distance = scan.nextInt();				
				graph.addEdge(fromCity, toCity, distance);
			}
			
		} 
		
		catch (FileNotFoundException e1) {
			System.out.println("File not found");
			e1.printStackTrace();
		}
		
		command();
		Scanner input = new Scanner(System.in);
		String key = input.next();
		if (key.equals("E")) {
			System.out.println("Bye.");
		}
		while (!key.equals("E")){
			
			if (!key.equals("Q") && !key.equals("D") &&
				!key.equals("I") && !key.equals("R") && !key.equals("H")) {
				System.out.print("Wrong command. Enter again. " );
				key = input.nextLine();					
			}
			
			
			if (key.equals("Q")) {
				System.out.print("City Code: " );
				String code = input.next();
				if (!fullMap.containsKey(code))
					System.out.println("City code is not recognized");
				else
					System.out.println(fullMap.get(code));			
			}
			
			if (key.equals("D")) {
				System.out.print("Enter 2 city codes: ");
				String code1 = input.next();
				String code2 = input.next();
				if (!names.containsKey(code1) || !names.containsKey(code2)) {
					System.out.println("One or more city code does not exist. ");
				}
				else {
					String name1 = names.get(code1);
					String name2 = names.get(code2);
					int source = numbers.get(code1);
					int target = numbers.get(code2);
					String[] path = graph.dijistra(source, target);
					System.out.println("The minimum distance between " + name1 + " and " + name2
							+ " is " + path[0]);
					System.out.println("Through the route: " + path[1]);
				}
				
				
			}
			
			if (key.equals("I")) {
				System.out.print("Enter 2 city codes and distance: ");		
				String code1 = input.next();
				String code2 = input.next();
				int dist = input.nextInt();
				int source = numbers.get(code1);
				int target = numbers.get(code2);
				String name1 = names.get(code1);
				String name2 = names.get(code2);
				if (!names.containsKey(code1) || !names.containsKey(code2)) {
					System.out.println("One or more city code does not exist. ");
				}
				
				if (graph.isEdge(source, target)) {
					System.out.println("There exist a road between this 2 cities. ");
				}
				else {
					
					graph.addEdge(source, target, dist);
					System.out.println("You have inserted a road from " + name1 + " to " + name2
							+ " with distance of " + dist);				
				}
				
			}
			
			if (key.equals("R")) {
				System.out.print("Enter 2 city codes to remove road: ");
				String code1 = input.next();
				String code2 = input.next();
				int source = numbers.get(code1);
				int target = numbers.get(code2);
				String name1 = names.get(code1);
				String name2 = names.get(code2);
				if (!names.containsKey(code1) || !names.containsKey(code2)) {
					System.out.println("One or more city code does not exist. ");
				}
				
				if (!graph.isEdge(source, target)) {
					System.out.println("The road between " + name1 + " and " + name2
							+ " does not exist");
				}
				else {
					graph.removeEdge(source, target);
					System.out.println("You have removed the road from " + name1 + 
							" and " + name2);
				}
				
			}
			
			if (key.equals("H")) {
				command();				
			}
			
			System.out.println();
			System.out.print("Command? ");
			key = input.next();				
		}
		input.close();

	}
	
	
	
	public static void command() {
		System.out.println("Command? ");
		System.out.println("	Q Query the city information by entering the city code.");
		System.out.println("	D Find the minimum distance between two cities.");
		System.out.println("	I Insert a road by entering two city codes and distance.");
		System.out.println("	R Remove an existing road by entering two city codes.");
		System.out.println("	H Display this message.");
		System.out.println("	E Exit.");
		
	}

}
