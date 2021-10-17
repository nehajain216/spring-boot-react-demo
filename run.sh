#!/bin/bash

declare project_dir=$(dirname $0)
declare dc_app=${project_dir}/docker-compose.yaml

function build_api() {
    ./gradlew build -x test
}

function start_db() {
    echo "Starting postgres database"
    docker-compose -f ${dc_app} up -d todolist-db
}

function start_api() {
    build_api
    echo "Starting API docker containers...."
    docker-compose -f ${dc_app} up --build --force-recreate -d todolist-api
    docker-compose -f ${dc_app} logs -f
}

function start() {
    build_api
    echo "Starting all docker containers...."
    docker-compose -f ${dc_app} up --build --force-recreate -d todolist-ui
    docker-compose -f ${dc_app} logs -f
}

function stop() {
    echo "Stopping docker containers...."
    docker-compose -f ${dc_app} stop
    docker-compose -f ${dc_app} rm -f
}

function restart() {
    stop
    sleep 3
    start
}
action="start"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}
