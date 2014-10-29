RecipeBookClient = new function() {

this.addRecipe = function(newRecipe) {
RecipeBookAppUtil.makeRequest("POST","/recipebook/recipes/",newRecipe,
function(html) {
$("#response-textarea").val(JSON.stringify(html));
RecipeBookClient.viewRecipes();
});
}

this.viewRecipe = function(recipeName) {
RecipeBookAppUtil.makeRequest("GET","/recipebook/recipes/"+recipeName+"", null ,
function(html) {
$("#response-textarea").val(JSON.stringify(html));
$("#request-textarea").val(recipeName);
});

}

var initStatus=true;

this.viewRecipes = function() {
	
	if(initStatus){		
		initStatus=false;
		RecipeBookClient.initViewRecipes();
	}
	else{
		RecipeBookAppUtil.makeJsonRequest("GET","/recipebook/recipes/", null ,function(html) {
			//console.log(html);
			RecipeBook.loadRecipes(html);
		});
	}
}

this.initViewRecipes = function() {
RecipeBookAppUtil.makeRequest("GET","/recipebook/recipes/", null ,function(html) {
	//console.log(html);
	RecipeBook.loadRecipes(html);
	$("#response-textarea").val(JSON.stringify(html));
	if(html.Infor != null){
		alert(html.Infor +"\n Click 'New Recipe' to add a recipe");
 	}	
});
}


}
