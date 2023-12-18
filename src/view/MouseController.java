package view;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {
    private View view;

    public MouseController(View view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JComponent component = (JComponent) e.getSource();
        switch (component.getName()) {
            case "start":
                view.changePanel(View.GAME_PANEL);
                view.gamePanel.start();
                break;
            case "exit":
                view.exit();
                break;
            case "highScore":
                // TODO
                view.changePanel(View.HIGH_SCORE_SCREEN);
                break;
            case "help":
                // TODO
                view.changePanel(View.HELP_SCREEN);
                break;
            case "back":
                view.changePanel(View.MAIN_MENU);
                view.gamePanel.stop();
                break;
            case "symbol1":
            case "symbol2":
            case "symbol3":
            case "symbol4":
            case "symbol5":
            case "symbol6":
                this.view.gamePanel.castSpell(component.getName());
                break;
            case "remove":
                view.gamePanel.removeLastSymbol();
                break;
            default: // case label from end screen
                this.view.changePanel(View.MAIN_MENU);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
