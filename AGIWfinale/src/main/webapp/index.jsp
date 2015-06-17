<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="style.css" />
		<script src="js/d3.min.js"></script>
		<script src="js/d3pie.min.js"></script>
		<title>AGIW Final Project - LackBase</title>		
	</head>
	
	<body>
	
		<form id="formStats" method="POST" action="stats.do"></form>
	
		<table class="tabella">
			<tr>
				<td colspan="2">
					<img id="logo" src="images/logom.png" alt="LackBase logo">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<h1>AGIW Final Project<br></h1><br><br>
					<h1>About</h1><br>
					<p id="box">Freebase.com is a huge knowledge base, but is far from complete. We decided to extend a particular part of it:<br>
					- the triples Soccer Players Relation Nationalities<br>
					- the triples Soccer Teams Relation Stadiums<br>
					<br>
					To make this happen we crawled a famous and big website about this topic: Transfermarkt.com.
					Using XPath queries we iteratively and automatically selected the data we wanted and we used it to extend the Freebase triples!
					</p>
					<div style="display:none" id="box">
						<ol>
							<li>test</li>
							<li>test</li>
							<li>test</li>
							<li>test</li>
						</ol>
					</div>
					<h1>Results</h1><br>
				</td>
			</tr>
			
			<tr>
				<td><div id="pieChart"></div></td>
				<td><div id="pieChart2"></div></td>
			</tr>
			<tr style="text-align:center">
				<td><a href="pdf/triplepn.pdf">View triples with key (PDF)</a></td>
				<td><a href="pdf/triplecs.pdf">View triples with key (PDF)</a></td>
			</tr>
			<tr style="text-align:center">
				<td><a href="pdf/triplepn2.pdf">View triples without key (PDF)</a></td>
				<td><a href="pdf/triplecs2.pdf">View triples without key (PDF)</a></td>
			</tr>
			
			
			<tr>
				<td colspan="2" style="padding-top:50px"><h1><img src="images/extraball.png">Extra</h1><br></td>
			</tr>
			<tr>
				<td colspan="2"><span></span></td>
			</tr>
			
			
		</table>


		
		<script>
		var pie2 = new d3pie("pieChart2", {
			"header": {
				"title": {
					"text": "Team - Stadium",
					"color": "#000000",
					"fontSize": 24,
					"font": "verdana"
				},
				"subtitle": {
					"text": "Percentage of new Team->Stadium found",
					"fontSize": 12,
					"font": "verdana"
				},
				"titleSubtitlePadding": 10
			},
			"footer": {
				"color": "#999999",
				"fontSize": 11,
				"font": "open sans",
				"location": "bottom-center"
			},
			"size": {
				"canvasHeight": 450,
				"canvasWidth": 450,
				"pieOuterRadius": "85%"
			},
			"data": {
				"sortOrder": "value-asc",
				"content": [
					{
						"label": "Teams without stadium",
						"value": ${statsS[0]},
						"color": "#fb7406"
					},
					{
						"label": "Teams with stadium",
						"value": ${statsS[1]},
						"color": "#fd9f00"
					}
				]
			},
			"labels": {
				"outer": {
					"format": "none",
					"pieDistance": 32
				},
				"mainLabel": {
					"font": "verdana",
					"fontSize": 12
				},
				"percentage": {
					"color": "#ffffff",
					"font": "verdana",
					"fontSize": 18,
					"decimalPlaces": 0
				},
				"value": {
					"color": "#ffffff",
					"font": "verdana",
					"fontSize": 20
				},
				"lines": {
					"enabled": true,
					"style": "straight",
					"color": "#cccccc"
				},
				"truncation": {
					"enabled": true
				}
			},
			"tooltips": {
				"enabled": true,
				"type": "placeholder",
				"string": "{label}: {percentage}%",
				"styles": {
					"fadeInSpeed": 249,
					"fontSize": 14
				}
			},
			"effects": {
				"load": {
					"speed": 4000
				},
				"pullOutSegmentOnClick": {
					"effect": "linear",
					"speed": 400,
					"size": 8
				}
			},
			"misc": {
				"pieCenterOffset": {
					"y": 10
				}
			}
		});
		</script>
		
		<script>
		var pie = new d3pie("pieChart", {
			"header": {
				"title": {
					"text": "Player - Nationality",
					"color": "#000000",
					"fontSize": 24,
					"font": "verdana"
				},
				"subtitle": {
					"text": "Percentage of new Player->Nationality found",
					"fontSize": 12,
					"font": "verdana"
				},
				"titleSubtitlePadding": 10
			},
			"footer": {
				"color": "#999999",
				"fontSize": 11,
				"font": "open sans",
				"location": "bottom-center"
			},
			"size": {
				"canvasHeight": 450,
				"canvasWidth": 450,
				"pieOuterRadius": "85%"
			},
			"data": {
				"sortOrder": "value-asc",
				"content": [
					{
						"label": "Players without nationality",
						"value": ${statsN[0]},
						"color": "#fb7406"
					},
					{
						"label": "Player with nationality",
						"value": ${statsN[1]},
						"color": "#fd9f00"
					}
				]
			},
			"labels": {
				"outer": {
					"format": "none",
					"pieDistance": 32
				},
				"mainLabel": {
					"font": "verdana",
					"fontSize": 12
				},
				"percentage": {
					"color": "#ffffff",
					"font": "verdana",
					"fontSize": 18,
					"decimalPlaces": 0
				},
				"value": {
					"color": "#ffffff",
					"font": "verdana",
					"fontSize": 20
				},
				"lines": {
					"enabled": true,
					"style": "straight",
					"color": "#cccccc"
				},
				"truncation": {
					"enabled": true
				}
			},
			"tooltips": {
				"enabled": true,
				"type": "placeholder",
				"string": "{label}: {percentage}%",
				"styles": {
					"fadeInSpeed": 249,
					"fontSize": 14
				}
			},
			"effects": {
				"load": {
					"speed": 4000
				},
				"pullOutSegmentOnClick": {
					"effect": "linear",
					"speed": 400,
					"size": 8
				}
			},
			"misc": {
				"pieCenterOffset": {
					"y": 10
				}
			}
		});
		</script>
		
		<script>

			var margin = {top: 20, right: 20, bottom: 30, left: 40},
			    width = 960 - margin.left - margin.right,
			    height = 500 - margin.top - margin.bottom;
			
			var x0 = d3.scale.ordinal()
			    .rangeRoundBands([0, width], .1);
			
			var x1 = d3.scale.ordinal();
			
			var y = d3.scale.linear()
			    .range([height, 0]);
			
			var color = d3.scale.ordinal()
			    .range(["#973E02",
			            "#B14802", 
			            "#CA5202", 
			            "#E35C02", 
			            "#FD6502", 
			            "#FD761C", 
			            "#FD8535", 
			            "#FD944E", 
			            "#FDA468", 
			            "#FDB381", 
			            "#FDC29B", 
			            "#FDD1B4"]);
			
			var xAxis = d3.svg.axis()
			    .scale(x0)
			    .orient("bottom");
			
			var yAxis = d3.svg.axis()
			    .scale(y)
			    .orient("left")
			    .tickFormat(d3.format(".2s"));
			
			var svg = d3.select("span").append("svg")
			    .attr("width", width + margin.left + margin.right)
			    .attr("height", height + margin.top + margin.bottom)
			  .append("g")
			    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			
			d3.csv("topExtraStats.csv", function(error, data) {
			  if (error) throw error;
			
			  var ageNames = d3.keys(data[0]).filter(function(key) { return key !== "State"; });
			
			  data.forEach(function(d) {
			    d.ages = ageNames.map(function(name) { return {name: name, value: +d[name]}; });
			  });
			
			  x0.domain(data.map(function(d) { return d.State; }));
			  x1.domain(ageNames).rangeRoundBands([0, x0.rangeBand()]);
			  y.domain([0, d3.max(data, function(d) { return d3.max(d.ages, function(d) { return d.value; }); })]);
			
			  svg.append("g")
			      .attr("class", "x axis")
			      .attr("transform", "translate(0," + height + ")")
			      .call(xAxis);
			
			  svg.append("g")
			      .attr("class", "y axis")
			      .call(yAxis)
			    .append("text")
			      .attr("transform", "rotate(-90)")
			      .attr("y", 6)
			      .attr("dy", ".71em")
			      .style("text-anchor", "end")
			      .text("Million $");
			
			  var state = svg.selectAll(".state")
			      .data(data)
			    .enter().append("g")
			      .attr("class", "g")
			      .attr("transform", function(d) { return "translate(" + x0(d.State) + ",0)"; });
			
			  state.selectAll("rect")
			      .data(function(d) { return d.ages; })
			    .enter().append("rect")
			      .attr("width", x1.rangeBand())
			      .attr("x", function(d) { return x1(d.name); })
			      .attr("y", function(d) { return y(d.value); })
			      .attr("height", function(d) { return height - y(d.value); })
			      .style("fill", function(d) { return color(d.name); });
			
			  var legend = svg.selectAll(".legend")
			      .data(ageNames.slice().reverse())
			    .enter().append("g")
			      .attr("class", "legend")
			      .attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });
			
			  legend.append("rect")
			      .attr("x", width - 18)
			      .attr("width", 18)
			      .attr("height", 18)
			      .style("fill", color);
			
			  legend.append("text")
			      .attr("x", width - 24)
			      .attr("y", 9)
			      .attr("dy", ".35em")
			      .style("text-anchor", "end")
			      .text(function(d) { return d; });
			
			});
			
		</script>

	</body>
</html>