/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operating.system;

import java.util.List;

/* implement this class for all four strategies */
public abstract class AllocationStrategy {

    protected List<Job> Jobs;
   // protected ArrayList<Job> Queue;

    public AllocationStrategy(List<Job> jobs) {
        super();
        Jobs = jobs;
    }

    public abstract void run();
    // update current job by 1 tick
    // check if the job queue might need to be changed.
    // check for jobs to add to the queue
}
