package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.MjpegServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class subVisionSystem extends SubsystemBase {
  private UsbCamera intakeCamera = null;
	public static MjpegServer intakeCameraServer;

	private UsbCamera shooterCamera = null;
	public static MjpegServer shooterCameraServer;

  private ArrayList<String> cameraNames = new ArrayList<>();
	private ArrayList<UsbCamera> cameras = new ArrayList<>();
	private ArrayList<Mat> outputMats = new ArrayList<>();
  
  public subVisionSystem() {
    try {
      String intakeCameraName = "Front-Center";
      intakeCamera = new UsbCamera(intakeCameraName, 0);
      intakeCamera.setVideoMode(PixelFormat.kMJPEG, 320, 240, 15);
      intakeCameraServer = new MjpegServer(intakeCameraName, 1185);
      intakeCameraServer.setSource(intakeCamera);
      
      String shooterCameraName = "Back-Center";
      shooterCamera = new UsbCamera(shooterCameraName, 1);
      shooterCamera.setVideoMode(PixelFormat.kMJPEG, 320, 240, 15);
      shooterCameraServer = new MjpegServer(shooterCameraName, 1187);
      shooterCameraServer.setSource(shooterCamera);

      cameras.addAll(Arrays.asList(
        intakeCamera,
        shooterCamera
      ));

      for (var camera: cameras) {
        camera.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
      }

      cameraNames.addAll(Arrays.asList(
        intakeCameraName,
        shooterCameraName
      ));
    } catch (Exception e) {
      System.out.println("Exception constructing camera in RobotVision");
    }
  }
    
  @Override
  public void periodic() {
    
  }

  public subVisionSystem startCameraStreams() {
		new Thread(() -> {
			CvSink[] cvSinks = Arrays.stream(cameras.toArray()).map(
				camera -> CameraServer.getVideo((VideoSource) camera)).toArray(CvSink[]::new);

			CvSource[] outputStreams = Arrays.stream(cameraNames.toArray()).map(
				cameraName -> CameraServer.putVideo((String) cameraName, 640, 480)).toArray(CvSource[]::new);

			ArrayList<Mat> sources = new ArrayList<>();
			for (int iii = 0; iii < cameras.size(); iii++) {
				sources.add(new Mat());
				outputMats.add(new Mat());
			}

			while(!Thread.interrupted()) {
				for (int iii = 0; iii < cameras.size(); iii++) {
					var source = sources.get(iii);
					var output = outputMats.get(iii);

					cvSinks[iii].grabFrame(source);
					Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
					outputStreams[iii].putFrame(output);
				}
			}
		}).start();
		return this;
	}
}
