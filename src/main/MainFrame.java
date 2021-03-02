/**
 * MainFrame.java
 * Project Name: Minesweeper
 *
 * Launch the program with JFrame.
 *
 * This file has the main method. It uses psnels objects,
 * StaticTool objects and StaticTool objects.
 */

package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.Timer;

import panel.BombjMenuBar;
import panel.BombjPanel;
import panel.FacejPanel;
import timer.TimerListener;
import tools.StaticTool;

public class MainFrame extends JFrame {
  private static final long serialVersionUID = 1L;
  private BombjMenuBar menuBar = new BombjMenuBar(this);
  private FacejPanel facejPanel = new FacejPanel(this);
  private BombjPanel bombjPanel = new BombjPanel(this);
  private TimerListener timerListener = new TimerListener(this);
  private Timer timer = new Timer(1000, timerListener);

  /**
   * Parameters: MainFrame mainFrame
   * Constructor, initialize mainframe with defualt values.
   * ("this" uses features of Jframe)
   * Return values: null
   */
  public MainFrame() {
    this.setJMenuBar(menuBar);
    this.add(facejPanel, BorderLayout.NORTH);
    this.add(bombjPanel);
    this.setIconImage(StaticTool.imageIcon.getImage());
    this.setTitle("Game");
    this.setSize(new Dimension(220, 300));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.pack();
    this.setVisible(true);
  }

  /**
   * Parameters: null
   * Restart the game and init the variables again.
   * Return values: null
   */
  public void reStartGame() {
    this.remove(facejPanel);
    this.remove(bombjPanel);

    StaticTool.bombCount = StaticTool.allcount;
    StaticTool.timecount = 0;
    StaticTool.isStart = false;

    facejPanel = new FacejPanel(this);
    bombjPanel = new BombjPanel(this);
    this.add(facejPanel, BorderLayout.NORTH);
    this.add(bombjPanel);
    this.pack();
    this.validate();

    getTimer().stop();
  }

  public FacejPanel getFacejPanel() {
    return facejPanel;
  }

  public BombjPanel getBombjPanel() {
    return bombjPanel;
  }

  public Timer getTimer() {
    return timer;
  }

  public static void main(String[] args) {
    new MainFrame();
  }
}
