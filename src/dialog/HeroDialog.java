/**
 * HeroDialog.java
 * Project Name: Minesweeper
 *
 * Set HeroList GUI dialog window with JDialog.
 *
 * It uses HeroBean objects, MainFrame objects and StaticTool objects.
 * And MainFrame uses TimerListener and Panels objects.
 */

package dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import bean.HeroBean;
import main.MainFrame;
import tools.StaticTool;

public class HeroDialog extends JDialog {
  private static final long serialVersionUID = 1L;
  private JPanel panel = null;
  private int level = 0;
  JTextArea textArea = new JTextArea();

  /**
   * Parameters: MainFrame mainFrame
   * Constructor, initialize using mainframe.
   * Return values: null
   */
  public HeroDialog(int level, MainFrame mainFrame) {
    super(mainFrame);
    this.level = level;
    this.setTitle("Statistics");
    this.add(getPanel());
    this.setSize(new Dimension(220, 150));
    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setModal(true);
    this.setVisible(true);
  }

  /**
   * Parameters: null
   * Set and design with JPanel.
   * Return values: JPanel
   */
  public JPanel getPanel() {
    textArea.setEditable(false);
    textArea.setLineWrap(true);

    JScrollPane scrollPane = new JScrollPane(textArea);
    addMessage();
    panel = new JPanel(new BorderLayout());
    panel.add(scrollPane);
    return panel;
  }

  /**
   * Parameters: null
   * Add Hero information to the HeroList.
   * Return values: null
   */
  private void addMessage() {
    if (level == 1) {
      for (HeroBean heroBean : StaticTool.treeSetC) {
        textArea.append(heroBean.toString() + "\n");
      }
    } else if (level == 2) {
      for (HeroBean heroBean : StaticTool.treeSetZ) {
        textArea.append(heroBean.toString() + "\n");
      }
    } else {
      for (HeroBean heroBean : StaticTool.treeSetG) {
        textArea.append(heroBean.toString() + "\n");
      }
    }
  }
}
