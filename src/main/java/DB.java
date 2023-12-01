import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Collection;
//"jdbc:postgresql://localhost:5432/postgres"
public class DB {

    private static String DB_Username;
    private static String DB_Password;
    private static String DB_Url;

    public String getDB_Username() {
        return DB_Username;
    }

    public void setDB_Username(String DB_Username) {
        this.DB_Username = DB_Username;
    }

    public String getDB_Password() {
        return DB_Password;
    }

    public void setDB_Password(String DB_Password) {
        this.DB_Password = DB_Password;
    }

    public String getDB_Url() {
        return DB_Url;
    }

    public void setDB_Url(String DB_Url) {
        this.DB_Url = DB_Url;
    }
}
