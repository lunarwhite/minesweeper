/**
 * BombjPanel.java
 * Project Name: Minesweeper
 *
 * Set the bombs interface, using swing.JLabel.
 *
 * It uses MainFrame objects, objects MineLabel,
 * Listener objects and StaticTool objects.
 * And MainFrame uses TimerListener and Panels objects.
 */

package panel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import bean.MineLabel;
import listener.Listener;
import main.MainFrame;
import tools.StaticTool;

public class BombjPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  MineLabel[][] labels = new MineLabel[StaticTool.allrow][StaticTool.allcol];
  private Listener listener;
  private MainFrame mainFrame;

  /**
   * Parameters: MainFrame mainFrame
   * Constructor, initialize mainframe.
   * Return values: null
   */
  public BombjPanel(MainFrame mainFrame) {
    this.mainFrame = mainFrame;
    this.setLayout(new GridLayout(StaticTool.allrow, StaticTool.allcol));
    init();
  }

  /**
   * Parameters: null
   * Initialize jPanel, set icons, labels and other layout.
   * Return values: null
   */
  private void init() {
    listener = new Listener(labels, mainFrame);

    for (int i = 0; i < labels.length; i++) {
      for (int j = 0; j < labels[i].length; j++) {
        labels[i][j] = new MineLabel(i, j);
        labels[i][j].setIcon(StaticTool.iconBlank);
        labels[i][j].addMouseListener(listener);
        this.add(labels[i][j]);
      }
    }
    Border borderLow = BorderFactory.createLoweredBevelBorder();
    Border borderEmpty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    Border borderCom1 = BorderFactory.createCompoundBorder(borderEmpty, borderLow);

    this.setBorder(borderCom1);
    this.setBackground(Color.LIGHT_GRAY);
  }
}
