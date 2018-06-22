// Package main implements a command-line task manager.
// A manager is a TaskList object, which is started with the Run() function
// and then scans and executes user commands.
package main

import "os"

func main() {
	NewTaskList(os.Stdin, os.Stdout).Run()
}
