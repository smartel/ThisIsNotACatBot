import java.text.SimpleDateFormat;
import java.util.Date;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Main {

	public static void main(String[] args) {
		SimpleDateFormat sdf = null;
		ImageGetter ig = null;
		ImageTweeter it = null;
		Date date = null;
		String imagePath = null;
		ConfigurationBuilder cb = null;
		TwitterFactory tf = null;
		Twitter twitter = null;

		// hardcoding properties for now as final statics in Properties, until we set up either input args or a properties file
		cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(Properties.CONSUMER_KEY);
		cb.setOAuthConsumerSecret(Properties.CONSUMER_SECRET);
		cb.setOAuthAccessToken(Properties.ACCESS_TOKEN);
		cb.setOAuthAccessTokenSecret(Properties.ACCESS_SECRET);
		tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
		
		ig = new ImageGetter(Properties.BASE_DIR);
		it = new ImageTweeter();
		sdf = new SimpleDateFormat("MM-dd HH:mm:ss z");
		
		try {
			// Loop to continuously download and tweet images
			while (true) {
				// reset fields
				imagePath = null;
				date = null;
				
				// attempt to download an image,
				// returning the filepath of the image saved on disk.
				// if it fails to grab an image, the path will be null.
				imagePath = ig.downloadImageToDisk(Properties.TARGET_URL);
				
				// upon successful image download, attempt to tweet it
				if (imagePath != null) {
					date = new Date(System.currentTimeMillis());
					System.out.println(sdf.format(date) + " | Debug: image saved to: " + imagePath);
					
					if (Properties.DO_TWEET) {
						try {
							it.addImage(imagePath, twitter);
						} catch (TwitterException e) {
							e.printStackTrace();
							System.exit(0);
						}
					}
				}
				
				Thread.sleep(Properties.LOOP_DELAY);
			}
		} catch (Exception e) {
			System.out.println("Unexpected error, abort cat.");
			e.printStackTrace();
		}
	}
	
}
