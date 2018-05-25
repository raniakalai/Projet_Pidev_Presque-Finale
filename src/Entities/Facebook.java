/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 *
 * @author Fedi
 */
public class Facebook {
    public static User FacebookInfo()
    {
                String domain = "http://radixcode.com";
        String appId = "1536537523327125";
       
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,email";
        

                //+ 
        User user=null;
        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        Boolean test=true;
        while(test){
       
            if(!driver.getCurrentUrl().contains("facebook.com")){
                test=false;
            String url = driver.getCurrentUrl();
                       System.out.println(url);
           accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
           //System.out.println(accessToken);
            driver.quit();
           
               FacebookClient fbClient = new DefaultFacebookClient(accessToken);
               user = fbClient.fetchObject("me",User.class,Parameter.with("fields", "name,email,first_name,last_name,gender"));
               // System.out.println(user.getEmail()+user.getGender());
                System.out.println(user.getName());
                return user;
            }
       
        }
        return user;
    }
}
