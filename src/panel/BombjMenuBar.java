/**
 * BombjMenuBar.java
 * Project Name: Minesweeper
 *
 * Provides different difficulties options to players, using swing.JMenuBar.
 *
 * It uses MainFrame objects, objects MineLabel and StaticTool objects.
 * And MainFrame uses TimerListener and Panels objects.
 */

package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dialog.AboutSweeping;
import dialog.HeroDialog;
import dialog.UserDefinedDialog;
import main.MainFrame;
import tools.StaticTool;

public class BombjMenuBar extends JMenuBar {
  private static final long serialVersionUID = 1L;

  JMenu menuGame = new JMenu("Game");
  JMenu menuHelp = new JMenu("Help");
  JMenu menuHero = new JMenu("Hero List");

  JMenuItem menuItemStart = new JMenuItem("New Game");
  JMenuItem menuItemCustom = new JMenuItem("Custom");
  JMenuItem menuItemExit = new JMenuItem("Exit");
  JMenuItem menuItemAbout = new JMenuItem("About");

  JMenuItem menuItemC = new JMenuItem("Easy");
  JMenuItem menuItemZ = new JMenuItem("Medium");
  JMenuItem menuItemG = new JMenuItem("Expert");

  JMenuItem menuHeroC = new JMenuItem("Easy Mode");
  JMenuItem menuHeroZ = new JMenuItem("Medium Mode");
  JMenuItem menuHeroG = new JMenuItem("Expert Mode");


  MainFrame mainFrame;

  /**
   * Parameters: MainFrame mainFrame
   * Constructor, initialize mainframe.
   * Return values: null
   */
  public BombjMenuBar(MainFrame mainFrame) {
    this.mainFrame = mainFrame;
    init();
  }

  /**
   * Parameters: null
   * Initialize jMenuBar, add menu items and actionListeners.
   * Return values: null
   */
  private void init() {
    menuGame.add(menuItemStart);
    menuItemStart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainFrame.reStartGame();
      }
    });

    menuGame.addSeparator(); // add separator(lines) into menu

    menuGame.add(menuItemC);
    menuItemC.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StaticTool.allrow = 9;
        StaticTool.allcol = 9;
        StaticTool.allcount = 10;
        mainFrame.reStartGame();
      }
    });

    menuGame.add(menuItemZ);
    menuItemZ.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StaticTool.allrow = 16;
        StaticTool.allcol = 16;
        StaticTool.allcount = 40;
        mainFrame.reStartGame();
      }
    });

    menuGame.add(menuItemG);
    menuItemG.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StaticTool.allrow = 16;
        StaticTool.allcol = 30;
        StaticTool.allcount = 99;
        mainFrame.reStartGame();
      }
    });
    menuGame.addSeparator(); // add separator(lines) into menu
    menuGame.add(menuItemCustom);
    menuItemCustom.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new UserDefinedDialog(mainFrame);
      }
    });

    menuGame.addSeparator(); // add separator(lines) into menu
    menuGame.add(menuHero);
    menuHero.add(menuHeroC);
    menuHeroC.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new HeroDialog(1, mainFrame);
      }
    });
    menuHero.add(menuHeroZ);
    menuHeroZ.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new HeroDialog(2, mainFrame);
      }
    });
    menuHero.add(menuHeroG);
    menuHeroG.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new HeroDialog(3, mainFrame);
      }
    });

    menuGame.addSeparator(); // add separator(lines) into menu
    menuGame.add(menuItemExit);
    menuItemExit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(JFrame.EXIT_ON_CLOSE);
      }
    });

    menuHelp.add(menuItemAbout);
    menuItemAbout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new AboutSweeping(mainFrame);
      }
    });

    this.add(menuGame);
    this.add(menuHelp);
  }
}
