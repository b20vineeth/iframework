package com.web.framework.util;

public class QueryString {

	 public static final String FIND_PRIVILEGE_DETAILS = "SELECT setup.PRVCOD FROM  asignusrgrp usrgrp "
			+ "inner join usrdtl dtl on dtl.id=usrgrp.usrid "
			+ "inner join asignprvusrmst usrmst on usrmst.asgnusrgrpid=usrgrp.id"
			+ "inner join  asignprvcodgrp asgn on asgn.PRVGRPID=usrmst.asgnprvgrpid "
			+ "inner join prvcodgrp  grp on grp.id=asgn.PRVGRPID"
			+ "inner join prvcodsetup setup on setup.id=asgn.PRVID "
			+ "where dtl.id=:usrid "
			+ "and grp.status='Y' and asgn.status='Y' and setup.status='Y'"
			+ "and grp.FRMDAT<=:fromDate and grp.TODAT>=:toDate "
			+ "and asgn.FRMDAT<=:fromDate and asgn.TODAT>=:toDate "
			+ "and setup.FRMDAT<=:fromDate and setup.TODAT>=:toDate"
			+ "and usrgrp.status='Y' and dtl.status='A' and usrmst.status='Y'"
			+ "and usrgrp.FRMDAT<=:fromDate and usrgrp.TODAT>=:toDate "
			+ "and dtl.FRMDAT<=:fromDate and dtl.TODAT>=:toDate "
			+ "and usrmst.FRMDAT<=:fromDate and usrmst.TODAT>=:toDate";
 
}
