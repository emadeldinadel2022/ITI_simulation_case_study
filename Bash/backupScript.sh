#!/bin/bash

# Oracle credentials
DB_USER="univ"
DB_PASSWORD="1234"
DB_SID="XE"  # Oracle System Identifier (SID) of your database
DIRECTORY="backup_dir" # Directory object where the backup file will be stored
BACKUP_FILE="backup_file.dmp" # Backup file name


expdp $DB_USER/$DB_PASSWORD@$DB_SID directory="$DIRECTORY" dumpfile=$BACKUP_FILE


if [ $? -eq 0 ]; then
    echo "Backup successful. Backup file: $BACKUP_FILE"
else
    echo "Backup failed."
fi

