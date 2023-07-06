#!/bin/bash

cd /home/ec2-user
ls -la

# JAVA_HOME
export JAVA_HOME="/usr/lib/jvm/java-11-amazon-corretto.x86_64/"

echo printenv
printenv

# move
cd /home/ec2-user/spring-boot2-train

# spring-boot:run
nohup /bin/bash mvnw spring-boot:run &
