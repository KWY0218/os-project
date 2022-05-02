package team.os.model;

public class Process {
	private int pId;
	private int arrivalTime;
	private int burstTime;
	private int waitingTime = 0;
	private int turnAroundTime = 0;
	private int normalizedTime = 0;
	private int workedCoreIndex = -1;
	
	public Process(int pId, int arrivalTime, int burstTime){
		this.pId = pId;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
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

	public int getNormalizedTime() {
		return normalizedTime;
	}

	public void setNormalizedTime(int normalizedTime) {
		this.normalizedTime = normalizedTime;
	}

	public int getWorkedCoreIndex() {
		return workedCoreIndex;
	}

	public void setWorkedCoreIndex(int workedCoreIndex) {
		this.workedCoreIndex = workedCoreIndex;
	}	
}