 
package seacomtools;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.control.Button;

import java.util.*;
import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.cell.*;

import java.sql.*;
//import oracle.jdbc.pool.*;
import java.util.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.stage.Modality;

public class SeacomToolsApp extends Application { 
    // Tool ArrayList, Observable List, List View 
    ArrayList<Tool> toolArray = new ArrayList<>();
    public static ObservableList<Tool> olTool = FXCollections.observableArrayList(); 
    public static ObservableList<Tool> olAvailableTool = FXCollections.observableArrayList(); 
    public static ListView<Tool> toolListView = new ListView<Tool>(); 
    public static ListView<Tool> toolMenuListView = new ListView<Tool>();
   
    // Employee ArrayList, Observable List
    ArrayList<Employee> empArray = new ArrayList<>();
    public static ObservableList<Employee> olEmployees = FXCollections.observableArrayList();
    public static ListView<Employee> employeeListView = new ListView<Employee>(); 
    public static ListView<Employee> employeeMenuListView = new ListView<Employee>();
    
    // Locations ArrayList, Observable List
    ArrayList<Locations> locationArray = new ArrayList<>();
    public static ObservableList<Locations> olLocations = FXCollections.observableArrayList(); 
    public static ListView<Locations> locationsListView = new ListView<Locations>();
    
    // Checkout Record ArrayList, Observable List
    ArrayList<CheckoutRecord> checkoutArray = new ArrayList<>();
    public static ObservableList olCheckoutRecord = FXCollections.observableArrayList(); 
    public static ListView<CheckoutRecord> checkoutListView = new ListView<CheckoutRecord>(); 
    
    // Checkout Record History ArrayList, Observable List 
    ArrayList<CheckoutRecord> checkoutHistory = new ArrayList<>();
    public static ObservableList olCheckoutHistory = FXCollections.observableArrayList();
    public static ListView<CheckoutRecord> checkoutHistoryListView = new ListView<CheckoutRecord>();
    
    // Status Options ArrayList, Observable List 
    public static String[] statusOpts = { " ", "In", "Out", "Broken", "Needs Maint"};
    public static ObservableList olStatus = FXCollections.observableArrayList(statusOpts);
    
    // Main Menu Pane and Btns 
    VBox rightMenuPane = new VBox();
    VBox checkoutMenuPane = new VBox(); 
    VBox toolMenuPane = new VBox(); 
    VBox empMenuPane = new VBox();
    BorderPane menuPane = new BorderPane();
    Button btnAddToolWindow = new Button("Add New Tool");
    Button btnCheckOutWindow = new Button("Check Out Tool");
    Button btnCheckIn = new Button("Check In Tool"); 
    Button btnMenuAddEmp = new Button("Add New Employee");
    Button btnMenuAddLocation = new Button("Add New Location");
    Button btnSelectedToolCheckout = new Button("Checkout Selected Tool");
    Button btnEditEmp = new Button("Edit Selected Employee Record");  
    Label lblTitle = new Label("Seacom Tools Application"); 
    TabPane tabPane = new TabPane(); 
    Tab tab1 = new Tab("Checkout Records", checkoutMenuPane);
    Tab tab2 = new Tab("Available Tools", toolMenuPane);
    Tab tab3 = new Tab("Current Employees", empMenuPane); 
    
    
    // Add Tools Variables 
    Label lblToolNum = new Label("Tool #: ");
    Label lblToolName = new Label("Tool Name: "); 
    Label lblToolStatus = new Label("Tool Status: ");
    Label lblDescription = new Label("Tool Description: ");
    Label lblModelNum = new Label("Model Number: ");
    Label lblSerialNum = new Label("Serial Number: ");
    Label lblPurchDate = new Label("Purchase Date: ");
    Label lblPrice = new Label("Purchase Price: ");
    Label lblExtraInfo = new Label("Extra Tool Info");
    Label lblShopLocation = new Label("Shop Location");
    
    TextField txtToolNum = new TextField();
    TextField txtToolName = new TextField();
    ComboBox<String> cboStatus = new ComboBox();
    TextField txtDescription = new TextField();
    TextField txtModelNum = new TextField();
    TextField txtSerialNum = new TextField();
    TextField txtPurchDate = new TextField();
    TextField txtPrice = new TextField();
    TextField txtExtraInfo = new TextField(); 
    TextField txtShopLocation = new TextField();
    Button btnAddTool = new Button("Add New Tool"); 
    Button btnEditTool = new Button("Edit Selected Tool"); 
    Button btnClearField = new Button("Clear Fields");
    
    // Add Tools Panes for displaying input area and listView of Tools
    BorderPane rootPane = new BorderPane(); 
    GridPane addToolsPane = new GridPane(); // Top of Pane
    HBox showTools = new HBox(); // Bottom of Pane 
    
    //Checkout Tools Labels for input
    Label lblCheckoutToolNum = new Label("Tool Number: ");
    Label lblEmployee = new Label("Employee: ");
    Label lblLocation = new Label("Location: ");
    Label lblDate = new Label("Date: ");
    
    // Checkout Tools Selections and Btn for Checking out
    ComboBox<Tool> cboTools = new ComboBox(olTool);
    ComboBox<Employee> cboEmps = new ComboBox();
    ComboBox<Locations> cboLocations = new ComboBox();
    TextField txtCheckoutDate = new TextField(); 
    Button btnCheckoutTool = new Button("Checkout Tool");  
    
    Button btnReturn = new Button("Back to Menu"); 
    Button btnReturn1 = new Button("Back to Menu"); 
    Button btnReturn2 = new Button("Back to Menu");
    Button btnReturn3 = new Button("Back to Menu");
    Button btnReturn4 = new Button("Back to Menu");
    
    GridPane checkoutPane = new GridPane(); 
    
    // Check In Tools ListView and Btn
    Button btnCheckInTool = new Button("Check Tool In");
    BorderPane checkinPane = new BorderPane();
    HBox checkinHBoxTop = new HBox();
    GridPane checkinBottom = new GridPane(); 
    
    // Add Employees Variables 
    Label lblEmpFirstName = new Label("First Name: ");
    Label lblEmpLastName = new Label("Last Name: "); 
    Label lblEmpPhone = new Label("Phone Number: ");
    Label lblEmpEmail = new Label("Email: "); 
    TextField txtEmpFirstName = new TextField();
    TextField txtEmpLastName = new TextField();
    TextField txtEmpPhone = new TextField();
    TextField txtEmpEmail = new TextField();
    GridPane addEmpGridPane = new GridPane(); 
    Button btnAddEmp = new Button("Add Employee"); 
    
    // Add Locations Variables
    Label lblLocationName = new Label("Location Name: ");
    Label lblLocationAddress = new Label("Location Address: ");
    TextField txtLocationName = new TextField();
    TextField txtLocationAddress = new TextField();
    Button btnAddLocation = new Button("Add Location");
    GridPane addLocationPane = new GridPane(); 
    
    
    @Override
    public void start(Stage primaryStage) {
        
         //Sample Employees and Locations
        addTool(1, "Test", "In", "Test Tool", "abc123", "123456", "1/1/2022", 19.99, "Test", "Connex"); 
        Employee sampleEmp = new Employee("Bill Sanders", "8045648822", "btb@mindspring.com");
        empArray.add(sampleEmp);
        olEmployees.add(sampleEmp);
        Locations sampleLocation = new Locations("Shop", "2314 Commerce Center Drive" );
        locationArray.add(sampleLocation);
        olLocations.add(sampleLocation);
        
        // Main Menu 
        rightMenuPane.getChildren().addAll( btnCheckOutWindow, btnCheckIn, btnAddToolWindow, 
                btnMenuAddEmp, btnMenuAddLocation);  
        rightMenuPane.setSpacing(15.0); 
        rightMenuPane.setAlignment(Pos.CENTER);        
        checkoutMenuPane.getChildren().addAll(checkoutListView, btnCheckInTool); 
        toolMenuPane.getChildren().addAll(toolMenuListView, btnSelectedToolCheckout); 
        empMenuPane.getChildren().addAll(employeeMenuListView, btnEditEmp);
        toolMenuListView.setItems(olAvailableTool); 
        employeeMenuListView.setItems(olEmployees);
        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        menuPane.setRight(rightMenuPane);
        menuPane.setLeft(tabPane); 
        menuPane.setTop(lblTitle); 
        lblTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        BorderPane.setAlignment(lblTitle, Pos.CENTER); 
        
        Scene scene = new Scene(menuPane, 500, 500);
        
        // Add Tools 
        addToolsPane.add(lblToolNum, 0, 0);
        addToolsPane.add(txtToolNum, 1, 0);
        addToolsPane.add(lblToolName, 0, 1);
        addToolsPane.add(txtToolName, 1, 1);
        addToolsPane.add(lblToolStatus, 0, 2);
        addToolsPane.add(cboStatus, 1, 2);
        cboStatus.getItems().addAll(statusOpts);
        addToolsPane.add(lblDescription, 0, 3);
        addToolsPane.add(txtDescription, 1, 3);
        addToolsPane.add(lblModelNum, 0, 4);
        addToolsPane.add(txtModelNum, 1, 4);
        addToolsPane.add(lblSerialNum, 0, 5);
        addToolsPane.add(txtSerialNum, 1, 5);
        addToolsPane.add(lblPurchDate, 0, 6); 
        addToolsPane.add(txtPurchDate, 1, 6);
        addToolsPane.add(lblPrice, 0, 7);
        addToolsPane.add(txtPrice, 1, 7);
        addToolsPane.add(lblExtraInfo, 0, 8);
        addToolsPane.add(txtExtraInfo, 1, 8);
        addToolsPane.add(lblShopLocation, 0, 9);
        addToolsPane.add(txtShopLocation, 1, 9);
        addToolsPane.add(btnAddTool, 0, 10);
        addToolsPane.add(btnReturn, 0, 11); 
        addToolsPane.add(btnEditTool, 0, 12); 
        addToolsPane.add(btnClearField, 0, 13);
        addToolsPane.setAlignment(Pos.CENTER);
        showTools.getChildren().addAll(toolListView);
        toolListView.setPrefSize(300,300);
        showTools.setAlignment(Pos.CENTER);
        rootPane.setTop(addToolsPane);
        rootPane.setBottom(showTools);
        Scene toolScene = new Scene(rootPane, 600, 600);
        
        toolListView.setItems(olTool); 
        
        //Edit Tools 
        Button btnSave = new Button("Save"); 
        Button btnCancel = new Button("Cancel");
        
        //Checkout Tool
        checkoutPane.add(lblCheckoutToolNum,0,0);
        checkoutPane.add(lblEmployee, 0, 1);
        checkoutPane.add(lblLocation, 0,2);
        checkoutPane.add(lblDate, 0, 3); 
        checkoutPane.add(cboTools, 1, 0);
        checkoutPane.add(cboEmps, 1, 1);
        cboEmps.getItems().addAll(olEmployees);
        checkoutPane.add(cboLocations, 1,2);
        cboLocations.getItems().addAll(olLocations);
        checkoutPane.add(txtCheckoutDate,1,3);
        checkoutPane.add(btnCheckoutTool, 0,4);
        checkoutPane.add(btnReturn1,0,5);
        checkoutPane.setAlignment(Pos.CENTER); 

        Scene checkoutScene = new Scene(checkoutPane, 300, 200); 
        
        //CheckIn Tool 
//        checkinHBoxTop.getChildren().addAll(checkoutListView); 
//        checkoutListView.setPrefSize(300,300); 
//        checkinBottom.add(btnCheckInTool, 0,0); 
//        checkinBottom.add(btnReturn2, 0, 1);
//        checkinPane.setTop(checkinHBoxTop);
//        checkinPane.setBottom(checkinBottom);
//        Scene checkinScene = new Scene (checkinPane, 400, 400); 
//        
        checkoutListView.setItems(olCheckoutRecord); 
        
        //Add Employee Pane
        addEmpGridPane.add(lblEmpFirstName, 0, 0);
        addEmpGridPane.add(lblEmpLastName, 0, 1);
        addEmpGridPane.add(lblEmpPhone, 0, 2);
        addEmpGridPane.add(lblEmpEmail, 0, 3);
        addEmpGridPane.add(txtEmpFirstName, 1, 0);
        addEmpGridPane.add(txtEmpLastName, 1, 1);
        addEmpGridPane.add(txtEmpPhone, 1, 2);
        addEmpGridPane.add(txtEmpEmail, 1, 3); 
        addEmpGridPane.add(btnAddEmp, 0, 4); 
        addEmpGridPane.add(btnReturn3, 1, 4);
        Scene addEmpScene = new Scene(addEmpGridPane, 500, 500); 
        
        //Add Location Pane
        addLocationPane.add(lblLocationName, 0, 0); 
        addLocationPane.add(lblLocationAddress, 0, 1);
        addLocationPane.add(txtLocationName, 1, 0);
        addLocationPane.add(txtLocationAddress, 1, 1);
        addLocationPane.add(btnAddLocation, 0, 2); 
        addLocationPane.add(btnReturn4, 1, 2); 
        Scene addLocationScene = new Scene(addLocationPane, 500, 500);
        
        primaryStage.setTitle("Seacom Tool App");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        btnAddToolWindow.setOnAction(e -> {
           
           primaryStage.setScene(toolScene);
           primaryStage.show();
           
        });
        
        btnAddTool.setOnAction(e -> {
            addTool(); 
            
        }); 
        
        btnEditTool.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override 
            public void handle(ActionEvent eventEditTool)
            {
                GridPane editToolsPane = new GridPane(); 
                
                Label lblEToolNum = new Label("Tool #: ");
                Label lblEToolName = new Label("Tool Name: "); 
                Label lblEToolStatus = new Label("Tool Status: ");
                Label lblEDescription = new Label("Tool Description: ");
                Label lblEModelNum = new Label("Model Number: ");
                Label lblESerialNum = new Label("Serial Number: ");
                Label lblEPurchDate = new Label("Purchase Date: ");
                Label lblEPrice = new Label("Purchase Price: ");
                Label lblEExtraInfo = new Label("Extra Tool Info");
                Label lblEShopLocation = new Label("Shop Location");

                TextField txtEToolNum = new TextField();
                TextField txtEToolName = new TextField();
                ComboBox<String> cboEStatus = new ComboBox();
                TextField txtEDescription = new TextField();
                TextField txtEModelNum = new TextField();
                TextField txtESerialNum = new TextField();
                TextField txtEPurchDate = new TextField();
                TextField txtEPrice = new TextField();
                TextField txtEExtraInfo = new TextField(); 
                TextField txtEShopLocation = new TextField();
                
                editToolsPane.add(lblEToolNum, 0, 0);
                editToolsPane.add(txtEToolNum, 1, 0);
                editToolsPane.add(lblEToolName, 0, 1);
                editToolsPane.add(txtEToolName, 1, 1);
                editToolsPane.add(lblEToolStatus, 0, 2);
                editToolsPane.add(cboEStatus, 1, 2);
                cboEStatus.getItems().addAll(statusOpts);
                editToolsPane.add(lblEDescription, 0, 3);
                editToolsPane.add(txtEDescription, 1, 3);
                editToolsPane.add(lblEModelNum, 0, 4);
                editToolsPane.add(txtEModelNum, 1, 4);
                editToolsPane.add(lblESerialNum, 0, 5);
                editToolsPane.add(txtESerialNum, 1, 5);
                editToolsPane.add(lblEPurchDate, 0, 6); 
                editToolsPane.add(txtEPurchDate, 1, 6);
                editToolsPane.add(lblEPrice, 0, 7);
                editToolsPane.add(txtEPrice, 1, 7);
                editToolsPane.add(lblEExtraInfo, 0, 8);
                editToolsPane.add(txtEExtraInfo, 1, 8);
                editToolsPane.add(lblEShopLocation, 0, 9);
                editToolsPane.add(txtEShopLocation, 1, 9);
                editToolsPane.add(btnSave, 0, 10);
                editToolsPane.add(btnCancel, 0, 11);    
                
                txtEToolNum.setText(String.valueOf(toolListView.getSelectionModel().getSelectedItem().getToolNum()));
                txtEToolName.setText(toolListView.getSelectionModel().getSelectedItem().getToolName()); 
                cboEStatus.setValue(toolListView.getSelectionModel().getSelectedItem().getToolStatus());
                txtEDescription.setText(toolListView.getSelectionModel().getSelectedItem().getToolDescription());
                txtEModelNum.setText(toolListView.getSelectionModel().getSelectedItem().getModelNum());
                txtESerialNum.setText(toolListView.getSelectionModel().getSelectedItem().getSerialNum());
                txtEPurchDate.setText(toolListView.getSelectionModel().getSelectedItem().getpurchaseDate()); 
                txtEPrice.setText(String.valueOf(toolListView.getSelectionModel().getSelectedItem().getPurchasePrice()));
                txtEExtraInfo.setText(toolListView.getSelectionModel().getSelectedItem().getExtraInfo());
                txtEShopLocation.setText(toolListView.getSelectionModel().getSelectedItem().getShopLocation());               
                
                Scene editToolsScene = new Scene(editToolsPane, 300, 300); 
                
                Stage editToolsWindow = new Stage();
                editToolsWindow.setTitle("Edit Tool"); 
                editToolsWindow.setScene(editToolsScene); 
                editToolsWindow.initModality(Modality.WINDOW_MODAL); 
                editToolsWindow.show(); 
                
                btnCancel.setOnAction(e-> {
                    
                    editToolsWindow.close(); 
                }); 
                
                btnSave.setOnAction(e-> {

                    int var = olTool.indexOf(toolListView.getSelectionModel().getSelectedItem());

                    olTool.get(var).setToolNum(Integer.parseInt(txtEToolNum.getText())); 
                    olTool.get(var).setToolName(txtEToolName.getText());
                    olTool.get(var).setToolStatus(cboEStatus.getValue());
                    olTool.get(var).setToolDescription(txtEDescription.getText());
                    olTool.get(var).setModelNum(txtEModelNum.getText());
                    olTool.get(var).setSerialNum(txtESerialNum.getText()); 
                    olTool.get(var).setPurchaseDate(txtEPurchDate.getText());
                    olTool.get(var).setPurchasePrice(Double.parseDouble(txtEPrice.getText()));
                    olTool.get(var).setExtraInfo(txtEExtraInfo.getText());
                    olTool.get(var).setShopLocation(txtEShopLocation.getText()); 
                     
                    toolListView.refresh();
                    editToolsWindow.close();
                });               
            }
        }); 

        btnEditEmp.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent eventEditEmp)
            {
                GridPane editEmpGridPane = new GridPane();

                Label lblEditEmpFirstName = new Label("Name: ");
                Label lblEditEmpPhone = new Label("Phone Number: ");
                Label lblEditEmpEmail = new Label("Email: "); 
                TextField txtEditEmpName = new TextField();
                TextField txtEditEmpPhone = new TextField();
                TextField txtEditEmpEmail = new TextField();
                Button btnEditSaveEmp = new Button("Save Employee Changes"); 
                Button btnEditCancelEmp = new Button("Cancel"); 

                editEmpGridPane.add(lblEditEmpFirstName, 0, 0);
                editEmpGridPane.add(lblEditEmpPhone, 0, 1);
                editEmpGridPane.add(lblEditEmpEmail, 0, 2);
                editEmpGridPane.add(txtEditEmpName, 1, 0); 
                editEmpGridPane.add(txtEditEmpPhone, 1, 1); 
                editEmpGridPane.add(txtEditEmpEmail, 1, 2); 
                editEmpGridPane.add(btnEditSaveEmp, 1, 3); 
                editEmpGridPane.add(btnEditCancelEmp, 1, 4); 

                txtEditEmpName.setText(String.valueOf(employeeMenuListView.getSelectionModel().getSelectedItem().getName())); 
                txtEditEmpPhone.setText(String.valueOf(employeeMenuListView.getSelectionModel().getSelectedItem().getPhoneNum()));
                txtEditEmpEmail.setText(String.valueOf(employeeMenuListView.getSelectionModel().getSelectedItem().getEmail()));

                Scene editEmpScene = new Scene(editEmpGridPane, 300, 300); 
                Stage editEmpWindow = new Stage();
                editEmpWindow.setScene(editEmpScene);
                editEmpWindow.setTitle("Edit Employee");
                editEmpWindow.initModality(Modality.WINDOW_MODAL); 
                editEmpWindow.show(); 

                btnEditCancelEmp.setOnAction(e-> {
                    editEmpWindow.close();
                });

                btnEditSaveEmp.setOnAction(e->{
                    int var = olEmployees.indexOf(employeeMenuListView.getSelectionModel().getSelectedItem()); 

                    olEmployees.get(var).setName(txtEditEmpName.getText());
                    olEmployees.get(var).setPhoneNum(txtEditEmpPhone.getText());
                    olEmployees.get(var).setEmail(txtEditEmpEmail.getText()); 

                    employeeMenuListView.refresh();
                    editEmpWindow.close(); 
                });
                
            }
        });
              
        btnClearField.setOnAction(e-> {
            clearToolFields();
        }); 
        
        // Actions for returning to the main menu
        btnReturn.setOnAction(e -> { returnToMenu(primaryStage, scene); });
        btnReturn1.setOnAction(e -> { returnToMenu(primaryStage, scene); });
        btnReturn2.setOnAction(e -> { returnToMenu(primaryStage, scene); });
        btnReturn3.setOnAction(e -> { returnToMenu(primaryStage, scene); });
        btnReturn4.setOnAction(e -> { returnToMenu(primaryStage, scene); });
        
        // Shows Checkout Screen
        btnCheckOutWindow.setOnAction(e -> {           
            primaryStage.setScene(checkoutScene);
            primaryStage.show();
        }); 

        btnSelectedToolCheckout.setOnAction(e -> {
            //cboTools.setItem(toolMenuListView.getSelectionModel().getSelectedItem());
            cboTools.setValue(toolMenuListView.getSelectionModel().getSelectedItem());
            
            primaryStage.setScene(checkoutScene);
            primaryStage.show();
        });
            
       
        btnCheckoutTool.setOnAction(e -> {
           checkoutTool();           
        });
        
        // Shows Check-in Screen
//        btnCheckIn.setOnAction(e -> {        
//            primaryStage.setScene(checkinScene);
//            primaryStage.show();        
//        });
        
        btnCheckInTool.setOnAction(e -> {
            checkinTool(checkoutListView.getSelectionModel().getSelectedItem());    
        }); 
        
        /*menuItemEmp.setOnAction(e-> {
            primaryStage.setScene(addEmpScene);
            primaryStage.show();
        });  */
        
       /* menuItemLoc.setOnAction(e-> {
            primaryStage.setScene(addLocationScene);
            primaryStage.show();
        });*/
        
        btnAddEmp.setOnAction(e-> {
            addEmp();           
        }); 
        
        btnAddLocation.setOnAction(e->{
           addLocation(); 
        });
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
    
    public void returnToMenu (Stage primaryStage, Scene scene)
    {
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void addEmp()
    {
        String fName = txtEmpFirstName.getText();
        String lName = txtEmpLastName.getText();
        String phoneNum = txtEmpPhone.getText();
        String email = txtEmpEmail.getText();
        String fullName = fName + " " + lName;
        
        Employee newEmp = new Employee(fullName, phoneNum, email);
        
        empArray.add(newEmp);
        olEmployees.add(newEmp); 
        cboEmps.getItems().add(newEmp);
        
        txtEmpFirstName.clear();
        txtEmpLastName.clear();
        txtEmpPhone.clear();
        txtEmpEmail.clear();
        
    } 
    
    public void addLocation()
    {
        String locationName = txtLocationName.getText();
        String locationAddress = txtLocationAddress.getText();
  
        Locations newLocation = new Locations(locationName, locationAddress);
        
        locationArray.add(newLocation);
        olLocations.add(newLocation);
        cboLocations.getItems().add(newLocation); 
        
        txtLocationName.clear();
        txtLocationAddress.clear();
        
    }
    
    
    public void addTool()
    {
     
        int toolNum = Integer.parseInt(txtToolNum.getText());
        String name = txtToolName.getText();
        String toolStatus = cboStatus.getValue();
        String description = txtDescription.getText();
        String modelNum = txtModelNum.getText();
        String serialNum = txtSerialNum.getText();
        String purchDate = txtPurchDate.getText();
        Double price = Double.parseDouble(txtPrice.getText());
        String extraInfo = txtExtraInfo.getText();
        String shopLocation = txtShopLocation.getText();
        
        Tool newTool = new Tool(toolNum, name, toolStatus, description, modelNum, serialNum,
                purchDate, price, extraInfo, shopLocation);
        
        toolArray.add(newTool);
        olTool.add(newTool); 
        olAvailableTool.add(newTool); 
        
        clearToolFields();
                 
    } 
    
    public void addTool(int toolNum, String toolName, String toolStatus, String toolDescription, String modelNum, 
        String serialNum, String purchaseDate, double purchasePrice, String extraInfo, String shopLocation) 
    {
        
        Tool newTool = new Tool(toolNum, toolName, toolStatus, toolDescription, modelNum, serialNum,
                purchaseDate, purchasePrice, extraInfo, shopLocation);
        
        toolArray.add(newTool);
        olTool.add(newTool); 
        olAvailableTool.add(newTool);
        toolListView.refresh();
        
        
    }
    
    public void clearToolFields()
    {
        txtToolNum.clear();
        txtToolName.clear();
        cboStatus.setValue(" ");
        txtDescription.clear();
        txtModelNum.clear();
        txtSerialNum.clear();
        txtPurchDate.clear();
        txtPrice.clear();
        txtExtraInfo.clear(); 
        txtShopLocation.clear(); 
    }
    
    public void editTool(Tool currentTool)
    {
        txtToolNum.setText(String.valueOf(currentTool.getToolNum()));
        txtToolName.setText(currentTool.getToolName()); 
        cboStatus.setValue(currentTool.getToolStatus());
        txtDescription.setText(currentTool.getToolDescription());
        txtModelNum.setText(currentTool.getModelNum());
        txtSerialNum.setText(currentTool.getSerialNum());
        txtPurchDate.setText(currentTool.getpurchaseDate()); 
        txtPrice.setText(String.valueOf(currentTool.getPurchasePrice()));
        txtExtraInfo.setText(currentTool.getExtraInfo());
        txtShopLocation.setText(currentTool.getShopLocation()); 
    }
    
    public void checkoutTool()
    { 
        
        Tool toolNumber = cboTools.getValue();
        Employee emp = cboEmps.getValue(); 
        Locations locations = cboLocations.getValue(); 
        String checkoutDate = txtCheckoutDate.getText();
        
        CheckoutRecord newRecord = new CheckoutRecord(toolNumber, emp, locations, 
        checkoutDate); 
        
        checkoutArray.add(newRecord);
        olCheckoutRecord.add(newRecord); 
        olAvailableTool.remove(toolNumber); 
        
        toolNumber.setToolStatus("Out");
        
        cboTools.getSelectionModel().clearSelection();
        cboEmps.getSelectionModel().clearSelection();
        cboLocations.getSelectionModel().clearSelection();
        txtCheckoutDate.clear(); 
        
        checkoutListView.refresh();
        
        
    } 
    
    public void checkinTool(CheckoutRecord checkin)
    {  
        //Change Tool Status back to In
        checkin.getToolNumber().setToolStatus("In");
        
        // Add Record to History for potential future research
        checkoutHistory.add(checkin);
        olCheckoutHistory.add(checkin);
        olAvailableTool.add(checkin.getToolNumber()); 
        
        // Remove record from Checkout
        olCheckoutRecord.remove(checkin);
        checkoutArray.remove(checkin);
        
    }
    
}
