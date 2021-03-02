/**
 * UserDefinedListener.java
 * Project Name: Minesweeper
 *
 * Set Listener of user definition with ActionListener.
 *
 * It uses MainFrame objects, UserDefinedDialog objects and StaticTool objects.
 * And MainFrame uses TimerListener and Panels objects.
 */

package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dialog.UserDefinedDialog;
import main.MainFrame;
import tools.StaticTool;

public class UserDefinedListener implements ActionListener {
  UserDefinedDialog userDefinedDialog;
  MainFrame mainFrame;

  /**
   * Parameters: UserDefinedDialog userDefinedDialog, MainFrame mainFrame
   * Constructor, initialize mainframe.
   * Return values: null
   */
  public UserDefinedListener(UserDefinedDialog userDefinedDialog, MainFrame mainFrame) {
    this.mainFrame = mainFrame;
    this.userDefinedDialog = userDefinedDialog;
  }

  /**
   * Parameters: ActionEvent e
   * Recieve input customed data and init.
   * Return values: null
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == userDefinedDialog.getButtonCancer()) {
      userDefinedDialog.dispose();
      mainFrame.reStartGame();
    } else if (e.getSource() == userDefinedDialog.getButtonSure()) {
      String highT = userDefinedDialog.getjTextFieldHigh().getText();
      Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
      Matcher matcher = pattern.matcher(highT);
      int row = 0;
      if (!matcher.matches()) {
        userDefinedDialog.getjLabelMessage().setText("[try again] integer should be in 9~30");
        return;
      } else {
        row = Integer.parseInt(highT);
        if (row < 9 || row > 30) {
          userDefinedDialog.getjLabelMessage().setText("[try again] integer should be in 9~30");
          return;
        }
      }
      String colT = userDefinedDialog.getjTextFieldWide().getText();
      int col = 0;
      try {
        col = Integer.parseInt(colT);
        if (col < 9 || col > 30) {
          userDefinedDialog.getjLabelMessage().setText("[try again] integer should be in 9~30");
          return;
        }
      } catch (Exception e2) {
        userDefinedDialog.getjLabelMessage().setText("[try again] integer should be in 9~30");
        return;
      }

      String mineT = userDefinedDialog.getjTextFieldBomb().getText();
      int mine = 0;
      try {
        mine = Integer.parseInt(mineT);
        if (mine >= row * col) {
          userDefinedDialog.getjLabelMessage().setText("[try again] no more than Height * Weight");
          return;
        }
      } catch (Exception e3) {
        userDefinedDialog.getjLabelMessage().setText("[try again] no more than Height * Weight");
        return;
      }
      userDefinedDialog.dispose();
      StaticTool.allrow = row;
      StaticTool.allcol = col;
      StaticTool.allcount = mine;

      mainFrame.reStartGame();
    }
  }
}
