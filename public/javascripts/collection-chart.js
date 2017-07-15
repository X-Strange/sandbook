 google.load('visualization', '1.1', {packages: ['controls']});
      google.setOnLoadCallback(drawVisualization);
function drawVisualization() {
  // Prepare the data
  var data = google.visualization.arrayToDataTable([
          ['Year', 'Min-Price', 'Max-Price','Average-Price'],
          [new Date(2011, 1, 28),  50,      60, 55],
          [new Date(2012, 1, 28),  60,      70, 65],
          [new Date(2013, 1, 28),  70,       80, 75],
          [new Date(2014, 1, 28),  80,      90, 85]
        ]);

  // Define a slider control for the Age column.
  var slider = new google.visualization.ControlWrapper({
    'controlType': 'DateRangeFilter',
    'containerId': 'control1',
    'options': {
      'filterColumnLabel': 'Year',
    'ui': {'labelStacking': 'vertical', format:{pattern: "yyyy"}}
    }
  });




  // Define a Pie chart
  var pie = new google.visualization.ChartWrapper({
    'chartType': 'LineChart',
    'containerId': 'collection-chart',
    'options': {
                        'width': '100%',
                        'chartArea':{left:80, width:'80%'}

                      }

  });

  // Define a table
  var table = new google.visualization.ChartWrapper({
    'chartType': 'Table',
    'containerId': 'collection-table'

  });

  // Create a dashboard
  new google.visualization.Dashboard(document.getElementById('collection-dash')).
      // Establish bindings, declaring the both the slider and the category
      // picker will drive both charts.
      bind([slider], [pie, table]).
      // Draw the entire dashboard.
      draw(data);
}