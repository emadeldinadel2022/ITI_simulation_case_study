export ORACLE_SID=digital
export ORACLE_BASE=/u01/app/oracle
export ORACLE_HOME=/u01/app/oracle/product/11.2.0/xe
export PATH=/usr/sbin:$PATH
export PATH=$ORACLE_HOME/bin:$PATH
expdp univ/univ full=y directory=exp_dump dumpfile=full_bkp.dmp logile=full_bkp.log
