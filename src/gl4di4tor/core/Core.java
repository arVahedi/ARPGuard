package gl4di4tor.core;

import gl4di4tor.ui.form.MainPanel;

import javax.swing.*;
import java.util.Timer;

/**
 * Created by Gladiator on 6/11/2016.
 */
public class Core {
    private static Timer timer;
    private static boolean started = false;

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        startEngine();
        MainPanel.getInstance().show();
    }

    public static void startEngine() {
        try {
            if (!started) {
                timer = new Timer();
                DetectorEngine engine = new DetectorEngine();
                timer.schedule(engine, 0, 1000);
                started = true;
            }
        } catch (Exception ex) {
            started = false;
            ex.printStackTrace();
        }
    }

    public static void stopEngine() {
        try {
            if (started) {
                timer.cancel();
                started = false;
            }
        } catch (Exception ex) {
            started = true;
        }
    }

    public static void toggleStatus() {
        if (started) {
            stopEngine();
        } else {
            startEngine();
        }
    }

    public static boolean isStarted() {
        return started;
    }

    public static void setStarted(boolean started) {
        Core.started = started;
    }
}
