using System.Collections.Generic;
using System.Text;

namespace Tasks
{
    internal class ProjectList
    {
        private IDictionary<string, IList<Task>> _tasks;

        public ProjectList(IDictionary<string, IList<Task>> tasks)
        {
            this._tasks = tasks;
        }

        public string Show()
        {
            var builder = new StringBuilder();
            foreach (var project in _tasks)
            {
                builder.AppendLine(project.Key);
                foreach (var task in project.Value)
                {
                    var format = string.Format("    [{0}] {1}: {2}", (task.Done ? 'x' : ' '), task.Id, task.Description);
                    builder.AppendLine(format);
                }
                builder.AppendLine();
            }

            return builder.ToString();
        }
    }
}
