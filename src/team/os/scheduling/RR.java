package team.os.scheduling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import team.os.model.CPU;
import team.os.model.Core;
import team.os.model.History;
import team.os.model.Process;

public class RR implements Scheduler {

	public History schedule(List<Process> processList, List<Core> coreList) {

		double totalPowerConsumption = 0;
		int totalBurstTime = 0;

		// 히스토리를 생성한다.
		History history = new History();

		// Ready Queue를 준비한다.
		Queue<Process> readyQueue = new LinkedList<Process>();

		// Round Robin을 위한 Queue를 준비한다.
		Queue<Process> roundRobinQueue = new LinkedList<Process>();

		// 모든 프로세스가 끝나지 않았다면
		while(!CPU.isTerminatedAllProcess(processList)) {

			// Arrival Time이 도래한 프로세스를 Ready Queue에 추가한다.
			for(Process process : processList)

				if(process.getArrivalTime() == totalBurstTime)

					readyQueue.offer(process);

			// 수행 시간을 1초 증가한다.
			System.out.printf("Total Burst Time: %d\n", totalBurstTime++);

			// 라운드 로빈 큐에 프로세스가 있다면 Ready Queue의 맨 뒤에 삽입한다.
			if(!roundRobinQueue.isEmpty()) {

				Queue<Process> tQueue = new LinkedList<Process>();

				while(!readyQueue.isEmpty())

					tQueue.offer(readyQueue.poll());

				while(!tQueue.isEmpty())

					readyQueue.offer(tQueue.poll());

				while(!roundRobinQueue.isEmpty()) {

					Process process = roundRobinQueue.poll();
					coreList.get(process.getWorkingCoreIndex()).setWorking(false);
					process.setWorkingCoreIndex(-1);
					process.setWorkingTimeOfTurn(0);
					readyQueue.offer(process);

				}

			}

			System.out.print("ReadyQueue: {");

			for(Process process : readyQueue)

				System.out.printf("P%d, ", process.getpId());

			System.out.println("}");

			// 큐가 변하기 때문에 크기를 미리 저장한다.
			int processQueueSize = readyQueue.size();

			// 종료된 프로세스에 할당된 코어들을 휴식시킨다.
			for(Process process : processList)

				if(process.isTerminated() && process.getWorkingCoreIndex() != -1) {

					coreList.get(process.getWorkingCoreIndex()).setWorking(false);
					System.out.printf("Core %d is relaxing.\n", process.getWorkingCoreIndex());
					process.setWorkingCoreIndex(-1);

				}

			// 큐에 저장된 만큼 반복한다.
			for(int processIndex = 0; processIndex < processQueueSize; processIndex++) {

				// 큐에서 프로세스를 선택한다.
				Process process = readyQueue.poll();

				// 코어 인덱스를 초기화한다.
				int coreIndex = -1;

				// 프로세스에 할당된 코어가 없다면
				if((coreIndex = process.getWorkingCoreIndex()) == -1)

					// 코어를 추천받는다.
					coreIndex = CPU.getRecommendCore(coreList, CPU.priorityType, process.getBurstTime());

				// 사용 가능한 코어가 없으면 프로세스를 큐에 입력하고 컨티뉴한다.
				if(coreIndex == -1) {

					// 코어가 할당되지 않은 프로세스는 Waiting Time을 1증가한다.
					process.setWaitingTime(process.getWaitingTime() + 1);
					
					readyQueue.offer(process);
					continue;

				}

				// 코어를 할당하고 작업을 시작한다.
				Core core = coreList.get(coreIndex);
				core.setWorking(true);

				// 프로세스에 코어 인덱스를 입력한다.
				process.setWorkingCoreIndex(coreIndex);
				System.out.printf("Process P%d have Core %d.\n", process.getpId(), coreIndex);

				System.out.printf("P%d -> %d - %d = ", process.getpId(), process.getRemainBurstTime(), core.getPower(), Math.max(0, process.getRemainBurstTime() - core.getPower()));

				// 프로세스의 남은 작업 시간을 코어의 파워만큼 감소한다.
				process.setRemainBurstTime(process.getRemainBurstTime() - core.getPower());

				// 이번 큐에서 일한 만큼 기록한다.
				process.setWorkingTimeOfTurn(process.getWorkingTimeOfTurn() + 1);

				// 프로세스의 남은 작업시간이 0 이하라면
				if(process.getRemainBurstTime() <= 0) {

					// 프로세스를 종료한다. 
					process.setTerminated(true);
					process.setTurnAroundTime(totalBurstTime - process.getArrivalTime());
					process.setNormalizedTT((double) process.getTurnAroundTime() / process.getBurstTime());

					// 프로세스를 종료한다. 
					process.setTerminated(true);

					System.out.printf("P%d is terminated.\n", process.getpId());

				}

				// 프로세스의 남은 작업시간이 1 이상이라면
				else {

					// 타임 퀀텀만큼 일을 했다면 일을 멈추고 Round-Robin Queue에 삽입한다.
					if(CPU.timeQuantum <= process.getWorkingTimeOfTurn()) {

						roundRobinQueue.offer(process);

						System.out.printf("P%d is offered into Round-Robin Queue.\n", process.getpId());

					} else {

						// 프로세스를 Ready Queue에 삽입한다.
						readyQueue.offer(process);

						System.out.printf("P%d is offered.\n", process.getpId());

					}

				}

			}

			// 총 소비전력에 소비전력을 증가한다.
			totalPowerConsumption += CPU.getPowerConsumptionOfCoreList(coreList);

			// 총 소비전력에 대기전력을 증가한다.
			totalPowerConsumption += CPU.getStandbyPowerOfCoreList(coreList);

			// 히스토리를 추가한다.
			history.addPage(processList, new ArrayList<Process>(readyQueue));

			System.out.println();

		}

		// 히스토리에 시간 및 전력정보를 대입한다.
		history.setTotalBurstTime(totalBurstTime);
		history.setTotalPowerConsumption(totalPowerConsumption);

		return history;

	}

}