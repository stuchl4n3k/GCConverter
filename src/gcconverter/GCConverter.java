package gcconverter;

import gcconverter.component.MainForm;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import org.ipx.player.component.MainForm;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;

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
public class GCConverter {

    public static final String CACHE_DETAILS_BASE_URL = "http://www.geocaching.com/seek/cache_details.aspx?wp=";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    //new org.pushingpixels.substance.api.skin.RavenSkin()
                    javax.swing.UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    JDialog.setDefaultLookAndFeelDecorated(true);
                } catch (UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                MainForm view = new MainForm();
                //view.setExtendedState(JFrame.MAXIMIZED_BOTH);
                view.setLocationByPlatform(true);
                view.setVisible(true);
            }
        });
    }
}
