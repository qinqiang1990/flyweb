$(document).ready(function () {
	window.pop1 = $('.content1').popup({
		trigger: $('#popup-btn1'),
	    size: 'middle',
	    background: '#fff'
	});


	var pop2 = $('.content2').popup({
		trigger: $('#popup-btn2'),
	    width: 200,
	    height: 200,
	    background: 'transparent',
	    animate: false,
	    clickOutsideHide: false
	});

	$('.content2 .close-btn').click(function(event) {
		pop2.hide();
	});


})