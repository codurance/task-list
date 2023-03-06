package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface ShowService {

    void show(Map<String, List<Task>> tasks, PrintWriter out);



}


