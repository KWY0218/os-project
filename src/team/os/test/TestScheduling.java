package team.os.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import team.os.model.CPU;
import team.os.model.Core;
import team.os.model.ECore;
import team.os.model.History;
import team.os.model.PCore;
import team.os.model.Process;
import team.os.scheduling.*;

public class TestScheduling {

	public static void main(String[] args) {

		for(int j = 0; j < 1000; j++) {

			// 프로세스 리스트를 생성한다.
			List<Process> processList = new ArrayList<Process>();

			for(int i = 0; i < 15; i++)

				processList.add(new Process(processList.size() + 1, new Random().nextInt(15), new Random().nextInt(5) + 1));

			// 프로세스 리스트를 정렬한다.
			processList.sort(Comparator.naturalOrder());

			// 코어 리스트를 생성한다.
			List<Core> coreList = new LinkedList<Core>();

			for(int i = 0; i < CPU.MAX_CORE_SIZE; i++)

				// case 2는 disable
				switch(new Random().nextInt(3)) {

				case 0: coreList.add(new ECore()); break;
				case 1: coreList.add(new PCore()); break;

				}

			if(coreList.isEmpty())

				coreList.add(new ECore());

			// 예제 프로세스 및 코어
//			processList.clear();
//			processList.add(new Process(1, 0, 3));
//			processList.add(new Process(2, 1, 7));
//			processList.add(new Process(3, 3, 2));
//			processList.add(new Process(4, 5, 5));
//			processList.add(new Process(5, 6, 3));
//
//			coreList.clear();
//			coreList.add(new ECore());
//			coreList.add(new PCore());

			// 프로세스 및 코어 리스트를 출력한다.
			History history = new SRTN().schedule(processList, coreList);

			System.out.println("-- Core");

			for(Core core : coreList)

				System.out.printf("Core %d\t%s\n", coreList.indexOf(core) + 1, core.getClass().getSimpleName());

			System.out.println("-- Process State");

			for(Process process : processList)

				System.out.printf("P%02d\t%2d\t%2d\t%2d\t%2d\t%4.1f\n", process.getpId(), process.getArrivalTime(), process.getBurstTime(), process.getWaitingTime(), process.getTurnAroundTime(), process.getNormalizedTT());

			// 히스토리 테스트
			System.out.println("-- Gantt");

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

			System.out.println("-- Total");
			System.out.printf("History.getTotalBurstTime(): %d\n", history.getTotalBurstTime());
			System.out.printf("History.getTotalPowerConsumption(): %.1f\n", history.getTotalPoewrConsumption());

		}

	}

}
