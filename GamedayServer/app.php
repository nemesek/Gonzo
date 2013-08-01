<?php

//TODO ADD AUTH LOGIC

//USING GET INSTEAD OF POST FOR TESTING PURPOSES
if(isset($_GET["command"])){

	//Lets go ahead and make a db connection
	$con=mysqli_connect("localhost","root","root","gamedayapp");

	// Check connection
	if(mysqli_connect_errno($con)){
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}

	//print "<pre>";
	//print_r($jsonResult);
	//print "</pre>";

	$command = $_GET["command"];
	
	if($command == "getQuestionData"){ //HANDLES GETTING THE QUESTION DATA
	
		//TODO
		//print '{"question1":{"question":"This is the question for question 1, right?","answers":[{"answer":"This is answer choice1","numResponses":"20"},{"answer":"This is answer choice1","numResponses":"30"},{"answer":"This is answer choice1","numResponses":"90"},{"answer":"This is answer choice4","numResponses":"10"}]},"question2":{"question":"This is the question for question 2, right?","answers":[{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"}]},"question3":{"question":"This is the question for question 3, right?","answers":[{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"}]},"question4":{"question":"This is the question for question 4, right?","answers":[{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"}]},"question5":{"question":"This is the question for question 5, right?","answers":[{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"},{"answer":"This is answer choice1","numResponses":"0"}]}}';
		$jsonResult = array();
		
		$result = mysqli_query($con,"SELECT * FROM questions WHERE is_del = '0'");

		while($row = mysqli_fetch_array($result)){
		
			$jsonResult["question".$row["q_pk"]] = array();
			$jsonResult["question".$row["q_pk"]]["question"] = $row['q_text'];
			$jsonResult["question".$row["q_pk"]]["questionID"] = $row['q_pk'];
			$jsonResult["question".$row["q_pk"]]["answers"] = array();
			
			$tempResult = mysqli_query($con,"SELECT * FROM answers WHERE q_pk = " . $row["q_pk"]);
			
			while($tempRow = mysqli_fetch_array($tempResult)){
				$answer = [ "answer" => $tempRow["a_text"], "numResponses" => $tempRow["answer_count"], "answerID" => $tempRow["a_pk"]];
				array_push($jsonResult["question".$row["q_pk"]]["answers"],$answer);
			}
		}
		
		print json_encode($jsonResult);
	
	}else if($command == "newQuestion"){ //HANDLES ADDING A NEW QUESTION
		
		if(isset($_GET["questionQuestion"])){
			
			//WE NEED TO INSERT A QUESTION AND GIVE BACK THE ID SO WE CAN SUBMIT THE ANSWERS AS WELL
			
			$result = mysqli_query($con,"INSERT INTO questions VALUES(NULL,'".$_GET["questionQuestion"]."',0)");
			$result = mysqli_query($con,"SELECT q_pk FROM questions WHERE q_text ='".$_GET["questionQuestion"]."'");
			$row = mysqli_fetch_array($result);
			
			print $row["q_pk"];
			
		}else{
			print "Error, invalid arguements.";
		}
		
	}else if($command == "newAnswers"){ //HANDLES ADDING A NEW QUESTION
		
		if(isset($_GET["questionPK"]) && isset($_GET["answerChoices"])){
			
			$answerChoices = urldecode($_GET["answerChoices"]);
			$answerList = explode("|",$answerChoices);
			
			foreach($answerList as $answerChoice){
				$result = mysqli_query($con,"INSERT INTO answers VALUES(NULL,".$_GET["questionPK"].",'".$answerChoice."',0)");
			}

			$result = mysqli_query($con,"SELECT a_pk FROM answers WHERE q_pk =".$_GET["questionPK"]);
			$counter = 0;
			while($row = mysqli_fetch_array($result)){
				$counter += 1;
			}
			
			print $counter;
			
		}else{
			print "Error, invalid arguements.";
		}
	
	}else if($command == "postQuestionAnswer"){ //HANDLES APP SENDING AN ANSWER
		
		if(isset($_GET["questionID"]) && isset($_GET["answerID"])){
		
			//TODO: ADD CODE TO CHECK TO MAKE SURE MORE THAN ONE ANSWER PER DEVICE CAN HAPPEN FOR EACH QUESTION
			$result = mysqli_query($con,"UPDATE answers SET answer_count=answer_count+1 WHERE a_pk=".$_GET["answerID"]." and q_pk=".$_GET["questionID"]);
			$result = mysqli_query($con,"SELECT answer_count FROM answers WHERE a_pk=".$_GET["answerID"]." and q_pk=".$_GET["questionID"]);
		
			$row = mysqli_fetch_array($result);
			
			print $row["answer_count"];
		
		}else{
			print "Error, invalid arguements.";
		}
		
	}else{
		print "Error, command not recognized.";
	}
	
	mysqli_close($con);
	
}else{
	print "Error, no command.";
}




?>