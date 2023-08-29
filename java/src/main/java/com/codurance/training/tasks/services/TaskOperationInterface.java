package com.codurance.training.tasks.services;

import java.io.PrintWriter;

interface TaskOperationInterface {
    
    void show(PrintWriter out);
    
    void add(Item item,int lastId);
    
    void check (Checker checker);
    
    void help();
    
    void error(String command);
}