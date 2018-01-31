package cs431p1;

import java.util.*;

public class SJF {

	private int swapTime;
	private Queue<List<Integer>> processQ;
	private int CPUTime; 
	
	public SJF(Queue<List<Integer>> processesInfo, int switchTime) {
		CPUTime = 0;
		processQ = new LinkedList<>(processesInfo);
		processQ = reorderQ(processQ);
		swapTime = switchTime;
	}

	public List<List<Integer>> runProcesses() {

		List<List<Integer>> result = new ArrayList<>();
		
		System.out.println("********************************8SJF");
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
	
	private PriorityQueue<List<Integer>> reorderQ(Queue<List<Integer>> processesInfo) {
		
		Comparator<List<Integer>> comparator = new burstTimeComparator();
		
		PriorityQueue<List<Integer>> PQ  = new PriorityQueue<>(20, comparator);
		
		while (!processesInfo.isEmpty()) {
			//PQ.addAll(processesInfo);
			PQ.add(processesInfo.poll());
		}
		
		return PQ;
	}
	
	private class burstTimeComparator implements Comparator<List<Integer>> {

		@Override
		public int compare(List<Integer> o1, List<Integer> o2) {
			if (o1.get(1) < o2.get(1)) {
				return -1;
			}
			if (o1.get(1) > o2.get(1)) {
				return 1;
			}
			
			return 0;
		}	
	}
}

