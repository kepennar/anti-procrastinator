	// for each iterates over a list and runs a function for each element
	var forEach = Array.prototype.forEach,

	// No jQuery
	$$ = {
		selector : function(selector) {
			var all = document.querySelectorAll.bind(document)(selector);
			return {
		    	results: all,
		    	result: all[0]
		    }
		},
		get: function(options) {
			var args = Array.prototype.slice.call(arguments, 1);

			request = new XMLHttpRequest();
			request.open('GET', options.url, true);
	        request.setRequestHeader('Accept', options.contentType);

			request.onload = function() {
				if (request.status >= 200 && request.status < 400) {
					// Success!
					var data;
					if (options.contentType === 'application/json') {
						data = JSON.parse(request.responseText);
					} else {
						data = request.responseText;
					}				
					args.unshift(data);
					options.success.apply(this, args);
				}
			};

			request.onerror = function(e) {
				options.fail(e);
			};

			request.send();
		}
	};
	