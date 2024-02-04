package com.web.framework.util;

public class QueryString {

	public static final String FIND_FEATURE_DETAILS = "SELECT c.BEVCONFIGCOD,c.BEVCONFIGTYP,c.BEVSETUPID,c.BEVCONFIGNAM,s.BEVCOD,s.BEVNAM "
			+ " FROM  bevsetup s inner join bevconfig c on c.BEVSETUPID=s.id "
			+ " and s.TODAT>=:toDate  and s.FRMDAT<=:fromDate "
			+ " and c.TODAT>=:toDate and c.FRMDAT<=:fromDate "
			+ " and c.status='Y'  and s.status='Y' and s.BEVCOD=:configcod order by c.BEVPRITY ";
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
