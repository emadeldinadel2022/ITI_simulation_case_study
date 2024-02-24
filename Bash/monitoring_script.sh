#!/bin/bash


CPU_THRESHOLD=80 
MEMORY_THRESHOLD=80 
DISK_THRESHOLD=10


LOG_FILE="/var/log/system_monitor.log"


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

# Explanation:
# 1. Shebang indicates the script should be executed using Bash shell.
# 2. Thresholds are defined for CPU, memory, and disk usage.
# 3. The log file path is specified.
# 4. A function 'log_alert' is defined to log alerts to the specified log file.
# 5. CPU usage is checked using 'top' command, and an alert is logged if it exceeds the threshold.
# 6. Memory consumption is checked using 'free' command, and an alert is logged if it exceeds the threshold.
# 7. Disk space is checked using 'df' command, and an alert is logged if it falls below the threshold.

