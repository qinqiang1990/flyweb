function Region(param) {

	var self = this;
	self.$province = $("#" + param.province);
	self.$city = $("#" + param.city);
	self.$area = $("#" + param.area);
	self.param = param;

	self.init();

}

Region.prototype.init = function() {

	var self = this;

	self.init_province("");

	self.init_city("");

	self.init_area("");

};

Region.prototype.init_province = function(province) {

	var self = this;

	$.getJSON("public/region/province.json", function(data) {

		// 排序
		data.sort(function(a, b) {
			return a.ProSort - b.ProSort;
		});

		var html_province = "";

		$.each(data, function() {

			html_province += "<option value='" + this.ProID + "'>" + this.name
					+ "</option>";

		});

		self.$province.append(html_province);

	});

	self.$province.change(function() {

		self.init_city($(this).val());

	});

};

Region.prototype.init_city = function(ProID) {

	var self = this;

	$.getJSON("public/region/city.json", function(data) {

		if (ProID != "") {
			data = data.filter(function(item) {

				return item.ProID == ProID;

			});
		}
		alert(data);
		// 排序
		data.sort(function(a, b) {
			return a.CitySort - b.CitySort;
		});

		var html_city = "";

		$.each(data, function() {

			html_city += "<option value='" + this.CityID + "'>" + this.name
					+ "</option>";

		});
		self.$city.empty();
		self.$city.append(html_city);

	});

	self.$city.change(function() {

		self.init_area($(this).val());

	});
};

Region.prototype.init_area = function(CityID) {

	var self = this;

	$.getJSON("public/region/area.json", function(data) {

		// 筛选
		if (CityID != "") {
			data = data.filter(function(item) {

				return item.CityID == CityID;

			});
		}

		// 排序
		data.sort(function(a, b) {
			return a.DisSort - b.DisSort;
		});

		var html_area = "";

		$.each(data, function() {

			html_area += "<option>" + this.DisName + "</option>";

		});
		self.$area.empty();
		self.$area.append(html_area);

	});

};
