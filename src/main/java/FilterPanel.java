import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FilterPanel extends JPanel {
    public static final int MIDDEL_OF_WINDOWS = 450;
    public static final int BUTTON_START_Y=0;
    public static final int BUTTON_WIDTH=120;
    public static final int BUTTON_HEIGHT=40;
    public static final int PHOTO_BEFORE_X=0;
    public static final int PHOTO_BEFORE_Y=0;
    public static final int PHOTO_AFTER_X=570;
    public static final int PHOTO_AFTER_Y=0;
    public static final int PHOTO_WIDTH=400;
    public static final int PHOTO_HEIGHT=500;



    private JButton colorShiftLeft, colorShiftRight, grayscale, showBorder, negative,usernameSearch;
    private JTextField username;
    private String path="https://www.facebook.com/ ";
    private File before,after;
    BufferedImage beforeFilter,afterFilter;
    BufferedImage bufferedImage;
    private JLabel before1,after1;


    public FilterPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setRequestFocusEnabled(true);
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mori8\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("C:\\Users\\mori8\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
//        ChromeDriver driver=new ChromeDriver();
        colorShiftLeft =new JButton("colorShiftLeft");
        colorShiftRight =new JButton("colorShiftRight");
        grayscale =new JButton("grayscale");
        showBorder =new JButton("showBorder");
        negative =new JButton("negative");
        usernameSearch=new JButton("search username");
        username=new JTextField();
        before1=new JLabel();
        after1=new JLabel();

        this.add(colorShiftLeft);
        this.add(colorShiftRight);
        this.add(grayscale);
        this.add(showBorder);
        this.add(negative);
        this.add(usernameSearch);
        this.add(username);
        this.add(before1);
        this.add(after1);


        colorShiftLeft.setVisible(false);
        colorShiftRight.setVisible(false);
        grayscale.setVisible(false);
        showBorder.setVisible(false);
        negative.setVisible(false);


        usernameSearch.setBounds(MIDDEL_OF_WINDOWS+50,BUTTON_START_Y,150,BUTTON_HEIGHT);
        colorShiftLeft.setBounds(MIDDEL_OF_WINDOWS-10,usernameSearch.getY()+80,BUTTON_WIDTH,BUTTON_HEIGHT);
        colorShiftRight.setBounds(MIDDEL_OF_WINDOWS-10, colorShiftLeft.getY()+60,BUTTON_WIDTH,BUTTON_HEIGHT);
        grayscale.setBounds(MIDDEL_OF_WINDOWS-10, colorShiftRight.getY()+60,BUTTON_WIDTH,BUTTON_HEIGHT);
        showBorder.setBounds(MIDDEL_OF_WINDOWS-10, grayscale.getY()+60,BUTTON_WIDTH,BUTTON_HEIGHT);
        negative.setBounds(MIDDEL_OF_WINDOWS-10, showBorder.getY()+60,BUTTON_WIDTH,BUTTON_HEIGHT);
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
            try{
                beforeFilter=photo(PHOTO_BEFORE_X,PHOTO_BEFORE_Y,PHOTO_WIDTH,500,before1,before,true);
                afterFilter=photo(PHOTO_AFTER_X,PHOTO_AFTER_Y,PHOTO_WIDTH,PHOTO_HEIGHT,after1,after,true);
            }catch (Exception e){

            }
            colorShiftLeft.setVisible(true);
            colorShiftRight.setVisible(true);
            grayscale.setVisible(true);
            showBorder.setVisible(true);
            negative.setVisible(true);
        });
        colorShiftLeft.addActionListener((event) ->{
            Filters.colorShiftLeft(after);

            try{
            afterFilter=photo(PHOTO_AFTER_X,PHOTO_AFTER_Y,PHOTO_WIDTH,PHOTO_HEIGHT,after1,after,true);
            }catch (Exception e){

            }
          repaintPhoto();


        });
        colorShiftRight.addActionListener((event) ->{
            Filters.colorShiftRight(after);
            try{
                afterFilter=photo(PHOTO_AFTER_X,PHOTO_AFTER_Y,PHOTO_WIDTH,PHOTO_HEIGHT,after1,after,true);
            }catch (Exception e){

            }
            repaintPhoto();
        });
        grayscale.addActionListener((event) ->{
            Filters.grayscale(after);
            try{
                afterFilter=photo(PHOTO_AFTER_X,PHOTO_AFTER_Y,PHOTO_WIDTH,PHOTO_HEIGHT,after1,after,true);
            }catch (Exception e){

            }
            repaintPhoto();
        });
        showBorder.addActionListener((event) ->{
            Filters.showBorder(after);
            try{
                afterFilter=photo(PHOTO_AFTER_X,PHOTO_AFTER_Y,PHOTO_WIDTH,PHOTO_HEIGHT,after1,after,true);
            }catch (Exception e){

            }
            repaintPhoto();
        });
        negative.addActionListener((event) ->{
            Filters.negative(after);
            try{
                afterFilter=photo(PHOTO_AFTER_X,PHOTO_AFTER_Y,PHOTO_WIDTH,PHOTO_HEIGHT,after1,after,true);
            }catch (Exception e){

            }
            repaintPhoto();
        });
    }
    public void repaintPhoto(){
        try {
            after =new File("C:\\Users\\mori8\\Desktop\\after.jpeg");
            ImageIO.write(bufferedImage,"jpeg",after);
        }catch (Exception e) {
        }
    }
    public BufferedImage photo(int x, int y, int width, int height, JLabel photo,File file,boolean run) throws IOException {
        photo.setBounds(x, y, width, height);
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = resize(bufferedImage, PHOTO_WIDTH, height);
        BufferedImage buffer = (BufferedImage) image;
        photo.setIcon(new ImageIcon(image));
        if (run) {
            this.add(photo);
        }
        return buffer;

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

                }

            }while (photoSource==null);
            String url =photoSource.getAttribute("src");
            URL photoUrl=new URL(url);
            bufferedImage=ImageIO.read(photoUrl);
            before =new File("C:\\Users\\mori8\\Desktop\\before.jpeg");
            ImageIO.write(bufferedImage,"jpeg",before);
            after =new File("C:\\Users\\mori8\\Desktop\\after.jpeg");
            ImageIO.write(bufferedImage,"jpeg",after);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private BufferedImage resize(BufferedImage img, int width, int height) {

        double scalex = (double) width / img.getWidth();
        double scaley = (double) height / img.getHeight();
        double scale = Math.min(scalex, scaley);

        int w = (int) (img.getWidth() * scale);
        int h = (int) (img.getHeight() * scale);

        Image tmp = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);

        BufferedImage resized = new BufferedImage(w, h, img.getType());
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp,0, 0, null);
        g2d.dispose();

        return resized;
    }





}
