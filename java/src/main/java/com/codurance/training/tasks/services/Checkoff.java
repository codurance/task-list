package com.codurance.training.tasks.services;

class Checkoff extends Checker {
    
     void check() {

         TaskOperationImpl.setDone("abc", false);
    }

}