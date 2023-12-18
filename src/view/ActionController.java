package view;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionController extends AbstractAction {
    private View view;

    public ActionController(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent component= (JComponent) e.getSource();
        switch (component.getName()) {
            case "remove":
                view.gamePanel.removeLastSymbol();
                break;
            case "back":
                view.changePanel(View.MAIN_MENU);
                view.gamePanel.stop();
                break;
            default: // spell buttons case
                view.gamePanel.castSpell(component.getName());
        }
    }
}
