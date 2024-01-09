import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import java.util.Date;
import java.util.logging.Logger;

public class TaskListener implements JobListener {
    private static final Logger LOGGER = Logger.getLogger(TaskListener.class.getName());
    @Override
    public String getName() {
        return "Connect Database";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        final String jobName = context.getJobDetail().getKey().toString();
        System.out.println(new Date() + "ConnectToBeExecuted: " + jobName + " is starting...");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println(new Date() + "ConnectExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println(new Date() + "Success!");

        final String jobName = context.getJobDetail().getKey().toString();
        System.out.println(new Date() + "Cob : " + jobName + " is finished!!");

        if (!jobException.getMessage().equals("")) {
            System.out.println(new Date() + "Exception thrown by: " + jobName + " Exception: " + jobException.getMessage());
        }
    }
}
