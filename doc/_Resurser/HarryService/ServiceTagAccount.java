/**
 * author: Harry
 * date: 14/10/2012
 * 
 * class: ServiceTagAccount - This is a class for implementing the notification service
 *  It receives a start and stop command from where it is initiated. While running, the
 *  service runs in the background, presenting the ongoing event as a notification.
 * 
 */

package ad.view.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class ServiceTagAccount extends Service{
	private static final String TAG = ServiceTagAccount.class.getSimpleName();
	private Updater updater;
	public boolean isRunning = false;
	private static final int NOTIFICATION_ID = 1;
	
	// Below are a lot of auto-generated comments
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		updater = new Updater();
		
		Log.d(TAG, "onCreate'd"); // Unnecessary code for spotting event in LogCat
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 */
	@Override
	public synchronized void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		initNotification();
		super.onStart(intent, startId);
		
		//start the Updater
		if (!this.isRunning){
		updater.start();
		this.isRunning = true;
		}//end if this.isRunning
		//Loop is not necessary it's able to check for different statuses i.e. an unread message
		// maybe to change the icon with a red number on it?? prehaps.. 
		
		Log.d(TAG, "onStart'd"); // Unnecessary code for spotting event in LogCat
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 * 
	 * This method starts when the service stops, it casts an exception and interrupts
	 * the service by calling cancelNotification method. 
	 */
	@Override
	public synchronized void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		//stop the Updater
		if (this.isRunning){
		updater.interrupt();
		// Cast interruption if it's running. interrupt the updater.  
		}//end if this.isRunning
		
		updater = null; // One so-called clean up.. maybe not necessary
		cancelNotification();
		Log.d(TAG, "onDestroy'd"); // Unnecessary code for spotting event in LogCat
	}
	
// ------ Updater Thread
	class Updater extends Thread {
		public boolean isRunning = false;
		static final long DELAY = 3000; //updating every 3000 millisecond = 3 seconds
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			isRunning = true;
			while (isRunning) {
				// TODO Auto-generated method stub
				
				try {
					Log.d(TAG, "Updater run'ing"); // Unnecessary code for spotting event in LogCat
					
					//sleep
					Thread.sleep(DELAY); //*temporary* delay to observe result
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// interrupted
					isRunning = false;
					// Set isRunning to false, which ends this method
				} 
			}//WhileLoop
		}
		
		public boolean isRunning(){
			return this.isRunning;
		}
	}// ------ End of Updater Thread
	
	// ------ initNotification method
	private void initNotification() {
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		
		int icon = R.drawable.ic_launcher;
		CharSequence tickerText = "Hej Stefan :D";
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		// Set icon, and tickertext and put out the notification
		
		notification.flags = Notification.FLAG_ONGOING_EVENT;
		Context context = getApplicationContext();
		CharSequence contentTitle = "AppsolutDistribute";
		CharSequence contentText = "Release the Kraken!";
		// Flag the ongoing event with carefully planned title and content text
		
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, notification);
		// Det var tv� blinda programmerare... och en kunde C
		// get it? :D :D :D  ( C == se ) 
	}
	// ------ End of Notification method
	
	// ------ cancelNotification method
	private void cancelNotification() {
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		mNotificationManager.cancel(NOTIFICATION_ID);
	}
	// ------ end of cancelNotification method
}
