$(document).ready(function() {
	$('.phone').mask('(00) 00000-0000');
	$('.dateTime').mask('00/00/0000 00:00');
	$('.money').mask('#0.00', { reverse: true });
});