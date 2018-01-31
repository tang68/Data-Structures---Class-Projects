package cs431p1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class SchedulerMain {

	private static final int swapTime = 3;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter test file name: ");
		String testFile = scan.next();
		//String testFile = "sample.txt";
		String noExtensionFile = testFile.substring(0, testFile.length()-4);
		
		
		Queue<List<Integer>> processesInfo = readFile(testFile);
		
		FCFS fcfs = new FCFS(processesInfo, swapTime);
		List<List<Integer>> fcfsResult = fcfs.runProcesses();
		outputToCSV(fcfsResult, "fcfs-" + noExtensionFile + ".csv");
		
		SJF sjf = new SJF(processesInfo, swapTime);
		List<List<Integer>> sjfResult = sjf.runProcesses();
		outputToCSV(sjfResult, "sjf-" + noExtensionFile + ".csv");
		
		RoundRobin RR25 = new RoundRobin(processesInfo, swapTime, 25);
		List<List<Integer>> RR25Result = RR25.runProcesses();
		outputToCSV(RR25Result, "RR25-" + noExtensionFile + ".csv");
		
		RoundRobin RR50 = new RoundRobin(processesInfo, swapTime, 50);
		List<List<Integer>> RR50Result = RR50.runProcesses();
		outputToCSV(RR50Result, "RR50-" + noExtensionFile + ".csv");
		
		Lottery lottery = new Lottery(processesInfo, swapTime, 50);
		List<List<Integer>> lotteryResult = lottery.runProcesses();
		outputToCSV(lotteryResult, "lottery-" + noExtensionFile + ".csv");

	}
	
	private static void outputToCSV(List<List<Integer>> result, String fileName)  {
		//result stored in list of 5 lists (CPU time, PID, SburstTime, EburstTime, Completion)
		
		try {
			int totalCompletionTime = 0;
			int processCount = 0;
			FileWriter writer = new FileWriter(fileName);
			
			CSVUtils.writeLine(writer, Arrays.asList("CPU", "PID", "StartBurstTime", 
					"EndBurstTime", "CompletionTime"));
			
			for (int i = 0; i < result.size(); i++) {
				CSVUtils.writeLine(writer, result.get(i));
				if (result.get(i).get(4) != 0) {
					totalCompletionTime += result.get(i).get(4);
					processCount++;
				}	
			}
			
			CSVUtils.writeLine(writer, Arrays.asList("Avg Completion Time", 
					Integer.toString(totalCompletionTime/processCount) ));
			
			writer.flush();
			writer.close();
			
		} catch (Exception ex) {
			System.out.println("Unable to write to csv file");
			ex.printStackTrace();
		}
	}

	private static Queue<List<Integer>> readFile(String testFileName) {

		Queue<List<Integer>> q = new LinkedList<>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream(testFileName));

			while (scanner.hasNextLine()) {
				List<Integer> tmpProcess = new ArrayList<>();
				tmpProcess.add(Integer.parseInt(scanner.nextLine()));
				tmpProcess.add(Integer.parseInt(scanner.nextLine()));
				tmpProcess.add(Integer.parseInt(scanner.nextLine()));

				q.add(tmpProcess);

			}

		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file");
		} finally {
			scanner.close();
		}

		return q;
	}
}

