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
					- the couples Soccer Players -> Nationalities<br>
					- the couples Soccer Teams -> Stadiums<br>
					<br>
					To make this happen we crawled a famous and big website about this topic: Transfermarkt.com.
					Using XPath queries we iteratively and automatically selected the data we wanted and we used it to extend the Freebase couples!
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
					"text": "Percentage of new Team->Stadium couples found",
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
					"speed": 2000
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
					"text": "Percentage of new Player->Nationality couples found",
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
					"speed": 2000
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

	</body>
</html>