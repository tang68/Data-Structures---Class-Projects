package cs431p1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Lottery {

	private int swapTime;
	private List<List<Integer>> processQ;
	private int CPUTime;
	private int quantum;
	private int sumPriority;
	
	public Lottery(Queue<List<Integer>> processesInfo, int switchTime, int quantumTime) {
		CPUTime = 0;
		processQ = new LinkedList<>(processesInfo);
		swapTime = switchTime;
		quantum = quantumTime;
		sumPriority = 0;
	}

	public List<List<Integer>> runProcesses() {

		System.out.println("******************************* Lottery");
		Random random = new Random();
		
		List<List<Integer>> result = new ArrayList<>();
		sumPriority = getSumPriority();
		int previousPID = -1;
		
		while (processQ.size() != 0) {

			List<Integer> tmpResult = new ArrayList<>();
			int rand = random.nextInt(sumPriority) + 1;
			int executeProcessIndex = getProcessIndex(rand);
			int PID = processQ.get(executeProcessIndex).get(0);
			int SBT = processQ.get(executeProcessIndex).get(1);
			int EBT = SBT - quantum;
			int CT = 0;
			
			if (EBT < 0)
				EBT = 0;
			if (EBT == 0)
				CT = CPUTime + SBT;

			if (EBT != 0) {
				CPUTime += (previousPID == PID) ? quantum : quantum + swapTime;
				processQ.get(executeProcessIndex).set(1, EBT);
			}
				
			else {
				CPUTime += (previousPID == PID) ? SBT - EBT : swapTime + SBT - EBT;
				sumPriority -= processQ.get(executeProcessIndex).get(2);
				processQ.remove(executeProcessIndex);
			}
			
			tmpResult.add(CPUTime);
			tmpResult.add(PID);
			tmpResult.add(SBT);
			tmpResult.add(EBT);
			tmpResult.add(CT);
			
			previousPID = PID;
			System.out.println(tmpResult);
			result.add(tmpResult);
	
		}

		return result;
	}
	
	private int getSumPriority() {
		
		int result = 0;
		
		for (int i = 0; i < processQ.size(); i++) 
			result += processQ.get(i).get(2);
		return result;
	}
	
	private int getProcessIndex(int rand) {
		
		int sum = processQ.get(0).get(2);
		
		if (sum >= rand)
			return 0;
		
		for (int i = 1; i < processQ.size(); i++) {

			sum += processQ.get(i).get(2);
			
			if (sum >= rand)
				return i;
		}
		
		return -1;
	}
}










