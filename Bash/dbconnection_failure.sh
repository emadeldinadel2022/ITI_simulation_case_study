#!/bin/bash

# Log file
LOG_FILE="/var/log/system_monitor.log"

# Function to log alert
log_alert() {
    echo "$(date) - $1 - $2" >> "$LOG_FILE"
}

# Check database connection failures
db_connection_failures=$(tail -n 100 /var/log/mysql/error.log | grep "connection failed" | wc -l)
if [ "$db_connection_failures" -gt 0 ]; then
    log_alert "Database Connection Failures" "Number of database connection failures: $db_connection_failures"
fi
