require_relative 'task'

class TaskList
  QUIT = 'quit'

  def initialize(input, output)
    @input = input
    @output = output

    @tasks = {}
  end

  def run
    while true
      @output.print('> ')
      @output.flush

      command = @input.readline.strip
      break if command == QUIT

      execute(command)
    end
  end

private

  def execute(command_line)
    command, rest = command_line.split(/ /, 2)
    case command
    when 'show'
      show
    when 'add'
      add rest
    when 'check'
      check rest
    when 'uncheck'
      uncheck rest
    when 'help'
      help
    else
      error command
    end
  end

  def show
    @tasks.each do |project_name, project_tasks|
      @output.puts project_name
      project_tasks.each do |task|
        @output.printf("  [%c] %d: %s\n", (task.done? ? 'x' : ' '), task.id, task.description)
      end
      @output.puts
    end
  end

  def add(command_line)
    subcommand, rest = command_line.split(/ /, 2)
    if subcommand == 'project'
      add_project rest
    elsif subcommand == 'task'
      project, description = rest.split(/ /, 2)
      add_task project, description
    end
  end

  def add_project(name)
    @tasks[name] = []
  end

  def add_task(project, description)
    project_tasks = @tasks[project]
    if project_tasks.nil?
      @output.printf("Could not find a project with the name \"%s\".\n", project)
      return
    end
    project_tasks << Task.new(next_id, description, false)
  end

  def check(id_string)
    set_done(id_string, true)
  end

  def uncheck(id_string)
    set_done(id_string, false)
  end

  def set_done(id_string, done)
    id = id_string.to_i
    task = @tasks.collect { |project_name, project_tasks|
      project_tasks.find { |t| t.id == id }
    }.reject(&:nil?).first

    if task.nil?
      @output.printf("Could not find a task with an ID of %d.\n", id)
      return
    end

    task.done = done
  end

  def help
    @output.puts('Commands:')
    @output.puts('  show')
    @output.puts('  add project <project name>')
    @output.puts('  add task <project name> <task description>')
    @output.puts('  check <task ID>')
    @output.puts('  uncheck <task ID>')
    @output.puts()
  end

  def error(command)
    @output.printf("I don't know what the command \"%s\" is.\n", command)
  end

  def next_id
    @last_id ||= 0
    @last_id += 1
    @last_id
  end
end

if __FILE__ == $0
  TaskList.new($stdin, $stdout).run
end
