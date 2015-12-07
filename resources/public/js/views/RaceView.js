define([
  'jquery',
  'underscore',
  'backbone',
  'collections/RaceCollection',
  'text!templates/RaceTemplate.html'
], function($, _, Backbone, RaceCollection, RaceTemplate){

  //var contributorsListView;

  var RaceView = Backbone.View.extend({
    
    el: $("#page"),
    events: {
 	 'click #get-race' : 'getRace',
 	 'click #leave-race' : 'leaveRace'
       	},
    initialize:function() {

    },

    render: function(data){
      var data = data || {track : ""}
      var compiledTemplate = _.template( RaceTemplate, data );
      this.$el.html( compiledTemplate ); 
    },

     getRace: function(){
      var that = this;

      var onDataHandler = function(collection, response) {
           that.render({track : response.track});
           }

      that.collection = new RaceCollection([], {action : 'get/'}); 
      that.collection.fetch({ success : onDataHandler});
     },

     leaveRace: function(){
      var that = this;

      var onDataHandler = function(collection, response) {
           that.render({track : response.track});
           }

      that.collection = new RaceCollection([], {action : 'finish/?passed=0'}); 
      that.collection.fetch({ success : onDataHandler});
     },

  });
  return RaceView;
});
