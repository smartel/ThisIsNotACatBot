import java.io.File;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.UploadedMedia;

public class ImageTweeter {

	int picCount;
	long[] mediaIds;
	UploadedMedia m1;
	UploadedMedia m2;
	UploadedMedia m3;
	UploadedMedia m4;
	
	public ImageTweeter() {
		picCount = 0;
		mediaIds = new long[Properties.BATCH_SIZE];
		m1 = null;
		m2 = null;
		m3 = null;
		m4 = null;
	}
	
	/**
	 * Adds an image to attach to the tweet.
	 * If we are at the max number of desired images per tweet, then the tweet will be fired off.
	 */
	public boolean addImage(String imagePath, Twitter twitter) throws TwitterException {
		boolean wasSuccessful = true;
		
		// if we are only posting a single image we can skip most of the work (no need to make an album)
		if (Properties.BATCH_SIZE == 1) {
			return legacyAddImage(imagePath, twitter);
		}
		
		
		// if we are building an album we need to make and store media ids
		++picCount;
		File file = new File(imagePath);
		if (m1 == null) {
			m1 = twitter.uploadMedia(file);
			mediaIds[0] = m1.getMediaId();
		} else if (m2 == null) {
			m2 = twitter.uploadMedia(file);
			mediaIds[1] = m2.getMediaId();
		} else if (m3 == null) {
			m3 = twitter.uploadMedia(file);
			mediaIds[2] = m3.getMediaId();
		} else if (m4 == null) {
			m4 = twitter.uploadMedia(file);
			mediaIds[3] = m4.getMediaId();
		}
		
		
		if (picCount == Properties.BATCH_SIZE) {
			String statusMessage = "These cats do not exist. They are a test of my java bot pulling from https://thiscatdoesnotexist.com/";
			StatusUpdate status = new StatusUpdate(statusMessage);
			status.setMediaIds(mediaIds);
			twitter.updateStatus(status);
			
			// batch was tweeted, so reset the image count / media files
			picCount = 0; 
			m1 = null;
			m2 = null;
			m3 = null;
			m4 = null; // if size was less than 4, this may still be null, same for m3 if less than 3, ...
			// the values in mediaIds will be overwritten if reused, but whatever:
			mediaIds[0] = 0;
			mediaIds[1] = 0;
			mediaIds[2] = 0;
			mediaIds[3] = 0;
		}
		
		return wasSuccessful;
	}
	
	public boolean legacyAddImage(String imagePath, Twitter twitter) throws TwitterException {
		boolean wasSuccessful = true;
		
		String statusMessage = "This cat does not exist. This is a test of my java bot.";
		File image = new File(imagePath);
		StatusUpdate status = new StatusUpdate(statusMessage);
		status.setMedia(image);
		twitter.updateStatus(status);
		
		return wasSuccessful;
	}
	
}
