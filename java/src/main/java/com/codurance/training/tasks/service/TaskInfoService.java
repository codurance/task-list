package com.codurance.training.tasks.service;

import java.io.PrintWriter;

public interface TaskInfoService {
    public void show(PrintWriter out);
    public void showDueToday(PrintWriter out);
    public void help(PrintWriter out);
    public void error(PrintWriter out, String Command);
}
