package cs431p1;

import java.util.*;

public class FCFS {

	private int swapTime;
	private Queue<List<Integer>> processQ;
	private int CPUTime; 

	public FCFS(Queue<List<Integer>> processesInfo, int switchTime) {
		CPUTime = 0;
		processQ = new LinkedList<>(processesInfo);
		swapTime = switchTime;
	}
	
	public List<List<Integer>> runProcesses() {
		
		List<List<Integer>> result = new ArrayList<>();

		System.out.println("*********************************8FCFS");
		while (processQ.peek() != null) {
			
			List<Integer> tmpResult = new ArrayList<>();
			List<Integer> currentProcess = processQ.poll();
			int PID = currentProcess.get(0);
			int SBT = currentProcess.get(1);
			
			tmpResult.add(CPUTime);
			tmpResult.add(PID);
			tmpResult.add(SBT);
			tmpResult.add(0);
			tmpResult.add(CPUTime + SBT);
			
			CPUTime += (SBT + swapTime);
			
			result.add(tmpResult);
			
			
			System.out.println(tmpResult);
		}
		
		return result;
	}
}
