package team.os.test;

import java.util.LinkedList;
import java.util.List;

import team.os.model.Core;
import team.os.model.ECore;
import team.os.model.PCore;
import team.os.model.Process;
import team.os.scheduling.SPN;

public class TestSPN {

	/*
	 *	시간은 1초부터 넣어야함, 0 넣으면 바로 에러 뜸 ㅠㅠ
	 */
	public static void main(String[] args) {
		List<Process> processList = new LinkedList<>();
		processList.add(new Process(0,1,4));
		processList.add(new Process(1,1,2));
		processList.add(new Process(2,2,6));
		processList.add(new Process(3,2,1));
		processList.add(new Process(4,3,7));
		processList.add(new Process(5,3,2));
		processList.add(new Process(6,4,1));
		processList.add(new Process(7,4,9));
		
		List<Core> coreList = new LinkedList<Core>();
		coreList.add(new PCore());
		coreList.add(new PCore());
		coreList.add(new ECore());
		coreList.add(new ECore());
	
		new SPN().schedule(processList, coreList);
	}

}
