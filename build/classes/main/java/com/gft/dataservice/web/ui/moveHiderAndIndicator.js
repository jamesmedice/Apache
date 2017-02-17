DisableComponentListener = {
	disableElement : function(elementId, activeIconUrl) {
		var hiderId = elementId + "-disable-layer";
		var indicatorId = elementId + "-indicator-picture";

		elementId = "#" + elementId;
		$(elementId).after(
				'<div id="' + hiderId + '" style="position:absolute;">'
						+ '<img id="' + indicatorId + '" src="' + activeIconUrl
						+ '"/>' + '</div>');

		hiderId = "#" + hiderId;

		$(hiderId).css('opacity', '0.8');
		$(hiderId).css('text-align', 'center');
		$(hiderId).css('background-color', 'WhiteSmoke');
		$(hiderId).css('border', '1px solid DarkGray');

		$(hiderId).width($(elementId).outerWidth());
		$(hiderId).height($(elementId).outerHeight());

		$(hiderId).position({
			of : $(elementId),
			at : 'top left',
			my : 'top left'
		});

		$("#" + indicatorId).position({
			of : $(hiderId),
			at : 'center center',
			my : 'center center'
		});
	},

	hideComponent : function(elementId) {
		var hiderId = elementId + "-disable-layer";
		$('#' + hiderId).remove();
	}
};
