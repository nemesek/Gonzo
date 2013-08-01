var questionCount = 5;
			
var questionData;

$(document).ready(function(){

	//DO GET DATA
	$.ajax({
		url: "app.php",
		data: {"command":"getQuestionData"},
		dataType: "json",
		type: "GET" //TODO: CHANGE THIS TO POST WHEN WE SWITCH FROM DEBUG
	}).done(function(data) {
		questionData = data;

		for(i in questionData){
			var htmlText = "<li id='"+i+"' class='clickableItem clickableQuestion btn'>"+i+"</li>";
			$("#questionList").prepend(htmlText);
		}
		$(".clickableQuestion").click(setClickHandler);
		$("#addQuestion").click(setBtnAddQuestionHandler);
	});
	
	
});

var setBtnAddQuestionHandler = function(){ //ADD QUESTION MENU ITEM CLICK
		questionCount++;
		var newQuestion = "<li id='question"+questionCount+"' class='clickableItem clickableQuestion btn'> Question Item "+questionCount+"</li>\n";
		$(this).before(newQuestion);
		
		
		var newContentQuestionHtml = "<input type='text' id='txtQuestion' class='enterQuestion' value='Enter your question here' />\n";
		
		var newContentHtml = "";
		newContentHtml += "<br />";
		newContentHtml += "<ol id='answerChoices'>\n";
		newContentHtml += "<li><input type='text' value='Enter answer choice' class='enterAnswer lastAnswerChoice' /></li>\n";
		newContentHtml += "</ol>\n";
		newContentHtml += "<input type='button' id='btnAddChoice' value='Add another answer choice' />\n";
		newContentHtml += "<input type='button' id='btnSaveNewQuestion' value='Save' />\n";
		
		$("#questionDiv").html(newContentQuestionHtml);
		$("#answerDiv").html(newContentHtml);
		
		$("#txtQuestion").focus();
		
		$(".lastAnswerChoice").keypress(function(event) {
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if (keycode == '13') {
				$('#btnAddChoice').click();
			}
		});
		
		
		$("#btnAddChoice").click(function(){ //ADD BUTTON
			$(".lastAnswerChoice").unbind("keypress");
			$(".lastAnswerChoice").removeClass("lastAnswerChoice");
			$("#answerChoices").append("<li><input type='text' value='Enter answer choice' class='enterAnswer lastAnswerChoice' /></li>\n");
			$(".lastAnswerChoice").keypress(function(event) {
				var keycode = (event.keyCode ? event.keyCode : event.which);
				if (keycode == '13') {
					$('#btnAddChoice').click();
				}
			}).focus();
		});
		$("#btnSaveNewQuestion").click(function(){ //SAVE BUTTON
			questionData["question"+questionCount] = {};
			questionData["question"+questionCount]["question"] = $("#txtQuestion").val();
			questionData["question"+questionCount]["answers"] = [];
			$(".enterAnswer").each(function(index,element){
				questionData["question"+questionCount]["answers"].push({"answer":this.value,"numResponses":0});
			});
			
			$(".clickableItem").removeClass("disabled");
			
			$(".clickableItem").unbind('click');
			$(".clickableQuestion").click(setClickHandler);
			$("#addQuestion").click(setBtnAddQuestionHandler);
			
			$("#questionDiv").html("");
			$("#answerDiv").html("");
			
			$.ajax({
				url: "app.php",
				data: {"command":"newQuestion","questionQuestion":questionData["question"+questionCount]["question"]}, //NEED TO PERSIST THIS DATA
				dataType: "text",
				type: "GET" //TODO: CHANGE THIS TO POST WHEN WE SWITCH FROM DEBUG
			}).done(function(data) {
			
				/*
				We are using "|" as the separator value between answer choices
				*/
				
				numAnswers = questionData["question"+questionCount]["answers"].length;
			
				var answerString = "";
				for(i in questionData["question"+questionCount]["answers"]){
					answerString+= questionData["question"+questionCount]["answers"][i].answer + "|";
				}
				
				answerString = escape(answerString.substring(0, answerString.length - 1));
			
				$.ajax({
				url: "app.php",
				data: {"command":"newAnswers","questionPK":data,"answerChoices":answerString}, //NEED TO PERSIST THIS DATA
				dataType: "text",
				type: "GET" //TODO: CHANGE THIS TO POST WHEN WE SWITCH FROM DEBUG
				}).done(function(data) {
					if(data == numAnswers){
						//console.debug("Correct number of answers added");
					}
				});
			});
			
		});
		
		$(".clickableItem").unbind('click');
		$(".clickableItem").click(function(){
			alert("You must finish editing the current question before you can change questions");
		});
		
		$(".clickableItem").addClass("disabled");
	}

var setClickHandler = function(){ //REGISTERS THE QUESTION MENU ITEMS WITH THEIR ACTUAL QUESTION AND RENDERS
	
		var questionText = "<p>"+questionData[this.id].question+"</p>\n";
		
		$("#questionDiv").html(questionText);
		
		var text = "";
		
		text+="<ol>\n";
		
		var totalNumAnswers = 0;
		
		for(i in questionData[this.id].answers){
			totalNumAnswers += parseInt(questionData[this.id].answers[i].numResponses);
		}
		
		for(i in questionData[this.id].answers){
			
			var width = (1/(totalNumAnswers/parseInt(questionData[this.id].answers[i].numResponses)))*100;
		
			text+="<li>"+questionData[this.id].answers[i].answer+"</li>\n";
			text+="<div style='background-color:red;display:block;width:"+width+"%;'>"+questionData[this.id].answers[i].numResponses+"</div>\n";
		}
		text+="</ol>\n";
		$("#answerDiv").html(text);
	};