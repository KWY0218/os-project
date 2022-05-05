package team.os.scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import team.os.model.CPU;
import team.os.model.Core;
import team.os.model.ECore;
import team.os.model.History;
import team.os.model.PCore;
import team.os.model.Process;

public class OWN implements Scheduler{

	public History schedule(List<Process> processList, List<Core> coreList) {

		double totalPowerConsumption = 0;
		int totalBurstTime = 0;

		// 히스토리를 생성한다.
		History history = new History();

		// Ready Queue를 준비한다.
		Queue<Process> readyQueue = new LinkedList<Process>();

		// 모든 프로세스가 끝나지 않았다면
		while(!CPU.isTerminatedAllProcess(processList)) {

			// Arrival Time이 도래한 프로세스를 Ready Queue에 추가한다.
			for(Process process : processList)

				if(process.getArrivalTime() == totalBurstTime)

					readyQueue.offer(process);

			// 수행 시간을 1초 증가한다.
			System.out.printf("Total Burst Time: %d\n", totalBurstTime++);

			// 큐가 변하기 때문에 크기를 미리 저장한다.
			int processQueueSize = readyQueue.size();

			System.out.print("Queue: {");

			for(Process process : readyQueue)

				System.out.printf("P%d, ", process.getpId());

			System.out.println("}");

			// 종료된 프로세스에 할당된 코어들을 휴식시킨다.
			for(Process process : processList)

				if(process.isTerminated() && process.getWorkingCoreIndex() != -1) {

					coreList.get(process.getWorkingCoreIndex()).setWorking(false);
					System.out.printf("Core %d is relaxing.\n", process.getWorkingCoreIndex());
					process.setWorkingCoreIndex(-1);

				}

			// timeQuantum = (Total BurstTime of Processes in ReadyQueue) / (Size of ReadyQueue)
			int timeQuantum = 0;

			// Time Quantum을 설정한다.
			for(Process tProcess : readyQueue)

				timeQuantum += tProcess.getRemainBurstTime();

			timeQuantum /= readyQueue.size();

			System.out.printf("timeQuantum = %d\n", timeQuantum);

			// 큐에 저장된 만큼 반복한다.
			for(int processIndex = 0; processIndex < processQueueSize; processIndex++) {

				// 큐에서 프로세스를 선택한다.
				Process process = readyQueue.poll();

				// 코어 인덱스를 초기화한다.
				int coreIndex = -1;

				// 프로세스에 할당된 코어가 없다면
				if((coreIndex = process.getWorkingCoreIndex()) == -1)

					// 코어를 추천받는다.
					coreIndex = CPU.getRecommendCore(coreList);

				// 사용 가능한 코어가 없으면 프로세스를 큐에 입력하고 컨티뉴한다.
				if(coreIndex == -1) {

					readyQueue.offer(process);
					continue;

				}

				// 코어를 할당하고 작업을 시작한다.
				Core core = coreList.get(coreIndex);
				core.setWorking(true);

				// 프로세스에 코어 인덱스를 입력한다.
				process.setWorkingCoreIndex(coreIndex);
				System.out.printf("Process P%d have Core %d.\n", process.getpId(), coreIndex);

				System.out.printf("P%d -> %d - %d = ", process.getpId(), process.getRemainBurstTime(), core.getPower());

				// 프로세스의 남은 작업 시간을 코어의 파워만큼 감소한다.
				process.setRemainBurstTime(process.getRemainBurstTime() - core.getPower());

				System.out.println(process.getRemainBurstTime());

				// 타임 퀀텀만큼 일을 했다면 일을 멈추고 큐의 맨 뒤로 보낸다.
				if(timeQuantum <= process.getWorkingTimeOfTurn()) {

					// continue;
					// System.out.println(process.getpId() + "가 타임 퀀텀 이상의 작업을 함.");
					// process.setWorkingTimeOfTurn(0);
					
				}

				// 이번 큐에서 일한 만큼 기록한다.
				process.setWorkingTimeOfTurn(process.getWorkingTimeOfTurn() + core.getPower());

				System.out.println(process.getWorkingTimeOfTurn());

				// 프로세스의 남은 작업시간이 0 이하라면
				if(process.getRemainBurstTime() <= 0) {

					// 시간 정보를 기록한다.
					process.setTurnAroundTime(totalBurstTime - process.getArrivalTime());
					process.setWaitingTime(process.getTurnAroundTime() - process.getBurstTime());
					process.setNormalizedTT((double) process.getTurnAroundTime() / process.getBurstTime());

					// 프로세스를 종료한다. 
					process.setTerminated(true);
					process.setTurnAroundTime(totalBurstTime - process.getArrivalTime());
					process.setWaitingTime(process.getTurnAroundTime() - process.getBurstTime());
					process.setNormalizedTT((double) process.getTurnAroundTime() / process.getBurstTime());

					// 프로세스를 종료한다. 
					process.setTerminated(true);

					System.out.printf("P%d is terminated.\n", process.getpId());

				}

				// 프로세스의 남은 작업시간이 1 이상이라면
				else {

					// 프로세스를 큐에 삽입한다.
					readyQueue.offer(process);

					System.out.printf("P%d is offered.\n", process.getpId());

				}

			}

			// 총 소비전력에 소비전력을 증가한다.
			totalPowerConsumption += CPU.getPowerConsumptionOfCoreList(coreList);

			// 총 소비전력에 대기전력을 증가한다.
			totalPowerConsumption += CPU.getStandbyPowerOfCoreList(coreList);

			System.out.println();

			// 히스토리를 추가한다.
			history.addPage(processList, readyQueue);

		}

		System.out.printf("Total Burst Time: %d\n", totalBurstTime);
		System.out.printf("Total Power Consumption: %.1f\n\n", totalPowerConsumption);

		// 히스토리에 시간 및 전력정보를 대입한다.
		history.setTotalBurstTime(totalBurstTime);
		history.setTotalPowerConsumption(totalPowerConsumption);

		return history;

	}

	public static void main(String[] args) {

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

		// 예제 프로세스 및 코어
		processList.clear();
		processList.add(new Process(1, 0, 3));
		processList.add(new Process(2, 1, 7));
		processList.add(new Process(3, 3, 2));
		processList.add(new Process(4, 5, 5));
		processList.add(new Process(5, 6, 3));

		coreList.clear();
		coreList.add(new ECore());

		// 프로세스 및 코어 리스트를 출력한다.
		History history = new OWN().schedule(processList, coreList);

		System.out.println("-------- Core --------");

		for(Core core : coreList)

			System.out.printf("%s\n", core.getClass().getName());

		System.out.println("-------- Process --------");

		for(Process process : processList)

			System.out.printf("P%02d\t%2d\t%2d\n", process.getpId(), process.getArrivalTime(), process.getBurstTime());

		// 히스토리 테스트
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
