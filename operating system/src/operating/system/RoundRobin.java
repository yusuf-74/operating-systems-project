/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operating.system;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author yusuf
 */
class RoundRobin extends AllocationStrategy {
    int temp;
 int proceessArrivalTime;
 int waitingTime;
 double avgWaitingTime;
 double avgTurnAroundTime;
    public RoundRobin (List<Job> jobs)
    {
    super(jobs);
    }

    

    @Override
    public void run() {
       }
    void run(List<Job> jobList, int quantum) {
        Collections.sort(jobList, new Comparator<Job>() {
    @Override
    public int compare(Job a1, Job a2) {
        return a1.arrivalTime - a2.arrivalTime;
    }
});
       boolean ok = true;boolean idle = true;
       int cnt = 0, finished = 0, n = jobList.size();
       float sumT = 0 , sumW = 0;
 
 while(ok)
 {
     for (Job job:jobList)
     {
         /*System.out.println("p "+job.processId+" a t : "+job.arrivalTime);
         System.out.println("p "+job.processId+" c t : "+job.cpuTime);
         System.out.println("cnt "+ cnt);*/
         if (job.arrivalTime <= cnt && job.cpuTime > 0)
         { idle = false;
             System.out.println("P_ID :" + job.processId );
             if (job.turnAroundTime == 0)
                 {job.turnAroundTime = job.cpuTime;
                 job.CPUTime = job.cpuTime;
                     //System.out.println("ok");
                 }
             
             if (job.cpuTime > quantum)
             {
                 System.out.println("in : " + cnt + " out : " + (int)(cnt+quantum) + "\n ---------------------");
                 job.cpuTime =job.cpuTime- quantum;
                 cnt+=quantum;
                    // System.out.println("ok1");
             }
             else //if (job.cpuTime > 0)
             {
                     
                 finished++;
                 System.out.println("in : " + cnt + " out : " + (int)(cnt+job.cpuTime)+ "\n ---------------------" );
                 cnt+= job.cpuTime;
                /* System.out.println("cnt : "+ cnt);
                 System.out.println("cpu Time : " + job.CPUTime);*/
                 job.turnAroundTime = cnt - job.arrivalTime;
                 sumT += job.turnAroundTime;
                 job.waitingTime = (cnt - job.CPUTime)-job.arrivalTime;
                 sumW += job.waitingTime;
                 job.cpuTime=0;
                
             }
         }
         
         if (finished >= n)
         {ok = false;
         break;
         }
     }
      if (idle)
             cnt++;
         idle = true;
     
 }
 System.out.println("============================================ ");
 System.out.println("Process ID | Turnaround time | Waiting time ");
 System.out.println("============================================ ");
 for (Job job:jobList)
 System.out.println("      "+job.processId +"     |      "+" "+job.turnAroundTime+"      |       "+" "+job.waitingTime+" ");
  System.out.println("===============================================");
 System.out.println("Avg waiting time:"+sumW/jobList.size());
 System.out.println("===============================================");
 System.out.println("Avg turn around time:"+ (sumT /jobList.size()));
 System.out.println("===============================================");
 
    }
    public int min (List<Job> jobList , int cnt)
     {int mn = 10000;
         for (Job job : jobList)
         {
             if (cnt >= job.arrivalTime && job.cpuTime != 0)
             { mn = Math.min(job.arrivalTime, mn);}
         }
         return mn;
     }
    
}
