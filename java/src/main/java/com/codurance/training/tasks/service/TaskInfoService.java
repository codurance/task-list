package com.codurance.training.tasks.service;

import java.io.PrintWriter;

public interface TaskInfoService {
    void show(PrintWriter out);
    void showDueToday(PrintWriter out);
    void help(PrintWriter out);
    void error(PrintWriter out, String Command);

    void view(PrintWriter out, String commandLine);
}
