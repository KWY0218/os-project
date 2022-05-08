package team.os.model;

import java.util.List;

import team.os.enums.PriorityType;

public class CPU {

	public static final int MAX_CORE_SIZE = 4;
	public static int timeQuantum = 3;
	public static PriorityType priorityType = PriorityType.POWER;

	/**
	 * @param coreList
	 * @return Power consumption of coreList
	 */

	public static int getPowerConsumptionOfCoreList(List<Core> coreList) {

		int powerConsumption = 0;

		for(Core core : coreList)

			if(core.isWorking())

				powerConsumption += core.getPowerConsumption();

		return powerConsumption;
	}

	/**
	 * @param coreList
	 * @return Standby power of coreList
	 */

	public static double getStandbyPowerOfCoreList(List<Core> coreList) {

		double standbyPower = 0;

		for(Core core : coreList)

			if(!core.isWorking())

				standbyPower += 0.1;

		return standbyPower;

	}
	
	/**
	 * @param coreList
	 * @param schedulingType
	 * @return Index of Recommended Core in CoreList
	 */

	public static int getRecommendCore(List<Core> coreList, PriorityType schedulingType, int burstTime) {

		int locationOfFirst = -1;
		int locationOfPCore = -1;
		int locationOfECore = -1;
		int location = -1;

		for(int coreIndex = 0; coreIndex < coreList.size(); coreIndex++) {

			Core core = coreList.get(coreIndex);

			// 코어가 사용중이 아니라면
			if(!core.isWorking()) {

				// 먼저 발견된 코어의 위치를 저장한다.
				if(locationOfFirst == -1)

					locationOfFirst = coreIndex;

				// 
				if(core instanceof PCore && locationOfPCore == -1)

					locationOfPCore = coreIndex;

				else if(core instanceof ECore && locationOfECore == -1)

					locationOfECore = coreIndex;

			}

		}

		if(burstTime >= 4 && locationOfPCore != -1)
			location = locationOfPCore;

		else if(burstTime < 4 && locationOfECore != -1)
			location = locationOfECore;

		else
			location = locationOfFirst;

		return location;

	}

	/**
	 * @param processQueue
	 * @return All of process in process queue were terminated
	 */

	public static boolean isTerminatedAllProcess(List<Process> processQueue) {

		for(Process process : processQueue)

			if(!process.isTerminated())

				return false;

		return true;

	}
	
}
