import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by Zhengyang on 2017/10/2.
 */
public class VideoCaputreWithGUI{
    private JFrame frame;
    private JLabel label;


     void initGUI(JFrame frame,JLabel lable) {
        this.frame =frame;
        this.label = lable;
        frame = new JFrame("Camera Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 680);
        lable = new JLabel();
        frame.add(lable);
        frame.setVisible(true);
    }



     void runMainLoop(String[] args)  {
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
                    label.setIcon(imageIcon);
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
}
