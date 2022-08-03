/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operating.system;

public class Job {
 public int  CPUTime /*دا فاريابل احتياطي للي تحت*/, piriority /* اعطاها النظرة */;
 
 public int startTime = 0, endTime = 0; /* البروسيس ابتدت امتى وخلصت امتى ودا مش هنحتاجه اوي فشغلنا */
 public int processArrivalTime; /* كلاك تاني مرة بكبر البروجكت */ 
 public int waitingTime = 0;
 public int turnAroundTime = 0;

 
 public int arrivalTime,cpuTime,processId; /*اهم تلاتة الساعة تلاتة */
 
 // الكونستراكتور اللي شغالين عليه فال مين ميثود
 public Job(int id, int submitTime, int CPUTime, int Piriority) {
 super();
 this.processId = id;
 this.arrivalTime = submitTime;
 this.cpuTime = CPUTime;
 this.piriority = Piriority;
 }
 // كان كونستراكتور مبدئي عشان مكانش فيه بريوريتي لسه 
 public Job(int processId, int arrivalTime, int cpuTime) {
 this.processId = processId;
 this.arrivalTime = arrivalTime;
 this.cpuTime = cpuTime;
 }


 
 
 
}