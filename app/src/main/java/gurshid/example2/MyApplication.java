package gurshid.example2;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParsePushBroadcastReceiver;
import com.parse.SaveCallback;

/**
 * Created by Gurshid on 7/24/2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        Parse.initialize(new Parse.Configuration.Builder(this)
//                .applicationId("5mLnfzoi9NpOshGe0PxoQKWQyDaWmZo9ejAy0NAk")
//                .clientKey("u6cHmynCeoDTmaFvbzL5b3aJRdIOoywJSK6Q1wHJ")
//                .server("https://parseapi.back4app.com") // The trailing slash is important.
//                .build()
//        );

        Parse.initialize(new Parse.Configuration.Builder(getBaseContext())
                .applicationId("5mLnfzoi9NpOshGe0PxoQKWQyDaWmZo9ejAy0NAk")
                .clientKey("u6cHmynCeoDTmaFvbzL5b3aJRdIOoywJSK6Q1wHJ")
                .server("https://parseapi.back4app.com")
        .build()
        );

        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParsePush push = new ParsePush();
        push.setChannel("Football");
        push.setMessage("The Giants just scored! It's now 2-2 against the Mets.");
        push.sendInBackground();

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null)
                    Log.d("com.parse.push","Subscribed");
                else Log.e("com.parse.push","Unsibscribed",e);
            }
        });

    }
}
