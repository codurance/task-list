package com.codurance.training.tasks.services;


class Checkon extends Checker {

    void check() {

        TaskOperationImpl.setDone("abc", true);
    }

}