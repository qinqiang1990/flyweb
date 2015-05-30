function Region(param) {

	var self = this;
	this.province = param.province;
	this.city = param.city;
	this.area = param.area;
	this.param = param;
	this.init();
	
    this.SelP.onchange = function() {
        PCAS.SetC(this.PCA)
    };
    if (this.SelA) this.SelC.onchange = function() {
        PCAS.SetA(this.PCA)
    };
    PCAS.SetP(this)
};
}

Region.prototype.init = function() {

	this.init_province();

	this.init_city();
	
	this.init_area();

};

Region.prototype.init_province = function() {
	
	var self = this;
	
	$.getJSON("public/region/province.json", function(data) {
		
		//排序
		data.sort(function (a, b) {
			return a.ProSort-b.ProSort;
		});   
		
		var html_province = "";
		
		$.each(data, function() {
		
			html_province+= "<option>" + this.name + "</option>";
		
		});
  	
		$("#"+self.province).append(html_province);
		
	});
 

};

Region.prototype.init_city = function() {
	
	var self = this;
	
	$.getJSON("public/region/city.json", function(data) {
		
		 
		data = data.filter(function(item) {
		  
			return item.ProID == '10';
		
		});
		
		
		//排序
		data.sort(function (a, b) {
			return a.CitySort-b.CitySort;
		});  
		
		var html_city = "";
		
		$.each(data, function() {
		
			html_city+= "<option>" + this.name + "</option>";
		
		});
  	
		$("#"+self.city).append(html_city);
		
	});
 

};

Region.prototype.init_area = function() {
	
	var self = this;
	
	$.getJSON("public/region/area.json", function(data) {
		
		//筛选
		data = data.filter(function(item) {
			  
			return item.CityID == '66';
		
		});
		
		//排序
		data.sort(function (a, b) {
			return a.DisSort-b.DisSort;
		}); 
		
		var html_area = "";
		
		$.each(data, function() {
		
			html_area+= "<option>" + this.DisName + "</option>";
		
		});
  	
		$("#"+self.area).append(html_area);
		
	});
 

};
