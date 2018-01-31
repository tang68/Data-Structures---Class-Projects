package cs431p1;

import java.util.*;

import javax.xml.bind.PrintConversionEvent;

public class RoundRobin {
	
	private int swapTime;
	private Queue<List<Integer>> processQ;
	private int CPUTime; 
	private int quantum;

	public RoundRobin(Queue<List<Integer>> processesInfo, int switchTime, int quantumTime) {
		CPUTime = 0;
		processQ = new LinkedList<>(processesInfo);
		swapTime = switchTime;
		quantum = quantumTime;
	}

	public List<List<Integer>> runProcesses() {
		
		List<List<Integer>> result = new ArrayList<>();
		
		System.out.println("****************************RR");
		while(!processQ.isEmpty()) {
			
			List<Integer> tmpResult = new ArrayList<>();
			List<Integer> currentProcess = processQ.peek();
			int PID = currentProcess.get(0);
			int SBT = currentProcess.get(1);
			int EBT = SBT - quantum;
			int CT = 0;
			
			if (EBT < 0) EBT = 0;
			if (EBT == 0) CT = CPUTime + SBT;
			
			tmpResult.add(CPUTime);
			tmpResult.add(PID);
			tmpResult.add(SBT);
			tmpResult.add(EBT);
			tmpResult.add(CT);
			
			if (EBT != 0) {
				CPUTime += (processQ.size() == 1) ? quantum : (quantum + swapTime); 
				processQ.poll();
				processQ.add(Arrays.asList(PID, EBT, currentProcess.get(2)));
			}
				
			else {
				CPUTime += (processQ.size() == 1) ? (SBT - EBT) : (swapTime + SBT - EBT);
				processQ.poll();
			}
			
			
			System.out.println(tmpResult);
			result.add(tmpResult);
		}
		
		
		return result;
	}

}
