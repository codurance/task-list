import sys

from task_list.console import Console
from task_list.task_list import TaskList


def main():
    task_list = TaskList(Console(sys.stdin, sys.stdout))
    task_list.run()


main()

