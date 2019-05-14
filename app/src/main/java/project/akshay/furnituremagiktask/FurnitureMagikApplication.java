package project.akshay.furnituremagiktask;

import android.app.Application;

public class FurnitureMagikApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppUtilities.setDefaultFont(this,"SERIF","fonts/Product-Sans-Bold.ttf");

    }
}
