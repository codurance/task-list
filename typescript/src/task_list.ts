/// <reference path="../typings/tsd.d.ts" />

import readline = require('readline');

var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
//    terminal: false
});

rl.setPrompt("> ", 1);
rl.write("What's your name?\n");
rl.question("What's your name?\n", (answer) => {
    rl.write("Hello " + answer)
});
