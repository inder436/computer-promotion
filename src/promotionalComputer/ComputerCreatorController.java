/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package promotionalComputer;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author Mou Chen
 */
public class ComputerCreatorController implements Initializable {
    
    @FXML
    private TextField idTextField;

    @FXML
    private TextField stockTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private DatePicker productDatePicker;
   
    @FXML
    private ComboBox<String> brandChioceBox;

    @FXML
    private Label sumMsgLabel;

    @FXML
    private Label eroMsgLabel;
    
    @FXML
    private RadioButton trueRadioBtn;

    @FXML
    private RadioButton falseRadioBtn;
    
    @FXML
    private ImageView brandImageView;

    @FXML
    private ImageView touchScreenImageView;
    
    @FXML
    private Button getPriceBtn;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //configure the choicebox
        brandChioceBox.getItems().add("Dell");
        brandChioceBox.getItems().add("Acer");
        brandChioceBox.getItems().add("Lenovo");
        brandChioceBox.getItems().add("Hp");
        brandChioceBox.getItems().add("Asus");
        brandChioceBox.getItems().add("Apple");
        
        eroMsgLabel.setText("");
        sumMsgLabel.setText("");      
        

    }   
    
    /**
     * brand changer method is use to change the brand image during chooseing different brand.
     */
    @FXML
    public void brandChanger()
    {
        String brand = brandChioceBox.getValue();
        if (brand.equalsIgnoreCase("dell"))
            brandImageView.setImage(new Image("./images/dell.png"));
        else if (brand.equalsIgnoreCase("acer"))
            brandImageView.setImage(new Image("./images/acer.jpg"));
        else if (brand.equalsIgnoreCase("Lenovo"))
            brandImageView.setImage(new Image("./images/lenovo.jpg"));
        else if (brand.equalsIgnoreCase("hp"))
            brandImageView.setImage(new Image("./images/hp.jpg"));
        else if (brand.equalsIgnoreCase("ausu"))
            
            brandImageView.setImage(new Image("./images/asus.jpg"));
        else
            brandImageView.setImage(new Image("./images/apple.jpg"));
    }

    /**
     * setTrue and setFalse method just use to make sure radio btn can be use correctly.
     * when trueRadioBtn is true falseRadioBtn should be false. And in the application, there should be a picture shows that the computer is touch screen.
     * when falseRadioBtn is true trueRadioBtn should be false.
     */
  @FXML    
    public void setTrue()
    {
        trueRadioBtn.setSelected(true);
        falseRadioBtn.setSelected(false);
        touchScreenImageView.setImage(new Image("./images/touch screen.jpg"));
    }
    
    public void setFalse()
    {
        trueRadioBtn.setSelected(false);
        falseRadioBtn.setSelected(true);
        touchScreenImageView.setImage(null);
    }
    
    /**
     * getPriceBtnPushed method just use to get the error message of the input.
     **/
    
    @FXML
    public void getPriceBtnPushed()
    {
        if(!validationPassed()){
            return;
        }
        else
        {
             //try catch block use to get the value of other inputs and validate whether it is filled
            try{
                PromotionalComputer newPromotionalComputer = new PromotionalComputer(
                        Integer.valueOf(this.idTextField.getText()),
                        Integer.valueOf(this.stockTextField.getText()),
                        Double.parseDouble(this.priceTextField.getText()),
                        this.brandChioceBox.getValue(),
                        this.productDatePicker.getValue());
                //print the resulte message
                System.out.println(newPromotionalComputer);
                eroMsgLabel.setText("");
                sumMsgLabel.setText(newPromotionalComputer.toString());
            } catch (IllegalArgumentException e)
            {
                //print the error message catch from Promotionalcomputer.java file.
                sumMsgLabel.setText("");
                eroMsgLabel.setText(String.valueOf(e.getMessage()));  
            }            
        }
    }
    
    public boolean validationPassed(){
        
        //validate the empty value of id number, stock and price
        if (this.idTextField.getText().isEmpty())
        {
            sumMsgLabel.setText("");
            eroMsgLabel.setText("ID number cannot be empty"); 
            return false;
        }
        else{
            try{
                Integer.valueOf(idTextField.getText());
            }
            catch(Exception e){
                sumMsgLabel.setText("");
                eroMsgLabel.setText("ID number should be numbers"); 
                return false;
            }
        }
        
        if (this.stockTextField.getText().isEmpty())
        {
            sumMsgLabel.setText("");
            eroMsgLabel.setText("stock number cannot be empty"); 
            return false;
        }
        else{
            try{
                Integer.valueOf(stockTextField.getText());
            }
            catch(Exception e){
                sumMsgLabel.setText("");
                eroMsgLabel.setText("stock should be a whole number"); 
                return false;
            }
        }
        
        if (this.priceTextField.getText().isEmpty())
        {
            sumMsgLabel.setText("");
            eroMsgLabel.setText("Price cannot be empty"); 
            return false;
        }
        else{
            try{
                Double.valueOf(priceTextField.getText());
            }
            catch(Exception e){
                sumMsgLabel.setText("");
                eroMsgLabel.setText("The price must contain numbers only"); 
                return false;
            }
        }
        
        //if did not choose a date,there will throw a message shows date must be chosen.
        if (this.productDatePicker.getValue() == null)
        {
            sumMsgLabel.setText("");
            eroMsgLabel.setText("You need to set the product date!");
        }
        
        //if did not choose a brand,there will throw a message shows brand must be chosen.
        if (this.brandChioceBox.getValue() == null)
        {
            sumMsgLabel.setText("");
            eroMsgLabel.setText("You need choose a brand for the computer!");
        }
        
        //if trueRadioBtn or falseRadioBtn is chosen, countinue. Otherwise throw a error message to prompt user.
        try{
            if(trueRadioBtn.getText().equalsIgnoreCase("true")||falseRadioBtn.getText().equalsIgnoreCase("true")){ 
            }
        }
        catch(Exception e)
                {
                    sumMsgLabel.setText("");
                    eroMsgLabel.setText("You need to choose whether it is touch screen or not!");
                }  
        
        return true;
    
    }
}


    

            

