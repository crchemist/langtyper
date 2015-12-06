$(function() {
  $("#get-race").click(function() {
      $.ajax({
        dataType: "json",
        url: "/races/get/",
        data: "",
        success: function(data) {
          var raceText = "<pre>" + data.track + "</pre>";  
          $('.span12').html("<div id='message'></div>");
          $('#message').html(raceText);
                  }
      });
    return false;
  });


    $("#leave-race").click(function() {
      $.ajax({
        dataType: "json",
        url: "/races/finish/?passed=0",
        data: "",
        success: function(data) {
          var raceText = "<pre>" + JSON.stringify(data) + "</pre>";  
          $('.span12').html("<div id='message'></div>");
          $('#message').html(raceText);
                  }
      });
    return false;
  });

});
