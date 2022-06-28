 $.getJSON('/json/category.json', function(data) {
		let html = "";
		
		for (key in data) {
			let categoryId = data[key].Column1;
			let categoryName = data[key].Column2; 
			
			if(categoryId.toString().length == 3){
				html += '<li><a href="/Search?searchOption=catg&keyword=' + categoryId + '" id="' + categoryId + '">' + categoryName + '</a></li>';
			}
		}
		
		$("#sideList").append(html);
	});