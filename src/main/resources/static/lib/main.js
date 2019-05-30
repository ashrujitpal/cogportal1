'use strict';

$('.request-block__container').each(function( index, element ) {
  $(element).click(function(){
    $(element).toggleClass("request-block__container--selected");
  })
});