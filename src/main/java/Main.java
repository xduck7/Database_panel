import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws Exception {
        DB database = new DB();

        Connection connection;

        database.setDB_Username("postgres");
        database.setDB_Password("postgres");
        database.setDB_Url("jdbc:postgresql://localhost:5432/postgres");


        connection = DriverManager.getConnection(database.getDB_Url(),
                                                 database.getDB_Username(),
                                                 database.getDB_Password());
        Scanner sc = new Scanner(System.in);

        Panel panel = new Panel();

        while (panel.command != "0") {
            Prefabs.display_menu();

            panel.command = sc.nextLine();
            Statement state = connection.createStatement(); // объект для передачи запросов

            // КОМАНДЫ ПРОГРАММЫ
            if (panel.command.equals("1")) {
                panel.request = "SELECT * FROM users";
                Commands.log_view(panel.status_log, panel.request);
                ResultSet result = state.executeQuery(panel.request);
                while (result.next()) {
                    System.out.println(
                            result.getInt("id") + " "
                                    + result.getString("name") + " "
                                    + result.getString("phone"));
                }
            }
            else if (panel.command.equals("2")) {
                System.out.println("\n Enter a column (id, name, phone): ");
                panel.what = sc.nextLine();
                panel.request = "SELECT "+ panel.what +" FROM users";
                ResultSet result = state.executeQuery(panel.request);
                while (result.next()) {
                    System.out.println(result.getObject(panel.what));
                }
            }
            else if (panel.command.equals("3")) {
                System.out.println("Enter a user data \n Name: ");
                String name = sc.nextLine();
                System.out.println("Phone: ");
                String phone = sc.nextLine();
                panel.request = "INSERT INTO users (name, phone) values ('" + name + "','"+phone+"')";
                Commands.log_view(panel.status_log, panel.request);
                state.executeUpdate(panel.request);
                System.out.println("User " + name + " | " + phone + " was added");
            }
            else if (panel.command.equals("4")) {
                //вывод всего
                panel.request = "SELECT * FROM users";
                ResultSet result = state.executeQuery(panel.request);
                System.out.println("\n");
                Commands.log_view(panel.status_log, panel.request);
                while (result.next()) {
                    System.out.println(
                            result.getInt("id") + " "
                                    + result.getString("name") + " "
                                    + result.getString("phone"));
                }
                System.out.println("Which user are you going to edit?\nEnter id: ");
                panel.what = sc.nextLine();
                System.out.println("Enter a new name: ");
                String new_name = sc.nextLine();
                System.out.println("Enter a new phone: ");
                String new_phone = sc.nextLine();
                panel.request = "UPDATE users SET name = '"+new_name+"'," +
                        " phone = '"+new_phone+"' WHERE id = " + panel.what;
                state.executeUpdate(panel.request);
                Commands.log_view(panel.status_log, panel.request);
                System.out.println("You edited a user with id = " + panel.what);
            }
            else if (panel.command.equals("5")) {
                //вывод всего
                panel.request = "SELECT * FROM users";
                ResultSet result = state.executeQuery(panel.request);
                System.out.println("\n");
                while (result.next()) {
                    System.out.println(
                            result.getInt("id") + " "
                                    + result.getString("name") + " "
                                    + result.getString("phone"));
                }
                System.out.println("Which user are you going to delete?\nEnter id: ");
                panel.what = sc.nextLine();
                panel.request = "DELETE FROM users WHERE id = " + panel.what;
                state.executeUpdate(panel.request);
                Commands.log_view(panel.status_log, panel.request);
                System.out.println("You deleted a user with id = " + panel.what);
            }
            if (panel.command.equals("9")) {
                if (panel.status_log.equals("OFF")) {
                    panel.status_log = "ON";
                } else if (panel.status_log.equals("ON")) {
                    panel.status_log = "OFF";
                }
                continue;
            }


            //условия выхода
            else if (panel.command.equals("0")) {
                System.out.println("Connection is broken");
                break;
            } else {
                continue;
            }

        }
    }
}
