using System.Collections.Generic;

namespace Tasks
{
    public static class Tasks
    {
        public static void Main()
        {
            new TaskList(new RealConsole(), new Dictionary<string, IList<Task>>()).Run();
        }
    }
}
