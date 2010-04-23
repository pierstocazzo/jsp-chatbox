/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* Query Object */
var QueryObject = function(){
};

/* Selector */
var $ = function(query){
    qo = new QueryObject();

    if(typeof query == 'string'){
        qo.elmts = document.querySelectorAll(query);
    } else if(query instanceof Element){
        qo.elmts = [query];
    } else if(query instanceof QueryObject){
        qo = query;
    }

    return qo;
};

/* Manipulation */
// Changing Contents
QueryObject.prototype.html = function(html){
    for(i in this.elmts){
        this.elmts[i].innerHTML = html;
    }
};

QueryObject.prototype.val = function(val){
    if(val != null){
        for(i in this.elmts){
            this.elmts[i].value = val;
        }
        return this;
    } else {
        for(i in this.elmts){
            return this.elmts[i].value;
        }
    }
};

// Inserting Inside
QueryObject.prototype.append = function(content){
    if(typeof content == 'string'){
        for(i in this.elmts){
            container = document.createElement("div");
            container.innerHTML = content;
            temp = container.childNodes;
            for(t in temp){
                if(this.elmts[i] instanceof Element && temp.item(t) instanceof Element)
                    this.elmts[i].appendChild(temp.item(t));
            }
        }
    } else if(content instanceof QueryObject){
        for(i in this.elmts){
            for(j in content.elmts){
                if(this.elmts[i] instanceof Element && content.elmts(j) instanceof Element)
                    this.elmts[i].appendChild(content.elmts[j])
            }
        }
    } else if(content instanceof Element){
        for(i in this.elmts){
            this.elmts[i].appendChild(content);
        }
    }
    return this;
};

// Inserting Outside
QueryObject.prototype.after = function(content){
    if(typeof content == 'string'){
        for(i in this.elmts){
            container = document.createElement("div");
            container.innerHTML = content;
            temp = container.childNodes;
            for(t = temp.length-1; t >= 0; t--){
                if(this.elmts[i] instanceof Element && temp.item(t) instanceof Element)
                    this.elmts[i].parentNode.insertBefore(temp.item(t),this.elmts[i].nextSibling);
            }
        }
    } else if(content instanceof QueryObject){
        for(i in this.elmts){
            for(j = content.elmts.length; j >= 0; t--){
                if(this.elmts[i] instanceof Element && content.elmts(j) instanceof Element)
                    this.elmts[i].parentNode.insertBefore(content.elmts[j],this.elmts[i].nextSibling)
            }
        }
    } else if(content instanceof Element){
        for(i in this.elmts){
            this.elmts[i].parentNode.insertBefore(content,this.elmts[i].nextSibling);
        }
    }
    return this;
};

// Removing Element
QueryObject.prototype.remove = function(expr){
    for(i in this.elmts){
        if(this.elmts[i].parentNode){
            this.elmts[i].parentNode.removeChild(this.elmts[i])
        }
    }
    return this;
}

/* CSS */
//Class
QueryObject.prototype.addClass = function(String){
    for(i in this.elmts){
        if(!this.elmts[i].className) continue;

        addition = String.split(' ');
        classes = this.elmts[i].className.split(' ');
        for(a in addition){
            for(c in classes){
                if(classes[c] == addition[a]){
                    classes[c] = undefined;
                }
            }
        }

        classes = classes.concat(addition);
        this.elmts[i].className = classes.join(' ');
    }

    return this;
};

QueryObject.prototype.removeClass = function(String){
    for(i in this.elmts){
        if(!this.elmts[i].className) continue;

        removed = String.split(' ');
        classes = this.elmts[i].className.split(' ');
        for(r in removed){
            for(c in classes){
                if(classes[c] == removed[r]){
                    classes[c] = undefined;
                }
            }
        }
        
        this.elmts[i].className = classes.join(' ');
    }

    return this;
}

/* Events */
// Event Handling
QueryObject.prototype.click = function(fn){
    for(i in this.elmts){
        if(!!fn)
            this.elmts[i].onclick = fn;
        else
            if(this.elmts[i].onclick)
                this.elmts[i].onclick();
    }

    return this;
};

QueryObject.prototype.keypress = function(fn){
    for(i in this.elmts){
        this.elmts[i].onkeypress = fn;
    }
    return this;
}

/* Ajax */
$.ajax = function(options){
    var xmlhttp;

    if(!options.contentType) {
        options.contentType = 'application/x-www-form-urlencoded';
    }

    if(options.type == "GET") {
        options.data = null;
    }

    if(window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();

        xmlhttp.onload = function(){
            if(!!options.success)
                options.success(xmlhttp.responseText, xmlhttp.statusText);
            if(!!options.complete)
                options.complete(xmlhttp.responseText, xmlhttp.statusText);
        };
        
        xmlhttp.open(options.type,options.url,true);
        
        xmlhttp.setRequestHeader('Content-type', options.contentType);
        xmlhttp.send(options.data);
    }

    return xmlhttp;
};

$.post = function(url,data,callback,type){
    var opt = {
        url:url,
        type:"POST",
        data:data,
        dataType:type
    };

    if(!!callback)
        opt.complete = callback;

    return $.ajax(opt);
}

QueryObject.prototype.load = function(url,data,callback){
    var o = $(this);

    var opt = {
        url:url,
        type:"POST",
        data:data,
        success:function(data, status) {
            o.html(data);
        },
        dataType:'html'
    };

    if(!!callback)
        opt.complete = callback;

    $.ajax(opt)

    return this;
}