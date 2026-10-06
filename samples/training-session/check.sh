#!/bin/sh
set -eu
cd "$(dirname "$0")"
task_build=$(mktemp -d)
trap 'rm -rf "$task_build"' EXIT HUP INT TERM
javac --release 21 -d "$task_build" src/TrainingSession.java src/TrainingSessionTest.java
java -cp "$task_build" portfolio.TrainingSessionTest
