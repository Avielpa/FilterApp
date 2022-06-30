import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;

public class FilterPanel extends JPanel {
    public static final int MIDDEL_OF_WINDOWS = 450;
    public static final int BUTTON_START_Y=20;


    private JButton filter1,filter2,filter3,filter4,filter5 ,usernameSearch;
    private JTextField username;
    private String path="https://www.facebook.com/ ";

    public FilterPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setRequestFocusEnabled(true);
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mori8\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("C:\\Users\\mori8\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
//        ChromeDriver driver=new ChromeDriver();
        filter1=new JButton("filter 1");
        filter2=new JButton("filter 2");
        filter3=new JButton("filter 3");
        filter4=new JButton("filter 4");
        filter5=new JButton("filter 5");
        usernameSearch=new JButton("search username");
        username=new JTextField();


        this.add(filter1);
        this.add(filter2);
        this.add(filter3);
        this.add(filter4);
        this.add(filter5);
        this.add(usernameSearch);
        this.add(username);

        usernameSearch.setBounds(MIDDEL_OF_WINDOWS+50,BUTTON_START_Y,150,50);
        filter1.setBounds(MIDDEL_OF_WINDOWS,usernameSearch.getY()+80,100,50);
        filter2.setBounds(MIDDEL_OF_WINDOWS,filter1.getY()+60,100,50);
        filter3.setBounds(MIDDEL_OF_WINDOWS,filter2.getY()+60,100,50);
        filter4.setBounds(MIDDEL_OF_WINDOWS,filter3.getY()+60,100,50);
        filter5.setBounds(MIDDEL_OF_WINDOWS,filter4.getY()+60,100,50);
        username.setBounds(MIDDEL_OF_WINDOWS-50,BUTTON_START_Y+10,100,30);

        usernameSearch.addActionListener((event) ->{
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\mori8\\Downloads\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("C:\\Users\\mori8\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
            ChromeDriver driver=new ChromeDriver();
            path=path+username.getText();
            driver.get(path);
            driver.manage().window().maximize();
            ImagemFileOnSite(driver);

        });



    }

    public void ImagemFileOnSite(ChromeDriver driver) {
        try {
            driver.get(path);
            WebElement profilePictureLink=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div/div[1]/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div/a"));
            profilePictureLink.click();
            WebElement photoSource=null;
            do {
                try {
                    photoSource=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[4]/div/div/div[1]/div/div[3]/div[2]/div/div[2]/div/div[1]/div/div[2]/div/div/div/img"));
                }catch (Exception e){
                    System.out.println("error");
                }

            }while (photoSource==null);
            String url =photoSource.getAttribute("src");
            URL photoUrl=new URL(url);
            BufferedImage bufferedImage=ImageIO.read(photoUrl);
            File before =new File("C:\\Users\\mori8\\Desktop\\before.jpeg");
            ImageIO.write(bufferedImage,"jpeg",before);
            File after =new File("C:\\Users\\mori8\\Desktop\\after.jpeg");
            ImageIO.write(bufferedImage,"jpeg",after);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }





}
