package gcconverter;

import java.awt.SplashScreen;

/**
 *  Copyright 2012 Petr Stuchlik

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

/**
 *
 * @author stuchl4n3k
 */
public class Main {

    public static final String CACHE_DETAILS_BASE_URL = "http://www.geocaching.com/seek/cache_details.aspx?wp=";
    private static SplashScreen splash;

    private static void splashInit() {
        splash = SplashScreen.getSplashScreen();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // initialize splash overlay drawing parameters
        splashInit();

        // bootstrap the application
        Application app = new Application();
        app.bootStrap();

        // Create and display the form
        app.createView();
    }
}
