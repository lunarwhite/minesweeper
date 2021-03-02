/**
 * Listener.java
 * Project Name: Minesweeper
 *
 * Actions listener for the player.
 *
 * It uses MainFrame objects, StaticTool objects,
 * HeroBean objects, LayBomb objects and MineLabel objects.
 * And MainFrame uses TimerListener and Panels objects.
 */

package listener;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import bean.HeroBean;
import bean.MineLabel;
import main.MainFrame;
import tools.LayBomb;
import tools.StaticTool;

public class Listener implements MouseListener {
  MineLabel[][] mineLabel;
  MainFrame mainFrame;
  private boolean isDoublePress = false;

  /**
   * Parameters: MineLabel[][] mineLabel, MainFrame mainFrame
   * Constructor, initialize mainframe and mineLabel.
   * Return values: null
   */
  public Listener(MineLabel[][] mineLabel, MainFrame mainFrame) {
    this.mineLabel = mineLabel;
    this.mainFrame = mainFrame;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  /**
   * Parameters: MouseEvent e
   * Take actions for mouse press.
   * Return values: null
   */
  @Override
  public void mousePressed(MouseEvent e) {
    MineLabel mineLabel = (MineLabel) e.getSource();
    int row = mineLabel.getRowx();
    int col = mineLabel.getColy();

    if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK + InputEvent.BUTTON3_DOWN_MASK) {
      isDoublePress = true;
      doublePress(row, col);
    } else if (e.getModifiers() == InputEvent.BUTTON1_MASK && mineLabel.isFlagTag() == false) {
      if (mineLabel.isExpendTag() == false) {
        mineLabel.setIcon(StaticTool.icon0);
      }
      mainFrame.getFacejPanel().getLabelFace().setIcon(StaticTool.clickIcon);
    } else if (e.getModifiers() == InputEvent.BUTTON3_MASK && mineLabel.isExpendTag() == false) {
      if (mineLabel.getRightClickCount() == 0) {
        mineLabel.setIcon(StaticTool.flagIcon);
        mineLabel.setRightClickCount(1);
        mineLabel.setFlagTag(true);
        StaticTool.bombCount--;
        mainFrame.getFacejPanel().setNumber(StaticTool.bombCount);
      } else if (mineLabel.getRightClickCount() == 1) {
        mineLabel.setIcon(StaticTool.askIcon);
        mineLabel.setRightClickCount(2);
        mineLabel.setFlagTag(false);
        StaticTool.bombCount++;
        mainFrame.getFacejPanel().setNumber(StaticTool.bombCount);
      } else {
        mineLabel.setIcon(StaticTool.iconBlank);
        mineLabel.setRightClickCount(0);
      }
    }
  }

  /**
   * Parameters: MouseEvent e
   * Take actions for mouse released.
   * Return values: null
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    MineLabel mineLabel = (MineLabel) e.getSource();
    int row = mineLabel.getRowx();
    int col = mineLabel.getColy();
    if (isDoublePress) {
      isDoublePress = false;
      if (mineLabel.isExpendTag() == false && mineLabel.isFlagTag() == false) {
        backIcon(row, col);
      } else {
        boolean isEquals = isEquals(row, col);
        if (isEquals) {
          doubleExpand(row, col);
        } else {
          backIcon(row, col);
        }
      }
      mainFrame.getFacejPanel().getLabelFace().setIcon(StaticTool.smileIcon);

    } else if (e.getModifiers() == InputEvent.BUTTON1_MASK && mineLabel.isFlagTag() == false) {
      if (StaticTool.isStart == false) {
        LayBomb.lay(this.mineLabel, row, col);
        StaticTool.isStart = true;
      }
      mainFrame.getTimer().start();

      if (mineLabel.isMineTag() == true) {
        bombAction(row, col);
        mineLabel.setIcon(StaticTool.bloodIcon);
        mainFrame.getFacejPanel().getLabelFace().setIcon(StaticTool.faultFaceIcon);
      } else {
        mainFrame.getFacejPanel().getLabelFace().setIcon(StaticTool.smileIcon);
        expand(row, col);
      }
    }
    isWin();
  }

  /**
   * Parameters: int row, int col
   * Visit the 2D array and the bombs.
   * Return values: null
   */
  private void bombAction(int row, int col) {
    for (int i = 0; i < mineLabel.length; i++) {
      for (int j = 0; j < mineLabel[i].length; j++) {
        if (mineLabel[i][j].isMineTag()) {
          if (mineLabel[i][j].isFlagTag() == false) {
            mineLabel[i][j].setIcon(StaticTool.blackBombIcon);
          }
        } else {
          if (mineLabel[i][j].isFlagTag()) {
            mineLabel[i][j].setIcon(StaticTool.errorBombIcon);
          }
        }
      }
    }

    mainFrame.getTimer().stop();

    for (int i = 0; i < mineLabel.length; i++) {
      for (int j = 0; j < mineLabel[i].length; j++) {
        mineLabel[i][j].removeMouseListener(this);
      }
    }
  }

  /**
   * Parameters: int x, int y
   * Expand non-bombs area.
   * Return values: null
   */
  private void expand(int x, int y) {
    int count = mineLabel[x][y].getCounAround();
    if (mineLabel[x][y].isExpendTag() == false && mineLabel[x][y].isFlagTag() == false) {
      if (count == 0) {
        mineLabel[x][y].setIcon(StaticTool.num[count]);
        mineLabel[x][y].setExpendTag(true);
        for (int i = Math.max(0, x - 1); i <= Math.min(mineLabel.length - 1, x + 1); i++) {
          for (int j = Math.max(0, y - 1); j <= Math.min(mineLabel[x].length - 1, y + 1); j++) {
            expand(i, j);
          }
        }
      } else {
        mineLabel[x][y].setIcon(StaticTool.num[count]);
        mineLabel[x][y].setExpendTag(true);
      }
    }
  }

  /**
   * Parameters: int x, int y
   * Set question icon.
   * Return values: null
   */
  private void backIcon(int i, int j) {
    for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1, i + 1); x++) {
      for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.allcol - 1, j + 1); y++) {
        if (mineLabel[x][y].isFlagTag() == false && mineLabel[x][y].isExpendTag() == false) {
          int rightClickCount = mineLabel[x][y].getRightClickCount();
          if (rightClickCount == 2) {
            mineLabel[x][y].setIcon(StaticTool.askIcon);
          } else {
            mineLabel[x][y].setIcon(StaticTool.iconBlank);
          }
        }
      }
    }
  }

  /**
   * Parameters: int x, int y
   * Calculate mines around and return if around count equals flag count.
   * Return values: null
   */
  private boolean isEquals(int i, int j) {
    int count = mineLabel[i][j].getCounAround();
    int flagCount = 0;
    for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1, i + 1); x++) {
      for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.allcol - 1, j + 1); y++) {
        if (mineLabel[x][y].isFlagTag()) {
          flagCount++;
        }
      }
    }
    if (count == flagCount) {
      return true;
    }
    return false;
  }

  /**
   * Parameters: int i, int j
   * Re set question icon.
   * Return values: null
   */
  private void doublePress(int i, int j) {
    for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1, i + 1); x++) {
      for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.allcol - 1, j + 1); y++) {
        if (mineLabel[x][y].isExpendTag() == false && mineLabel[x][y].isFlagTag() == false) {
          int rightClickCount = mineLabel[x][y].getRightClickCount();
          if (rightClickCount == 1) {
            mineLabel[x][y].setIcon(StaticTool.askPressIcon);
          } else {
            mineLabel[x][y].setIcon(StaticTool.icon0);
          }
        }
      }
    }
  }

  /**
   * Parameters: int i, int j
   * Click at same time to expand areas.
   * Return values: null
   */
  private void doubleExpand(int i, int j) {
    for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1, i + 1); x++) {
      for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.allcol - 1, j + 1); y++) {
        if (mineLabel[x][y].isMineTag()) {
          if (mineLabel[x][y].isFlagTag() == false) {
            bombAction(x, y);
          }
        } else {
          if (mineLabel[x][y].isFlagTag() == false) {
            expand(x, y);
          }
        }
      }
    }
  }

  /**
   * Parameters: null
   * Figure out if is win and get the winner's name.
   * Return values: null
   */
  private void isWin() {
    int needCount = StaticTool.allrow * StaticTool.allcol - StaticTool.allcount;
    int expendCount = 0;
    for (int i = 0; i < mineLabel.length; i++) {
      for (int j = 0; j < mineLabel[i].length; j++) {
        if (mineLabel[i][j].isExpendTag()) {
          expendCount++;
        }
      }
    }
    if (needCount == expendCount) {
      for (int i = 0; i < mineLabel.length; i++) {
        for (int j = 0; j < mineLabel[i].length; j++) {
          if (mineLabel[i][j].isMineTag() && mineLabel[i][j].isFlagTag() == false) {
            mineLabel[i][j].setIcon(StaticTool.flagIcon);
            mineLabel[i][j].setFlagTag(true);
          }
        }
      }

      mainFrame.getFacejPanel().setNumber(0);
      mainFrame.getTimer().stop();
      for (int i = 0; i < mineLabel.length; i++) {
        for (int j = 0; j < mineLabel[i].length; j++) {
          mineLabel[i][j].removeMouseListener(this);
        }
      }

      mainFrame.getFacejPanel().getLabelFace().setIcon(StaticTool.winFaceIcon);
      int level = StaticTool.getLevel();
      if (level != 0) {
        if (level == 1) {
          String name = JOptionPane.showInputDialog(mainFrame,
              "Well Done! Beginner Mode! Plz input your name!");
          if (name != null) {
            StaticTool.treeSetC.add(new HeroBean(StaticTool.timecount, name));
          }
        } else if (level == 2) {
          String name = JOptionPane.showInputDialog(mainFrame,
              "Well Done! Intermediate Mode! Plz input your name!");
          if (name != null) {
            StaticTool.treeSetZ.add(new HeroBean(StaticTool.timecount, name));
          }
        } else if (level == 3) {
          String name = JOptionPane.showInputDialog(mainFrame,
              "Well Done! Expert Mode! Plz input your name!");
          if (name != null) {
            StaticTool.treeSetG.add(new HeroBean(StaticTool.timecount, name));
          }
        }
      }
    }
  }
}
