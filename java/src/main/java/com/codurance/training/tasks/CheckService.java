package com.codurance.training.tasks;

import java.io.PrintWriter;

public interface CheckService {
    public void setDone (String idString, boolean done, PrintWriter out);
}
