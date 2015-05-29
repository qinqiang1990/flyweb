/**
 * 
 */
function setArea(province,city){
	var PI = 0;
	var CI = 0;
	var area = new Array();
	for (i = 0; i < PCAP.length; i++) {
		if(PCAP[i].indexOf(province)!=-1){
			PI = i;
			break;
		}
	}
	for (i = 1; i < PCAC[PI].length; i++) {
		if(PCAC[PI][i].indexOf(city)!=-1){
			CI = i-1;
			break;
		}
		
	}
	for (i = 2; i < PCAA[PI][CI].length; i++) {
		area.push(PCAA[PI][CI][i]);
	}
	return area;
}