/**
 * LayBomb.java
 * Project Name: Minesweeper
 *
 * Lay Bombs randomly, init the game.
 *
 * It uses MineLabel objects.
 */

package tools;

import java.util.Random;

import bean.MineLabel;

public class LayBomb {
  /**
   * Parameters: MineLabel[][] label, int row, int col
   * Set the bombs randomly in the 2D array.
   * row and col are customized numbers
   * Return values: null
   */
  public static void lay(MineLabel[][] label, int row, int col) {
    int count = 0;
    Random random = new Random();
    while (count < StaticTool.allcount) {
      int x = random.nextInt(StaticTool.allrow);
      int y = random.nextInt(StaticTool.allcol);
      if (label[x][y].isMineTag() == false && !(x == row && y == col)) {
        label[x][y].setMineTag(true);
        label[x][y].setCounAround(9);
        count++;
      }
    }
    computeBomb(label);
  }

  /**
   * Parameters: MineLabel[][] label
   * This method set the counAround variable for each member
   * who is not a bomb in label[][], (for a bomb, it's 9)
   * which records how many bombs are around a vacant cell.
   * Return values: null
   */
  public static void computeBomb(MineLabel[][] label) {
    for (int i = 0; i < label.length; i++) {
      for (int j = 0; j < label[i].length; j++) {
        if (label[i][j].isMineTag() == false) {
          int count = 0;
          for (int x = Math.max(0, i - 1); x <= Math.min(
              StaticTool.allrow - 1, i + 1); x++) {
            for (int y = Math.max(0, j - 1); y <= Math.min(
                StaticTool.allcol - 1, j + 1); y++) {
              if (label[x][y].isMineTag() == true) {
                count++;
              }
            }
          }
          label[i][j].setCounAround(count);
        }
      }
    }
  }
}
