#!/bin/bash

# Log file
LOG_FILE="/var/log/system_monitor.log"

# Function to log alert
log_alert() {
    echo "$(date) - $1 - $2" >> "$LOG_FILE"
}

# Check for unusual login attempts or security-related events (example: failed SSH logins)
unusual_login_attempts=$(grep "Failed password" /var/log/auth.log | wc -l)
if [ "$unusual_login_attempts" -gt 0 ]; then
    log_alert "Unusual Login Attempts" "Number of failed login attempts: $unusual_login_attempts"
fi
