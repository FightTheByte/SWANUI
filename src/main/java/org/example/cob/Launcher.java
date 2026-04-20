package org.example.cob;

import javafx.application.Application;
import org.example.cob.eventbus.SwanEventBus;
import org.example.cob.handlers.SwanProgramHandler;

public class Launcher {
    public static void main(String[] args) {
        SwanEventBus.registerListener(SwanProgramHandler.getInstance(false));
        Application.launch(HelloApplication.class, args);
    }
}
