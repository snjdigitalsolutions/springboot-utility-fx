package com.snjdigitalsolutions.springbootutilityfx.node.utility;

import javafx.concurrent.Task;

public class TaskStarter {

    public static void startTask(Task<?> task) {
        new Thread(task).start();
    }

}
