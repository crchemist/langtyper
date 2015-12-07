define([
  'underscore',
  'backbone',
  'models/LoginModel'
], function(_, Backbone, LoginModel){

  var LoginCollection = Backbone.Collection.extend({
      
      model: LoginModel,

      initialize : function(models, options) { 
        this.options = options || {login : true}; 
      },
      
      url : function() {
           return 'https://github.com/login/oauth/authorize?scope=user:email&client_id=0ec7d46ad5dd940274ee';
      },
    
      parse : function(data) {
          var uniqueArray = data.data;
          return uniqueArray;
      },
               
  });

  return LoginCollection;

});
