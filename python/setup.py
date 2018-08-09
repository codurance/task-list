from setuptools import setup

setup(
    name='task-list',
    version='0.0.1',
    packages=['task_list'],
    url='https://github.com/codurance/task-list',
    description='Task List Kata',
    entry_points={
        'console_scripts': [
            "task_list = task_list.__main__:main",
        ]
    }
)
