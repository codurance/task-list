package com.codurance.training.operations.customize;

import com.codurance.training.task.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomizeId implements CustomizeIdInterface{
    @Override
    public void customize(PrintWriter out,String id, String newId, Map<String, List<Task>> tasks) {
        Pattern numberPattern = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
        Matcher numberPatternMatcher = numberPattern.matcher(newId);
        boolean checkIfNewIdIsNumber = numberPatternMatcher.matches();

        Pattern alphaNumericPattern=Pattern.compile("[A-Z0-9]+", Pattern.CASE_INSENSITIVE);
        Matcher alphaNumericPatternMatcher = alphaNumericPattern.matcher(newId);
        boolean checkIfNewIdDoesNotContainSpecialCharacters = alphaNumericPatternMatcher.matches();

        if(!checkIfNewIdIsNumber && checkIfNewIdDoesNotContainSpecialCharacters){
                for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
                    for (Task task : project.getValue()) {
                        if (task.getId().equals(id)) {
                            task.setId(newId);
                            return;
                        }
                    }
                }
        }else{
            out.println("New Id should not be a number or contain any special characters");
        }

    }
}
