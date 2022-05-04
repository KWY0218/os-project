package team.os.model;

public class Process implements Comparable<Object> {
	
	private int pId;
	private int arrivalTime;
	private int burstTime;
	private int remainBurstTime;
	private int waitingTime = 0;
	private int turnAroundTime = 0;
	private double normalizedTT = 0;
	private int workingCoreIndex = -1;
	private boolean terminated = false;
	
	/* 라운드 로빈 적용 시, 현재 차례에서 일한 시간 */
	private int workingTimeOfTurn = 0;
	
	/* HRRN 때 사용 
	 * age : assignWaitingTime/burstTime 
	 * assignWaitingTime : readyList 안에 있는 시간
	 */
	private double age = 0;
	private int assignWaitingTime = 0;
	
	public Process(int pId, int arrivalTime, int burstTime){
		this.pId = pId;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.remainBurstTime = burstTime;
	}
	
	public int getWorkingTimeOfTurn() {
		return workingTimeOfTurn;
	}

	public void setWorkingTimeOfTurn(int workingTimeOfTurn) {
		this.workingTimeOfTurn = workingTimeOfTurn;
	}

	public int getRemainBurstTime() {
		return remainBurstTime;
	}

	public void setRemainBurstTime(int remainBurstTime) {
		this.remainBurstTime = remainBurstTime;
	}
	
	public boolean isTerminated() {
		return terminated;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getTurnAroundTime() {
		return turnAroundTime;
	}

	public void setTurnAroundTime(int turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}

	public double getNormalizedTT() {
		return normalizedTT;
	}

	public void setNormalizedTT(double normalizedTT) {
		this.normalizedTT = normalizedTT;
	}

	public int getWorkingCoreIndex() {
		return workingCoreIndex;
	}

	public void setWorkingCoreIndex(int workedCoreIndex) {
		this.workingCoreIndex = workedCoreIndex;
	}
	
	/*
	 * 정렬이 오름차순이므로 -를 붙임 
	 */
	public double getAge() {
		return -age;
	}

	/*
	 *	readyList에 있던 시간을 늘리고, age를 계산한다. 
	 */
	public void setAssignWaitingTime() {
		assignWaitingTime++;
		age = assignWaitingTime/burstTime;
	}

	@Override
	public int compareTo(Object o) {

		int time = arrivalTime - ((Process) o).arrivalTime;

		if(time > 0) return 1;
		else if(time < 0) return -1;
		else return 0;
		
	}	
}