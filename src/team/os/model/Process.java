package team.os.model;

public class Process implements Comparable<Object> {
	
	private int pId;
	private int arrivalTime;
	private int burstTime;
	private int remainBurstTime;
	private int waitingTime = 0;
	private int turnAroundTime = 0;
	private int normalizedTime = 0;
	private int workingCoreIndex = -1;
	private boolean terminated = false;
	
	public Process(int pId, int arrivalTime, int burstTime){
		this.pId = pId;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.remainBurstTime = burstTime;
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

	public int getNormalizedTime() {
		return normalizedTime;
	}

	public void setNormalizedTime(int normalizedTime) {
		this.normalizedTime = normalizedTime;
	}

	public int getWorkingCoreIndex() {
		return workingCoreIndex;
	}

	public void setWorkingCoreIndex(int workedCoreIndex) {
		this.workingCoreIndex = workedCoreIndex;
	}

	@Override
	public int compareTo(Object o) {

		int time = arrivalTime - ((Process) o).arrivalTime;

		if(time > 0) return 1;
		else if(time < 0) return -1;
		else return 0;
		
	}	
}