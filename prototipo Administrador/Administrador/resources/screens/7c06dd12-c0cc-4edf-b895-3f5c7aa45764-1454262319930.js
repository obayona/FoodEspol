jQuery("#simulation")
  .on("click", ".s-7c06dd12-c0cc-4edf-b895-3f5c7aa45764 .toggle", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-radio-button-off-dark")) {
      if(jFirer.data("jimHasToggle")) {
        jFirer.removeData("jimHasToggle");
        jEvent.undoCases(jFirer);
      } else {
        jFirer.data("jimHasToggle", true);
        event.backupState = true;
        event.target = jFirer;
        cases = [
          {
            "blocks": [
              {
                "actions": [
                  {
                    "action": "jimSetValue",
                    "parameter": {
                      "target": "#s-radio-button-off-dark",
                      "value": ""
                    },
                    "exectype": "serial",
                    "delay": 0
                  },
                  {
                    "action": "jimChangeStyle",
                    "parameter": [ {
                      "#s-7c06dd12-c0cc-4edf-b895-3f5c7aa45764 #s-radio-button-off-dark": {
                        "attributes": {
                          "font-size": "20.0pt",
                          "font-family": "materialdesignjim-Regular,Arial"
                        }
                      }
                    },{
                      "#s-7c06dd12-c0cc-4edf-b895-3f5c7aa45764 #s-radio-button-off-dark .valign": {
                        "attributes": {
                          "vertical-align": "middle",
                          "text-align": "left"
                        }
                      }
                    },{
                      "#s-7c06dd12-c0cc-4edf-b895-3f5c7aa45764 #s-radio-button-off-dark span": {
                        "attributes": {
                          "color": "#8AD9D2",
                          "text-align": "left",
                          "text-decoration": "none",
                          "font-family": "materialdesignjim-Regular,Arial",
                          "font-size": "20.0pt"
                        }
                      }
                    } ],
                    "exectype": "serial",
                    "delay": 0
                  }
                ]
              }
            ],
            "exectype": "serial",
            "delay": 0
          }
        ];
        jEvent.launchCases(cases);
      }
    }
  });