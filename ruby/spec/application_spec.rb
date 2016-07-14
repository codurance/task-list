require 'rspec'
require 'stringio'
require 'timeout'

require_relative '../lib/task_list'

describe 'application' do
  PROMPT = '> '

  around :each do |example|
    @input_reader, @input_writer = IO.pipe
    @output_reader, @output_writer = IO.pipe

    application = TaskList.new(@input_reader, @output_writer)
    @application_thread = Thread.new do
      application.run
    end
    @application_thread.abort_on_exception = true

    example.run

    @input_reader.close
    @input_writer.close
    @output_reader.close
    @output_writer.close
  end

  after :each do
    next unless still_running?
    sleep 1
    next unless still_running?
    @application_thread.kill
    raise 'The application is still running.'
  end

  it 'works' do
    Timeout::timeout 1 do
      execute('show')

      execute('add project secrets')
      execute('add task secrets Eat more donuts.')
      execute('add task secrets Destroy all humans.')

      execute('show')
      read_lines(
        'secrets',
        '  [ ] 1: Eat more donuts.',
        '  [ ] 2: Destroy all humans.',
        ''
      )

      execute('add project training')
      execute('add task training Four Elements of Simple Design')
      execute('add task training SOLID')
      execute('add task training Coupling and Cohesion')
      execute('add task training Primitive Obsession')
      execute('add task training Outside-In TDD')
      execute('add task training Interaction-Driven Design')

      execute('check 1')
      execute('check 3')
      execute('check 5')
      execute('check 6')

      execute('show')
      read_lines(
          'secrets',
          '  [x] 1: Eat more donuts.',
          '  [ ] 2: Destroy all humans.',
          '',
          'training',
          '  [x] 3: Four Elements of Simple Design',
          '  [ ] 4: SOLID',
          '  [x] 5: Coupling and Cohesion',
          '  [x] 6: Primitive Obsession',
          '  [ ] 7: Outside-In TDD',
          '  [ ] 8: Interaction-Driven Design',
          ''
      )

      execute('quit')
    end
  end

  def execute(command)
    read PROMPT
    write command
  end

  def read(expected_output)
    actual_output = @output_reader.read(expected_output.length)
    expect(actual_output).to eq expected_output
  end

  def read_lines(*expected_output)
    expected_output.each do |line|
      read "#{line}\n"
    end
  end

  def write(input)
    @input_writer.puts input
  end

  def still_running?
    @application_thread && @application_thread.alive?
  end
end
