#!/bin/bash

# Log file
LOG_FILE="/var/log/system_monitor.log"

# Function to log alert
log_alert() {
    echo "$(date) - $1 - $2" >> "$LOG_FILE"
}

# Check HTTP error responses
http_errors=$(tail -n 100 /var/log/apache2/error.log | grep "HTTP error" | wc -l)
if [ "$http_errors" -gt 0 ]; then
    log_alert "HTTP Error Responses" "Number of HTTP error responses: $http_errors"
fi
