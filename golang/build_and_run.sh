#!/bin/bash

pushd "$(dirname "$0")" > /dev/null

GOPATH="$PWD":"$GOPATH" go build -o bin/task task && bin/task

popd > /dev/null
