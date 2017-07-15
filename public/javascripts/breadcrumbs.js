 $(document).ready(function(){

  $(function() {
    $('#tools a[href^="/' + location.pathname.split("/")[1] + '"]').parent().addClass('active');
  });
});