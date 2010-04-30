/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = function(){
    currentState = {
        tab:'rooms',
        id:'',
        lastMsgId:-1,
        prevTab:undefined
    };

    deselectAllTabs = function(){
        $('.tab').removeClass('selected');
    };

    createNewTab = function(tab,title){
        id = tab+'-'+title;
        $('#tabs ul').append('<li id="'+id+'" class="tab"><span>#'+title+'</span> <a class="close-tab-button" href="#" onClick="closeTab(\''+id+'\')">x</a></li>');
        $('#'+id+'.tab span').click(function(){
            currentState.tab = tab;
            currentState.id = id;
            currentState.prevTab=$('.tab.selected');
            deselectAllTabs();
            $('#'+id+'.tab').addClass('selected');
        });
        $('#'+id+'.tab span').click();
    };

    createNewRoomTab = function(title){
        createNewTab('room',title);
        loadRoom(title);
    };

    loadRoom = function(roomname){
        $('#main').load('AjaxRequestHandler','tab=room&roomname='+roomname);
    };

    closeTab = function(id){
        currentState.prevTab.click();
    };

    $('#main').load('AjaxRequestHandler','tab=rooms',function(){
        deselectAllTabs();
        $('#rooms').addClass('selected');

    });

    refreshRoom = function(){
        $('#main').load('AjaxRequestHandler','tab=rooms',null);
    };

    refreshFriend = function(mode){
        $('#main').load('AjaxRequestHandler','tab=friends&view='+mode,null);
    }

    $('#rooms').click(function(){
        currentState.tab = 'rooms';
        currentState.prevTab=$(this);
        deselectAllTabs();
        $(this).addClass('selected');
        $('#main').load('AjaxRequestHandler','tab=rooms',null);
    });

    $('#friends').click(function(){
        currentState.tab = 'friends';
        currentState.prevTab=$(this);
        deselectAllTabs();
        $(this).addClass('selected');
        $('#main').load('AjaxRequestHandler','tab=friends&view=all',null);
    });

    $('#inputline').keypress(function(e){
        if(e.which == 13) {
            if(/^\/create [A-Za-z0-9]+( [0-9]*)?$/.test($(this).val())){
                param = $(this).val().split(" ");
                $.post('AjaxRequestHandler','act=create&roomname='+param[1]+'&kode='+param[2],function(data){
                    createNewRoomTab(param[1]);
                });
            } else if(/^\/join [A-Za-z0-9]+$/.test($(this).val())){

            } else if(/^\/chat [A-Za-z0-9]+$/.test($(this).val())){

            } else if(/^\/addfriend [A-Za-z0-9]+$/.test($(this).val())){
                param = $(this).val().split(" ");
                $.post('AjaxRequestHandler','act=addfriend&friendname='+param[1],function(data){
                   if(currentState.tab == 'friends'){
                       refreshFriend();
                   } 
                });
            } else if(/^\/exit$/.test($(this).val())){

            } else if(/^\/info [A-Za-z0-9]+$/.test($(this).val())){

            } else if(/^\/kick [A-Za-z0-9]+$/.test($(this).val())){

            } else if(/^\/ban [A-Za-z0-9]+$/.test($(this).val())){

            } else if(/^\/unban [A-Za-z0-9]+$/.test($(this).val())){

            } else if(/^\/persist$/.test($(this).val())){

            } else if(/^\/temp$/.test($(this).val())){

            } else if(/^\/setowner [A-Za-z0-9]+$/.test($(this).val())){

            } else {

            }
            $(this).val('');
        }
    });
};