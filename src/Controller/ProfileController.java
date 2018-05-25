/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Services.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author samih
 */
public class ProfileController implements Initializable {
    CRUD_USER CU;
    public String pseudo;
 @FXML Label NomLabel;
 @FXML Label PrenomLabel;
 @FXML Label EmailLabel;
 @FXML Label NumLabel;
 @FXML ImageView ImageUser;
    @FXML
    private ScrollPane SP;
    @FXML
    private AnchorPane AP;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
      
        
        
        
        
        String PathImage = InscriController.current_user.getPhoto_profile();
//        System.out.println("file:\\\\\\C:\\wamp64\\www\\Images\\"+PathImage);
       // System.out.println(PathImage);
  NomLabel.setText(InscriController.current_user.getNomuser());
  PrenomLabel.setText(InscriController.current_user.getPrenomuser());
  EmailLabel.setText(InscriController.current_user.getEmailuser());
  NumLabel.setText(InscriController.current_user.getNumerouser());
  if(PathImage!=null)
  ImageUser.setImage(new Image("file:\\\\\\C:\\wamp64\\www\\Images\\"+PathImage, 350,272, true, true));
       
 
  
    }
 
      @FXML
    private void go_Back_Menu(ActionEvent event) throws IOException {
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Menu.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        
        
    MenuController ncont = fxmlLoader.<MenuController>getController();
    
        Scene scene = new Scene(root,800,600);
        Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow() ;
        
        stage.setScene(scene);
        stage.show();
        
    }

     @FXML
    private void upload_action(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Open Resource File");
Stage stage=new Stage();
File file = fileChooser.showOpenDialog(stage);
if(file!=null){
    Upload u=new Upload();
  u.upload(file);
    String Fileurl="file:\\\\\\"+file.getPath();
    Fileurl=Fileurl.replace("\\", "/");
    ImageUser.setImage(new Image(Fileurl, 350,272, true, true));
    CRUD_USER crud = new CRUD_USER();
    crud.insertPhoto(file.getName(),InscriController.current_user);
    InscriController.current_user.setPhoto_profile(file.getName());
 
    }
     
    }
  
    
}
