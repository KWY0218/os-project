package team.os.scheduling;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import team.os.model.Process;
import team.os.model.CPU;
import team.os.model.Core;
import team.os.model.History;

public class SRTN  implements Scheduler{
	
	/*	순서
	 *
	 * 	1. 모든 프로세스가 종료할 때 까지 반복한다.
	 * 	2. 현재시간과 AT가 같은 프로세스들을 readyList 안에 넣는다.
	 * 	3. readyList 안에 있는 값을 remainBT 순으로 정렬한다.
	 * 	4. 일이 끝난 코어를 비운다.
	 * 	5. 코어가 남아있고 && readyList 안에 프로세스가 있다면 계속 readyList 맨 앞에 있는 프로세스에 코어를 할당한다.
	 *  6. 코어가 가득 차있고, readyList에 프로세스가 있으면 remainBT를 계산해서 선점을 진행한다.
	 * 	7. 선점이 끝난 후 readyList를 remainBT 순으로 정렬한다.	
	 * 	8. 코어를 할당받은 프로세스들을 일시킨다.
	 * 	9. 1초 동안 mProcessList와 mCoreList의 변경사항을 history에 넣는다.
	 * 	10. 1초를 증가한다.
	 * 	11. 코어 할당이 끝난 후 일하고 있는 코어의 누적 전력량을 갱신한다.
	 *  12. 총 BT와 전력량을 History 에 저장한다.
	 */
	@Override
	public History schedule(List<Process> mProcessList, List<Core> mCoreList) {
		History history = new History();
		int currentTime = 0;
		double totalAccConsumption= 0;
		List<Process> readyList = new LinkedList<>();
		
		// 1. 모든 프로세스가 끝날 때까지 반복
		while (!CPU.isTerminatedAllProcess(mProcessList)){
			// 2. 현재시간과 AT가 같은 프로세스들을 readyList 안에 넣는다.
			for(Process process:mProcessList) {
				if(process.getArrivalTime() == currentTime) readyList.add(process);
			}
			
			// 3. readyList 안에 있는 값을 remainBurstTime 순으로 정렬한다.
			readyList.sort(Comparator.comparing(Process::getRemainBurstTime));
			
			// 4. 일이 끝난 코어를 비운다.
			for(Process process:mProcessList) {
				if(process.isTerminated() &&process.getWorkingCoreIndex()!=-1) {
					// 4. 이 코어는 일을 다했다고 설정한다.
					mCoreList.get(process.getWorkingCoreIndex()).setWorking(false);
					
					// 4. 이 프로세스는 일을 다했다고 설정한다.
					process.setWorkingCoreIndex(-1);
				}
			}
			
			// 5. 코어가 남아있고 && readyList 안에 프로세스가 있다면 readyList 맨 앞에 있는 프로세스에 코어를 할당한다. 둘 중 하나가 false가 나올 때 까지 반복한다.
			while(CPU.getRecommendCore(mCoreList,CPU.priorityType, -1) != -1 && !readyList.isEmpty()) {
				Process process = readyList.get(0);

				// 5. 코어가 남아있으니 코어를 추천받는다.
				int coreIndex = CPU.getRecommendCore(mCoreList,CPU.priorityType, process.getBurstTime());
				
				// 5. 코어를 할당 받은 프로세스의 할당 받은 코어의 인덱스를 설정하고,  working 중인 것을 명시한다.
				process.setWorkingCoreIndex(coreIndex);
				mCoreList.get(coreIndex).setWorking(true);

				// 5. 코어를 할당 받은 readyList 맨 앞 요소를 제거한다.
				readyList.remove(0);
			}

			// 6. 코어가 가득 차있고, readyList에 프로세스가 있으면 remainBT를 계산해서 선점을 한다.
			while(!readyList.isEmpty()) {
				// 6. 코어 안에 있는 값 중 remainBT 가 가장 큰 프로세스를 찾는다.
				Process maxProcess = new Process(-1,-1,-1);
				
				for(Process process:mProcessList) {
					if(process.getWorkingCoreIndex() != -1) {
						if(maxProcess.getRemainBurstTime() < process.getRemainBurstTime())
							maxProcess = process;
					}
				}
				if(maxProcess.getpId()!=-1) {
				// 6. readyList 최상단에 있는 프로세스의 remainBT와 방금 코어에서 찾은 최대 프로세스의 remainBT를 비교한다.
				if(maxProcess.getRemainBurstTime()>readyList.get(0).getRemainBurstTime()) {
					// 6. 코어에서 찾은 프로세스의 BT가 더 크면, 해당 프로세스의 코어 인덱스를 readyList 최상단에 있는 프로세스에게 할당한다.
					readyList.get(0).setWorkingCoreIndex(maxProcess.getWorkingCoreIndex());
					
					// 6. mProcessList 에도 값을 설정한다.
					readyList.get(0).setWorkingCoreIndex(maxProcess.getWorkingCoreIndex());
					
					// 6. maxProcess 의 코어 값을 -1로 설정한다.
					maxProcess.setWorkingCoreIndex(-1);
					
					// 6. mProcessList 에도 코어를 나오는 프로세스의 정보르 수정한다.   
					maxProcess.setWorkingCoreIndex(-1);
					
					// 6. maxProcess 를 readyList안에 넣는다.
					readyList.add(maxProcess);
					
					// 6. 코어로 들어간 프로세스의 정보를 readyList 에서 없앤다.
					readyList.remove(0);	
				} else break;
				}
			}
			
			// 7. 다시 readyList를 remainBT 순으로 정렬한다.
			readyList.sort(Comparator.comparing(Process::getRemainBurstTime));
			
			for(Process process:readyList) process.setWaitingTime(process.getWaitingTime()+1);
			// 10. 1초 시간을 증가한다.
			currentTime++;
			
			// 8. 코어를 할당받은 프로세스들을 일시킨다.
			for(Process process:mProcessList) {
				// 8. 코어를 할당받은 프로세스들은 coreIndex가 있으므로 -1이 아닌 것은 할당받은 프로세스이다.
				if(process.getWorkingCoreIndex() != -1) {
					
					// 8. 프로세스의 remainBT 를 할당 받은 코어의 power 만큼 줄인다.
					process.setRemainBurstTime(process.getRemainBurstTime()-mCoreList.get(process.getWorkingCoreIndex()).getPower());
					
					// 8. 만약 remainBT 가 0 이하가 되면 프로세스와 코어가 일을 다했다고 설정한다. 
					if(process.getRemainBurstTime()<=0) {
						// 8. 프로세스가 종료 됐음을 명시하고, 일이 끝난 프로세스의 TT, WT, NTT를 설정한다.
						process.setTerminated(true);
						process.setTurnAroundTime(currentTime-process.getArrivalTime());
						process.setNormalizedTT((double) process.getTurnAroundTime()/process.getBurstTime());
					}
				}
			}
			// 9. 1초 동안의 변화를 보낸다.
			history.addPage(mProcessList, readyList);
			
			// 11. 코어 할당이 끝난 후 일하고 있는 코어의 누적 전력량을 갱신한다.
			totalAccConsumption += CPU.getPowerConsumptionOfCoreList(mCoreList);
			totalAccConsumption += CPU.getStandbyPowerOfCoreList(mCoreList);
		}
		
		// 12. 총 BT와 전력량을 History 에 저장한다.
		history.setTotalBurstTime(currentTime);
		history.setTotalPowerConsumption(totalAccConsumption);
		return history;
	}

}
