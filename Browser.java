package bbbbrowser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.net.IDN;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Browser extends JFrame implements HyperlinkListener {
    JEditorPane editorPane = new JEditorPane();

    private final JLabel lblStatus = new JLabel(" ");

    /**
     * The browser
     */
    public Browser() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel browserPanel = new JPanel();
        browserPanel.setLayout(new BorderLayout());

        browserPanel.add(new JLabel("URL: "), BorderLayout.WEST);
        JTextField txtURL = new JTextField("");
        browserPanel.add(txtURL, BorderLayout.CENTER);
        getContentPane().add(browserPanel, BorderLayout.NORTH);
        getContentPane().add(editorPane, BorderLayout.CENTER);
        getContentPane().add(lblStatus, BorderLayout.SOUTH);

        ActionListener al = ae -> {
            try {
                String url = ae.getActionCommand().toLowerCase();
                if (url.startsWith("http://")) url = url.substring(7);
                editorPane.setPage("http://" + IDN.toASCII(url));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(Browser.this, "Browser problem: " + e.getMessage());
            }
        };

        txtURL.addActionListener(al);
        setSize(300, 300);
        setVisible(true);
    }

    /**
     * Updates the hyperlink for the URL you just typed in
     * @param hle the hyperlink event that needs updating
     */
    @Override
    public void hyperlinkUpdate(HyperlinkEvent hle) {
        HyperlinkEvent.EventType evtype = hle.getEventType();
        if (evtype == HyperlinkEvent.EventType.ENTERED)
            lblStatus.setText(hle.getURL().toString());
        else if (evtype == HyperlinkEvent.EventType.EXITED)
            lblStatus.setText(" ");
    }

    /**
     * Main class
     * @param args run it
     */
    public static void main(String[] args) {
        new Browser();
    }
}
