(function(window, undefined) {
  var dictionary = {
    "7c06dd12-c0cc-4edf-b895-3f5c7aa45764": "NuevoMenu",
    "6cd3e8ab-eab2-4f28-bf67-e6f09f4cca12": "info",
    "3932dcff-fbc4-4253-b878-ffcc451dbbbc": "platos",
    "2a49204e-3044-4203-bb54-79da0bddb8bb": "Menus",
    "7044a398-3dd9-49a1-a372-40f1a86557ae": "EditarUbicacion",
    "fe9c5880-0f1b-43fc-9a13-9741033100df": "NuevoPlato",
    "d12245cc-1680-458d-89dd-4f0d7fb22724": "login",
    "f39cced0-6af6-4c06-9d7d-a230c734f2a8": "AgregarPlatoMenu",
    "b0ed1802-55e3-48ee-83d7-0fa9251622c1": "EditarMenu",
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