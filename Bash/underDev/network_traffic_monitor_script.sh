#!/bin/bash

# Log file
LOG_FILE="/var/log/system_monitor.log"

# Function to log alert
log_alert() {
    echo "$(date) - $1 - $2" >> "$LOG_FILE"
}

network_traffic=$(netstat -i | awk '{if(NR>2)print $1,$2,$3,$4,$5,$6}')
echo "$network_traffic" | while read line; do
    interface=$(echo "$line" | awk '{print $1}')
    rx_packets=$(echo "$line" | awk '{print $2}')
    tx_packets=$(echo "$line" | awk '{print $3}')
    if [ "$rx_packets" -gt 1000 ] || [ "$tx_packets" -gt 1000 ]; then
        log_alert "Unusual Network Traffic" "Interface $interface - RX: $rx_packets packets, TX: $tx_packets packets"
    fi
done
