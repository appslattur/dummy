package ServiceHandler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Arnar Jónsson on 9.2.2015.
 */
public class ServiceBootStarter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent starterIntent = new Intent(context, AppService.class);
        context.startService(starterIntent);
    }
}
