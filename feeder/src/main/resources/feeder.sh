#!/usr/bin/env bash

PROJ_DIR="/home/jay/work/gigaspace/mirrorexample3"

export GS_LOOKUP_LOCATORS="localhost:4174"
export GS_LOOKUP_GROUPS="xap-15.0.0"

java -jar $PROJ_DIR/feeder/target/feeder-1.0-SNAPSHOT-jar-with-dependencies.jar
