/*!
 *@name     jquery.barrager.js
 *@author   yaseng@uauc.net
 *@url      https://github.com/yaseng/jquery.barrager.js
 */
(function($) {

	$.fn.barrager = function(barrage) {
		barrage = $.extend({
			close:true,
			bottom: 0,
			max: 10,
			speed: 6,
			color: '#fff',
			old_ie_color : '#000000'
		}, barrage || {});

		var time = new Date().getTime();
		var barrager_id = 'barrage_' + time;
		var id = '#' + barrager_id;
		
		var _dInfo = {
			userId:barrage.userId,
			danmakuId:barrage.danmakuId,
			nickName:barrage.nickName
		}
		
		var div_barrager = $("<div class='barrage' id='" + barrager_id + "' name='" + JSON.stringify(_dInfo) +"'></div>").appendTo($(this));
		var window_height = $(this).height() - 100;
		// var bottom = (barrage.bottom == 0) ? Math.floor(Math.random() * window_height + 40) : barrage.bottom;
		div_barrager.css("position", "absolute");
		// div_barrager.css("bottom", bottom + "px");
		div_barrager.css("bottom", (Math.floor(Math.random() * window_height + 40 ) + "px"));
		div_barrager_box = $("<div class='barrage_box cl'></div>").appendTo(div_barrager);
		if(barrage.img){

			div_barrager_box.append("<a class='portrait z' href='javascript:;'></a>");
			var img = $("<img src='' >").appendTo(id + " .barrage_box .portrait");
			img.attr('src', barrage.img);
		}
		
		div_barrager_box.append(" <div class='z p'></div>");
		if(barrage.close){

			div_barrager_box.append(" <div class='close z'></div>");

		}
		
		var content = $("<a title='' target='_blank'></a>").appendTo(id + " .barrage_box .p");
		content.attr({
			'id': barrage.id
		}).empty().append(barrage.info);
		if(navigator.userAgent.indexOf("MSIE 6.0")>0  ||  navigator.userAgent.indexOf("MSIE 7.0")>0 ||  navigator.userAgent.indexOf("MSIE 8.0")>0  ){

			content.css('color', barrage.old_ie_color);

		}else{

			content.css('color', barrage.color);

		}
		
		var i = -500;
		div_barrager.css('right', i);
		var looper = setInterval(barrager, barrage.speed);
        var dwidth =$(this).width();
        console.log(dwidth);
		function barrager() {
			
			if (i < dwidth) {
				i += 1;

				$(id).css('right', i);
			} 
			else {

				$(id).remove();
 				return false;
			}

		}


		div_barrager_box.mouseover(function() {
			clearInterval(looper);
		});

		div_barrager_box.mouseout(function() {
			looper = setInterval(barrager, barrage.speed);
		});

		$(id+'.barrage .barrage_box .close').click(function(){

			$(id).remove();

		})

	}
 
	$.fn.barrager.removeAll=function(){

		 $('.barrage').remove();

	}

})(jQuery);