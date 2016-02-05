(function(window, undefined) {
  var dictionary = {
    "921f9c76-2c42-4504-8105-5ca046d8e700": "Informacion",
    "63b118b9-502d-4836-b000-9e7b1c984d8b": "ListaPlatosAlmuerzos",
    "92c41a19-118d-4038-bed7-7bf4161ec874": "Menus",
    "04852e63-bf1c-4cbb-b95a-c939059499d8": "Platos",
    "d12245cc-1680-458d-89dd-4f0d7fb22724": "Restaurante",
    "f39803f7-df02-4169-93eb-7547fb8c961a": "Template 1"
  };

  var uriRE = /^(\/#)?(screens|templates|masters)\/(.*)(\.html)?/;
  window.lookUpURL = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, url;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      url = folder + "/" + canvas;
    }
    return url;
  };

  window.lookUpName = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, canvasName;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      canvasName = dictionary[canvas];
    }
    return canvasName;
  };
})(window);