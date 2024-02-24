create directory EXP_DUMP as ' /u01/EXPORT_BKP/ ';

SELECT * FROM dba_directories;

grant read, write on directory EXP_DUMP to univ;

grant exp_full_database, imp_full_database to univ;