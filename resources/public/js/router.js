// Filename: router.js
define([
  'jquery',
  'underscore',
  'backbone',
  'views/home/HomeView',
  'views/RaceView',
  'collections/LoginCollec',
], function($, _, Backbone, HomeView, RaceView, LoginCollec) {
  
  var AppRouter = Backbone.Router.extend({
    routes: {
      // Define some URL routes
      'races': 'getRace',
      'login': 'loginUser',
      'logout': 'loguotUser',
      
      // Default
      '*actions': 'defaultAction'
    }
  });
  
  var initialize = function(){

    var app_router = new AppRouter;
    
    app_router.on('route:getRace', function () {
    
        var raceView = new RaceView();
        raceView.render()
    });

    app_router.on('route:loginUser', function () {
        var homeView = new HomeView();
    });

    app_router.on('route:logoutUser', function () {
        var homeView = new HomeView();
    });

    app_router.on('route:defaultAction', function (actions) {
     
        var homeView = new HomeView();
        homeView.render();
    });

    Backbone.history.start();
  };
  return { 
    initialize: initialize
  };
});
