#!/bin/sh

set -e

mvn appassembler:assemble
chmod +x ./target/appassembler/bin/task-list
./target/appassembler/bin/task-list
