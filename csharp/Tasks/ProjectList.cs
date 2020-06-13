using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Tasks
{
    internal class ProjectList
    {
        private readonly IDictionary<string, IList<Task>> _tasks;

        public ProjectList(IDictionary<string, IList<Task>> tasks)
        {
            _tasks = tasks;
        }

        public string Show()
        {
            var builder = new StringBuilder();
            foreach (var (key, value) in _tasks)
            {
                builder.AppendLine(key);
                foreach (var task in value)
                {
                    var format = $"    [{(task.Done ? 'x' : ' ')}] {task.Id}: {task.Description}";
                    builder.AppendLine(format);
                }
                builder.AppendLine();
            }

            return builder.ToString();
        }

        public void Add(string projectName)
        {
            _tasks[projectName] = new List<Task>();
        }

        public IList<Task> FindProject(string projectName) => _tasks[projectName];

        public IEnumerable<Task> AllTasksInProjects() => _tasks.Values.SelectMany(list => list);
    }
}
