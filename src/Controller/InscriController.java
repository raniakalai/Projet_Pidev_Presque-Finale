/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Facebook;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import Entities.user;
import Services.CRUD_USER;
import Utiles.Basededonne;
import com.restfb.types.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author samih
 */
public class InscriController implements Initializable {
     Connection cnx = Basededonne.getInstance().getConnection();
 @FXML JFXTextField Pseudo ;
 @FXML JFXPasswordField Mot_passe ;
 @FXML Label LabelErreur ;
 @FXML Label labelBann;
 @FXML Pane pane_mot_passe ;
 @FXML AnchorPane MainAnchor;
 
 public static user current_user;
 CRUD_USER cr = new CRUD_USER();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
      
     
    }   
   
      @FXML private void connection_user(ActionEvent event)throws SQLException, IOException
    {
        
       cr.authentification(Pseudo.getText(), Mot_passe.getText());
        if (current_user != null )
        {
        if((current_user.getRole()==0) || (current_user.getRole()==3))
        {
           
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Menu.fxml"));
        Parent root = (Parent)fxmlLoader.load();
       
    MenuController ncont = fxmlLoader.<MenuController>getController();    
    Scene scene = new Scene(root,800,600);
        Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow() ;
        
        stage.setScene(scene);
        stage.show();  
        }
       
        else if (current_user.getRole()==2){
            labelBann.setVisible(true);
            Pseudo.clear();
            Mot_passe.clear();
        }
        }
        else 
        {
             TrayNotification tn = new TrayNotification("Oups","Vos informations sont incorrects", NotificationType.ERROR);
        tn.showAndDismiss(Duration.millis(3000));
            LabelErreur.setVisible(true);
            Pseudo.clear();
            Mot_passe.clear();
        }

    }
        

    @FXML
    private void NewTest(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/FXMLDocument.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        
    FXMLDocumentController ncont = fxmlLoader.<FXMLDocumentController>getController();
        Scene scene = new Scene(root,800,600);
        Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow() ;
        
        stage.setScene(scene);
        stage.show();
        
    }
      @FXML
    private void go_mot_passe(ActionEvent event) throws IOException {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/Views/EnvoieMotPasse.fxml"));
      pane_mot_passe.getChildren().add(newLoadedPane);
      MainAnchor.setDisable(true);
        
    }
    @FXML
    private void fb_connect(MouseEvent event) throws SQLException, IOException {
        User fb = Facebook.FacebookInfo();
        CRUD_USER su = new CRUD_USER();
        user user = new user(fb.getFirstName(),fb.getLastName(),fb.getName(),fb.getEmail(),0);
        user authen = su.authentification(user.getPseudouser(),user.getPassword());
        System.out.println(authen);
        if (authen == null) {
            su.insertFb(user);
            user=su.authentification(user.getPseudouser(),"");
            System.out.println(user);
            current_user=user ;
            redirect(event);
         
        }
    }
    public void redirect(Event event) throws IOException
    {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Menu.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        TrayNotification tn = new TrayNotification("Bienvenue","Connexion avec succes", NotificationType.SUCCESS);
        tn.showAndDismiss(Duration.millis(3000));
    MenuController ncont = fxmlLoader.<MenuController>getController();    
    Scene scene = new Scene(root,800,600);
        Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow() ;
        stage.setScene(scene);
        stage.show();  
    }
}
