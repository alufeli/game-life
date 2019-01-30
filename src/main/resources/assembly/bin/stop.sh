#!/bin/sh

PID=$(cat ms.pid)
kill -9 $PID
rm ms.pid

echo "stop BusinessSupervise success"