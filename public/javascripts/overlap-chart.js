 google.load('visualization', '1.1', {packages: ['controls']});
      google.setOnLoadCallback(drawVisualization);
function drawVisualization() {
  // Prepare the data
        var data = google.visualization.arrayToDataTable([
          ['Retailer', 'Competitor', 'Shared', 'Kohls'],
          ['Target',  30,   15,    55   ],
          ['Macys',  20,    40,    40   ],
          ['JC Penney',  10,    30,    60    ],
          ['Zappos',  50,    15,   35   ],

        ]);

  // Define a category picker control for the Gender column
  var categoryPicker = new google.visualization.ControlWrapper({
    'controlType': 'CategoryFilter',
    'containerId': 'picker',
    'options': {
      'filterColumnLabel': 'Retailer',
      'ui': {
      'labelStacking': 'vertical',
        'allowTyping': false,
        'allowMultiple': true

      }
    }
  });




  // Define a Pie chart
  var pie = new google.visualization.ChartWrapper({
    'chartType': 'BarChart',
    'containerId': 'chart',
    'options': {
          'width': '100%',
          'height':300,
          'isStacked': true,
          'title': 'Overlap',
          'hAxis':{title:'Percentage'},
          'chartArea':{left:80, width:'80%'}

        }
  });

  // Define a table
  var line = new google.visualization.ChartWrapper({
    'chartType': 'LineChart',
    'containerId': 'linechart',
    'options': {
              'width': '100%',
              'height':400,
              'title': 'Prices',
                'chartArea':{left:80, width:'80%'}

            }

  });

  // Create a dashboard
  new google.visualization.Dashboard(document.getElementById('dashboard')).
      // Establish bindings, declaring the both the slider and the category
      // picker will drive both charts.
      bind([categoryPicker], [pie, line]).
      // Draw the entire dashboard.
      draw(data);
}