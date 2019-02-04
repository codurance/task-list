#!/bin/bash

set -e

cd "$(dirname "${BASH_SOURCE[0]}")"

mvn package appassembler:assemble
echo
echo
echo
sh ./target/appassembler/bin/task-list
