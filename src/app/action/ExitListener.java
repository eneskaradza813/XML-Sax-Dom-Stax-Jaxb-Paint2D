package app.action;

import app.PaintWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        PaintWindow.getInstance().dispose();
    }

}
