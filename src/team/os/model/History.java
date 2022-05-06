package team.os.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class History {

   private List<List<Process>> processHistory;
   private List<Queue<Process>> readyQueue;
   private int totalBurstTime = 0;
   private double totalPowerConsumption = 0.0;

   public History() {

      processHistory = new ArrayList<List<Process>>();
      readyQueue = new ArrayList<Queue<Process>>();

   }

   public void addPage(List<Process> mProcessList, List<Process> mReadyQueue) {

      List<Process> processList = new ArrayList<Process>();
      for(Process process : mProcessList) {
         Process newProcess = new Process(process.getpId(), process.getArrivalTime(), process.getBurstTime());
         newProcess.setWorkingCoreIndex(process.getWorkingCoreIndex());
         processList.add(newProcess);

      }

      processHistory.add(processList);

      // queue 복사
      Queue<Process> tQueue = new LinkedList<Process>(mReadyQueue);

      readyQueue.add(tQueue);

   }

   public List<List<Process>> getHistory() {

      return processHistory;

   }

   public List<Queue<Process>> getReadyQueue() {
      return readyQueue;
   }

   public int getTotalBurstTime() {
      
      return totalBurstTime;

   }

   public void setTotalBurstTime(int mTotalBurstTime) {

      totalBurstTime = mTotalBurstTime;

   }

   public double getTotalPoewrConsumption() {
      
      return totalPowerConsumption;
      
   }
   
   public void setTotalPowerConsumption(double mTotalPowerConsumption) {

      totalPowerConsumption = mTotalPowerConsumption;

   }

}
