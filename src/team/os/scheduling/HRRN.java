package team.os.scheduling;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import team.os.model.CPU;
import team.os.model.Core;
import team.os.model.History;
import team.os.model.Process;

public class HRRN implements Scheduler{
	
	/*	순서
	 *
	 * 	1. 모든 프로세스가 종료할 때 까지 반복한다.
	 * 	2. 현재시간과 AT가 같은 프로세스들을 readyList 안에 넣는다.
	 * 	3. readyList 안에 있는 값을 age 순으로 정렬한다.
	 * 	4. 코어가 남아있고 && readyList 안에 프로세스가 있다면 계속 readyList 맨 앞에 있는 프로세스에 코어를 할당한다.
	 * 	5. 코어 할당이 끝난 후 일하고 있는 코어의 누적 전력량을 갱신한다.
	 * 	6. readyList 내에 있는 프로세스들의 age를 갱신한다.
	 * 	7. 코어를 할당받은 프로세스들을 일시킨다.
	 * 	8. 1초 동안 mProcessList와 mCoreList의 변경사항을 history에 넣는다.
	 * 	9. 1초를 증가한다.
	 * 	10. 프로세스 동작이 끝난 후 총 전력량과 소비시간을 history에 넣는다.
	 */
	@Override
	public History schedule(List<Process> mProcessList, List<Core> mCoreList) {
		History history = new History();
		int currentTime = 0;
		double totalAccConsumption= 0;
		List<Process> readyList = new LinkedList<>();
		
		while (!CPU.isTerminatedAllProcess(mProcessList)){
			// 2. 현재시간과 AT가 같은 프로세스들을 readyList 안에 넣는다.
			for(Process process:mProcessList) {
				if(process.getArrivalTime() == currentTime) readyList.add(process);
			}
			
			// 3. readyList 안에 있는 값을 age 순으로 정렬한다.
			readyList.sort(Comparator.comparing(Process::getAge));
			
			// 4. 코어가 남아있고 && readyList 안에 프로세스가 있다면 readyList 맨 앞에 있는 프로세스에 코어를 할당한다. 둘 중 하나가 false가 나올 때 까지 반복한다.
			while(CPU.getRecommendCore(mCoreList) != -1 && !readyList.isEmpty()) {
				// 4. 코어가 남아있으니 코어를 추천받는다.
				int coreIndex = CPU.getRecommendCore(mCoreList);
				
				// 4. readyList 에서 BT가 가장 짧은 프로세스 
				Process currentProcess = readyList.get(0);
				
				// 4. 코어를 할당 받은 프로세스의 할당 받은 코어의 인덱스를 설정하고, waitingTime을 설정하고, working 중인 것을 명시한다. 
				mProcessList.get(currentProcess.getpId()).setWorkingCoreIndex(coreIndex);
				mProcessList.get(currentProcess.getpId()).setWaitingTime(currentTime-mProcessList.get(currentProcess.getpId()).getArrivalTime());
				mCoreList.get(coreIndex).setWorking(true);
				
				// 4. 코어를 할당 받은 readyList 맨 앞 요소를 제거한다.
				readyList.remove(0);
			}
			
			// 5. 코어 할당이 끝난 후 일하고 있는 코어의 누적 전력량을 갱신한다. cpu 에서 새로 만든 함수임
			totalAccConsumption += CPU.getPowerConsumptionOfCoreList(mCoreList);
			totalAccConsumption += CPU.getStandbyPowerOfCoreList(mCoreList);
			
			// 6. readyList에서 대기중인 프로세스들의 age를 증가한다. 
			for(Process process:readyList) process.setAssignWaitingTime();
			
			// 7. 코어를 할당받은 프로세스들을 일시킨다.
			for(Process process:mProcessList) {
				// 7. 코어를 할당받은 프로세스들은 coreIndex가 있으므로 -1이 아닌 것은 할당받은 프로세스이다.
				if(process.getWorkingCoreIndex() != -1) {
					
					// 7. 프로세스의 remainBT 를 할당 받은 코어의 power 만큼 줄인다.
					process.setRemainBurstTime(process.getRemainBurstTime()-mCoreList.get(process.getWorkingCoreIndex()).getPower());
					
					// 7. 만약 remainBT 가 0 이하가 되면 프로세스와 코어가 일을 다했다고 설정한다. 
					if(process.getRemainBurstTime()<=0) {
						// 7. 이 코어는 일을 다했다고 설정한다.
						mCoreList.get(process.getWorkingCoreIndex()).setWorking(false);
						
						// 7. 이 프로세스는 일을 다했다고 설정한다.
						process.setWorkingCoreIndex(-1);
						
						// 7. 프로세스가 종료 됐음을 명시하고, 일이 끝난 프로세스의 TT, NTT를 설정한다.
						process.setTerminated(true);
						process.setTurnAroundTime(currentTime-process.getArrivalTime());
						process.setNormalizedTT(process.getTurnAroundTime()/process.getBurstTime());
					}
				}
			}
			// 8. 1초 동안의 변화를 보낸다.
			history.addPage(mProcessList, readyList);
			
			// 9. 1초 시간을 증가한다.
			currentTime++;
		}
		// 10. 프로세스 동작이 끝난 후 총 전력량과 소비시간을 history에 넣는다.
		history.setTotalBurstTime(currentTime);
		history.setTotalPowerConsumption(totalAccConsumption);
		return history;
	}

}
