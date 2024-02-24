#!/bin/bash


LOG_FILE="/var/log/disk_space_monitor.log"


log_disk_space() {
    echo "$(date) - Disk space usage: $1" >> "$LOG_FILE"
}


df -h | awk '$NF=="/"{print $(NF-1)}' | sed 's/%//' | while read -r disk_usage; do
    if [ "$disk_usage" -lt 10 ]; then
        log_disk_space "Low Disk Space: $disk_usage%" # If disk usage is less than 10%, log the disk space usage.
    fi
done

# Explanation:
# 1. The log file path is specified where disk space alerts will be logged.
# 2. A function 'log_disk_space' is defined to log disk space alerts to the specified log file.
# 3. Disk space is checked using the 'df' command to get disk space usage information.
# 4. 'awk' command filters the output to only include the percentage of disk space used for the root directory (/).
# 5. 'sed' command removes the percentage sign from the disk usage value.
# 6. A 'while' loop reads the disk usage value line by line.
# 7. If the disk usage is less than 10%, a disk space alert is logged using the 'log_disk_space' function.

