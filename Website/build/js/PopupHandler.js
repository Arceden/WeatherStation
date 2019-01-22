var PopupHandler = function () {
  this.item = {
    type: null,
    element: null,
    close_button: null,
    state: false
  }; //Add popups to the list

  this.newPopup = function (element, type) {
    if (type == null) type = element.id;
    this.item = {
      type: type,
      element: element,
      close_button: this.searchCloseElement(element),
      state: false
    };
    return this;
  }; //Search for the close button


  this.searchCloseElement = function (element) {
    var c = element.children[0];

    for (var x = 0; x < c.childNodes.length; x++) {
      if (c.children[x].className == "close") {
        return c.children[x];
      }
    }

    return null;
  }; //Open popup


  this.open = function (type) {
    this.item.element.classList.remove('invisible');
    this.item.state = true;
  }; //Close popup


  this.close = function (type) {
    this.item.element.classList.add('invisible');
    this.item.state = false;
  }; //Get element


  this.getElement = function (type) {
    return this.item.element;
  }; //Get close element


  this.getCloseElement = function () {
    return this.item.close_button;
  };
};