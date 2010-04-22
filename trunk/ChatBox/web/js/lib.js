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
    for(i in this.elmts){
        this.elmts[i].value = val;
    }
};

// Inserting Inside
QueryObject.prototype.append = function(content){
    if(typeof content == 'string'){
        for(i in this.elmts){
            this.elmts[i].innerHTML += content;
        }
    } else if(content instanceof QueryObject){
        for(i in this.elmts){
            for(j in content.elmts){
                this.elmts[i].appendChild(content.elmts[j])
            }
        }
    } else if(content instanceof Element){
        for(i in this.elmts){
            return this.elmts[i].appendChild(content);
        }
    }
    return this;
};

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
}

/* Events */
// Event Handling
QueryObject.prototype.click = function(fn){
    for(i in this.elmts){
        this.elmts[i].onclick = fn;
    }

    return this;
};

QueryObject.prototype.keypress = function(fn){
    for(i in this.elmts){
        this.elmts[i].onkeypress = fn;
    }
}

/* Ajax */
$.ajax = function(options){
    var xmlhttp;

    if(options.type == "GET") {
        options.data = null;
}

    if(window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function(options){
            var response;

            if(xmlhttp.readyState == 4){
                switch(option.dataType){
                    case 'text':
                        response = xmlhttp.responseText;
                    break;
                    case 'html':
                        response = xmlhttp.responseText;
                    break;
                    case 'xml':
                        response = xmlhttp.responseText;
                    break;
                    case 'json':
                        response = eval('('+xmlhttp.responseText+')');
                    break;
                }
                if(xmlhttp.status == 200) {
                    options.success(xmlhttl);
                }
                else {
                    options.error(xmlhttp);
                }
                options.complete(xmlhttp);
            }
        };
        xmlhttp.open(option.type,option.url,true);
        xmlhttp.send(option.data);
    }
};