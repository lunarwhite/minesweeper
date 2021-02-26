/**
 * HeroBean.java
 * Project Name: Minesweeper
 *
 * Maintain the winner's name and time record.
 *
 * This class dont't use other objects.
 */

package bean;

public class HeroBean implements Comparable<HeroBean> {
  private int time;
  private String name;

  /**
   * Parameters: int time, String name
   * Constructor, initialize HeroBean.
   * Return values: null
   */
  public HeroBean(int time, String name) {
    super();
    this.time = time;
    this.name = name;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Parameters: HeroBean o
   * Override compareTo,
   * return > 0 means this.time > o.time
   * Return values: int (this.time - o.time)
   */
  @Override
  public int compareTo(HeroBean o) {
    return this.time - o.time;
  }

  /**
   * Parameters: int x, int y
   * Override toString, return time and name in String.
   * Return values: String
   */
  @Override
  public String toString() {
    return time + "second\t" + name;
  }
}
