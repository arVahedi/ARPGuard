package gl4di4tor.ui.form.base;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gladiator on 6/12/2016.
 */
public abstract class BasePanel {
    protected JFrame mainFrame;
    protected JLabel copyRightLbl;
    protected final static String COPY_RIGHT = "CopyRight © Gl4di4tor 2016, All Right Reserved.";

    protected BasePanel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        this.mainFrame = new JFrame("ARP Guard");
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setSize(300, 300);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setLayout(new GridLayout(3, 1));
        this.mainFrame.setAlwaysOnTop(false);

        String className = getLookAndFeelClassName("Nimbus");
        UIManager.setLookAndFeel(className);

        this.copyRightLbl = new JLabel(COPY_RIGHT, JLabel.CENTER);
        this.copyRightLbl.setSize(50, 50);

        //TODO: add copyRightLbl to bottom of mainFrame. and remove code from child;
    }

    protected abstract void prepareGUI() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException;

    public abstract void show();

    private static String getLookAndFeelClassName(String nameSnippet) {
        UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : plafs) {
            if (info.getName().contains(nameSnippet)) {
                return info.getClassName();
            }
        }
        return null;
    }
}
