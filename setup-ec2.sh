#!/bin/bash

# cd
cd /home/ec2-user/spring-boot2-train

# chmod
chmod 755 mvnw

# spring-boot:run
nohup ./mvnw spring-boot:run &
