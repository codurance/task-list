namespace Tasks
{
    public static class Tasks
    {
        public static void Main(string[] args)
        {
            new TaskList(new RealConsole()).Run();
        }
    }
}
