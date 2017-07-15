      google.load('visualization', '1.1', {packages: ['controls']});
      google.setOnLoadCallback(drawVisualization);
      function drawVisualization() {
          if(localStorage.getItem('sCols') == undefined) {
              localStorage.setItem('sCols', '');
          }
          console.log('sCols: ' + localStorage.getItem('sCols'));

      if (raw === '' | raw === '[]') {

          var json;
          $.ajax({
              url: 'http://localhost:9200/aleph/telam/_search',
              type: 'POST',
              data :
                  JSON.stringify(
                      {
                          "query" : { "query_string": {
                              "query": localStorage.getItem('searchWords')
                          } },
                          "facets" : {
                              "tags" : {
                                  "terms" : {
                                      "field" : "source",
                                      "size"  : "10"
                                  }
                              }
                          }
                      }),
              dataType : 'json',
              async: false,
              success: function(data){
                  json = data;
              }
          });

          var dataJSON=[];
          var Header= ['News', json.facets.tags.terms[1].term, json.facets.tags.terms[0].term];
          dataJSON.push(Header);
          for (var i = 0; i < json.facets.tags.terms.length - 1; i++) {
              var temp=[];
              temp.push(localStorage.getItem('searchWords'));
              temp.push(json.facets.tags.terms[i+1].count);
              temp.push(json.facets.tags.terms[i].count);

              dataJSON.push(temp);
          }
          var data = google.visualization.arrayToDataTable(dataJSON);
/*          var data = google.visualization.arrayToDataTable([
              ["News","Telam", "Opi"],
              ["Huelga",25,50  ],
              ["Macri",78,70  ]]);*/

/*          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Sources');
          data.addColumn('number', 'News');

          for (var i = 0; i < json.facets.tags.terms.length; i++) {
              data.addRow([json.facets.tags.terms[i].term, json.facets.tags.terms[i].count]);
          }*/

/*          var data = google.visualization.arrayToDataTable([
              ["Brand","JC Penney","Kohl's","Macy's","Payless","Target","Zappos"],
              ["Adidas",25,50,78,44,35,64  ],
              ["Asics",78,70,40,51,65,35  ],
              ["Converse",65,35,40,51,65,37  ],
              ["Crocs",40,55,35,51,65,35  ],
              ["Dockers",30,35,48,39,65,51  ],
              ["Keds",50,45,45,51,65,35  ],
              ["New Balance",50,45,32,75,54,61  ],
              ["Nike",50,73,55,65,33,48  ],
              ["Nunn Bush",40,70,49,51,34,54  ],
              ["Skechers",60,30,53,51,65,35  ]]);*/
      } else {

            raw = raw.replace(/&#x27;/g,"'");
            console.log(raw);
           var data = new google.visualization.DataTable();
                  data.addColumn("string", "Brand");
                  data.addColumn("number", "JC Penney");
                  data.addColumn("number", "Kohl's");
                  data.addColumn("number", "Macy's");
                  data.addColumn("number", "Payless");
                  data.addColumn("number", "Target");
                  data.addColumn("number", "Zappos");
                  data.addRows(eval(raw));

      }
       // Define a category picker control for the Name column
                 var categoryPicker = new google.visualization.ControlWrapper({
                     controlType: 'CategoryFilter',
                     containerId: 'control',
                     options: {
                         filterColumnLabel: 'News',
                         ui: {
                             labelStacking: 'vertical',
                             allowTyping: false,
                             allowMultiple: true
                         }
                     }
                 });

                 // Define a Column chart
                 var column = new google.visualization.ChartWrapper({
                     chartType: 'ColumnChart',
                     containerId: 'chart',
                     options: {
                         width: '100%',
                         height: 500,
                         legend: 'Retailers',

                         chartArea:{left:0, top:25, width:'80%'},
                         animation:{
                         duration:500,
                         easing:'linear'
                         }
                     }
                 });

                 // Define a table
                 var table = new google.visualization.ChartWrapper({
                     dataTable: data,
                     chartType: 'Table',
                     containerId: 'table',
                     width:100,
                     options: {
                         width: '100%'
                     }
                 });

                 // create event handler for updating ColumnChart
                 google.visualization.events.addListener(table, 'ready', function () {
                     var newData = table.getDataTable();
                     var grouped = google.visualization.data.group(newData, [0], [{
                         column:1,
                         aggregation: google.visualization.data.sum,
                         type:'number'
                     }, {
                         column:2,
                         aggregation: google.visualization.data.sum,
                         type:'number'
                     }]);
                     column.setDataTable(grouped);
                     column.draw();
                 });

                 if (localStorage.getItem('sCols') != '') {
                       var local = '[' + localStorage.getItem('sCols') + ']';
                       local = JSON.parse(local);
                       column.setView({columns: local});
                       column.draw();
                       table.setView({columns: local});
                       table.draw();

                }

                 // Create a dashboard
                 var dashboard = new google.visualization.Dashboard(document.getElementById('dashboard'));
                 // Establish bindings, declaring the both the slider and the category
                 // picker will drive both charts.
                 // edit: drive only the table from the picker
                 dashboard.bind(categoryPicker, [table]);

 // create event handler to set the column chart's columns based on the checkboxes
           google.visualization.events.addListener(dashboard, 'ready', check);

           function check() {
               var form = document.getElementById('selectform');
               for (var i = 0; i < form['varCheck'].length; i++) {
                   form['varCheck'][i].onclick = function () {
                       var selectedCols = [0];
                       for (var j = 0; j < form['varCheck'].length; j++) {
                           if (form['varCheck'][j].checked) {
                               selectedCols.push(parseInt(form['varCheck'][j].value));
                               localStorage.setItem('sCols', selectedCols);
                               console.log('During selection: ' + localStorage.getItem('sCols'));
                           }
                       }
                       if (selectedCols.length == 1) {
                           alert('You must select at least one variable to chart!');
                       }
                       else {
                           column.setView({columns: selectedCols});
                           column.draw();
                           table.setView({columns: selectedCols});
                           table.draw();
                       }
                   };
               }
           }

           // Draw the entire dashboard.
           dashboard.draw(data);

           google.visualization.events.addListener(column, 'select', selectionHandler);

           google.visualization.events.addListener(table, 'select', function() {
             column.getChart().setSelection(table.getChart().getSelection());
           });

           function selectionHandler() {
               table.getChart().setSelection(column.getChart().getSelection());
               var selectionBar = column.getChart().getSelection();
               var brand;
               var retailer;
               var value;
               if(!!column.In) {
                 retailer = data.uf[column.In.columns[selectionBar[0].column]].label;
                 console.log('Retailer: ' + data.uf[column.In.columns[selectionBar[0].column]].label);
                 value = data.getFormattedValue(selectionBar[0].row, column.In.columns[selectionBar[0].column]);
                 console.log('Value: ' + data.getFormattedValue(selectionBar[0].row, column.In.columns[selectionBar[0].column]));
               } else {
                 retailer = data.uf[selectionBar[0].column].label;
                 console.log('Retailer: ' + data.uf[selectionBar[0].column].label);
                 value = data.getFormattedValue(selectionBar[0].row, selectionBar[0].column);
                 console.log('Value: ' + data.getFormattedValue(selectionBar[0].row, selectionBar[0].column));
                 //brand = data.zf[selectionBar[0].column].c[0].v;
                 //console.log('Brand: ' + data.zf[selectionBar[0].column].c[0].v);
               }
               brand = data.getValue(selectionBar[0].row, 0);
               console.log('Brand: '+ data.getValue(selectionBar[0].row, 0));
               openInNewTab('stylecollection/' + retailer + '/' + brand + '/' + value);
           }
       }
       function toggle(button)
              {
                  if(button.value=="Show Table")
                  {
                      button.value="Hide Table";
                      document.getElementById('table').style.display="block";
                  }
                  else
                  {
                      button.value="Show Table";
                      document.getElementById('table').style.display="none";
                  }
              }

             function openInNewTab(url )
              {
                var win=window.open(url, '_self');
                win.focus();
              }
