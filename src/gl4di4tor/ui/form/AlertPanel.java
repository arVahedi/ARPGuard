package gl4di4tor.ui.form;

import gl4di4tor.core.Core;
import gl4di4tor.ui.form.base.BasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gladiator on 6/11/2016.
 */
public class AlertPanel extends BasePanel {
    private JLabel attackerLbl;
    private JPanel panel;
    private JButton okButton;

    private String attackerIp = "UNKNOW";
    private final static String TEMPLATE_MSG = "Attacker ip address is : ";

    public AlertPanel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super();
        prepareGUI();
    }

    public AlertPanel(String attackerIp) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super();
        this.attackerIp = attackerIp;
        prepareGUI();
    }

    @Override
    protected void prepareGUI() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        this.mainFrame.setTitle("Alert - ARP Spoofing attack detected !!");
        this.mainFrame.setSize(400, 200);
        this.mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.mainFrame.setAlwaysOnTop(true);

        this.attackerLbl = new JLabel("", JLabel.CENTER);
        this.attackerLbl.setSize(100, 50);

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());

        this.okButton = new JButton("OK");
        this.okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.startEngine();
                try {
                    MainPanel.getInstance().changeStatusButtonLabel();
                } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
                mainFrame.dispose();
            }
        });
        this.panel.add(this.okButton);


        this.mainFrame.add(attackerLbl);
        this.mainFrame.add(this.panel);
        this.mainFrame.add(this.copyRightLbl);
    }

    @Override
    public void show() {
        this.attackerLbl.setText(TEMPLATE_MSG + this.attackerIp);
        this.copyRightLbl.setText(COPY_RIGHT);
        this.copyRightLbl.setFont(new Font(this.copyRightLbl.getFont().getName(), Font.PLAIN,
                10));

        Core.stopEngine();
        try {
            MainPanel.getInstance().changeStatusButtonLabel();
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        this.mainFrame.setVisible(true);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JLabel getAttackerLbl() {
        return attackerLbl;
    }

    public void setAttackerLbl(JLabel attackerLbl) {
        this.attackerLbl = attackerLbl;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public String getAttackerIp() {
        return attackerIp;
    }

    public void setAttackerIp(String attackerIp) {
        this.attackerIp = attackerIp;
    }

    public JLabel getCopyRightLbl() {
        return copyRightLbl;
    }

    public void setCopyRightLbl(JLabel copyRightLbl) {
        this.copyRightLbl = copyRightLbl;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setOkButton(JButton okButton) {
        this.okButton = okButton;
    }
}
