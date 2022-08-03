/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package operating.system;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperatingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 // Process command line arguments
 // read the file
 
 
 Scanner sc = new Scanner(System.in);
 Scanner sc1 = new Scanner(System.in);
 Scanner sc2 = new Scanner(System.in);
 
 
 String filename ;
 String allocationStrategy = "FCFS";
 int quantum=20;

 
 /*filename = args[0];
 allocationStrategy = args[1];*/
 while(true)
 {
 filename = "file1.txt";
 int n;
        System.out.println("===========================================\n New process\n===========================================");
       // دا كان انترفيس للبروجكت كامل 
        /* System.out.println("choose Allocation strategy ...");
        System.out.println("1 -> FCFS        2 -> RR      3 -> SRT       4 -> H_Piriority .");
        System.out.println("if you want to exit press 5 .");
        n = sc.nextInt();*/
        
      /*  
 if (n == 1)
     allocationStrategy = "FCFS";
 else if (n == 2)
 {allocationStrategy = "RR";
     System.out.println("enter quantum ..");
     quantum = sc.nextInt();
 }
 else if (n == 3)
     allocationStrategy = "SRT";
 else if (n == 4)
     allocationStrategy = "HP";
 else if (n == 5)
 {
     System.out.println("Good bye ");
     break;
 }*/
 System.out.println("choose text file ");
        System.out.println("               1                                      2                         ");
        System.out.println("               |                                      |                         ");
        System.out.println("              \\/                                      \\/                      ");
        System.out.println("P_ID | Arrival | Cpu | priority   ||   P_ID | Arrival | Cpu | priority ");
        System.out.println("  1  |    0    |  2  |     6      ||     1  |    0    |  6  |     2    ");
        System.out.println("  2  |    7    |  4  |     12     ||     2  |    7    |  12 |     4    ");
        System.out.println("  3  |    2    |  5  |     1      ||     3  |    2    |  1  |     5    ");
        System.out.println("  4  |    8    |  1  |     3      ||     4  |    8    |  3  |     1    ");
        System.out.println("  5  |    4    |  3  |     8      ||     5  |    8    |  4  |     3    ");
     System.out.println("enter file number ");
 n = sc.nextInt();
 if (n == 1 )
     filename = "file1.txt";
 else if (n == 2)
     filename = "file2.txt";
 
 
 //filename = sc.nextLine();
 
 /*if(args.length==3){
 quantum = new Integer(args[2]);
 }*/
 
 
 
 try {
  
 File f =  new File (filename);
 Scanner in = new Scanner (f) ;
 //System.out.println("processId arrivalTime cpuTime");
 // دا بكريت ليست من الاوبجكت اللي اسمه جوب عشان اباصيه للكلاس اللي هينفذلي الشغل
 List<Job> jobList = new ArrayList<Job>();
 while (in.hasNext()) {
 
 String a[] = in.next().split(",");//   ويخزنه فاراي   1234  <-  1,2,3,4  بياخد السطر  
 int processId = new Integer(a[0]);  // بيحول الكراكتر ل انتجر
 int arrivalTime = new Integer(a[1]);
 int cpuTime = new Integer(a[2]);
 int piriority = new Integer(a[3]);
 
 Job job = new Job(processId,arrivalTime,cpuTime,piriority);
 
 jobList.add(job);
 
 //System.out.println(processId+" "+ arrivalTime+" " + cpuTime);
 }
 // الشغل كله هنا 
 if("FCFS".equalsIgnoreCase(allocationStrategy)){
 
 FirstComeFirstServed firstComeFirstServed = new FirstComeFirstServed(jobList);

 firstComeFirstServed.run(jobList);
 // باقي البروجكت
 }else if("SRT".equalsIgnoreCase(allocationStrategy)){
 
 ShortestRemainingTime shortestRemainingTime = new ShortestRemainingTime(jobList);
 shortestRemainingTime.run(jobList);
 
 }
 else if("RR".equalsIgnoreCase(allocationStrategy)){
 
 RoundRobin roundRobin = new RoundRobin(jobList);
 roundRobin.run(jobList,quantum);
 
 }
 else
 {
    HighistPiriority hp = new HighistPiriority(jobList);
    hp.run(jobList);
 }
 
     System.out.println("do you want to continue ? ");
     System.out.println("1 -> YES     ,  2 ->  NO");
     n = sc.nextInt();
     if (n == 1)
         continue;
     else if (n == 2)
     {System.out.println("Good bye ");
         break;
     }
 
 } catch (IOException e) {
 e.printStackTrace();
 } 
 
    }
    }
}

