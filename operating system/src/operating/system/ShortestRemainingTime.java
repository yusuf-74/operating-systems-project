
package operating.system;

import java.util.List;

/**
 *
 * @author yusuf
 */
class ShortestRemainingTime extends AllocationStrategy {

    public ShortestRemainingTime(List<Job> jobs) {
        super(jobs);
    }

    @Override
    public void run() {

    }

    void run(List<Job> jobList) {
        boolean ok = true;
        boolean idle = true ;
        int cnt = 0 , cnt1 = 0;
        Job last = new Job (0,0,0,0);
        int finished = 0;
        double sumT =0;
        double sumW = 0;
        
        for (Job job : jobList) {
            job.CPUTime = job.cpuTime;
            job.processArrivalTime = job.arrivalTime;
        }
        
        while (ok) {
            for (Job job : jobList) {
               
                if (job.arrivalTime <= cnt) {
                    if (job.cpuTime == min(jobList , cnt)) 
                    {
                        idle = false;
                        
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
                         //System.out.println("job id : "+job.processId);
                        job.cpuTime--;
                        cnt++;
                       // System.out.println("job number : "+job.processId + " cnt : " + cnt);
                        if (job.cpuTime == 0)
                        {
                           // System.out.println("job number : "+job.processId + " cnt : " + cnt);
                        finished++;
                        job.endTime = cnt;
                        job.turnAroundTime = cnt - job.arrivalTime;
                        sumT += job.turnAroundTime;
                        job.waitingTime = (cnt - job.CPUTime)-job.arrivalTime;
                        sumW += job.waitingTime;
                        }
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
        System.out.println("============================================ ");
        System.out.println("Process ID | Turnaround time | Waiting time ");
        System.out.println("============================================ ");
        for (Job job:jobList)
 System.out.println("      "+job.processId + "     |      "+" "+job.turnAroundTime+"      |       "+" "+job.waitingTime+" ");
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
             if (cnt >= job.arrivalTime && job.cpuTime != 0)
             { 
                 mn = Math.min(job.cpuTime, mn);
             
             }
         }
         return mn;
     }
}
