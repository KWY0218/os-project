package team.os.test;

import java.util.LinkedList;
import java.util.List;

import team.os.model.Core;
import team.os.model.ECore;
import team.os.model.History;
import team.os.model.PCore;
import team.os.model.Process;
import team.os.scheduling.HRRN;

public class TestHRRN {

	public static void main(String[] args) {
		List<Process> processList = new LinkedList<>();
		List<Core> coreList = new LinkedList<Core>();
		// test1
//		processList.add(new Process(0,0,3));
//		processList.add(new Process(1,1,7));
//		processList.add(new Process(2,3,2));
//		processList.add(new Process(3,5,5));
//		processList.add(new Process(4,6,3));
//		coreList.add(new ECore());
		
		// test 2
		processList.add(new Process(0,0,3));
		processList.add(new Process(1,0,7));
		processList.add(new Process(2,3,2));
		processList.add(new Process(3,3,5));
		processList.add(new Process(4,6,3));
		processList.add(new Process(5,6,3));
		processList.add(new Process(6,8,7));
		processList.add(new Process(7,8,2));
		processList.add(new Process(8,9,5));
		processList.add(new Process(9,9,3));
		coreList.add(new ECore());
		coreList.add(new PCore());
	
		History history = new HRRN().schedule(processList, coreList);
		
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
