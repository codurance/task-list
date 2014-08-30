/// <reference path="../typings/tsd.d.ts" />
///

import nodeunit = require('nodeunit');
import stream = require('stream');
import task_list = require('../src/task_list');

export function application_test(test: nodeunit.Test) {
    var input = new stream.PassThrough();
    var output = new stream.PassThrough();
    var tl = new task_list.TaskList(input, output);

    function read(expected) {
        var data = output.read(expected.length);
        if(data != null) {
            test.equal(data.toString(), expected);
            return true;
        }
        return false;
    }

    test.expect(2);
    var count = 1;
    output.on('readable', () => {
        switch(count){
            case 1:
                if(!read("What's your name?\n> "))
                    return;
                input.write("Bob\r\n");
                break;
            case 2:
                if(!read("Hello Bob\n> "))
                    return;
                test.done();
                break;
            default:
                test.ok(false, "message");
                break;
        }
        count += 1;
    });

    tl.run();
}
