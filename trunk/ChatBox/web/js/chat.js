/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = function(){
    deselectAllTabs = function(){
        $('.tab').removeClass('selected');
    };

    createNewTab = function(id,title){
        $('#tabs ul').append('<li id="'+id+'" class="tab">'+title+' <a class="close-tab-button" href="#" onClick="closeTab(\''+id+'\')">x</a></li>');
        $('.tab#'+id).click(function(){
            alert('clicked');
            deselectAllTabs();
            $(this).addClass('selected');
        });
    };

    closeTab = function(id){
        $('.tab#'+id).remove();
    };

    $('#main').load('AjaxRequestHandler','page=rooms',function(){
        deselectAllTabs();
        $('#rooms').addClass('selected');
    });
    
    $('#rooms').click(function(){
        $('#main').load('AjaxRequestHandler','page=rooms',function(){
            deselectAllTabs();
            $('#rooms').addClass('selected');
        });
    });

    $('#friends').click(function(){
        $('#main').load('AjaxRequestHandler','page=friends',function(){
            deselectAllTabs();
            $('#friends').addClass('selected');
        });
    });

    $('#inputline').keypress(function(e){
        if(e.which == 13) {
            $(this).val('');
        }
    });
};