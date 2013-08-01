<?php
	session_start();

	if(isset($_POST["txtUsername"]) && isset($_POST["txtPassword"])){
		if($_POST["txtUsername"] == "root" && $_POST["txtPassword"] == "root"){
			$_SESSION["loggedIn"] = "1";
		}
	}else if(isset($_POST["logout"])){
		$_SESSION["loggedIn"] = "0";
		session_destroy();
	}
?>

<html>
	<head>
		<title>Login Page</title>
		
	</head>
	<body>
	
	<?php
		if(!empty($_SESSION["loggedIn"])){
	?>
			<p>You are logged in!</p>
			<form name="frmLogout" method="POST" action="" id="frmLogout" class="authForm">
				<td colspan=2><input type="submit" value="Logout" class="inputButton" name="logout" value="1"></td>
			</form>
	
	<?php	
		}else{
	?>
	
			<form name="frmLogin" method="POST" action="" id="frmLogin" class="authForm">
			
				<table>
					<tr>
						<td>Username</td>
						<td><input type="text" class="inputText" id="txtUsername" name="txtUsername" size="10" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" class="inputText" id="txtPassword" name="txtPassword" size="10" /></td>
					</tr>
					<tr>
						<td colspan=2><input type="submit" value="Submit" class="inputButton"></td>
					</tr>
				</table>
			
			</form>
	
	<?php
		}
	?>
	
	</body>
</html>