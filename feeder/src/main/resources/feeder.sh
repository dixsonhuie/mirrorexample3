#!/usr/bin/env bash

export GS_LOOKUP_LOCATORS="localhost:4174"
export GS_LOOKUP_GROUPS="xap-15.0.0"

java -jar feeder-1.0-SNAPSHOT.jar
