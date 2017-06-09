package treeNode;
import java.util.Scanner;

public class Project1 {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter integers that need to be added to the tree(-1 to stop): ");
		int valToAdd = scan.nextInt();
		while (valToAdd != -1) {
			bst.addElement(valToAdd);
			valToAdd = scan.nextInt();
		}
		
		System.out.print("\nPreorder Traverse: ");
		bst.preorderTraverse();
		System.out.print("\nInorder Traverse: ");
		bst.inorderTraverse();
		System.out.print("\nPostorder Traverse: ");
		bst.postorderTraverse();
		System.out.print("\nDescending: ");
		bst.desc();
		
		System.out.println();
		
		displayCommand();
		
		String command = scan.next();
//		if (command.equalsIgnoreCase("E")) 
//			System.out.println("Adios !!!");
		try {
			while (!command.equalsIgnoreCase("E")){
				if (!command.equalsIgnoreCase("I") && !command.equalsIgnoreCase("D")
						&& !command.equalsIgnoreCase("P") && !command.equalsIgnoreCase("S")
						&& !command.equalsIgnoreCase("H") && !command.equalsIgnoreCase("E")) {
					System.out.println("Command is not recognized.");
					
				}
				
				if (command.equalsIgnoreCase("I")) {
					System.out.print("Enter value to be inserted: ");
					bst.addElement(scan.nextInt());
					System.out.print("Inorder: ");
					bst.inorderTraverse();
					System.out.println();
				}
				
				if (command.equalsIgnoreCase("D")) {
					System.out.print("Enter value to be removed: ");
					bst.remove(scan.nextInt());
					System.out.print("Inorder: ");
					bst.inorderTraverse();
					System.out.println();
				}
				
				if (command.equalsIgnoreCase("P")) {
					System.out.print("Enter a value to find its predecessor: ");
					int val = scan.nextInt();
					System.out.println("Predecessor of " + val + " is: " 
							+ bst.predecessor(val).getData());
					
				}
				
				if (command.equalsIgnoreCase("S")) {
					System.out.print("Enter a value to find its successor: ");
					int val = scan.nextInt();
					System.out.println("Successor of " + val + " is: "
							+ bst.successor(val).getData());
				}
				
				if (command.equalsIgnoreCase("H")) {
					displayCommand();
				}
				System.out.println();
				System.out.print("Command? ");
				command = scan.next();
			}
		}
		catch (RuntimeException e){
			System.out.println("No such entry");
		}
		System.out.println("Peace Outtt!");
	}
		
		
	
	
	private static void displayCommand(){
		System.out.println("Command?");
		System.out.println("I - Insert a value");
		System.out.println("D - Delete a value");
		System.out.println("P - Find predecessor");
		System.out.println("S - Find successor");
		System.out.println("E - Exit the program");
		System.out.println("H - Display this message");
		
	}

}
