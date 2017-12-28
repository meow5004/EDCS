package th.co.wacoal.springtemplates.db;



public class DatabaseOracleInfo {
    public static final String URL = "jdbc:oracle:thin:@10.11.9.3:1521:wacoal";
    //public static final String USER = "testtime";
    //public static final String PASSWORD = "testtime";
    public static final String USER = "timecard";
    public static final String PASSWORD = "oratime";
    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private DatabaseOracleInfo() {
    }
}