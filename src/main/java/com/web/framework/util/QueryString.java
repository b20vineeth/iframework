package com.web.framework.util;

public class QueryString {

	public static final String FIND_FEATURE_DETAILS = "SELECT c.BEVCONFIGCOD,c.BEVCONFIGTYP,c.BEVSETUPID,c.BEVCONFIGNAM,s.BEVCOD,s.BEVNAM "
			+ " FROM  bevsetup s inner join bevconfig c on c.BEVSETUPID=s.id "
			+ " and s.TODAT>=:toDate  and s.FRMDAT<=:fromDate "
			+ " and c.TODAT>=:toDate and c.FRMDAT<=:fromDate "
			+ " and c.status='Y'  and s.status='Y' and s.BEVCOD=:configcod order by c.BEVPRITY ";
 
}
