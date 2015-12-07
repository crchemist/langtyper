define([
  'jquery',
  'underscore',
  'backbone',
   'text!templates/home/homeTemplate.html'
], function($, _, Backbone, homeTemplate){

  var HomeView = Backbone.View.extend({
    el: $("#page"),
    events: {
	 'click #login' : 'loginUser',
	 'click #logout' : 'logoutUser'
 	},

    loginUser: function(){
      $("#log").attr("id", "logout");
      $("#log").html("Logout");
	},

    logoutUser: function(){

	},


    render: function(){
      this.$el.html(homeTemplate);
    }

  });

  return HomeView;
  
});
