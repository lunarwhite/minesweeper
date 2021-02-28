/**
 * UserDefinedDialog.java
 * Project Name: Minesweeper
 *
 * Set GUI dialog window to custom game mode with JDialog.
 *
 * It uses UserDefinedListener objects, MainFrame objects and StaticTool objects.
 * And MainFrame uses TimerListener and Panels objects.
 */

package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import listener.UserDefinedListener;
import main.MainFrame;
import tools.StaticTool;

public class UserDefinedDialog extends JDialog {
  private static final long serialVersionUID = 1L;

  private JLabel jlabelHigh = new JLabel("Height: ");
  private JLabel jlabelWide = new JLabel("Width:  ");
  private JLabel jlabelBomb = new JLabel("Num of Mines: ");
  private JLabel jlabelMessage = new JLabel("    ");

  private JTextField jtextFieldHigh;
  private JTextField jtextFieldWide;
  private JTextField jtextFieldBomb;

  private JButton buttonSure;
  private JButton buttonCancer;
  private JPanel panel;

  MainFrame mainFrame;

  /**
   * Parameters: MainFrame mainFrame
   * Constructor, initialize with mainframe.
   * Return values: null
   */
  public UserDefinedDialog(final MainFrame mainFrame) {
    super(mainFrame);
    this.mainFrame = mainFrame;
    jlabelMessage.setFont(new Font("Ubuntu", Font.PLAIN, 12));
    jlabelMessage.setForeground(Color.red);
    this.setTitle("Custom");
    this.add(getPanel());
    this.add(jlabelMessage, BorderLayout.NORTH);
    this.setSize(new Dimension(300, 150));
    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);

    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowDeactivated(WindowEvent e) {
        mainFrame.reStartGame();
      }
    });
    this.setModal(true);
    this.setVisible(true);
  }

  /**
   * Parameters: null
   * Set and design with JPanel.
   * Return values: JPanel
   */
  public JPanel getPanel() {
    panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));

    jtextFieldHigh = new JTextField(StaticTool.allrow + "");
    jtextFieldHigh.setPreferredSize(new Dimension(30, 20));
    jtextFieldHigh.addKeyListener(new KeyListener() {
      @Override
      public void keyReleased(KeyEvent e) {
        String text = jtextFieldHigh.getText();
        Pattern pattern = Pattern.compile("^[0-9]{1,2}$");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
          jlabelMessage.setText("[try again] Only allow positive integer");
          if (text.length() > 2) {
            jtextFieldHigh.setText(text.substring(0, 2));
          }
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {
        char ch = e.getKeyChar();
        if ((ch < '0') || (ch > '9')) {
          jlabelMessage.setText("[try again] Only allow positive integer");
          e.setKeyChar((char) 8);
        } else {
          jlabelMessage.setText("    ");
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {

      }
    });

    Box boxHigh = Box.createHorizontalBox();
    boxHigh.add(jlabelHigh);
    boxHigh.add(jtextFieldHigh);

    jtextFieldWide = new JTextField(StaticTool.allcol + "");
    jtextFieldWide.setPreferredSize(new Dimension(30, 20));
    jtextFieldWide.addKeyListener(new KeyListener() {
      @Override
      public void keyReleased(KeyEvent e) {
        String text = jtextFieldWide.getText();

        Pattern pattern = Pattern.compile("^[0-9]{1,2}$");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
          jlabelMessage.setText("[try again] Only allow positive integer");
          if (text.length() > 2) {
            jtextFieldWide.setText(text.substring(0, 2));
          }
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {

        char ch = e.getKeyChar();
        if ((ch < '0') || (ch > '9')) {
          jlabelMessage.setText("[try again] Only allow positive integer");
          e.setKeyChar((char) 8);
        } else {
          jlabelMessage.setText("    ");
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
      }
    });

    Box boxWide = Box.createHorizontalBox();
    boxWide.add(jlabelWide);
    boxWide.add(jtextFieldWide);

    jtextFieldBomb = new JTextField(StaticTool.bombCount + "");
    jtextFieldBomb.setPreferredSize(new Dimension(30, 20));
    jtextFieldBomb.addKeyListener(new KeyListener() {
      @Override
      public void keyReleased(KeyEvent e) {
        String text = jtextFieldBomb.getText();

        Pattern pattern = Pattern.compile("^[0-9]{1,4}$");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
          jlabelMessage.setText("[try again] Only allow positive integer");
          if (text.length() > 4) {
            jtextFieldBomb.setText(text.substring(0, 4));
          }
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {

        char ch = e.getKeyChar();
        if ((ch < '0') || (ch > '9')) {
          jlabelMessage.setText("[try again] Only allow positive integer");
          e.setKeyChar((char) 8);
        } else {
          jlabelMessage.setText("    ");
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {

      }
    });

    Box boxBomb = Box.createHorizontalBox();
    boxBomb.add(jlabelBomb);
    boxBomb.add(jtextFieldBomb);

    Border border1 = BorderFactory.createEmptyBorder(5, 20, 5, 5);
    Box boxS = new Box(BoxLayout.Y_AXIS);
    boxS.add(boxHigh);
    boxS.add(Box.createVerticalStrut(8));
    boxS.add(boxWide);
    boxS.add(Box.createVerticalStrut(8));
    boxS.add(boxBomb);
    boxS.add(Box.createVerticalStrut(8));
    boxS.setBorder(border1);

    buttonSure = new JButton("  OK  ");

    UserDefinedListener definedListener = new UserDefinedListener(this, mainFrame);
    buttonSure.setPreferredSize(new Dimension(70, 30));
    buttonSure.setMargin(new Insets(0, 2, 0, 2));
    buttonSure.addActionListener(definedListener);

    buttonCancer = new JButton("Cancel");
    buttonCancer.setMargin(new Insets(0, 2, 0, 2));
    buttonCancer.setSize(new Dimension(70, 30));
    buttonCancer.addActionListener(definedListener);

    Box boxT = new Box(BoxLayout.Y_AXIS);
    boxT.add(buttonSure);
    boxT.add(Box.createVerticalStrut(25));
    boxT.add(buttonCancer);
    boxT.setBorder(border1);
    panel.add(boxS);
    panel.add(boxT);

    JPanel jpanel = new JPanel();
    Border border = BorderFactory.createEmptyBorder(3, 15, 5, 15);
    jpanel.setBorder(border);
    jpanel.add(panel);
    return jpanel;
  }

  public JLabel getjLabelMessage() {
    return jlabelMessage;
  }

  public JTextField getjTextFieldHigh() {
    return jtextFieldHigh;
  }

  public JTextField getjTextFieldWide() {
    return jtextFieldWide;
  }

  public JTextField getjTextFieldBomb() {
    return jtextFieldBomb;
  }

  public JButton getButtonSure() {
    return buttonSure;
  }

  public JButton getButtonCancer() {
    return buttonCancer;
  }

}
