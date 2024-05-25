#!/bin/bash

docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -v ./init.sql:/docker-entrypoint-initdb.d/init.sql -itd mysql:latest
