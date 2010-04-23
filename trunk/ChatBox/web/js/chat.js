/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = function(){
    currentState = {
        mode:'rooms',
        id:0,
        lastMsgId:-1,
        prevTab:undefined
    };

    deselectAllTabs = function(){
        $('.tab').removeClass('selected');
    };

    createNewTab = function(mode,id,title){
        $('#tabs ul').append('<li id="'+id+'" class="tab"><span>'+title+'</span> <a class="close-tab-button" href="#" onClick="closeTab(\''+id+'\')">x</a></li>');
        $('#'+id+'.tab span').click(function(){
            currentState.mode = mode;
            currentState.id = id;
            currentState.prevTab=$('.tab.selected');
            deselectAllTabs();
            $('#'+id+'.tab').addClass('selected');
        });
    };

    closeTab = function(id){
        $('#'+id+'.tab').remove();
        deselectAllTabs();
        currentState.prevTab.addClass('selected');
    };

    $('#main').load('AjaxRequestHandler','page=rooms',function(){
        deselectAllTabs();
        $('#rooms').addClass('selected');

    });
    
    $('#rooms').click(function(){
        currentState.mode = 'rooms';
        currentState.prevTab=$(this);
        deselectAllTabs();
        $(this).addClass('selected');
        $('#main').load('AjaxRequestHandler','page=rooms',null);
    });

    $('#friends').click(function(){
        currentState.mode = 'friends';
        currentState.prevTab=$(this);
        deselectAllTabs();
        $(this).addClass('selected');
        $('#main').load('AjaxRequestHandler','page=friends',null);
    });

    $('#inputline').keypress(function(e){
        if(e.which == 13) {
            $(this).val('');
        }
    });
};