 google.load('visualization', '1.1', {packages: ['controls']});
      google.setOnLoadCallback(drawVisualization);
function drawVisualization() {
  // Prepare the data
        var data = google.visualization.arrayToDataTable([
          ['Retailer', 'Competitor', 'Shared', 'Kohls'],
          ['Target',  30,    15,    55   ],
          ['Macys',  20,    40,    40   ],
          ['JC Penney',  10,    30,    60    ],
          ['Zappos',  50,    15,    35   ],

        ]);

  // Define a category picker control for the Gender column
  var categoryPicker = new google.visualization.ControlWrapper({
    'controlType': 'CategoryFilter',
    'containerId': 'control',
    'options': {
      'filterColumnLabel': 'Retailer',
      'ui': {
      'labelStacking': 'vertical',
        'allowTyping': false,
        'allowMultiple': false,

      }
    }
  });




  // Define a Pie chart
  var bar = new google.visualization.ChartWrapper({
    'chartType': 'BarChart',
    'containerId': 'panel-1',
    'options': {
          'width': '100%',

          'isStacked': true,
          'title': 'Overlap',
          'chartArea':{left:80, width:'70%'}

        }
  });

  // Define a table
  var line = new google.visualization.ChartWrapper({
    'chartType': 'LineChart',
    'containerId': 'panel-2',
    'options': {
              'width': '100%',

              'title': 'Prices',
                'chartArea':{left:80, width:'70%'}

            }

  });
    var area = new google.visualization.ChartWrapper({
      'chartType': 'AreaChart',
      'containerId': 'panel-3',
      'options': {
                'width': '100%',

                'title': 'Sales',
                  'chartArea':{left:80, width:'70%'}

              }

    });
        var pie = new google.visualization.ChartWrapper({
          'chartType': 'PieChart',
          'containerId': 'panel-4',
          'options': {
                    'width': '100%',

                    'title': 'Offers and Promotions',
                      'chartArea':{left:80, width:'100%'}

                  }

        });

  // Create a dashboard
  new google.visualization.Dashboard(document.getElementById('main-dashboard')).
      // Establish bindings, declaring the both the slider and the category
      // picker will drive both charts.
      bind([categoryPicker], [bar, area,pie, line]).
      // Draw the entire dashboard.
      draw(data);
}