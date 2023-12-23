package com.web.framework.util;

public class QueryString {

	public static final String FIND_FEATURE_DETAILS = "SELECT c.BEVCONFIGCOD,c.BEVCONFIGTYP,c.BEVSETUPID,c.BEVCONFIGNAM,s.BEVCOD,s.BEVNAM "
			+ " FROM  bevsetup s inner join bevconfig c on c.BEVSETUPID=s.id "
			+ " and s.TODAT>=:toDate  and s.FRMDAT<=:fromDate "
			+ " and c.TODAT>=:toDate and c.FRMDAT<=:fromDate "
			+ " and c.status='Y'  and s.status='Y' and s.BEVCOD=:configcod";
	public static final String FIND_PRIVILEGE_DETAILS = "SELECT ps.id,ps.frmdat,ps.todat,ps.crtdat,ps.lstupd,ps.status,ps.prvcod,ps.prvnam,ps.srccod,u.id userid,u.username  " + 
			" FROM privilegesetup ps inner join users  u on ps.usrid=u.id    where 1=1" ;
	public static final String FIND_PRIVILEGE_GRP_DETAILS = "SELECT ps.id,ps.frmdat,ps.todat,ps.crtdat,ps.lstupd,ps.status,ps.prvgrpcod,ps.prvgrpnam,u.id userid,u.username  " + 
			" FROM PRIVILEGEGRPSETUP ps inner join users  u on ps.usrid=u.id    where  ps.status='Y'";
	public static final String FIND_ASSIGN_PRIVILEGE_GRP_DETAILS = "SELECT  map.id,map.frmdat,map.todat,map.crtdat,map.lstupd,map.status mapstatus, " + 
			" u.id userid,u.username ,p.id prvid,p.prvcod,p.prvnam ,p.status prvstatus,p.frmdat pfromdate,p.todat ptodate, " + 
			" prvgrp.id prvgrpid ,prvgrp.prvgrpcod,prvgrp.prvgrpnam,prvgrp.status prvgrpstatus,prvgrp.frmdat prvgrpfrmdat,prvgrp.todat prvgrptodat " + 
			"  FROM PRIVILEGEGRPmap map  inner join users  u on map.usrid=u.id  inner join privilegesetup p on p.id=map.prvid inner join privilegegrpsetup prvgrp on prvgrp.id=map.prvgrpid where 1=1 ";
	public static final String FIND_USRGRP_DETAILS = "SELECT ps.id,ps.frmdat,ps.todat,ps.crtdat,ps.lstupd,ps.status,ps.USRGRPCOD,ps.USRGRPNAM,u.id userid,u.username   \r\n" + 
			"		  FROM USERGRPSETUP ps inner join users  u on ps.usrid=u.id    where 1=1 ";

}
