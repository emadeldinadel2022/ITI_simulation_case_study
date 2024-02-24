#!/bin/bash

# Log file
LOG_FILE="/var/log/disk_space_monitor.log"

# Function to log disk space
log_disk_space() {
    echo "$(date) - Disk space usage: $1" >> "$LOG_FILE"
}

# Check disk space
df -h | awk '$NF=="/"{print $(NF-1)}' | sed 's/%//' | while read -r disk_usage; do
    if [ "$disk_usage" -lt 10 ]; then
        log_disk_space "Low Disk Space: $disk_usage%"
    fi
done

