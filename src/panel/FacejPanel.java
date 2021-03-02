/**
 * FacejPanel.java
 * Project Name: Minesweeper
 *
 * Set the main framework interface, using swing.JLabel.
 *
 * It uses MainFrame objects and StaticTool objects.
 * And MainFrame uses TimerListener and Panels objects.
 */

package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.MainFrame;
import tools.StaticTool;

public class FacejPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private JLabel labelCountG = new JLabel();
  private JLabel labelCountS = new JLabel();
  private JLabel labelCountB = new JLabel();
  private JLabel labelTimeG = new JLabel();
  private JLabel labelTimeS = new JLabel();
  private JLabel labelTimeB = new JLabel();
  private JLabel labelFace = new JLabel();

  MainFrame mainFrame;

  /**
   * Parameters: MainFrame mainFrame
   * Constructor, initialize mainframe.
   * Return values: null
   */
  public FacejPanel(MainFrame frame) {
    this.mainFrame = frame;
    this.setLayout(new BorderLayout());
    init();
  }

  /**
   * Parameters: null
   * Initialize jPanel and border, set icons, labels and other layout.
   * Return values: null
   */
  private void init() {
    JPanel panel = new JPanel();
    BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
    panel.setLayout(boxLayout);

    FaceLableListener faceLableListener;
    faceLableListener = new FaceLableListener();
    panel.addMouseListener(faceLableListener);

    Icon icon0 = new ImageIcon("./image/d0.gif");
    Icon icon1 = new ImageIcon("./image/d" + StaticTool.allcount / 10 + ".gif");
    Icon icon2 = new ImageIcon("./image/d" + StaticTool.allcount % 10 + ".gif");
    labelCountB.setIcon(icon0);
    labelCountS.setIcon(icon1);
    labelCountG.setIcon(icon2);

    Icon iconSmile = new ImageIcon("./image/face0.gif");
    labelFace.setIcon(iconSmile);

    labelTimeS.setIcon(icon0);
    labelTimeG.setIcon(icon0);
    labelTimeB.setIcon(icon0);

    panel.add(Box.createHorizontalStrut(2));
    panel.add(labelCountB);
    panel.add(labelCountS);
    panel.add(labelCountG);
    panel.add(Box.createHorizontalGlue());
    panel.add(labelFace);
    panel.add(Box.createHorizontalGlue());
    panel.add(labelTimeB);
    panel.add(labelTimeS);
    panel.add(labelTimeG);
    panel.add(Box.createHorizontalStrut(2));

    Border borderLow = BorderFactory.createLoweredBevelBorder();

    Border borderEmpty = BorderFactory.createEmptyBorder(2, 2, 2, 2);
    Border borderCom1 = BorderFactory.createCompoundBorder(borderLow, borderEmpty);

    panel.setBorder(borderCom1);
    panel.setBackground(Color.LIGHT_GRAY);

    this.add(panel);
    Border borderEmpty2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);

    this.setBorder(borderEmpty2);
    this.setBackground(Color.LIGHT_GRAY);
  }

  public JLabel getLabelFace() {
    return labelFace;
  }

  /**
   * Parameters: int count
   * Set time for different modes.
   * Return values: null
   */
  public void setTime(int count) {
    int timeG = count % 10;
    int timeS = count / 10 % 10;
    int timeB = count / 100;

    labelTimeG.setIcon(StaticTool.time[timeG]);
    labelTimeS.setIcon(StaticTool.time[timeS]);
    labelTimeB.setIcon(StaticTool.time[timeB]);
  }

  /**
   * Parameters: int count
   * Set number for different modes.
   * Return values: null
   */
  public void setNumber(int count) {
    int timeB = 0;
    if (count < 0) {
      timeB = 10;
    } else {
      timeB = count / 100;
    }

    int timeG = Math.abs(count) % 10;
    int timeS = Math.abs(count) / 10 % 10;

    labelCountG.setIcon(StaticTool.time[timeG]);
    labelCountS.setIcon(StaticTool.time[timeS]);
    labelCountB.setIcon(StaticTool.time[timeB]);
  }

  public class FaceLableListener extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
        labelFace.setIcon(StaticTool.downSmileIcon);
        mainFrame.getTimer().stop();
      }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
        mainFrame.reStartGame();
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
        mainFrame.getTimer().start();
        labelFace.setIcon(StaticTool.smileIcon);
      }
    }
  }
}
