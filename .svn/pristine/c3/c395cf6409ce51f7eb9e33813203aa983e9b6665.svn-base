RecipeBook = new function() {

this.addNewRecipe = function () {
//console.log("Click  on addNewRecipe");

	var recNameo=$("#recipe-name").val();
	var ingredo=$("#ingredients").val();
	var direco=$("#directions").val();
	var newRecipe = "recName="+recNameo+"&ingred="+ingredo+"&direc="+direco;

RecipeBookClient.addRecipe(newRecipe);
$('#addRecipe').modal('toggle');
}


this.loadRecipes = function(data) {
	$.get('template/book.html', function(template) {
	var html = Mustache.to_html(template, data);
		$("#recipe-window").html(html);
	});
	}

}
