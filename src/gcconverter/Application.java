package gcconverter;

import gcconverter.component.MainForm;
import gcconverter.control.MainController;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;

/**
 *
 * @author stuchl4n3k
 */
public class Application {
    
    private MainController mainController;
    private MainForm mainForm;
    
    public Application() {
    }
    
    public void bootStrap() {
        mainController = new MainController(this);
    }
    
    public void createView() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                // set Look and Feel
                try {
                    javax.swing.UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    JDialog.setDefaultLookAndFeelDecorated(true);
                } catch (UnsupportedLookAndFeelException ex) {
                }

                mainForm = new MainForm(mainController);
                mainForm.setExtendedState(JFrame.MAXIMIZED_BOTH);
                mainForm.setLocationByPlatform(true);
                mainForm.setVisible(true);
            }
        });
    }
    
    public void exit() {
        System.exit(0);
    }
    
}
