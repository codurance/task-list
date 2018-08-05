Python
=====
Usage
-----
The application requires Python 3.5 or above

cd into the python directory

To run tests:
```
python -m unittest -v
```

To run the application:
```
python -m task_list
```
Notes on testing
----------------
For the end-to-end testing, a subprocess was used instead of threading. The subprocess module allows
you to create a new process and connect to their input and output pipes. 
The test calls the 'task-list' module and then injects the inputs into stdin. 
The IO Pipe is blocking which more closely emulates the real behavior of stdin when calling readline. 
The call will block until data is written to stdin, 
likewise stdout.read will block until there is data to be read.
Because of the potential blocking, a threaded timer was introduced 
to kill the subprocess on deadlock. The timeout is currently set to 2 seconds
but if additional tests run longer than that the timeout should be increased.

As the subprocess captures all input and output, using print statements for debugging during tests
will not work as expected. 