package team.os.test;

import java.util.LinkedList;
import java.util.List;

import team.os.model.Core;
import team.os.model.ECore;
import team.os.model.History;
import team.os.model.PCore;
import team.os.model.Process;
import team.os.scheduling.SRTN;

public class TestSRTN {

	public static void main(String[] args) {
		List<Process> processList = new LinkedList<>();
		processList.add(new Process(0,0,3));
		processList.add(new Process(1,0,7));
		processList.add(new Process(2,0,7));
		processList.add(new Process(3,0,5));
		processList.add(new Process(4,0,7));
		processList.add(new Process(5,0,7));
		processList.add(new Process(6,0,9));
		processList.add(new Process(7,0,7));
		processList.add(new Process(8,2,3));
		processList.add(new Process(9,2,3));
		processList.add(new Process(10,2,3));
		processList.add(new Process(11,2,3));
		
		List<Core> coreList = new LinkedList<Core>();
		coreList.add(new ECore());
		coreList.add(new PCore());
		
		History history = new SRTN().schedule(processList, coreList);
		
		System.out.println("-------- History --------");

		System.out.print("       ");

		for(int historyIndex = 0; historyIndex < history.getHistory().size(); historyIndex++)

			System.out.printf("%4d ", historyIndex);

		System.out.println();

		for(int coreIndex = 0; coreIndex < coreList.size(); coreIndex++) {

			System.out.printf("Core%2d", coreIndex + 1);

			for(List<Process> pl : history.getHistory()) {

				boolean worked = false;
				
				for(Process p : pl)

					if(p.getWorkingCoreIndex() == coreIndex) {

						System.out.printf("%5d", p.getpId());

						worked = true;
						
					}
				
				if(!worked)
					
					System.out.printf("     ");
				
			}

			System.out.println();

		}


		System.out.println("History.getTotalBurstTime(): " + history.getTotalBurstTime());

	}

}
