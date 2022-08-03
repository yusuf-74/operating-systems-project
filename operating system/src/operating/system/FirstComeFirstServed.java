/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operating.system;


import java.util.List;

public class FirstComeFirstServed extends AllocationStrategy {

    int temp;
    int proceessArrivalTime;
    int waitingTime;
    double avgWaitingTime;
    double avgTurnAroundTime;

    public FirstComeFirstServed(List<Job> jobs) {
        super(jobs);
    }

    @Override
    public void run() {

    }

    public void run(List<Job> jobList) {
        int cnt = 0 , finished = 0 ;
        double sumT = 0 , sumW = 0;
        boolean ok = true;  
        boolean idle = true;   
        
        while (ok)
        {
        for (Job job : jobList)
        {
           
                    if (job.arrivalTime == min(jobList , cnt)) 
                    {
                        idle = false; // عشان اتجنب الانفينيتي لوب اللي ممكن تحصل بسبب ان البروسيسور ممكن يعدي عليه وقت ويبقى خامل 
                         
                        System.out.println("P_ID :" + job.processId );
                        // البروسيس دخلت امتى وخلصت امتى//
                        System.out.println("in : " + cnt + " out : " + (int)(cnt+job.cpuTime) + "\n ---------------------" );
                        cnt+=job.cpuTime; // ال cnt -> بيعبر عن الخط الزمني للتاسكات 
                        
                        
                        
                        finished++; // دا بيعبر عن عدد التاسكات الخلصانة عشان لما اخلص التاسكات ابريك من الوايل لوب
                        job.endTime = cnt; // دا اي كلام عشان البروجكت يبقى كبير
                        job.waitingTime = (cnt - job.cpuTime)-job.arrivalTime; // هنا الويتنج تايم عبارة عن الوقت اللي البروسيس خدته فالسيستيم بس برا ال بروسيسور
                        // عشان كدا بشوف انا التايم يونيت عندي كام وبطرح منها الوقت اللي هيا قعدته جوا البروسيسور وبعدها اطرح وقت وصولها
                        /*
                        P_ID | Arrival | Cpu 
                          1  |    0    |  6                 البروسيس الاولى واصلة اول حاجة عند تايم صفر وهتاخد ست ثواني فالبروسيسور
                          2  |    7    |  12                cnt( = 6 ) - cputime (6) - arraivaltime (0)  كدا الويتنج بتاعها عبارة عن  
                          3  |    2    |  1      -->        cnt( = 7 ) - cputime (1) - arraivaltime (2) -> Waiting time -> 4 البروسيس اللي بعدها هتبقى التالتة لانها واصلة قبل التانية 
                          4  |    8    |  3                    and so on
                          5  |    8    |  4 
                        */
                        //System.out.println("waiting time : " + job.waitingTime+ " cnt : "+ cnt);
                        sumW += job.waitingTime; // هحتاجه فالاخر عشان احسب الافريدج
                        job.turnAroundTime = cnt - job.arrivalTime;
                        //System.out.println("turn : " + job.turnAroundTime + " cnt : "+ cnt);
                        sumT += job.turnAroundTime;// هحتاجه فالاخر عشان احسب الافريدج
                        
                        job.cpuTime = 0;
                    } 
                    
                
                if (finished >= jobList.size())
                {ok = false;
                break;}
            }
        if (idle)
             cnt++;
                idle = true;
        }
        
        
        
        System.out.println("============================================ ");
        System.out.println("Process ID | Turnaround time | Waiting time ");
        System.out.println("============================================ ");
        for (Job job : jobList)
        {System.out.println("      " + job.processId + "     |      " + " " + job.turnAroundTime + "      |       " + " " + job.waitingTime + " ");
            System.out.println("----------------------------------------");
        }
        System.out.println("===============================================");

        System.out.println("Avg waiting time:" + sumW / jobList.size());
        System.out.println("===============================================");
        System.out.println("Avg turn around time:" + sumT / jobList.size());
        System.out.println("===============================================");

    }
     public int min (List<Job> jobList , int cnt) // فانكشن بتحسبلي اقل ارايفل تايم فالجوب ليست عشان يدخل الاول
             //وفنفس الوقت الجوب اللي هيدور عليها لازم تكون في نطاق النقطة الزمنية اللي انا باعتهاله 
     {int mn = 10000;
         for (Job job : jobList)
         {
             if (cnt >= job.arrivalTime && job.cpuTime != 0)
             { mn = Math.min(job.arrivalTime, mn);}
         }
         return mn;
     }

}
