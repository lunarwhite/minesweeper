/**
 * TimerListener.java
 * Project Name: Minesweeper
 *
 * Record time of the game with ActionListener.
 *
 * It uses MainFrame objects and StaticTool objects.
 * And MainFrame uses TimerListener and Panels objects.
 */

package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MainFrame;
import tools.StaticTool;

public class TimerListener implements ActionListener {
  MainFrame mainFrame;

  /**
   * Parameters: MainFrame mainFrame
   * Constructor, initialize mainframe.
   * Return values: null
   */
  public TimerListener(MainFrame mainFrame) {
    this.mainFrame = mainFrame;
  }

  /**
   * Parameters: ActionEvent e
   * Set time in timer.Handle the biggest time count.
   * Return values: null
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    StaticTool.timecount++;
    if (StaticTool.timecount > 999) {
      StaticTool.timecount = 999;
    }
    mainFrame.getFacejPanel().setTime(StaticTool.timecount);
  }
}
