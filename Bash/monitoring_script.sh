#!/bin/bash

# Set thresholds
CPU_THRESHOLD=80 # in percent
MEMORY_THRESHOLD=80 # in percent
DISK_THRESHOLD=10 # in percent

# Log file
LOG_FILE="/var/log/system_monitor.log"

# Function to log alert
log_alert() {
    echo "$(date) - $1 - $2" >> "$LOG_FILE"
}

# Check CPU usage
cpu_usage=$(top -bn1 | grep "Cpu(s)" | sed "s/.*, *\([0-9.]*\)%* id.*/\1/" | awk '{print 100 - $1}')
if (( $(awk 'BEGIN {print "'$cpu_usage'" > "'$CPU_THRESHOLD'"}') )); then
    log_alert "High CPU Usage" "CPU usage is above threshold: $cpu_usage%"
fi

# Check memory consumption
memory_usage=$(free | awk '/Mem/{printf "%.2f", $3/$2 * 100.0}')
if (( $(awk 'BEGIN {print "'$memory_usage'" > "'$MEMORY_THRESHOLD'"}') )); then
    log_alert "High Memory Consumption" "Memory consumption is above threshold: $memory_usage%"
fi

# Check disk space
disk_usage=$(df -h | awk '$NF=="/"{print $(NF-1)}' | sed 's/%//')
if (( $disk_usage < $DISK_THRESHOLD )); then
    log_alert "Low Disk Space" "Disk space is below threshold: $disk_usage%"
fi

