import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ConnectDb implements Job {
    private static final Logger LOGGER = Logger.getLogger(TaskListener.class.getName());
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//10.121.4.182:1521/report", "bi_report", "E#5qWC#$s");
            System.out.println(new Date() + "Connected to database");
            System.out.println("call PCK_KPI_FTTH.MAIN(trunc(sysdate, 'DD') - 1)");

            String command = "call PCK_KPI_FTTH.MAIN(trunc(sysdate, 'DD') - 1)";

            CallableStatement cstmt =  con.prepareCall(command);

            cstmt.execute();
            cstmt.close();
            System.out.println(new Date() + "Finish!!!!!!");

            List<Integer> toSortList = Ints.asList(toSort);
            Collections.sort(toSortList);

        } catch(Exception ex) {
            try {
                throw ex;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        };

    }
}
