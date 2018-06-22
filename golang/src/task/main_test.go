package main

import (
	"bufio"
	"fmt"
	"io"
	"sync"
	"testing"
)

type scenarioTester struct {
	*testing.T

	inWriter   io.Writer
	outReader  io.Reader
	outScanner *bufio.Scanner
}

func TestRun(t *testing.T) {
	// setup input/output
	inPR, inPW := io.Pipe()
	defer inPR.Close()
	outPR, outPW := io.Pipe()
	defer outPR.Close()
	tester := &scenarioTester{
		T:          t,
		inWriter:   inPW,
		outReader:  outPR,
		outScanner: bufio.NewScanner(outPR),
	}

	// run main program
	var wg sync.WaitGroup
	go func() {
		wg.Add(1)
		NewTaskList(inPR, outPW).Run()
		outPW.Close()
		wg.Done()
	}()

	// run command-line scenario
	fmt.Println("(show empty)")
	tester.execute("show")

	fmt.Println("(add project)")
	tester.execute("add project secrets")
	fmt.Println("(add tasks)")
	tester.execute("add task secrets Eat more donuts.")
	tester.execute("add task secrets Destroy all humans.")

	fmt.Println("(show tasks)")
	tester.execute("show")
	tester.readLines([]string{
		"secrets",
		"    [ ] 1: Eat more donuts.",
		"    [ ] 2: Destroy all humans.",
		"",
	})

	fmt.Println("(add second project)")
	tester.execute("add project training")
	fmt.Println("(add more tasks)")
	tester.execute("add task training Four Elements of Simple Design")
	tester.execute("add task training SOLID")
	tester.execute("add task training Coupling and Cohesion")
	tester.execute("add task training Primitive Obsession")
	tester.execute("add task training Outside-In TDD")
	tester.execute("add task training Interaction-Driven Design")

	fmt.Println("(check tasks)")
	tester.execute("check 1")
	tester.execute("check 3")
	tester.execute("check 5")
	tester.execute("check 6")

	fmt.Println("(show completed tasks)")
	tester.execute("show")
	tester.readLines([]string{
		"secrets",
		"    [X] 1: Eat more donuts.",
		"    [ ] 2: Destroy all humans.",
		"",
		"training",
		"    [X] 3: Four Elements of Simple Design",
		"    [ ] 4: SOLID",
		"    [X] 5: Coupling and Cohesion",
		"    [X] 6: Primitive Obsession",
		"    [ ] 7: Outside-In TDD",
		"    [ ] 8: Interaction-Driven Design",
		"",
	})

	fmt.Println("(quit)")
	tester.execute("quit")

	// make sure main program has quit
	inPW.Close()
	wg.Wait()
}

// execute calls a command, by writing it into the scenario writer.
// It first reads the command prompt, then sends the command.
func (t *scenarioTester) execute(cmd string) {
	p := make([]byte, len(prompt))
	_, err := t.outReader.Read(p)
	if err != nil {
		t.Errorf("Prompt could not be read: %v", err)
		return
	}
	if string(p) != prompt {
		t.Errorf("Invalid prompt, expected \"%s\", got \"%s\"", prompt, string(p))
		return
	}
	// send command
	fmt.Fprintln(t.inWriter, cmd)
}

// readLines reads lines from the scenario scanner, making sure they match
// the expected given lines.
// In case it fails or does not match, makes the calling test fail.
func (t *scenarioTester) readLines(lines []string) {
	for _, expected := range lines {
		if !t.outScanner.Scan() {
			t.Errorf("Expected \"%s\", no input found", expected)
			break
		}
		actual := t.outScanner.Text()
		if actual != expected {
			t.Errorf("Expected \"%s\", got \"%s\"", expected, actual)
		}
	}
	if err := t.outScanner.Err(); err != nil {
		t.Fatalf("Could not read input: %v", err)
	}
}

// discardLines reads lines from the scenario scanner, and drops them.
// Used to empty buffers.
func (t *scenarioTester) discardLines(n int) {
	for i := 0; i < n; i++ {
		if !t.outScanner.Scan() {
			t.Error("Expected a line, no input found")
			break
		}
	}
	if err := t.outScanner.Err(); err != nil {
		t.Fatalf("Could not read input: %v", err)
	}
}
