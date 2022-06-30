import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Filters {
    public static final int RGB_COLOR = 255;
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int BLUE = 3;

//    public static void main(String[] args) {
//        File imageFile = new File("C:\\Users\\IMOE001\\IdeaProjects\\untitled2\\images.png");
//        if (imageFile.exists()) {
//            System.out.println("File found");
//        } else {
//            System.out.println("Wrong patch!");
//        }
//        negative(imageFile);
//        colorShiftRight(imageFile);
//        colorShiftLeft(imageFile);
//        mirror(imageFile);
//        grayscale(imageFile);
//        eliminateRedGreenBlue(imageFile, 1);
//        eliminateRedGreenBlue(imageFile, 2);
//        eliminateRedGreenBlue(imageFile, 3);
//        showBorder(imageFile);
//    }

    public static void negative(File image) {
        try {
            BufferedImage image1 = ImageIO.read(image);
            for (int x = 0; x < image1.getWidth(); x++) {
                for (int y = 0; y < image1.getHeight(); y++) {
                    int pixel = image1.getRGB(x, y);
                    Color pixelColor = new Color(pixel);
                    int red = RGB_COLOR - pixelColor.getRed();
                    int blue = RGB_COLOR - pixelColor.getBlue();
                    int green = RGB_COLOR - pixelColor.getGreen();
                    Color newColor = new Color(red, green, blue);
                    image1.setRGB(x, y, newColor.getRGB());

                }
            }

            File after = new File("C:\\Users\\mori8\\Desktop\\after.jpeg");
            ImageIO.write(image1, "jpeg", after);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void colorShiftRight(File image) {
        try {
            BufferedImage image1 = ImageIO.read(image);
            for (int x = 0; x < image1.getWidth(); x++) {
                for (int y = 0; y < image1.getHeight(); y++) {
                    int pixel = image1.getRGB(x, y);
                    Color pixelColor = new Color(pixel);
                    int blue = pixelColor.getRed();
                    int green = pixelColor.getBlue();
                    int red = pixelColor.getGreen();
                    Color newColor = new Color(red, green, blue);
                    image1.setRGB(x, y, newColor.getRGB());

                }
            }

            File file = new File("C:\\Users\\mori8\\Desktop\\after.jpeg");
            ImageIO.write(image1, "jpeg", file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void colorShiftLeft(File image) {
        try {
            BufferedImage image1 = ImageIO.read(image);
            for (int x = 0; x < image1.getWidth(); x++) {
                for (int y = 0; y < image1.getHeight(); y++) {
                    int pixel = image1.getRGB(x, y);
                    Color pixelColor = new Color(pixel);
                    int green = pixelColor.getRed();
                    int red = pixelColor.getBlue();
                    int blue = pixelColor.getGreen();
                    Color newColor = new Color(red, green, blue);
                    image1.setRGB(x, y, newColor.getRGB());

                }
            }

            File file = new File("C:\\Users\\mori8\\Desktop\\after.jpeg");
            ImageIO.write(image1, "jpeg", file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void mirror(File image) {
        try {
            BufferedImage mirror = ImageIO.read(image);
            BufferedImage mirror1 = new BufferedImage(mirror.getWidth(), mirror.getHeight(), 1);
            for (int x = 0; x < mirror.getWidth(); x++) {
                for (int y = 0; y < mirror.getHeight(); y++) {
                    int pixel = mirror.getRGB(x, y);
                    Color pixelColor = new Color(pixel);
                    int red = pixelColor.getRed();
                    int blue = pixelColor.getBlue();
                    int green = pixelColor.getGreen();
                    Color newColor = new Color(red, green, blue);
                    mirror1.setRGB(mirror.getWidth() - 1 - x, y, newColor.getRGB());
                }
            }

            File file = new File("C:\\Users\\mori8\\Desktop\\after.jpeg");
            ImageIO.write(mirror, "jpeg", file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void grayscale(File image) {
        try {
            BufferedImage mirror = ImageIO.read(image);
            for (int x = 0; x < mirror.getWidth(); x++) {
                for (int y = 0; y < mirror.getHeight(); y++) {
                    int pixel = mirror.getRGB(x, y);
                    Color pixelColor = new Color(pixel);
                    int red = pixelColor.getRed();
                    int blue = pixelColor.getBlue();
                    int green = pixelColor.getGreen();
                    int everage = (red + blue + green) / 3;
                    Color newColor = new Color(everage, everage, everage);
                    mirror.setRGB(x, y, newColor.getRGB());
                }

            }

            File file = new File("C:\\Users\\mori8\\Desktop\\after.jpeg");
            ImageIO.write(mirror, "jpeg", file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void eliminateRedGreenBlue(File image, int color) {
        try {
            BufferedImage mirror = ImageIO.read(image);
            for (int x = 0; x < mirror.getWidth(); x++) {
                for (int y = 0; y < mirror.getHeight(); y++) {
                    int pixel = mirror.getRGB(x, y);
                    Color pixelColor = new Color(pixel);
                    int red = pixelColor.getRed();
                    int blue = pixelColor.getBlue();
                    int green = pixelColor.getGreen();
                    if (color == RED) {
                        red = 0;
                    }
                    if (color == GREEN) {
                        green = 0;
                    }
                    if (color == BLUE) {
                        blue = 0;
                    }
                    Color newColor = new Color(red, green, blue);
                    mirror.setRGB(x, y, newColor.getRGB());
                }

            }

            File file = new File("C:\\Users\\mori8\\Desktop\\after.jpeg");
            ImageIO.write(mirror, "jpeg", file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void showBorder(File image) {
        try {
            BufferedImage mirror = ImageIO.read(image);
            Color previousPixel = null;
            for (int x = 0; x < mirror.getWidth(); x++) {
                for (int y = 0; y < mirror.getHeight(); y++) {
                    int pixel = mirror.getRGB(x, y);
                    Color pixelColor = new Color(pixel);
                    if (previousPixel != null && !isPixelSimilar(previousPixel, pixelColor)) {
                        mirror.setRGB(x, y, Color.YELLOW.getRGB());
                    }
                    previousPixel = pixelColor;
                }

            }

            File file = new File("C:\\Users\\mori8\\Desktop\\after.jpeg");
            ImageIO.write(mirror, "jpeg", file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean isPixelSimilar(Color color1, Color color2) {
        boolean similar = false;
        int redDifret = Math.abs(color1.getRed() - color2.getRed());
        int greenDifret = Math.abs(color1.getGreen() - color2.getGreen());
        int blueDifrent = Math.abs(color1.getBlue() - color2.getBlue());
        if (redDifret + greenDifret + blueDifrent < 90) {
            similar = true;
        }
        return similar;
    }

}