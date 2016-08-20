package gl4di4tor.ui.form;

import gl4di4tor.core.Core;
import gl4di4tor.ui.form.base.BasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gladiator on 6/12/2016.
 */
public class MainPanel extends BasePanel {

    private static MainPanel instance = null;

    private JLabel serviceNameLbl;
    private JLabel serviceStatusLbl;
    private JPanel panel;
    //    private JToggleButton toggleEngineBtn;
    private JButton toggleEngineBtn;

    private MainPanel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super();
        prepareGUI();
    }

    public static MainPanel getInstance() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        if (instance == null) {
            instance = new MainPanel();
        }

        return instance;
    }

    @Override
    protected void prepareGUI() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        this.mainFrame.setSize(300, 150);
        this.serviceNameLbl = new JLabel("ARP Spoofing Detector Engine", JLabel.CENTER);
        this.serviceStatusLbl = new JLabel("");

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());

        this.toggleEngineBtn = new JButton("");
        this.toggleEngineBtn.setSize(20, 10);
        changeStatusButtonLabel();
        this.toggleEngineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.toggleStatus();
                changeStatusButtonLabel();
            }
        });
        this.panel.add(this.toggleEngineBtn);

        this.mainFrame.add(this.serviceNameLbl);
//        this.mainFrame.add(serviceStatusLbl);
//        this.mainFrame.add(this.toggleEngineBtn);
        this.copyRightLbl.setFont(new Font(this.copyRightLbl.getFont().getName(), Font.PLAIN, 10));
        this.mainFrame.add(this.copyRightLbl);
    }

    @Override
    public void show() {
        this.mainFrame.setVisible(true);
    }

    public void changeStatusButtonLabel() {
        if (Core.isStarted()) {
            this.toggleEngineBtn.setText("Stop");
        } else {
            this.toggleEngineBtn.setText("Start");
        }
    }

    public JLabel getServiceNameLbl() {
        return serviceNameLbl;
    }

    public void setServiceNameLbl(JLabel serviceNameLbl) {
        this.serviceNameLbl = serviceNameLbl;
    }

    public JLabel getServiceStatusLbl() {
        return serviceStatusLbl;
    }

    public void setServiceStatusLbl(JLabel serviceStatusLbl) {
        this.serviceStatusLbl = serviceStatusLbl;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setToggleEngineBtn(JButton toggleEngineBtn) {
        this.toggleEngineBtn = toggleEngineBtn;
    }

    public JButton getToggleEngineBtn() {
        return toggleEngineBtn;
    }

}
