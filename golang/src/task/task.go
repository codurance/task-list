package main

// Task describes an elementary task.
type Task struct {
	id          int64
	description string
	done        bool
}

// NewTask initializes a Task with the given ID, description and completion status.
func NewTask(id int64, description string, done bool) *Task {
	return &Task{
		id:          id,
		description: description,
		done:        done,
	}
}

// GetID returns the task ID.
func (t *Task) GetID() int64 {
	return t.id
}

// GetDescription returns the task description.
func (t *Task) GetDescription() string {
	return t.description
}

// IsDone returns whether the task is done or not.
func (t *Task) IsDone() bool {
	return t.done
}

// SetDone changes the completion status of the task.
func (t *Task) SetDone(done bool) {
	t.done = done
}
