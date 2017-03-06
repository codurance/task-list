/// <reference path="../typings/nodeunit/nodeunit.d.ts" />

import assert = require('assert');
import nodeunit = require('nodeunit');
import stream = require('stream');
import task_list = require('../src/task_list');

class Expectation {
    constructor(public ctxt: TestCtxt) {
        this.ctxt.expectations.push(this)
    }

    test(): boolean {
        this.ctxt.test.ok(false, "Override me");
        return true;
    }
}

class TestCtxt
{
    input = new stream.PassThrough();
    output = new stream.PassThrough();
    expectations : Expectation[] = [];
    tl = new task_list.TaskList(this.input, this.output);

    constructor(public test: nodeunit.Test){
        var count = 0;
        this.output.on('readable', () => {
            if(count > this.expectations.length) {
                this.test.ok(false, "Got more output than expected proabably didn't quit")
                this.test.done();
            } else if(count == this.expectations.length) {
                this.test.ok(true);
                this.test.done();
            } else if(this.expectations[count].test()) {
                count += 1;
            }
        });
        this.output.on('end', () => {
            this.test.equal(count, this.expectations.length);
            this.test.done();
        });
    }

    read(expected) {
        var data = this.output.read(expected.length);
        if (data != null) {
            data = data.toString();
            assert.equal(data, expected);
            return true;
        }
        return false;
    }

    run() {
        this.test.expect(1);
        this.tl.run();
    }
}

class ExecuteExpectation extends Expectation {
    prompt = '> ';

    constructor(ctxt: TestCtxt, public cmd:string) {
        super(ctxt)
    }

    test() {
        if (!this.ctxt.read(this.prompt))
            return false;
        this.ctxt.input.write(this.cmd + '\n');
        return true;
    }
}

class OutputExpectation extends Expectation {

    constructor(ctxt: TestCtxt, public out:string) {
        super(ctxt)
    }

    test() {
        return this.ctxt.read(this.out)
    }
}

export function application_test(test: nodeunit.Test) {
    var ctxt = new TestCtxt(test);

    function execute(cmd: string) {
        new ExecuteExpectation(ctxt, cmd);
    }

    function readLines(...strings: string[]) {
        strings.forEach((s) => {
            new OutputExpectation(ctxt, s + '\n');
        })
    }

    execute('show');

    execute("add project secrets");
    execute("add task secrets Eat more donuts.");
    execute("add task secrets Destroy all humans.");

    execute("show");
    readLines(
        "secrets",
        "    [ ] 1: Eat more donuts.",
        "    [ ] 2: Destroy all humans.",
        ""
    );

    execute("add project training");
    execute("add task training Four Elements of Simple Design");
    execute("add task training SOLID");
    execute("add task training Coupling and Cohesion");
    execute("add task training Primitive Obsession");
    execute("add task training Outside-In TDD");
    execute("add task training Interaction-Driven Design");

    execute("check 1");
    execute("check 3");
    execute("check 5");
    execute("check 6");

    execute("show");
    readLines(
        "secrets",
        "    [x] 1: Eat more donuts.",
        "    [ ] 2: Destroy all humans.",
        "",
        "training",
        "    [x] 3: Four Elements of Simple Design",
        "    [ ] 4: SOLID",
        "    [x] 5: Coupling and Cohesion",
        "    [x] 6: Primitive Obsession",
        "    [ ] 7: Outside-In TDD",
        "    [ ] 8: Interaction-Driven Design",
        ""
    );
    execute('quit');

    ctxt.run();
}
