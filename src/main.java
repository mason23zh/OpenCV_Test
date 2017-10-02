import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zhengyang on 2017/10/1.
 */
public class main {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    private JFrame frame;
    private JLabel lable;

    public static void main(String[] args) throws Exception {

        main m = new main();
        m.initGUI();
        m.runMainLoop(args);
    }

    private void initGUI(){
        frame = new JFrame("Camera Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 680);
        lable = new JLabel();
        frame.add(lable);
        frame.setVisible(true);
    }

    private void runMainLoop(String[] args) {
        ImageProcessor imageProcessor = new ImageProcessor();
        Mat webcamMatImage = new Mat();
        Image tempImage;

        //Camera set up
        VideoCapture capture = new VideoCapture(0);
        //resolution
        capture.set(Videoio.CAP_PROP_FRAME_WIDTH, 320);
        capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 240);

        if (capture.isOpened()) {
            while ((true)) {
                capture.read(webcamMatImage);
                if (!webcamMatImage.empty()) {
                    tempImage = imageProcessor.toBufferedImage(webcamMatImage);
                    ImageIcon imageIcon = new ImageIcon(tempImage, "Captured Video");
                    lable.setIcon(imageIcon);
                    frame.pack();
                }else{
                    System.out.println("Frame not captured");
                    break;
                }
            }
        }else{
            System.out.println("Couldn't open capture.");
        }
    }

    public static void displayPictureInGUI() {
        //For GUI display image
        String filePath = "F:\\Java_Project_NEW\\Project_0.1/test.jpg";
        Mat newImage = Imgcodecs.imread(filePath);
        if(newImage.dataAddr() == 0){
            System.out.println("Couldn't open file:" + filePath);
        }else {
            ImageViewer imageViewer = new ImageViewer();
            imageViewer.show(newImage, "loaded image");
        }
    }

    public static void exampleDisplay() throws Exception{
        System.out.println("Welcome to OpenCV " + Core.VERSION);

        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(10));
        System.out.println("OpenCV Mat:" + m);

        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));

        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));

        System.out.println("OpenCV Mat data:\n" + m.dump());

        Mat image2 = new Mat(480, 640, CvType.CV_8UC3);
        System.out.println(image2 + "rows" + image2.rows() + "cols" +
                image2.cols() + "elementsize:" + image2.elemSize());

        System.out.println(openFile("F:\\Java_Project_NEW\\Project_0.1/test.jpg").size());
    }

    public static Mat openFile(String fileName) throws Exception{
        Mat newImage = Imgcodecs.imread(fileName);
        if(newImage.dataAddr() == 0){
            throw new Exception("Couldn't open file " + fileName);
        }
        return newImage;
    }

}
