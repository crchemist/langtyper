define([
  'underscore',
  'backbone',
  'models/RaceModel'
], function(_, Backbone, RaceModel){

  var RaceCollection = Backbone.Collection.extend({
      
      model: RaceModel,

      initialize : function(models, options) {
        this.options = options;      
      },
      
      url : function() {
        return '/races/' + this.options.action;
      },
    
      parse : function(data) {
          var uniqueArray = data.data;
          return uniqueArray;
      },
               
  });

  return RaceCollection;

});
