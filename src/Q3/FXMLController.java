/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Q3;

import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class FXMLController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    //the object order is an observable list which contains all the elements of the command.
    private ObservableList<String> order = FXCollections.observableArrayList();
    //the total price of the order
    private double cost = 0;
    
    //each anchorpane contains the components for flavour/toppings/syrup control
    //by default only the pane flavour is visible
    @FXML
    private AnchorPane  flavourRoot;
    @FXML
    private AnchorPane  toppingsRoot;
    @FXML
    private AnchorPane  syrupRoot;
    //the listview allows to display the composition of the galley and refreshes itself
    @FXML
    private ListView<String> listView ;
    @FXML
    private Text costTotal;
    
    //the three buttons at the top allow access to the different breadcrumbs to add components to the ice cream.
    @FXML
    private void flavourBtn(){
        flavourRoot.setVisible(true);
        toppingsRoot.setVisible(false);
        syrupRoot.setVisible(false);
    }
    @FXML
    private void toppingsBtn(){
        flavourRoot.setVisible(false);
        toppingsRoot.setVisible(true);
        syrupRoot.setVisible(false);
    }
    @FXML
    private void syrupBtn(){
        flavourRoot.setVisible(false);
        toppingsRoot.setVisible(false);
        syrupRoot.setVisible(true);
    }
/************************************************************************************************************************************************************************************************************************************************************/
    //buttons add a component to the order list and increment the price of the order
    @FXML
    private void chocBtn(){
        order.add("Chocolat");
        addCost(3.5);
    }
    @FXML
    private void strawBtn(){
        order.add("Strawberry");
        addCost(3.5);
    }
    @FXML
    private void vanBtn(){
        order.add("Vanille");
        addCost(3.5);
    }
    @FXML
    private void chocSyruBtn(){
        order.add("Chocolate syrup");
        addCost(1);
    }
    @FXML
    private void butSyrBtn(){
        order.add("Butterscotch syrup");
        addCost(1);
    }
    @FXML
    private void berSyrBtn(){
        order.add("Berry syrup");
        addCost(1);
    }
    @FXML
    private void chocTopBtn(){
        order.add("Chocolate chips");
        addCost(1.5);
    }
    @FXML
    private void sprTopBtn(){
        order.add("Sprinkles");
        addCost(1.5);
    }
    @FXML
    private void pepTopBtn(){
        order.add("Peppermint");
        addCost(1.5);
    }
    @FXML
    private void nutTopBtn(){
        order.add("Nuts");
        addCost(1.5);
    }
    
/*************************************************************************************************************************************************************************************************************************************************************/    
    //clear the list and reset the price
    @FXML
    private void clearBtn(){
        order.clear();
        cost=0;
        addCost(0);
    }
    //exit the program
    @FXML
    private void exitBtn(){
        System.exit(0);
    }
    
    //writes in the database the number and the price of the order
    @FXML
    private void orderBtn(){
        //retrieves the time of the command
        LocalDateTime obj = LocalDateTime.now();
        try
        {
            // create a mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/iceorder?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            
            // create a sql date object so we can use it in our INSERT statement
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            
            // the mysql insert statement
            String query = " insert into iceorder (date, totalCost)"  + " values (?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            String date =String.valueOf(obj);
            preparedStmt.setString (1, date);
            preparedStmt.setDouble (2, cost);
            
            // execute the preparedstatement
            preparedStmt.execute();
            
            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    
    //adds the price recovered in parameter
    private void addCost(double n){
        cost+=n;
        String costText =String.valueOf(cost);
        costTotal.setText(costText);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //connection between listview and observable arraylist 
        listView.setItems(order);
        //set defaut price
        addCost(0);
    }
}
