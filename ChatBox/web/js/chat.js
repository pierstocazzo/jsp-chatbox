/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = function(){
    $('.tab').click(function(){
        $('.tab').removeClass('selected');
        $(this).addClass('selected');
    });

    $('#inputline').keypress(function(e){
        if(e.which == 13) {
            $(this).val('');
        }
    });
};