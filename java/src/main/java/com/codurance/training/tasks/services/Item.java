package com.codurance.training.tasks.services;

import com.codurance.training.tasks.entities.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

abstract class Item {

    abstract void add(Map<String, List<Task>> tasks, String itemName, String description, PrintWriter out, int lastId);
} 