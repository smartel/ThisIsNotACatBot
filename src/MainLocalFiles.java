import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainLocalFiles {

	// ALBUM VERSION
	public static void main(String[] args) {
		ImageTweeter it = null;
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
		
		it = new ImageTweeter();
		
		try {
			
			// Provide a hardcoded image path and see if the tweet makes it. Early testing of the api.
			imagePath = "D:\\catbot\\manual posts\\test3\\test3.png";
			
			if (imagePath != null) {
				if (Properties.DO_TWEET) {
					try {
						it.addImage(imagePath, twitter);
						imagePath = "D:\\catbot\\manual posts\\test3\\test4.png";
						it.addImage(imagePath, twitter);
						imagePath = "D:\\catbot\\manual posts\\test3\\test5.png";
						it.addImage(imagePath, twitter);
						imagePath = "D:\\catbot\\manual posts\\test3\\test6.png";
						it.addImage(imagePath, twitter);
					} catch (TwitterException e) {
						e.printStackTrace();
						System.exit(0);
					}
				}
			}
				
		} catch (Exception e) {
			System.out.println("Unexpected error, abort cat.");
			e.printStackTrace();
		}
	}
	
	/** SINGLE IMAGE
	public static void main(String[] args) {
		ImageTweeter it = null;
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
		
		it = new ImageTweeter();
		
		try {
			
			// Provide a hardcoded image path and see if the tweet makes it. Early testing of the api.
			imagePath = "D:\\catbot\\manual posts\\test1\\test1.jpg";
			
			if (imagePath != null) {
				if (Properties.DO_TWEET) {
					try {
						it.addImage(imagePath, twitter);
					} catch (TwitterException e) {
						e.printStackTrace();
						System.exit(0);
					}
				}
			}
				
		} catch (Exception e) {
			System.out.println("Unexpected error, abort cat.");
			e.printStackTrace();
		}
	}
	*/
	
}
