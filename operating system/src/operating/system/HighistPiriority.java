/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operating.system;

import java.util.List;

/**
 *
 * @author yusuf
 */
public class HighistPiriority extends AllocationStrategy{

    public HighistPiriority(List<Job> jobs) {
        super(jobs);
    }
    @Override
    public void run() {
        
    }
    void run(List<Job> jobList) {
        boolean ok = true;
        boolean idle = true;
        int cnt = 0 , cnt1 = 0;
        Job last = new Job(10,0,0,0);
        int finished = 0;
        double sumT =0;
        double sumW = 0;
        
        for (Job job : jobList) {
            job.CPUTime = job.cpuTime;
            job.processArrivalTime = job.arrivalTime;
        }
        for(Job job : jobList)
        {
        job.CPUTime = job.cpuTime;
        }
        while (ok) {
            for (Job job : jobList) {
               
                if (job.arrivalTime <= cnt) {
                    /*System.out.println("min(jobList , cnt) : " + min(jobList , cnt) );
                    System.out.println("job p : " + job.piriority);*/
                    if (job.piriority == min(jobList , cnt) && job.cpuTime > 0) 
                    {
                        
                        idle = false;
                         //System.out.println("job id : "+job.processId);
                         if (cnt1 == 0)
                         {
                         last = job;
                         cnt1++;
                         }
                         if (job.processId != last.processId)
                         {
                             
                             System.out.println("P_ID " + last.processId);
                             System.out.println("in : " + last.startTime + " out : " + cnt);
                             System.out.println("switch from " + last.processId + " to " + job.processId + "\n ---------------------");
                             job.startTime = cnt;
                             last = job;
                         }
                         cnt+= job.cpuTime;
                        job.cpuTime = 0;
                        //System.out.println("job cpuT : " + job.cpuTime);
                        
                        //last = job;
                        
                            
                            finished++;
                        job.endTime = cnt;
                        job.turnAroundTime = cnt - job.arrivalTime;
                        sumT += job.turnAroundTime;
                        job.waitingTime = (cnt - job.CPUTime)-job.arrivalTime;
                        sumW += job.waitingTime;
                        
                    } 
                    
                }
                
                if (finished >= jobList.size())
                {ok = false;
                break;}
            }
            if (idle)
                    cnt++;
                idle = true;
        }
        System.out.println("P_ID " + last.processId);
                             System.out.println("in : " + last.startTime + " out : " + cnt);
                             //System.out.println("switch from " + last.processId + " to " + job.processId);
        System.out.println("============================================ ");
        System.out.println("Process ID | Turnaround time | Waiting time ");
        System.out.println("============================================ ");
        for (Job job:jobList)
 System.out.println("      "+job.processId +"     |      "+" "+job.turnAroundTime+"      |       "+" "+job.waitingTime+" ");
        System.out.println("----------------------------------------");
  System.out.println("===============================================");
 System.out.println("Avg waiting time:"+sumW/jobList.size());
 System.out.println("===============================================");
 System.out.println("Avg turn around time:"+ (sumT /jobList.size()));
 System.out.println("===============================================");
    }

    
     private int min (List<Job> jobList , int cnt)
     {int mn = 10000;
         for (Job job : jobList)
         {
             if (cnt >= job.arrivalTime && job.cpuTime > 0)
             { mn = Math.min(job.piriority, mn);}
         }
         return mn;
     }
    
}
