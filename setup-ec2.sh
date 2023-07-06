#!/bin/bash

# JAVA_HOME
export JAVA_HOME="/usr/lib/jvm/java-11-amazon-corretto.x86_64/"

# move
cd spring-boot2-train

# spring-boot:run
bash mvnw spring-boot:run &
