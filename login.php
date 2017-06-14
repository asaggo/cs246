<?php
    $con = mysqli_connect("asaggo0717.000webhostapp.com", "id1915312_jessie", "cs246", "id1915312_byui_testing_center");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $username, $password, $firstname, $lastname, $isAdmin);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["username"] = $username;
        $response["password"] = $password;
        $response["firstname"] = $firstname;
        $response["lastname"] = $lastname;
        $response["isAdmin"] = $isAdmin;
    }
    
    echo json_encode($response);
?>