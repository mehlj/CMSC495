package JJIGSAWED;

import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author jmehl
 */
public class Schedule {
    
    private enum scheduleLimit{
        SEVEN,
        FOURTEEN,
        THIRTY
    };
    
    public Schedule(){
        
    }
    
    public ResultSet generateSchedule(int projectID,int scheduleLimit){
        
        Date theDate;
        
        ResultSet rs = null;
        
        return rs;
        
    }
    
    public ResultSet modifyScheduleLimit(int projectID, int scheduleLimit){
        ResultSet rs =  null;
        
        return rs;
    }
    
    
    
}
