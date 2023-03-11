package com.codurance.training.error;

import java.io.PrintWriter;

public interface ErrorInterface {
    void showError(PrintWriter out, String command);
}
