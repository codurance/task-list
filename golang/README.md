# Golang code

## Usage

Only a `go` binary is required. Get it through your distribution repositories or on the [official Golang site](https://golang.org/dl/).

#### Run the tests

```sh
> ./run_tests.sh [-v]
```

(Calls `go test` after setting up `GOPATH`)

#### Run the application

```sh
> ./build_and_run.sh
```

(Calls `go build` after setting up `GOPATH`)

## Notes on testing

The main scenario test in `main_test.go` writes to the input descriptor
of `TaskList.Run()`, and reads from its output descriptor, through
[`io.Pipe`](https://golang.org/pkg/io/#Pipe).

Writes and reads in pipes are blocking if the other side is not ready,
as a consequence, a missing read (invalid number of lines in the output,
for example) will lead to a deadlock.

Similarly, the scenario expects the `TaskList.Run()` to finish (reception of the
`quit` command), if it is forgotten another deadlock will be detected.

In the case of deadlocks, test output will be unusable: to help with debugging,
current scenario steps are logged on standard output and displayed when using
`./run_tests.sh -v`.
