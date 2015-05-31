$(function() {
	new Region({
		province : 'province',
		city : 'city',
		area : 'area'
	})
})

$(function() {

	$('#ttt').click(
			function() {

				var templateString = $("#tpl").html();
				templateString=templateString.replace(/\[<%/g,'<%').replace(/%>\]/g,'%>');
				 
				var compiled = _.template(templateString);
				var even = compiled({
					list : [ {
						firstName : '<a href="#">Zhang</a>',
						lastName : 'San',
						city : 'Shanghai'
					}, {
						firstName : 'Li',
						lastName : 'Si',
						city : '<a href="#">Beijing</a>'
					}, {
						firstName : 'Wang',
						lastName : 'Wu',
						city : 'Guangzhou'
					}, {
						firstName : 'Zhao',
						lastName : 'Liu',
						city : 'Shenzhen'
					} ]
				});

				$("#show").html(even);
 

			})

})