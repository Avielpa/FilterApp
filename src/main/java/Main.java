import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.w3c.dom.Document;
import org.w3c.dom.css.RGBColor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class Main extends JFrame {

    public static final int HEIGHT = 600 ;
    public static final int WIDTH = 1000;
    private Document document;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        FilterPanel workPanel = new FilterPanel(0, 0, WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(workPanel);
        this.setSize(WIDTH, HEIGHT);
    }
//        public static void main(String[] args) {
//        System.setProperty("webdriver.chrome.driver","C:\\Users\\mori8\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        ChromeOptions options=new ChromeOptions();
////        options.addArguments("C:\\Users\\new\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
//        ChromeDriver driver=new ChromeDriver(options);
//        String facebookLink="https://www.facebook.com/shaigivati";
//        try {
//            driver.get(facebookLink);
//            WebElement profilePictureLink=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div/div[1]/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div/a"));
//            profilePictureLink.click();
//            WebElement photoSource=null;
//            do {
//                try {
//                    photoSource=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div[2]/div/div[1]/div/div[2]/div/div/div/img"));
//                }catch (Exception e){
//                    System.out.println("error");
//                }
//
//            }while (photoSource==null);
//            String url =photoSource.getAttribute("src");
//            URL photoUrl=new URL(url);
//            System.out.println(photoUrl);
//            BufferedImage bufferedImage= ImageIO.read(photoUrl);
//            File output =new File("C:\\Users\\mori8\\Desktop\\output.jpeg");
//            ImageIO.write(bufferedImage,"jpeg",output);
//
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//
//    }
}
