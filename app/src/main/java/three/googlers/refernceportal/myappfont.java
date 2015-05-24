package three.googlers.refernceportal;

import android.app.Application;

public class myappfont extends Application {
@Override
public void onCreate() {
TypeFaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Burgfest.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
}
} 
