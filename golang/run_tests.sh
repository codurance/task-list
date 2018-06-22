#!/bin/bash

pushd "$(dirname "$0")" > /dev/null

GOPATH="$PWD":"$GOPATH" go test ./... $@

popd > /dev/null
