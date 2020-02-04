
export GIGA_15="/home/jay/work/gigaspace/gigaspaces-insightedge-enterprise-15.0.0"

export CLASSES_DIR="/home/jay/work/gigaspace/mirrorexample3/feeder/target/classes"

export MODEL_DIR="/home/jay/work/gigaspace/mirrorexample3/common/target/classes"

export CLASSPATH=$GIGA_15/lib/required/*

export CLASSPATH=$CLASSPATH:$MODEL_DIR

export CLASSPATH=$CLASSPATH:$CLASSES_DIR

export GS_LOOKUP_LOCATORS="localhost"
export GS_LOOKUP_GROUPS="xap-15.0.0"

java -Xms1g -Xmx1g -classpath "$CLASSPATH" com.gigaspaces.demo.feeder.Main