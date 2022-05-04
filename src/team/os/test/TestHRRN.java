package team.os.test;

import java.util.LinkedList;
import java.util.List;

import team.os.model.Core;
import team.os.model.ECore;
import team.os.model.PCore;
import team.os.model.Process;
import team.os.scheduling.HRRN;

public class TestHRRN {

	public static void main(String[] args) {
		List<Process> processList = new LinkedList<>();
		processList.add(new Process(0,0,3));
		processList.add(new Process(1,1,7));
		processList.add(new Process(2,3,2));
		processList.add(new Process(3,5,5));
		processList.add(new Process(4,6,3));
		
		List<Core> coreList = new LinkedList<Core>();
		coreList.add(new ECore());
	
		new HRRN().schedule(processList, coreList);
	}

}
