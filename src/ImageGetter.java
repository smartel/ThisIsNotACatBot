import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;

public class ImageGetter {

	String baseDirectory;
	
	/**
	 * Initialize the ImageGetter and set the directory where we'll store the downloaded images
	 * @param baseDirectory
	 */
	public ImageGetter(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}	
	
	/**
	 * Attempts to download an image and save it to disk, returning the filepath if successful or null otherwise
	 * @return filepath of image, or null if failed to download one
	 */
	public String downloadImageToDisk(String targetUrl) {
		Date date = null;
		String imagePath = null;
		BufferedImage image = null;
		File outputFile = null;
		URL url = null;
		
		// attempt to get the image from the website
		try {
			url = new URL(targetUrl);
			image = ImageIO.read(url);

			date = new Date();
			imagePath = baseDirectory + "\\" + date.getTime() + ".png";
			outputFile = new File(imagePath);
			ImageIO.write(image, "png", outputFile);
		} catch (Exception e) {
			// imagePath stays null, so we'll return as failed
		}

		return imagePath;
	}
	
}
