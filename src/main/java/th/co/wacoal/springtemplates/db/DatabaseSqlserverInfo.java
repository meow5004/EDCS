package th.co.wacoal.springtemplates.db;

public class DatabaseSqlserverInfo {
//    public static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=EDocCalService";

//    public static final String URL = "jdbc:sqlserver://10.11.2.43\\SQLEXPRESS:1433;databaseName=EDocCalService";
    public static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=EDocCalService";
    public static final String USER = "sa";
    //   public static final String PASSWORD = "1234";
    public static final String PASSWORD = "w@c0@l";
    public static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private DatabaseSqlserverInfo() {
    }
}
