//package msk.pobazar.wcquiz.data_remote;
//
//import android.util.Log;
//
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.FirebaseInstanceIdService;
//
//import static msk.pobazar.wcquiz.presentation.MainActivity.LOG;
//
//public class FIService extends FirebaseInstanceIdService {
//    @Override
//    public void onTokenRefresh() {
//        // Get updated InstanceID token.
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d(MainActivity.LOG, "Refreshed token: " + refreshedToken);
//
//        // If you want to send messages to this application instance or
//        // manage this apps subscriptions on the server side, send the
//        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);
//    }
//    private void sendRegistrationToServer(String token) {
//        // Add custom implementation, as needed.
//    }
//}