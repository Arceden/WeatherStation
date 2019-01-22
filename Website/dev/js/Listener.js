var Listener = function(){

    this.list = {};
    this.default_item = {
      elements: [],
      popupHandler: null
    };

    /**
     * Add multiple new elements
     * name = String
     * elements = HTMLCollection
     * PopupHandler = defined popupHandler
     */
    this.newElements = function(name, elements, PopupHandler){
      
      //If a list has not been defined, set it to a default empty list
      if(this.list[name]==undefined)
        this.list[name] = JSON.parse(JSON.stringify(this.default_item));
      
      //If a handler has been provided, add it to the list
      if(PopupHandler!=null)
        this.list[name].popupHandler = PopupHandler;

        if(elements==null)
            console.warn("Could not find any elements for "+name);

      //Add all elements to the list
      for(var x=0;x<elements.length;x++){
        this.list[name].elements.push(elements[x]);
      }

    }

    //action = open/close
    this.applyHandler = function(name){

      var item = this.list[name];

      // Loop through all elements to open
      for(var x=0;x<item.elements.length;x++){
        item.elements[x].addEventListener("click", function(){
          item.popupHandler.open();
        });  
      }

      //Close

      item.popupHandler.getCloseElement().addEventListener("click", function(){
        item.popupHandler.close();
      });

    }

  }